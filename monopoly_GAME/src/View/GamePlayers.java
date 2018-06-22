package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GamePlayers extends JFrame {

	private JPanel contentPane;
	private String[] numbers= {"2","3","4"};
	private JComboBox comboBox;
	private JTextField SecondPlayerTxt;
	private JTextField ThirdPlayerTxt;
	private JTextField FouthPlayerTxt;
	private int NumberOfPlayers;
	private JButton btnNewButton;
	private JLabel lblError;
	private JTextField FirstPlayerTxt;
	private JButton btnBack;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GamePlayers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 811);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox(numbers);
		comboBox.setBounds(546, 421, 219, 20);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String choosen = (String)comboBox.getSelectedItem();
				NumberOfPlayers = Integer.parseInt(choosen);
				switch(choosen) {
				case "2":
						TwoSelected();
						break;
				case "3":
						ThreeSelected();
						break;
				case "4":
						FourSelected();
						break;
				}
			}
		});
		contentPane.add(comboBox);

		
		FirstPlayerTxt = new JTextField();
		FirstPlayerTxt.setEnabled(false);
		FirstPlayerTxt.setBounds(546, 482, 141, 20);
		contentPane.add(FirstPlayerTxt);
		FirstPlayerTxt.setColumns(10);
		
		SecondPlayerTxt = new JTextField();
		SecondPlayerTxt.setBounds(546, 528, 141, 20);
		SecondPlayerTxt.setEnabled(false);
		contentPane.add(SecondPlayerTxt);
		SecondPlayerTxt.setColumns(10);
		
		ThirdPlayerTxt = new JTextField();
		ThirdPlayerTxt.setEnabled(false);
		ThirdPlayerTxt.setBounds(546, 571, 141, 20);
		contentPane.add(ThirdPlayerTxt);
		ThirdPlayerTxt.setColumns(10);
		
		FouthPlayerTxt = new JTextField();
		FouthPlayerTxt.setEnabled(false);
		FouthPlayerTxt.setBounds(544, 619, 143, 20);
		contentPane.add(FouthPlayerTxt);
		FouthPlayerTxt.setColumns(10);
		
		 lblError = new JLabel("");
		lblError.setBounds(379, 336, 509, 29);
		lblError.setFont(new Font("Arial", Font.BOLD, 18));
		lblError.setForeground(Color.RED);
		contentPane.add(lblError);
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(524, 675, 163, 39);
		btnNewButton.setIcon(new ImageIcon("img/StartButton.PNG"));
		contentPane.add(btnNewButton);
		
	    btnBack = new JButton("Back");
		btnBack.setBounds(998, 691, 69, 23);
		contentPane.add(btnBack);
		
		JLabel lblFourthPlayerName = new JLabel("Fourth Player name :");
		lblFourthPlayerName.setBounds(336, 614, 200, 26);
		lblFourthPlayerName.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblFourthPlayerName);
		
		JLabel lblSecondPlayerName = new JLabel("Second Player name :");
		lblSecondPlayerName.setBounds(336, 523, 200, 26);

		lblSecondPlayerName.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblSecondPlayerName);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of Players :");
		lblNumberOfPlayers.setBounds(336, 415, 193, 29);
		lblNumberOfPlayers.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblNumberOfPlayers);
		
		JLabel lblNewLabel_2 = new JLabel("Third Player name :");
		lblNewLabel_2.setBounds(336, 566, 193, 26);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblFirstPlayerName = new JLabel("First Player name :");
		lblFirstPlayerName.setBounds(336, 473, 193, 35);
		lblFirstPlayerName.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblFirstPlayerName);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img/Back2.png"));
		lblNewLabel_1.setBounds(288, 133, 600, 206);
		contentPane.add(lblNewLabel_1);

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/Back.jpg"));
		lblNewLabel.setBounds(10, 19, 1124, 742);
		contentPane.add(lblNewLabel);
		
	}
	
	public void SetErrorlbl(String error) {
		this.lblError.setText(error);
	}
	public void setBackListener(ActionListener l) {
		btnBack.addActionListener(l);
	}
	public void TwoSelected() {
		FirstPlayerTxt.setEnabled(true);
		SecondPlayerTxt.setEnabled(true);
		ThirdPlayerTxt.setEnabled(false);
		FouthPlayerTxt.setEnabled(false);
	}
	public void ThreeSelected() {
		FirstPlayerTxt.setEnabled(true);
		SecondPlayerTxt.setEnabled(true);
		ThirdPlayerTxt.setEnabled(true);
		FouthPlayerTxt.setEnabled(false);
	}
	public void FourSelected() {
		FirstPlayerTxt.setEnabled(true);
		SecondPlayerTxt.setEnabled(true);
		ThirdPlayerTxt.setEnabled(true);
		FouthPlayerTxt.setEnabled(true);
	}
	
	public void SetStartGameActionListener(ActionListener listener) {
		btnNewButton.addActionListener(listener);
	}
	public int getNumberOfPlayers() {
		return NumberOfPlayers;
	}
	public String getFirstPlayerName() {
		return FirstPlayerTxt.getText();
	}
	public String getSecondPlayerName() {
		return SecondPlayerTxt.getText();
	}
	public String getThirdPlayerName() {
		return ThirdPlayerTxt.getText();
	}
	public String getFourthPlayerName() {
		return FouthPlayerTxt.getText();
	}
}
