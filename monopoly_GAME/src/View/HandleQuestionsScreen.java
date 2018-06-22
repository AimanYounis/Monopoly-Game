package View;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class HandleQuestionsScreen extends JFrame{

	private JPanel mainPanel,panel;
	private JButton btnAddQuestion, btnRemoveQuestion, btnEditQuestion;
	
	public HandleQuestionsScreen() {
		initialize();
		initBtns();
	}
//
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainPanel = new JPanel();
		setBounds(100, 100, 710, 460);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(194, 147, 292, 218);
		mainPanel.add(panel);
		panel.setLayout(null);
	
	}
	
	private void initBtns() {
		btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.setBounds(12, 11, 268, 55);
		panel.add(btnAddQuestion);
		
		btnEditQuestion = new JButton("Edit Question");
		btnEditQuestion.setBounds(12, 77, 268, 55);
		panel.add(btnEditQuestion);
		
		btnRemoveQuestion = new JButton("Remove Question");
		btnRemoveQuestion.setBounds(12, 143, 268, 55);
		panel.add(btnRemoveQuestion);
	}
	
	public void setBtnAddQuestionListener(ActionListener listener) {
		btnAddQuestion.addActionListener(listener);
	}
	
	public void setBtnRemoveQuestionListener(ActionListener listener) {
		btnRemoveQuestion.addActionListener(listener);
	}
	
	public void setBtnEditQuestionListener(ActionListener listener) {
		btnEditQuestion.addActionListener(listener);
	}
}
