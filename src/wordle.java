import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class wordle extends JFrame { //home screen

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wordle window = new wordle();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public wordle() {
		initialize();
	}
	
	static int numGuesses = 6; //default number of guesses if it isn't specified in settings
	static int numLetters = 5; //default number of letters if it isn't specified in letters
	static boolean hardMode = false;
	static boolean timerOn = false;
	static int timer = 0;
	
	static int totalGames = 0;
	static double totalGuesses = 0;
	static double gamesWon = 0;
	static int totalHardMode = 0; //games won on hard mode

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setResizable(false);
		frame.setBounds(100, 100, 320, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindow2.Open();
			}
		});
		btnStart.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnStart.setBounds(50, 289, 213, 66);
		frame.getContentPane().add(btnStart);
		
		JButton btnGameStats = new JButton("Open Game Stats");
		btnGameStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameStats.Open();
			}
		});
		btnGameStats.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnGameStats.setBounds(50, 219, 213, 66);
		frame.getContentPane().add(btnGameStats);
		
		JButton btnOpenSettings = new JButton("Settings");
		btnOpenSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.Open();
			}
		});
		btnOpenSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnOpenSettings.setBounds(50, 150, 213, 66);
		frame.getContentPane().add(btnOpenSettings);
		
		JLabel lblTitle = new JLabel("Wordle");
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitle.setFont(new Font("Cochin", Font.BOLD, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(70, 20, 175, 50);
		frame.getContentPane().add(lblTitle);
		
		JButton btnRules = new JButton("How to Play");
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnRules, "How to play:\n"
						+ "1. Guess a word with the number of letters you chose to play with\n"
						+ "2. If the letter is green, it is the correct letter in the correct position of the secret word\n"
						+ "3. If the letter is yellow, it is in the word but in the incorrect position\n"
						+ "4. If the letter is neither green nor yellow, it is not in the secret word", 
						"Rules", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnRules.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnRules.setBounds(50, 82, 213, 66);
		frame.getContentPane().add(btnRules);
		
		
	}

}
