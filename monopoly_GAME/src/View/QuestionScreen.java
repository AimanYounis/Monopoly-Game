package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;

public class QuestionScreen extends JFrame {

	private JPanel contentPane, panel, panel_2, panel_1, panel_4, panel_5;
	private JCheckBox answer1, answer2, answer3, answer4;
	private JButton btnAnswer;
	private JLabel lblQuestion, lnlAnswer1, lnlAnswer2, lnlAnswer3, lnlAnswer4, lblPlayer, lblHasM;
	private ButtonGroup bGroup;

	/**
	 * Create the frame.
	 */
	public QuestionScreen() {
		super("Question");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 813, 256);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initPanel();
		initLaberl();
		initCheckBox();
		initBtns();
	}
	
	// makes the check boxes checkable one at a time (called in controller)
	public void initButtonGroup(boolean isMutiple){
		if(!isMutiple){
			bGroup = new ButtonGroup();
			bGroup.add(answer1);
			bGroup.add(answer2);
			bGroup.add(answer3);
			bGroup.add(answer4);
				
		}else
			lblHasM.setVisible(true);
	}
	
	public void setAnswerBtnListener(ActionListener listener){
		btnAnswer.addActionListener(listener);
	}
	
	
	public boolean getAnswer1() {
		return answer1.isSelected();
	}

	public boolean getAnswer2() {
		return answer2.isSelected();
	}

	public boolean getAnswer3() {
		return answer3.isSelected();
	}

	public boolean getAnswer4() {
		return answer4.isSelected();
	}
	
	public void setLblQuestion(String Question) {
		lblQuestion.setText(Question);;
	}

	public void setLnlAnswer1(String Answer1) {
		lnlAnswer1.setText(Answer1);
	}

	public void setLnlAnswer2(String Answer2) {
		lnlAnswer2.setText(Answer2);
	}

	public void setLnlAnswer3(String Answer3) {
		lnlAnswer3.setText(Answer3);
	}

	public void setLnlAnswer4(String Answer4) {
		lnlAnswer4.setText(Answer4);
	}

	private void initPanel(){
		panel = new JPanel();
		panel.setBounds(10, 55, 787, 33);
		contentPane.add(panel);
		
		panel_2 = new JPanel();
		panel_2.setBounds(10, 99, 73, 123);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(93, 99, 704, 123);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		panel_5 = new JPanel();
        panel_5.setBounds(370, 11, 185, 33);
        contentPane.add(panel_5);
			
		panel_4 = new JPanel();
		panel_4.setBounds(717, 11, 80, 33);
		contentPane.add(panel_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(565, 11, 142, 33);
		contentPane.add(panel_3);
		
	    lblHasM = new JLabel("Has multiple answers");
	    lblHasM.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	    lblHasM.setVisible(false);
		panel_3.add(lblHasM);
		
	}
	
	private void initLaberl(){
		
		lblQuestion = new JLabel("Question");
		panel.add(lblQuestion);
		
	    lnlAnswer1 = new JLabel("New label");
		lnlAnswer1.setBounds(10, 11, 650, 14);
		panel_1.add(lnlAnswer1);
		
		lnlAnswer2 = new JLabel("New label");
		lnlAnswer2.setBounds(10, 36, 650, 14);
		panel_1.add(lnlAnswer2);
		
		lnlAnswer3 = new JLabel("New label");
		lnlAnswer3.setBounds(10, 61, 650, 14);
		panel_1.add(lnlAnswer3);
		
		lblPlayer = new JLabel("");
        panel_5.add(lblPlayer);
		
	    lnlAnswer4 = new JLabel("New label");
		lnlAnswer4.setBounds(10, 86, 650, 14);
		panel_1.add(lnlAnswer4);
	}
	
	private void initCheckBox(){
		answer1 = new JCheckBox("1)");
		answer1.setBounds(6, 7, 61, 23);
		panel_2.add(answer1);
			
		answer2 = new JCheckBox("2)");
		answer2.setBounds(6, 33, 61, 23);
		panel_2.add(answer2);
			
		answer3 = new JCheckBox("3)");
		answer3.setBounds(6, 59, 61, 23);
		panel_2.add(answer3);
			
		answer4 = new JCheckBox("4)");
		answer4.setBounds(6, 85, 67, 23);
		panel_2.add(answer4);
			
	}
	
	private void initBtns(){
		btnAnswer = new JButton("Answer");
		panel_4.add(btnAnswer);
	}

	public void initLabelsText(String text, ArrayList<String> answersForQuestion) {
		lblQuestion.setText(text); 
		if(answersForQuestion.size() == 2) {
			lnlAnswer1.setText(answersForQuestion.get(0));
			lnlAnswer2.setText(answersForQuestion.get(1));
		}else {
			lnlAnswer1.setText(answersForQuestion.get(0));
			lnlAnswer2.setText(answersForQuestion.get(1));
			lnlAnswer3.setText(answersForQuestion.get(2));
			lnlAnswer4.setText(answersForQuestion.get(3));
		}

		
	}

	public Map<String, Boolean> getAllAnswers() {
		Map<String, Boolean> map = new HashMap<>();
		map.put(lnlAnswer1.getText(), answer1.isSelected());
		map.put(lnlAnswer2.getText(), answer2.isSelected());
		map.put(lnlAnswer3.getText(), answer3.isSelected());
		map.put(lnlAnswer4.getText(), answer4.isSelected());
		
		return map;
	}
	
	public void SetPlayer(String playername) {
        lblPlayer.setText(playername);
    }
}
