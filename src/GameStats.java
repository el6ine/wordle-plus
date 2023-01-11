import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;

@SuppressWarnings("serial")
public class GameStats extends wordle {

	private JPanel contentPane;
	DecimalFormat df = new DecimalFormat("#.###");

	/**
	 * Launch the application.
	 */
	public static void Open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameStats frame = new GameStats();
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
	public GameStats() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 275);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Game Statistics");
		lblTitle.setBounds(5, 5, 440, 42);
		lblTitle.setFont(new Font("Cochin", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		JLabel lblNewLabel_1 = new JLabel("# of Games Played:");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(30, 59, 198, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Average # of Guesses:");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(30, 106, 198, 40);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Win Rate:");
		lblNewLabel_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(30, 158, 198, 35);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblTotalGames = new JLabel("");
		lblTotalGames.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalGames.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTotalGames.setBounds(264, 59, 153, 20);
		contentPane.add(lblTotalGames);
		lblTotalGames.setText(totalGames +" games");
		
		JLabel lblAvgGuesses = new JLabel("");
		lblAvgGuesses.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAvgGuesses.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAvgGuesses.setBounds(264, 108, 153, 26);
		contentPane.add(lblAvgGuesses);
		double avgGuesses = 0.0;
		if (totalGames > 0) {
			avgGuesses = totalGuesses/totalGames;
		}
		lblAvgGuesses.setText(df.format(avgGuesses) +" guesses");
		
		JLabel lblWinRate = new JLabel("");
		lblWinRate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWinRate.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblWinRate.setBounds(264, 160, 153, 20);
		contentPane.add(lblWinRate);
		double winRate = 0.0;
		if (totalGames > 0) {
			winRate = (gamesWon/(totalGames+0.0))*100.0;
		}
		lblWinRate.setText(df.format(winRate)+"%");
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("# of Games Won on Hard Mode:");
		lblNewLabel_1_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(30, 205, 310, 35);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblHardGames = new JLabel("0 games");
		lblHardGames.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHardGames.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHardGames.setBounds(264, 202, 153, 26);
		contentPane.add(lblHardGames);
		lblHardGames.setText(totalHardMode+" games");
	}

}
