import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class Settings extends wordle {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void Open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
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
	 * @throws NumberFormatException 
	 */
	public Settings() throws NumberFormatException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 517);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Settings");
		lblNewLabel.setBounds(5, 5, 290, 49);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Cochin", Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("# of Letters");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 80, 143, 36);
		contentPane.add(lblNewLabel_1);
		
		String[] letterOptions = {"4", "5", "6", "7"};
		JComboBox<String> comboBoxNumLetters = new JComboBox<String>(letterOptions);
		comboBoxNumLetters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numLetters = Integer.parseInt((String)comboBoxNumLetters.getSelectedItem());
			}
		});
		comboBoxNumLetters.setSelectedIndex(1);
		comboBoxNumLetters.setBounds(200, 86, 95, 27);
		contentPane.add(comboBoxNumLetters);
		
		JLabel lblNewLabel_1_1 = new JLabel("# of Guesses");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(20, 128, 143, 36);
		contentPane.add(lblNewLabel_1_1);
		
		JSpinner spinnerNumGuesses = new JSpinner(new SpinnerNumberModel(6, 3, 8, 1));
		spinnerNumGuesses.addChangeListener(new javax.swing.event.ChangeListener(){
		      public void stateChanged(javax.swing.event.ChangeEvent ce){
		    	  numGuesses = (int) spinnerNumGuesses.getValue();
		      }
		});
		spinnerNumGuesses.setBounds(210, 134, 84, 26);
		contentPane.add(spinnerNumGuesses);
		
		JCheckBox chckbxHardMode = new JCheckBox("Hard Mode");
		chckbxHardMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxHardMode.isSelected()) {
					hardMode = true;
				} else hardMode = false;
			}
		});
		chckbxHardMode.setVerticalTextPosition(SwingConstants.TOP);
		chckbxHardMode.setBounds(20, 172, 275, 41);
		contentPane.add(chckbxHardMode);
		
		JCheckBox chckbxTimer = new JCheckBox("Timer");
		chckbxTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxTimer.isSelected()) {
					timerOn = true;
					timer = 25;
				} else timerOn = false;
			}
		});
		chckbxTimer.setBounds(20, 295, 274, 36);
		contentPane.add(chckbxTimer);
		
		JTextPane txtpnInHardMode = new JTextPane();
		txtpnInHardMode.setText("In hard mode, you must use all letters that have been discovered, "
				+ "and cannot use any letters that have been discovered to be incorrect. "
				+ "You can only use words from a restricted dictionary.");
		txtpnInHardMode.setBounds(30, 209, 265, 93);
		contentPane.add(txtpnInHardMode);
		
		JSlider timerSlider = new JSlider(JSlider.HORIZONTAL, 10, 60, 25);
		timerSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (timerOn) {
					timer = timerSlider.getValue();
				}
			}
		});
		timerSlider.setBounds(20, 368, 275, 62);
		contentPane.add(timerSlider);
		timerSlider.setMajorTickSpacing(10);
		timerSlider.setMinorTickSpacing(5);
		timerSlider.setPaintLabels(true);
		timerSlider.setPaintTicks(true);
		
		JLabel lblNewLabel_2 = new JLabel("How much time do you want?");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(30, 329, 252, 36);
		contentPane.add(lblNewLabel_2);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close settings window and open game window
				GameWindow2.Open();
			}
		});
		btnStartGame.setBounds(70, 442, 163, 41);
		contentPane.add(btnStartGame);
	}
	
	
}
