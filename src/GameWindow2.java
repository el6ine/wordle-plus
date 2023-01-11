import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GameWindow2 extends wordle {

	private JPanel contentPane;
	private JTextField textField;

	int guessNum = 0;
	String secretWord = "";
	double n = 0; // for progress bar
	JLabel[][] guessArray = new JLabel[numGuesses][numLetters];
	Vector<Character> correctLetters = new Vector<Character>();
	Vector<Character> incorrectLetters = new Vector<Character>();
	String currentGuess;
	boolean gameOver = false;

	Thread stopwatchThread;
	JLabel lblTimer = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void Open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow2 frame = new GameWindow2();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameWindow2() throws IOException {
		secretWord = calculateWord.getWord(numLetters);
		setBounds(100, 100, 490, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setSize(new Dimension(40, 40));
		panel.setAlignmentY(1.0f);
		panel.setAlignmentX(1.0f);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblHardMode = new JLabel("");
		if (hardMode) {
			lblHardMode.setText("Hard Mode is on");
		}
		panel.add(lblHardMode);

		JProgressBar progressBar = new JProgressBar(0, 100);
		panel.add(progressBar);
		progressBar.setValue((int) ((double) guessNum / numGuesses) + 1);

		// JLabel lblTimer = new JLabel("");
		lblTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(lblTimer);
		if (timerOn) {
			lblTimer.setText(timer + " s");
			stopwatch();
			stopwatchThread.start();
		} else {
			lblTimer.setText("Timer is off");
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblGuess = new JLabel("guess:");
		lblGuess.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		panel_1.add(lblGuess);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isValid = true;
				if (textField.getText().length() != numLetters) {
					JOptionPane.showMessageDialog(contentPane, "Not enough letters entered", "Error",
							JOptionPane.ERROR_MESSAGE);
					isValid = false;
				} else if (hardMode) {
					try {
						for (int i = 0; i < correctLetters.size(); i++) {
							if (!textField.getText().toUpperCase().contains(correctLetters.elementAt(i) + "")) {
								JOptionPane.showMessageDialog(contentPane,
										"You must use letter " + correctLetters.elementAt(i), "Error",
										JOptionPane.ERROR_MESSAGE);
								isValid = false;
								break;
							}
						}
						if (!calculateWord.isRealWord(textField.getText(), numLetters)) {
							JOptionPane.showMessageDialog(contentPane, "Word not found in dictionary", "Error",
									JOptionPane.ERROR_MESSAGE);
							isValid = false;
						} 
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} if (isValid) {
					guessWord();
					progressBar.setValue((int) (((0.0 + guessNum) / numGuesses) * 100));
				}
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// if word is longer than the allowed number of letters, consume letters
				String validCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
				if (textField.getText().length() >= numLetters || !validCharacters.contains("" + e.getKeyChar())) {
					e.consume();
				}
			}
		});
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnGiveUp = new JButton("Give Up");
		btnGiveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameOver(false);
				btnGiveUp.setEnabled(false);
			}
		});
		panel_1.add(btnGiveUp);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setSize(new Dimension(50, 50));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(numGuesses, numLetters, 0, 0));

		// JLabel [][] guessArray = new JLabel [numLetters][numGuesses];
		for (int i = 0; i < numGuesses; i++) {
			for (int j = 0; j < numLetters; j++) {
				guessArray[i][j] = new JLabel();
				guessArray[i][j].setText("    ");
				guessArray[i][j].setFont(new Font("Lucida Grande", Font.PLAIN, 26));
				guessArray[i][j].setOpaque(true);
				guessArray[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				guessArray[i][j].setBackground(new Color(255, 255, 255));
				guessArray[i][j].setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				panel_2.add(guessArray[i][j]);
			}
		}
	}

	private void guessWord() {
		currentGuess = textField.getText().toUpperCase();
		int[] results = calculateWord.checkWord(secretWord, currentGuess, numLetters);
		Stack <Character> tempIncorrect = new Stack<Character>();
		for (int i = 0; i < numLetters; i++) {
			char currentLetter = currentGuess.charAt(i);
			if (hardMode && incorrectLetters.contains(currentLetter)) {
				for (int j = 0; j<=i; j++) {
					guessArray[guessNum][j].setText("");
					guessArray[guessNum][j].setBackground(Color.white);
				}
				JOptionPane.showMessageDialog(contentPane, "Cannot use letter " + currentGuess.charAt(i), "Error",
						JOptionPane.ERROR_MESSAGE);
				
				guessNum--;
				break;
			}
			guessArray[guessNum][i].setText(currentLetter + "");
			if (results[i] == 0) {
				guessArray[guessNum][i].setBackground(Color.gray);
				tempIncorrect.add(currentLetter);
			} else if (results[i] == 1) {
				guessArray[guessNum][i].setBackground(Color.yellow);
				correctLetters.add(currentLetter);
			} else if (results[i] == 2) {
				guessArray[guessNum][i].setBackground(Color.green);
				correctLetters.add(currentLetter);
			}
		}
		guessNum++;
		textField.setText("");
		while (!tempIncorrect.isEmpty()) {
			incorrectLetters.add(tempIncorrect.pop());
		}
		if (currentGuess.equals(secretWord)) {
			gameOver(true);
		} else if (guessNum == numGuesses) {
			gameOver(false);
		}
	}
	
	private void gameOver (boolean win) {
		gameOver = true;
		totalGuesses += guessNum;
		totalGames++;
		calculateWord.emptyArray();
		correctLetters.clear();
		incorrectLetters.clear();
		String temp = "";
		if (win) {
			gamesWon++;
			temp = "You won";
			if (hardMode) {
				totalHardMode++;
			}
		} else {
			temp = "You lost, the word was: " + secretWord;
		}
		int msgBox = JOptionPane.showOptionDialog(contentPane, temp + "\nDo you want to play again?",
				"Game Finished", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Open Game Stats", "Yes", "No" }, JOptionPane.NO_OPTION);
		if (msgBox == JOptionPane.NO_OPTION) {
			GameWindow2.Open();
			this.dispose();
		} else if (msgBox == JOptionPane.YES_OPTION) {
			GameStats.Open();
			this.dispose();
		}
		textField.setEnabled(false);
	}

	private void stopwatch() {
		stopwatchThread = new Thread() {
			public void run() {
				int temp = timer;
				try {
					// run the stopwatch counter in an infinite loop.
					while (true) {
						// wait for one second
						Thread.sleep(1000);
						if (!timerOn) {
							lblTimer.setText("Timer is off");
						} else if (!gameOver) {
							temp--;
							lblTimer.setText(temp + " s");
						} 
						if (temp == 0) {
							gameOver(false);
							// timer = 0;
							break;
						}
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}

}
