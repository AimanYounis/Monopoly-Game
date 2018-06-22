package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Objects.Answer;
import Model.Objects.Question;
import Utills.Constants;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class RemoveQuestion extends JFrame{

	private JPanel mainPanel, panel, panel_1, panel_2, panel_4, panel_3, panel_6;
	private JTextField question, answer1, answer2, answer3, answer4;
	private JCheckBox chckbxTrue4, chckbxTrue1, chckbxTrue3, chckbxTrue2, chckbxIsMultiple;
	private JButton btnRemove, btnCancel;
	private JPanel panel_5;
	private JTextField tags;
	private JTextField team;
	private JLabel lblDifficulity;
	private JComboBox<Integer> comboBox, comboBox_1;
	private JComboBox<Long> qIDCombobox;
	
	/**
	 * Create the application.
	 */
	public RemoveQuestion() {
		super("Remove Question");
		initialize();
		initLabels();
		initTextFields();
		initCheckboxs();
		initBtns();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 654, 482);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 73, 624, 369);
		mainPanel.add(panel);
		panel.setLayout(null);
		
	    panel_1 = new JPanel();
		panel_1.setBounds(12, 13, 430, 52);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(12, 78, 600, 142);
		panel.add(panel_2);
		panel_2.setLayout(null);

	    panel_4 = new JPanel();
		panel_4.setBounds(454, 13, 158, 52);
		panel.add(panel_4);
		panel_4.setLayout(null);

	    panel_3 = new JPanel();
		panel_3.setBounds(12, 316, 600, 46);
		panel.add(panel_3);
		
		panel_5 = new JPanel();
		panel_5.setBounds(12, 227, 600, 78);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		panel_6 = new JPanel();
		panel_6.setBounds(10, 11, 624, 49);
		mainPanel.add(panel_6);
		
		
	}
	
	private void initLabels(){
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(12, 13, 30, 16);
		panel_1.add(lblText);
		
		JLabel lblAnswer = new JLabel("Answer !:");
		lblAnswer.setBounds(12, 13, 63, 16);
		panel_2.add(lblAnswer);
		
		JLabel lblAnswer_1 = new JLabel("Answer 2:");
		lblAnswer_1.setBounds(12, 42, 63, 16);
		panel_2.add(lblAnswer_1);
		
		JLabel lblAnswer_2 = new JLabel("Answer 3:");
		lblAnswer_2.setBounds(12, 74, 63, 16);
		panel_2.add(lblAnswer_2);
		
		JLabel lblAnswer_3 = new JLabel("Answer 4:");
		lblAnswer_3.setBounds(12, 103, 78, 16);
		panel_2.add(lblAnswer_3);
		
		JLabel lblTags = new JLabel("Tags");
		lblTags.setBounds(12, 13, 56, 16);
		panel_5.add(lblTags);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setBounds(12, 42, 56, 16);
		panel_5.add(lblTeam);
		
		JLabel lblAnswerNum = new JLabel("Answers num:");
		lblAnswerNum.setBounds(503, 11, 84, 20);
		panel_2.add(lblAnswerNum);
		
		JLabel lblPickQuestionId = new JLabel("Pick Question ID: ");
		panel_6.add(lblPickQuestionId);
		
		lblDifficulity = new JLabel("Difficulity");
		lblDifficulity.setBounds(215, 42, 72, 19);
		panel_5.add(lblDifficulity);
	}
	
	private void initCheckboxs() {
		
		chckbxTrue1 = new JCheckBox("True");
		chckbxTrue1.setEnabled(false);
		chckbxTrue1.setBounds(426, 9, 71, 25);
		panel_2.add(chckbxTrue1);
		chckbxTrue3 = new JCheckBox("True");
		chckbxTrue3.setEnabled(false);
		chckbxTrue3.setBounds(426, 70, 71, 25);
		panel_2.add(chckbxTrue3);
		
		chckbxTrue2 = new JCheckBox("True");
		chckbxTrue2.setEnabled(false);
		chckbxTrue2.setBounds(424, 38, 71, 25);
		panel_2.add(chckbxTrue2);
		
		chckbxTrue4 = new JCheckBox("True");
		chckbxTrue4.setEnabled(false);
		chckbxTrue4.setBounds(426, 103, 71, 25);
		panel_2.add(chckbxTrue4);
		
		chckbxIsMultiple = new JCheckBox("Is Multiple");
		chckbxIsMultiple.setEnabled(false);
		chckbxIsMultiple.setBounds(8, 5, 142, 38);
		panel_4.add(chckbxIsMultiple);
	}
	
	private void initTextFields() {
		question = new JTextField();
		question.setEditable(false);
		question.setBounds(54, 10, 364, 22);
		panel_1.add(question);
		question.setColumns(10);
		
		answer1 = new JTextField();
		answer1.setEditable(false);
		answer1.setBounds(87, 10, 331, 22);
		panel_2.add(answer1);
		answer1.setColumns(10);
		
		answer2 = new JTextField();
		answer2.setEditable(false);
		answer2.setBounds(87, 39, 331, 22);
		panel_2.add(answer2);
		answer2.setColumns(10);
		
		answer3 = new JTextField();
		answer3.setEditable(false);
		answer3.setBounds(87, 71, 331, 22);
		panel_2.add(answer3);
		answer3.setColumns(10);
		
		answer4 = new JTextField();
		answer4.setEditable(false);
		answer4.setBounds(87, 100, 331, 22);
		panel_2.add(answer4);
		answer4.setColumns(10);
		
		tags = new JTextField();
		tags.setEditable(false);
		tags.setBounds(60, 10, 528, 22);
		panel_5.add(tags);
		tags.setColumns(10);
		
		team = new JTextField();
		team.setEditable(false);
		team.setBounds(60, 40, 116, 22);
		panel_5.add(team);
		team.setColumns(10);
		
		comboBox = new JComboBox<>();
		comboBox.setEnabled(false);
		for(int i = 1; i <= 3; i++) {
			comboBox.addItem(i);
		}
		
		comboBox_1 = new JComboBox<>();
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(503, 40, 63, 20);
		panel_2.add(comboBox_1);
		
		qIDCombobox = new JComboBox<>();
		panel_6.add(qIDCombobox);
		
		comboBox_1.addItem(Constants.MINANSWERS);
		comboBox_1.addItem(Constants.MAXANSWERS);
		
		comboBox.setBounds(286, 42, 41, 22);
		panel_5.add(comboBox);
	}
	

	private void initBtns() {
		
		btnRemove = new JButton("Remove");
		panel_3.add(btnRemove);
		
		btnCancel = new JButton("Cancel");
		panel_3.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
			}
		});
	}
	
	public boolean isMultiple() {
		return chckbxIsMultiple.isSelected();
	}
	
	public void setRemoveActionListener(ActionListener listener) {
		btnRemove.addActionListener(listener);
	}
	
	public void setPickQuestionIDListener(ActionListener listener){
		qIDCombobox.addActionListener(listener);
	}
	
	public long intgetqIDCombobox() {
		return qIDCombobox.getItemAt(qIDCombobox.getSelectedIndex());
	}

	public void setqIDCombobox(long[] list) {
		for(Long i : list)
			qIDCombobox.addItem(i);
	}

	public int getComboBox() {
		return comboBox.getItemAt(comboBox.getSelectedIndex());
	}

	public void setComboBox(long item) {
		int i = (int)item;

		comboBox.setSelectedIndex(i);
	}

	public String getQuestion() {
		return question.getText();
	}

	public void setQuestion(String question) {
		this.question.setText(question);
	}

	public String getAnswer1() {
		return answer1.getText();
	}

	public void setAnswer1(String answer) {
		answer1.setText(answer);
	}

	public String getAnswer2() {
		return answer2.getText();
	}

	public void setAnswer2(String answer) {
		answer2.setText(answer);
	}

	public String getAnswer3() {
		return answer3.getText();
	}

	public void setAnswer3(String answer) {
		answer3.setText(answer);
	}

	public String getTags() {
		return tags.getText();
	}

	public void setTags(ArrayList<String> answer) {
		String str = "";
		for(String a : answer)
			str += a + " ";
		tags.setText(str);
	}

	public String getTeam() {
		return team.getText();
	}

	public void setTeam(String answer) {
		team.setText(answer);
	}

	public String getAnswer4() {
		return answer4.getText();
	}

	public void setAnswer4(String answer) {
		answer4.setText(answer);
	}

	public boolean getChckbxTrue4() {
		return chckbxTrue4.isSelected();
	}

	public void setChckbxTrue4(boolean chckbxTrue2) {
		this.chckbxTrue4.setSelected(chckbxTrue2);
	}

	public boolean getChckbxTrue1() {
		return chckbxTrue1.isSelected();
	}

	public void setChckbxTrue1(boolean chckbxTrue2) {
		this.chckbxTrue1.setSelected(chckbxTrue2);
	}

	public boolean getChckbxTrue3() {
		return chckbxTrue3.isSelected();
	}

	public void setChckbxTrue3(boolean chckbxTrue2) {
		this.chckbxTrue3.setSelected(chckbxTrue2);
	}

	public boolean getChckbxTrue2() {
		return chckbxTrue2.isSelected();
	}

	public void setChckbxTrue2(boolean chckbxTrue2) {
		this.chckbxTrue2.setSelected(chckbxTrue2);
	}

	public void setQuestionObj(Question q) {
		
		ArrayList<Answer> a = q.getAnswers();
		
		setMultiple(q.isMultiple());
		setChckbxTrue1(a.get(0).isTrue());
		setChckbxTrue2(a.get(1).isTrue());
		setAnswer1(q.getAnswers().get(0).getAnswer());
		setAnswer2(q.getAnswers().get(1).getAnswer());	
		
		if(a.size() > 2){
			setChckbxTrue3(a.get(2).isTrue());
			setChckbxTrue4(a.get(3).isTrue());
			setAnswer3(q.getAnswers().get(2).getAnswer());
			setAnswer4(q.getAnswers().get(3).getAnswer());
			
		}
		
		setQuestion(q.getText());
		setTeam(q.getTeam());
		setComboBox(q.getDifficulty());
		setTags(q.getTags());
		
	}

	private void setMultiple(boolean multiple) {
		chckbxIsMultiple.setSelected(multiple);
		
	}

}
