package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SelectField extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btn;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SelectField(ArrayList<String> tags) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] array = tags.toArray(new String[tags.size()]);
		comboBox = new JComboBox(array);
		comboBox.setBounds(110, 46, 286, 20);
		comboBox.setSelectedIndex(0);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Please choose a catagory");
		lblNewLabel.setBounds(154, 21, 180, 14);
		contentPane.add(lblNewLabel);
		
		btn = new JButton("ok");
		btn.setBounds(198, 153, 89, 23);
		contentPane.add(btn);
		
	}
	

	public int SelectedItemIndex() {
		return comboBox.getSelectedIndex();
	}
	
	public void setOkButtonListener(ActionListener listener) {
		btn.addActionListener(listener);
	}
}
