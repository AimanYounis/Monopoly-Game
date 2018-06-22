package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GsmeLogScreen extends JFrame{
	
	private JPanel mainPanel;
	private JTextArea textArea;

	public GsmeLogScreen(){
		super("Game Log");
		init();
	}

	public void setText(String handleHistoryScreen) {
		textArea.setText(handleHistoryScreen);
		
	}
	
	private void init(){
		setBounds(100, 100, 669, 432);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}
}