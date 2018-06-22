package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnManageQuestion;
	private JButton btnHistory;
	private JButton btnLogout;


	/**
	 * Create the frame.
	 */
	
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 811);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("img/Back2.png"));
		lblNewLabel_1.setBounds(288, 133, 600, 206);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(466, 412, 162, 54);
		btnNewButton.setIcon(new ImageIcon("img/ButtonNewGame.PNG"));
		contentPane.add(btnNewButton);
		
		//Image image = new Image(getClass().getResource("img/Back.jpg"));
	//	contentPane.add(new JLabel(), BorderLayout.CENTER);
		setContentPane(contentPane);
		
		btnManageQuestion = new JButton("");
		btnManageQuestion.setBounds(489, 505, 126, 41);
		btnManageQuestion.setIcon(new ImageIcon("img/ManageQuestions.PNG"));
		contentPane.add(btnManageQuestion);
		
		btnHistory = new JButton("");
		btnHistory.setBounds(489, 582, 124, 43);
		btnHistory.setIcon(new ImageIcon("img/GamesHistory.PNG"));
		contentPane.add(btnHistory);
		
		btnLogout = new JButton("");
		btnLogout.setBounds(491, 657, 124, 33);
		btnLogout.setIcon(new ImageIcon("img/LogOut.PNG"));
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("n");
		lblNewLabel.setIcon(new ImageIcon("img/Back.jpg"));
		lblNewLabel.setBounds(0, 0, 1150, 811);
		contentPane.add(lblNewLabel);
	}
	
	public void SetNewGameActionListener(ActionListener listener){
		btnNewButton.addActionListener(listener);
	}

	public void setManageQuestionListener(ActionListener listener) {
		btnManageQuestion.addActionListener(listener);
	}
	public void setHistoryListener(ActionListener listener) {
		btnHistory.addActionListener(listener);
	}
	public void setLogoutListener(ActionListener listener) {
		btnLogout.addActionListener(listener);
	}
	
	
}
