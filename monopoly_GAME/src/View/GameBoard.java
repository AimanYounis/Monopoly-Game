package View;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Objects.Asset;
import Model.Objects.Player;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.JTextField;

public class GameBoard extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton button;
	private JTextArea textArea ;
	private ArrayList<JLabel> labels;
	private JLabel firstCurrentLabel;
	private JLabel secondCurrentLabel;
	private JLabel thirdCurrentLabel;
	private JLabel fourthCurrentLabel;
	private JLabel BoardFirst;
	private JLabel BackSecond;
	private JLabel label_0;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_10;
	private JLabel label_9;
	private JLabel label_6;
	private JLabel label_3;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_16;
	private JLabel label_17;
	private JLabel label_18;
	private JLabel label_19;
	private JLabel label_20;
	private JLabel label_15;
	private JLabel label_21;
	private JLabel label_22;
	private JLabel label_23;
	private JLabel label_24;
	private JLabel label_25;
	private JLabel label_26;
	private JLabel label_27;
	private JLabel label_28;
	private JLabel label_29;
	private JLabel label_30;
	private JLabel label_31;
	private JLabel label_32;
	private JLabel label_33;
	private JLabel label_34;
	private JLabel label_35;
	private JLabel label_36;
	private JLabel label_37;
	private JLabel label_38;
	private JLabel label_39;
	private JLabel label_40;
	private JLabel label_41;
	private JLabel label_42;
	private JLabel label_43;
	private JLabel label_44;
	private JLabel label_45;
	private JLabel label_46;
	private JLabel label_47;
	private JLabel label_48;
	private JLabel label_49;
	private JLabel label_50;
	private JLabel label_51;
	private JLabel label_52;
	private JLabel label_53;
	private JLabel label_54;
	private JLabel label_55;
	private JLabel label_56;
	private JLabel label_57;
	private JLabel label_58;
	private JLabel label_59;
	private JLabel label_60;
	private JLabel label_61;
	private JLabel label_62;
	private JLabel label_63;
	private JLabel label_64;
	private JLabel label_65;
	private JLabel label_66;
	private JLabel label_67;
	private JLabel label_68;
	private JLabel label_69;
	private JLabel label_70;
	private JLabel label_71;
	private JLabel label_72;
	private JLabel label_73;
	private JLabel label_74;
	private JLabel label_75;
	private JLabel label_76;
	private JLabel label_77;
	private JLabel label_78;
	private JLabel label_79;
	private JLabel label_80;
	private JLabel label_81;
	private JLabel label_82;
	private JLabel label_83;
	private JLabel label_84;
	private JLabel label_85;
	private JLabel label_86;
	private JLabel label_87;
	private JLabel label_88;
	private JLabel label_89;
	private JLabel label_90;
	private JLabel label_91;
	private JLabel label_92;
	private JLabel label_93;
	private JLabel label_94;
	private JLabel label_95;
	private JLabel label_96;
	private JLabel label_97;
	private JLabel label_98;
	private JLabel label_99;
	private JLabel label_100;
	private JLabel label_101;
	private JLabel label_102;
	private JLabel label_103;
	private JLabel label_104;
	private JLabel label_105;
	private JLabel label_106;
	private JLabel label_107;
	private JLabel label_108;
	private JLabel label_109;
	private JLabel label_110;
	private JLabel label_111;
	private JLabel label_112;
	private JLabel label_113;
	private JLabel label_114;
	private JLabel label_115;
	private JLabel label_116;
	private JLabel label_117;
	private JLabel label_118;
	private JLabel label_119;
	private JLabel label_120;
	private JLabel label_121;
	private JLabel label_122;
	private JLabel label_123;
	private JLabel label_124;
	private JLabel label_125;
	private JLabel label_126;
	private JLabel label_127;
	private JLabel label_128;
	private JLabel label_129;
	private JLabel label_130;
	private JLabel label_131;
	private JLabel label_132;
	private JLabel label_133;
	private JLabel label_134;
	private JLabel label_135;
	private JLabel label_136;
	private JLabel label_137;
	private JLabel label_138;
	private JLabel label_139;
	private JLabel label_140;
	private JLabel label_141;
	private JLabel label_142;
	private JLabel label_143;
	private JLabel label_144;
	private JLabel label_145;
	private JLabel label_146;
	private JLabel label_147;
	private JLabel label_148;
	private JLabel label_149;
	private JLabel label_150;
	private JLabel label_151;
	private JLabel label_152;
	private JLabel label_153;
	private JLabel label_154;
	private JLabel label_155;
	private JLabel label_156;
	private JLabel label_157;
	private JLabel label_158;
	private JLabel label_159;
	private JLabel FirstPlayerLabel;
	private JLabel SecondPlayerLabel;
	private JLabel ThirdPlayerLabel;
	private JLabel FourthPlayerLabel;
	private JLabel FirstIcon;
	private JLabel SecondIcon;
	private JLabel ThirdIcon;
	private JLabel FourthIcon;
	private JLabel lblBalanceFirst;
	private JLabel FirstPlayerBalance;
	private JLabel lblStrikesFirst;
	private JLabel FirstPlayerStrikes;
	private JLabel lblBalanceSecond;
	private JLabel lblStrikesSecond;
	private JLabel SecondPlayerBalance;
	private JLabel SecondPlayerStrikes;
	private JLabel lblBalanceThird;
	private JLabel ThirdPlayerBalance;
	private JLabel lblStrikesThird;
	private JLabel ThirdPlayerStrikes;
	private JLabel lblBalanceFourth;
	private JLabel FourthPlayerBalance;
	private JLabel lblStrikesFourth;
	private JLabel FourthPlayerStrikes;
	private JLabel FirstJail;
	private JLabel SecondJail;
	private JLabel ThirdJail;
	private JLabel FourthJail;
	private JTable AssetTable;
	private DefaultTableModel dTableModel;
	private JTextField textField;
	//= new DefaultTableModel(databaseInfo, columns);

	/**
	 * Create the frame.
	 */
	public GameBoard() {
		labels = new ArrayList<>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1150, 811);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(834, 565, 290, 138);
		textArea.setBackground(new Color(255, 255, 255));
		textArea.disable();
		textArea.setFont(new Font("Arial", Font.BOLD, 16));
		textArea.setForeground(Color.BLACK);
		contentPane.add(textArea);
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(941, 719, 83, 24);
		btnNewButton.setIcon(new ImageIcon("img/EndGameButton.PNG"));
		contentPane.add(btnNewButton);
		
		
		button = new JButton("");
		button.setBounds(1003, 531, 61, 24);
		button.setIcon(new ImageIcon("img/RollButton.PNG"));
		contentPane.add(button);
		
			InitAllLabels();
			InitPlayersToLabels();
		
		
		
		
		 lblBalanceFirst = new JLabel("Balance : ");
		lblBalanceFirst.setBounds(834, 51, 61, 24);
		lblBalanceFirst.setVisible(false);
		contentPane.add(lblBalanceFirst);
		
		FirstPlayerBalance = new JLabel("");
		FirstPlayerBalance.setBounds(890, 51, 83, 24);
		FirstPlayerBalance.setVisible(false);
		contentPane.add(FirstPlayerBalance);
		
		lblStrikesFirst = new JLabel("Strikes :");
		lblStrikesFirst.setBounds(988, 51, 61, 24);
		lblStrikesFirst.setVisible(false);
		contentPane.add(lblStrikesFirst);
		
		FirstPlayerStrikes = new JLabel("0");
		FirstPlayerStrikes.setBounds(1059, 51, 46, 24);
		FirstPlayerStrikes.setVisible(false);
		contentPane.add(FirstPlayerStrikes);
		
		lblBalanceSecond = new JLabel("Balance : ");
		lblBalanceSecond.setBounds(834, 112, 61, 24);
		lblBalanceSecond.setVisible(false);
		contentPane.add(lblBalanceSecond);
		
		lblStrikesSecond = new JLabel("Strikes :");
		lblStrikesSecond.setBounds(988, 112, 61, 24);
		lblStrikesSecond.setVisible(false);
		contentPane.add(lblStrikesSecond);
		
		SecondPlayerBalance = new JLabel("");
		SecondPlayerBalance.setBounds(890, 112, 83, 24);
		SecondPlayerBalance.setVisible(false);
		contentPane.add(SecondPlayerBalance);
		
		SecondPlayerStrikes = new JLabel("0");
		SecondPlayerStrikes.setBounds(1059, 112, 46, 24);
		SecondPlayerStrikes.setVisible(false);
		contentPane.add(SecondPlayerStrikes);
		
		lblBalanceThird = new JLabel("Balance : ");
		lblBalanceThird.setBounds(834, 181, 61, 24);
		lblBalanceThird.setVisible(false);
		contentPane.add(lblBalanceThird);
		
		ThirdPlayerBalance = new JLabel("");
		ThirdPlayerBalance.setBounds(890, 181, 83, 24);
		ThirdPlayerBalance.setVisible(false);
		contentPane.add(ThirdPlayerBalance);
		
		lblStrikesThird = new JLabel("Strikes :");
		lblStrikesThird.setBounds(988, 181, 61, 24);
		lblStrikesThird.setVisible(false);
		contentPane.add(lblStrikesThird);
		
		ThirdPlayerStrikes = new JLabel("0");
		ThirdPlayerStrikes.setBounds(1059, 181, 46, 24);
		ThirdPlayerStrikes.setVisible(false);
		contentPane.add(ThirdPlayerStrikes);
		
		lblBalanceFourth = new JLabel("Balance : ");
		lblBalanceFourth.setBounds(834, 255, 61, 24);
		lblBalanceFourth.setVisible(false);
		contentPane.add(lblBalanceFourth);
		
		FourthPlayerBalance = new JLabel("");
		FourthPlayerBalance.setBounds(890, 255, 88, 24);
		FourthPlayerBalance.setVisible(false);
		contentPane.add(FourthPlayerBalance);
		
		lblStrikesFourth = new JLabel("Strikes :");
		lblStrikesFourth.setBounds(988, 255, 61, 24);
		lblStrikesFourth.setVisible(false);
		contentPane.add(lblStrikesFourth);
		
		FourthPlayerStrikes = new JLabel("0");
		FourthPlayerStrikes.setBounds(1059, 255, 46, 24);
		FourthPlayerStrikes.setVisible(false);
		contentPane.add(FourthPlayerStrikes);
		
		FirstJail = new JLabel("New label");
		FirstJail.setBounds(1059, 11, 35, 35);
		FirstJail.setIcon(new ImageIcon("img/jail.png"));
		FirstJail.setVisible(false);
		contentPane.add(FirstJail);
		
		SecondJail = new JLabel("New label");
		SecondJail.setBounds(1059, 75, 35, 35);
		SecondJail.setIcon(new ImageIcon("img/jail.png"));
		SecondJail.setVisible(false);
		contentPane.add(SecondJail);
		
		ThirdJail = new JLabel("New label");
		ThirdJail.setBounds(1059, 140, 35, 35);
		ThirdJail.setIcon(new ImageIcon("img/jail.png"));
		ThirdJail.setVisible(false);
		contentPane.add(ThirdJail);
		
		FourthJail = new JLabel("New label");
		FourthJail.setBounds(1059, 220, 35, 35);
		FourthJail.setIcon(new ImageIcon("img/jail.png"));
		FourthJail.setVisible(false);
		contentPane.add(FourthJail);
		
		initTable();
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		textField.setEditable(false);
		textField.setBounds(834, 531, 139, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		BoardFirst = new JLabel("");
		BoardFirst.setIcon(new ImageIcon("img/Board.jpg"));
		BoardFirst.setBounds(0, 0, 811, 772);
		contentPane.add(BoardFirst);
		
		BackSecond = new JLabel("");
		BackSecond.setIcon(new ImageIcon("img/Back.jpg"));
		BackSecond.setBounds(0, 4, 1150, 772);
		contentPane.add(BackSecond);
		
		
	}
	
		private void initTable() {
		
		Object[][] databaseInfo;
		String[] columns = {"Asset","Price Paied","Owner"};
		databaseInfo = new Object[0][columns.length];
		
		dTableModel = new DefaultTableModel(databaseInfo, columns);
        
		AssetTable = new JTable(dTableModel); 
		JScrollPane scroll = new JScrollPane(AssetTable);
		scroll.setBounds(834, 296, 290, 222); 		// bounds must be set for the scrollPane 
		contentPane.add(scroll);
		

	}
	
	public void InitPlayersToLabels() {
		FirstPlayerLabel = new JLabel("");
		FirstPlayerLabel.setBounds(880, 18, 144, 24);
		FirstPlayerLabel.setVisible(false);
		FirstPlayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(FirstPlayerLabel);
		
		SecondPlayerLabel = new JLabel("");
		SecondPlayerLabel.setBounds(880, 80, 144, 24);
		SecondPlayerLabel.setVisible(false);
		SecondPlayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(SecondPlayerLabel);
		
		ThirdPlayerLabel = new JLabel("");
		ThirdPlayerLabel.setBounds(880, 148, 144, 27);
		ThirdPlayerLabel.setVisible(false);
		ThirdPlayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(ThirdPlayerLabel);
		
		FourthPlayerLabel = new JLabel("");
		FourthPlayerLabel.setBounds(880, 222, 144, 28);
		FourthPlayerLabel.setVisible(false);
		FourthPlayerLabel.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(FourthPlayerLabel);
		
		FirstIcon = new JLabel("firsticon");
		FirstIcon.setBounds(834, 11, 35, 35);
		FirstIcon.setIcon(new ImageIcon("img/rsz_motor.png"));
		FirstIcon.setVisible(false);
		contentPane.add(FirstIcon);
		
		SecondIcon = new JLabel("secondicon");
		SecondIcon.setBounds(834, 75, 35, 35);
		SecondIcon.setIcon(new ImageIcon("img/crown.png"));
		SecondIcon.setVisible(false);
		contentPane.add(SecondIcon);
		
		ThirdIcon = new JLabel("thirdicon");
		ThirdIcon.setBounds(834, 140, 35, 35);
		ThirdIcon.setIcon(new ImageIcon("img/snow.png"));
		ThirdIcon.setVisible(false);
		contentPane.add(ThirdIcon);
		
		FourthIcon = new JLabel("fourthicon");
		FourthIcon.setBounds(834, 220, 35, 35);
		FourthIcon.setIcon(new ImageIcon("img/hammer.png"));
		FourthIcon.setVisible(false);
		contentPane.add(FourthIcon);
		
	}
	
	
	public void InitAllLabels() {
		
		label_0 = new JLabel("11");
		label_0.setBounds(780, 729, 35, 35);
		label_0.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_0);
		label_0.setVisible(false);
		contentPane.add(label_0);
		
		 label_1 = new JLabel("12");
		label_1.setBounds(672, 741, 35, 35);
		label_1.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_1);
		label_1.setVisible(false);
		contentPane.add(label_1);
		
		 label_2 = new JLabel("13");
		label_2.setBounds(600, 741, 35, 35);
		label_2.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_2);
		label_2.setVisible(false);
		contentPane.add(label_2);
		
		 label_4 = new JLabel("15");
		label_4.setBounds(478, 741, 35, 35);
		label_4.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_4);
		label_4.setVisible(false);
		contentPane.add(label_4);
		
		 label_5 = new JLabel("16");
		label_5.setBounds(408, 741, 35, 35);
		label_5.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_5);
		label_5.setVisible(false);
		contentPane.add(label_5);
		
		 label_7 = new JLabel("18");
		label_7.setBounds(280, 741, 35, 35);
		label_7.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_7);
		label_7.setVisible(false);
		contentPane.add(label_7);
		
		 label_8 = new JLabel("19");
		label_8.setBounds(214, 741, 35, 35);
		label_8.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_8);
		label_8.setVisible(false);
		contentPane.add(label_8);
		
		 label_10 = new JLabel("111");
		label_10.setBounds(23, 741, 35, 35);
		label_10.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_10);
		label_10.setVisible(false);
		contentPane.add(label_10);
		
		 label_9 = new JLabel("110");
		label_9.setBounds(147, 741, 35, 35);
		label_9.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_9);
		label_9.setVisible(false);
		contentPane.add(label_9);
		
		 label_6 = new JLabel("17");
		label_6.setBounds(342, 741, 35, 35);
		label_6.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_6);
		label_6.setVisible(false);
		contentPane.add(label_6);
		
		 label_3 = new JLabel("14");
		label_3.setBounds(540, 741, 35, 35);
		label_3.setIcon(new ImageIcon("img/rsz_motor.png"));
		labels.add(label_3);
		label_3.setVisible(false);
		contentPane.add(label_3);
		
			 label_11 = new JLabel("112");
			label_11.setBounds(10, 616, 35, 35);
			label_11.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_11);
			label_11.setVisible(false);
			contentPane.add(label_11);
			
			 label_12 = new JLabel("113");
			label_12.setBounds(10, 540, 35, 35);
			label_12.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_12);
			label_12.setVisible(false);
			contentPane.add(label_12);
			
			 label_13 = new JLabel("114");
			label_13.setBounds(10, 486, 35, 35);
			label_13.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_13);
			label_13.setVisible(false);
			contentPane.add(label_13);
			
			 label_14 = new JLabel("115");
			label_14.setBounds(10, 419, 35, 35);
			label_14.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_14);
			label_14.setVisible(false);
			contentPane.add(label_14);
			
			 label_16 = new JLabel("117");
			label_16.setBounds(10, 296, 35, 35);
			label_16.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_16);
			label_16.setVisible(false);
			contentPane.add(label_16);
			
			 label_17 = new JLabel("118");
			label_17.setBounds(10, 236, 35, 35);
			label_17.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_17);
			label_17.setVisible(false);
			contentPane.add(label_17);
		
			
			 label_18 = new JLabel("119");
			label_18.setBounds(10, 171, 35, 35);
			label_18.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_18);
			label_18.setVisible(false);
			contentPane.add(label_18);
			
			 label_19 = new JLabel("120");
			label_19.setBounds(10, 105, 35, 35);
			label_19.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_19);
			label_19.setVisible(false);
			contentPane.add(label_19);
			
			 label_20 = new JLabel("121");
			label_20.setBounds(10, 11, 35, 35);
			label_20.setIcon(new ImageIcon("img/rsz_motor.png"));
			labels.add(label_20);
			label_20.setVisible(false);
			contentPane.add(label_20);
			
		    label_15 = new JLabel("116");
	        	label_15.setBounds(10, 355, 35, 35);
				label_15.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_15);
				label_15.setVisible(false);
				contentPane.add(label_15);
				
				
				label_21 = new JLabel("122");
				label_21.setBounds(118, 11, 35, 35);
				label_21.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_21);
				label_21.setVisible(false);
				contentPane.add(label_21);
				
				label_22 = new JLabel("123");
				label_22.setBounds(183, 11, 35, 35);
				label_22.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_22);
				label_22.setVisible(false);
				contentPane.add(label_22);
				
				label_23 = new JLabel("124");
				label_23.setBounds(250, 11, 35, 35);
				label_23.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_23);
				label_23.setVisible(false);
				contentPane.add(label_23);
				
				label_24 = new JLabel("125");
				label_24.setBounds(316, 11, 35, 35);
				label_24.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_24);
				label_24.setVisible(false);
				contentPane.add(label_24);
				
				label_25 = new JLabel("126");
				label_25.setBounds(383, 11, 35, 35);
				label_25.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_25);
				label_25.setVisible(false);
				contentPane.add(label_25);
				
				label_26 = new JLabel("127");
				label_26.setBounds(451, 11, 35, 35);
				label_26.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_26);
				label_26.setVisible(false);
				contentPane.add(label_26);
				
				label_27 = new JLabel("128");
				label_27.setBounds(510, 11, 35, 35);
				label_27.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_27);
				label_27.setVisible(false);
				contentPane.add(label_27);
				
				label_28 = new JLabel("129");
				label_28.setBounds(578, 11, 35, 35);
				label_28.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_28);
				label_28.setVisible(false);
				contentPane.add(label_28);
				
				label_29 = new JLabel("130");
				label_29.setBounds(648, 11, 35, 35);
				label_29.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_29);
				label_29.setVisible(false);
				contentPane.add(label_29);
				
				label_30 = new JLabel("131");
				label_30.setBounds(780, 11, 35, 35);
				label_30.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_30);
				label_30.setVisible(false);
				contentPane.add(label_30);
				
				label_31 = new JLabel("132");
				label_31.setBounds(780, 105, 35, 35);
				label_31.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_31);
				label_31.setVisible(false);
				contentPane.add(label_31);
				
				label_32 = new JLabel("133");
				label_32.setBounds(780, 171, 35, 35);
				label_32.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_32);
				label_32.setVisible(false);
				contentPane.add(label_32);
				
				label_33 = new JLabel("134");
				label_33.setBounds(780, 236, 35, 35);
				label_33.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_33);
				label_33.setVisible(false);
				contentPane.add(label_33);
				
				label_34 = new JLabel("135");
				label_34.setBounds(780, 296, 35, 35);
				label_34.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_34);
				label_34.setVisible(false);
				contentPane.add(label_34);
				
				label_35 = new JLabel("136");
				label_35.setBounds(780, 355, 35, 35);	
				label_35.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_35);
				label_35.setVisible(false);
				contentPane.add(label_35);
				
				label_36 = new JLabel("137");
				label_36.setBounds(780, 429, 35, 35);
				label_36.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_36);
				label_36.setVisible(false);
				contentPane.add(label_36);
				
				label_37 = new JLabel("138");
				label_37.setBounds(780, 486, 35, 35);
				label_37.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_37);
				label_37.setVisible(false);
				contentPane.add(label_37);
				
				label_38 = new JLabel("139");
				label_38.setBounds(780, 555, 35, 35);
				label_38.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_38);
				label_38.setVisible(false);
				contentPane.add(label_38);
				
				label_39 = new JLabel("140");
				label_39.setBounds(780, 616, 35, 35);
				label_39.setIcon(new ImageIcon("img/rsz_motor.png"));
				labels.add(label_39);
				label_39.setVisible(false);
				contentPane.add(label_39);
					
					label_40 = new JLabel("21");
					label_40.setBounds(780, 683, 35, 35);
					label_40.setIcon(new ImageIcon("img/crown.png"));
					labels.add(label_40);
					label_40.setVisible(false);
					contentPane.add(label_40);
				
					///////////////////////////////////////
				label_41 = new JLabel("22");
				label_41.setBounds(672, 719, 35, 35);
				label_41.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_41);
				label_41.setVisible(false);
				contentPane.add(label_41);
				
				label_42 = new JLabel("23");
				label_42.setBounds(600, 719, 35, 35);
				label_42.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_42);
				label_42.setVisible(false);
				contentPane.add(label_42);
				
				label_43 = new JLabel("24");
				label_43.setBounds(540, 719, 35, 35);
				label_43.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_43);
				label_43.setVisible(false);
				contentPane.add(label_43);
				
				label_44 = new JLabel("25");
				label_44.setBounds(478, 719, 35, 35);
				label_44.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_44);
				label_44.setVisible(false);
				contentPane.add(label_44);
				
				label_45 = new JLabel("26");
				label_45.setBounds(408, 719, 35, 35);
				label_45.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_45);
				label_45.setVisible(false);
				contentPane.add(label_45);
				
				label_46 = new JLabel("27");
				label_46.setBounds(342, 719, 35, 35);
				label_46.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_46);
				label_46.setVisible(false);
				contentPane.add(label_46);
				
				label_47 = new JLabel("28");
				label_47.setBounds(280, 719, 35, 35);
				label_47.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_47);
				label_47.setVisible(false);
				contentPane.add(label_47);
				
				label_48 = new JLabel("29");
				label_48.setBounds(214, 719, 35, 35);
				label_48.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_48);
				label_48.setVisible(false);
				contentPane.add(label_48);
				
				label_49 = new JLabel("210");
				label_49.setBounds(147, 719, 35, 35);
				label_49.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_49);
				label_49.setVisible(false);
				contentPane.add(label_49);
				
				label_50 = new JLabel("211");
				label_50.setBounds(10, 732, 35, 35);
				label_50.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_50);
				label_50.setVisible(false);
				contentPane.add(label_50);
				
				label_51 = new JLabel("212");
				label_51.setBounds(10, 635, 35, 35);
				label_51.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_51);
				label_51.setVisible(false);
				contentPane.add(label_51);
				
				label_52 = new JLabel("213");
				label_52.setBounds(10, 570, 35, 35);
				label_52.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_52);
				label_52.setVisible(false);
				contentPane.add(label_52);
				
				label_53 = new JLabel("214");
				label_53.setBounds(10, 509, 35, 35);
				label_53.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_53);
				label_53.setVisible(false);
				contentPane.add(label_53);
				
				label_54 = new JLabel("215");
				label_54.setBounds(10, 443, 35, 35);
				label_54.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_54);
				label_54.setVisible(false);
				contentPane.add(label_54);
				
				label_55 = new JLabel("216");
				label_55.setBounds(10, 376, 35, 35);
				label_55.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_55);
				label_55.setVisible(false);
				contentPane.add(label_55);
				
				label_56 = new JLabel("217");
				label_56.setBounds(10, 314, 35, 35);
				label_56.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_56);
				label_56.setVisible(false);
				contentPane.add(label_56);
				
				label_57 = new JLabel("218");
				label_57.setBounds(10, 256, 35, 35);
				label_57.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_57);
				label_57.setVisible(false);
				contentPane.add(label_57);
				
				label_58 = new JLabel("219");
				label_58.setBounds(10, 193, 35, 35);
				label_58.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_58);
				label_58.setVisible(false);
				contentPane.add(label_58);
				
				label_59 = new JLabel("220");
				label_59.setBounds(10, 125, 35, 35);
				label_59.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_59);
				label_59.setVisible(false);
				contentPane.add(label_59);
				
				label_60 = new JLabel("221");
				label_60.setBounds(10, 30, 35, 35);
				label_60.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_60);
				label_60.setVisible(false);
				contentPane.add(label_60);
				
				label_61 = new JLabel("222");
				label_61.setBounds(118, 33, 35, 35);
				label_61.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_61);
				label_61.setVisible(false);
				contentPane.add(label_61);
				
				label_62 = new JLabel("223");
				label_62.setBounds(183, 33, 35, 35);
				label_62.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_62);
				label_62.setVisible(false);
				contentPane.add(label_62);
				
				label_63 = new JLabel("224");
				label_63.setBounds(238, 30, 35, 35);
				label_63.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_63);
				label_63.setVisible(false);
				contentPane.add(label_63);
				
				label_64 = new JLabel("225");
				label_64.setBounds(304, 30, 35, 35);
				label_64.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_64);
				label_64.setVisible(false);
				contentPane.add(label_64);
				
				label_65 = new JLabel("226");
				label_65.setBounds(383, 33, 35, 35);
				label_65.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_65);
				label_65.setVisible(false);
				contentPane.add(label_65);
				
				label_66 = new JLabel("227");
				label_66.setBounds(468, 30, 35, 35);
				label_66.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_66);
				label_66.setVisible(false);
				contentPane.add(label_66);
				
				label_67 = new JLabel("228");
				label_67.setBounds(510, 33, 35, 35);
				label_67.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_67);
				label_67.setVisible(false);
				contentPane.add(label_67);
				
				label_68 = new JLabel("229");
				label_68.setBounds(578, 33, 35, 35);
				label_68.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_68);
				label_68.setVisible(false);
				contentPane.add(label_68);
				
				label_69 = new JLabel("230");
				label_69.setBounds(648, 33, 35, 35);
				label_69.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_69);
				label_69.setVisible(false);
				contentPane.add(label_69);
				
				label_70 = new JLabel("231");
				label_70.setBounds(754, 14, 35, 35);
				label_70.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_70);
				label_70.setVisible(false);
				contentPane.add(label_70);
				
				label_71 = new JLabel("232");
				label_71.setBounds(780, 128, 35, 35);
				label_71.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_71);
				label_71.setVisible(false);
				contentPane.add(label_71);
				
				label_72 = new JLabel("233");
				label_72.setBounds(780, 196, 35, 35);
				label_72.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_72);
				label_72.setVisible(false);
				contentPane.add(label_72);
				
				label_73 = new JLabel("234");
				label_73.setBounds(780, 259, 35, 35);
				label_73.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_73);
				label_73.setVisible(false);
				contentPane.add(label_73);
				
				label_74 = new JLabel("235");
				label_74.setBounds(780, 317, 35, 35);
				label_74.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_74);
				label_74.setVisible(false);
				contentPane.add(label_74);
				
				label_75 = new JLabel("236");
				label_75.setBounds(780, 379, 35, 35);
				label_75.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_75);
				label_75.setVisible(false);
				contentPane.add(label_75);
				
				label_76 = new JLabel("237");
				label_76.setBounds(780, 446, 35, 35);
				label_76.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_76);
				label_76.setVisible(false);
				contentPane.add(label_76);
				
				label_77 = new JLabel("238");
				label_77.setBounds(780, 500, 35, 35);
				label_77.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_77);
				label_77.setVisible(false);
				contentPane.add(label_77);
				
				label_78 = new JLabel("239");
				label_78.setBounds(780, 570, 35, 35);
				label_78.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_78);
				label_78.setVisible(false);
				contentPane.add(label_78);
				
				label_79 = new JLabel("240");
				label_79.setBounds(780, 631, 35, 35);
				label_79.setIcon(new ImageIcon("img/crown.png"));
				labels.add(label_79);
				label_79.setVisible(false);
				contentPane.add(label_79);
				
				
				// FROM HERE ~!!
				
				
				label_80 = new JLabel("31");
				label_80.setBounds(732, 683, 35, 35);
				label_80.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_80);
				label_80.setVisible(false);
				contentPane.add(label_80);
				
				label_81 = new JLabel("32");
				label_81.setBounds(648, 719, 35, 35);
				label_81.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_81);
				label_81.setVisible(false);
				contentPane.add(label_81);
				
				label_82 = new JLabel("33");
				label_82.setBounds(585, 719, 35, 35);
				label_82.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_82);
				label_82.setVisible(false);
				contentPane.add(label_82);
				
				label_83 = new JLabel("34");
				label_83.setBounds(523, 719, 35, 35);
				label_83.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_83);
				label_83.setVisible(false);
				contentPane.add(label_83);
				
				label_84 = new JLabel("35");
				label_84.setBounds(461, 719, 35, 35);
				label_84.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_84);
				label_84.setVisible(false);
				contentPane.add(label_84);
				
				label_85 = new JLabel("36");
				label_85.setBounds(383, 719, 35, 35);
				label_85.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_85);
				label_85.setVisible(false);
				contentPane.add(label_85);
				
				label_86 = new JLabel("37");
				label_86.setBounds(326, 719, 35, 35);
				label_86.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_86);
				label_86.setVisible(false);
				contentPane.add(label_86);
				
				label_87 = new JLabel("38");
				label_87.setBounds(263, 719, 35, 35);
				label_87.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_87);
				label_87.setVisible(false);
				contentPane.add(label_87);
				
				label_88 = new JLabel("39");
				label_88.setBounds(198, 719, 35, 35);
				label_88.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_88);
				label_88.setVisible(false);
				contentPane.add(label_88);
				
				label_89 = new JLabel("310");
				label_89.setBounds(118, 719, 35, 35);
				label_89.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_89);
				label_89.setVisible(false);
				contentPane.add(label_89);
				
				label_90 = new JLabel("311");
				label_90.setBounds(10, 719, 35, 35);
				label_90.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_90);
				label_90.setVisible(false);
				contentPane.add(label_90);
				
				label_91 = new JLabel("312");
				label_91.setBounds(40, 647, 35, 35);
				label_91.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_91);
				label_91.setVisible(false);
				contentPane.add(label_91);
				
				label_92 = new JLabel("313");
				label_92.setBounds(40, 570, 35, 35);
				label_92.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_92);
				label_92.setVisible(false);
				contentPane.add(label_92);
				
				label_93 = new JLabel("314");
				label_93.setBounds(40, 509, 35, 35);
				label_93.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_93);
				label_93.setVisible(false);
				contentPane.add(label_93);
				
				label_94 = new JLabel("315");
				label_94.setBounds(40, 446, 35, 35);
				label_94.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_94);
				label_94.setVisible(false);
				contentPane.add(label_94);
				
				label_95 = new JLabel("316");
				label_95.setBounds(40, 379, 35, 35);
				label_95.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_95);
				label_95.setVisible(false);
				contentPane.add(label_95);
				
				label_96 = new JLabel("317");
				label_96.setBounds(40, 317, 35, 35);
				label_96.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_96);
				label_96.setVisible(false);
				contentPane.add(label_96);
				
				label_97 = new JLabel("318");
				label_97.setBounds(40, 259, 35, 35);
				label_97.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_97);
				label_97.setVisible(false);
				contentPane.add(label_97);
				
				label_98 = new JLabel("319");
				label_98.setBounds(40, 196, 35, 35);
				label_98.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_98);
				label_98.setVisible(false);
				contentPane.add(label_98);
				
				label_99 = new JLabel("320");
				label_99.setBounds(33, 128, 35, 35);
				label_99.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_99);
				label_99.setVisible(false);
				contentPane.add(label_99);
				
				label_100 = new JLabel("321");
				label_100.setBounds(40, 33, 35, 35);
				label_100.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_100);
				label_100.setVisible(false);
				contentPane.add(label_100);
				
				label_101 = new JLabel("322");
				label_101.setBounds(147, 33, 35, 35);
				label_101.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_101);
				label_101.setVisible(false);
				contentPane.add(label_101);
				
				label_102 = new JLabel("323");
				label_102.setBounds(208, 33, 35, 35);
				label_102.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_102);
				label_102.setVisible(false);
				contentPane.add(label_102);
				
				label_103 = new JLabel("324");
				label_103.setBounds(260, 33, 35, 35);
				label_103.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_103);
				label_103.setVisible(false);
				contentPane.add(label_103);
				
				label_104 = new JLabel("325");
				label_104.setBounds(326, 33, 35, 35);
				label_104.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_104);
				label_104.setVisible(false);
				contentPane.add(label_104);
				
				label_105 = new JLabel("326");
				label_105.setBounds(408, 33, 35, 35);
				label_105.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_105);
				label_105.setVisible(false);
				contentPane.add(label_105);
				
				label_106 = new JLabel("327");
				label_106.setBounds(450, 33, 35, 35);
				label_106.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_106);
				label_106.setVisible(false);
				contentPane.add(label_106);
				
				label_107 = new JLabel("328");
				label_107.setBounds(530, 33, 35, 35);
				label_107.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_107);
				label_107.setVisible(false);
				contentPane.add(label_107);
				
				label_108 = new JLabel("329");
				label_108.setBounds(600, 33, 35, 35);
				label_108.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_108);
				label_108.setVisible(false);
				contentPane.add(label_108);
				
				label_109 = new JLabel("330");
				label_109.setBounds(672, 33, 35, 35);
				label_109.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_109);
				label_109.setVisible(false);
				contentPane.add(label_109);
				
				label_110 = new JLabel("331");
				label_110.setBounds(780, 33, 35, 35);
				label_110.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_110);
				label_110.setVisible(false);
				contentPane.add(label_110);
				
				label_111 = new JLabel("332");
				label_111.setBounds(754, 108, 35, 35);
				label_111.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_111);
				label_111.setVisible(false);
				contentPane.add(label_111);
				
				label_112 = new JLabel("333");
				label_112.setBounds(754, 174, 35, 35);
				label_112.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_112);
				label_112.setVisible(false);
				contentPane.add(label_112);
				
				label_113 = new JLabel("334");
				label_113.setBounds(754, 239, 35, 35);
				label_113.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_113);
				label_113.setVisible(false);
				contentPane.add(label_113);
				
				label_114 = new JLabel("335");
				label_114.setBounds(754, 299, 35, 35);
				label_114.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_114);
				label_114.setVisible(false);
				contentPane.add(label_114);
				
				label_115 = new JLabel("336");
				label_115.setBounds(754, 358, 35, 35);
				label_115.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_115);
				label_115.setVisible(false);
				contentPane.add(label_115);
				
				label_116 = new JLabel("337");
				label_116.setBounds(754, 432, 35, 35);
				label_116.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_116);
				label_116.setVisible(false);
				contentPane.add(label_116);
				
				label_117 = new JLabel("338");
				label_117.setBounds(754, 489, 35, 35);
				label_117.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_117);
				label_117.setVisible(false);
				contentPane.add(label_117);
				
				label_118 = new JLabel("339");
				label_118.setBounds(754, 565, 35, 35);
				label_118.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_118);
				label_118.setVisible(false);
				contentPane.add(label_118);
				
				label_119 = new JLabel("340");
				label_119.setBounds(754, 619, 35, 35);
				label_119.setIcon(new ImageIcon("img/snow.png"));
				labels.add(label_119);
				label_119.setVisible(false);
				contentPane.add(label_119);
						
				//from her !
						label_120 = new JLabel("41");
						label_120.setBounds(732, 719, 35, 35);
						label_120.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_120);
						label_120.setVisible(false);
						contentPane.add(label_120);
						
						label_121 = new JLabel("42");
						label_121.setBounds(648, 741, 35, 35);
						label_121.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_121);
						label_121.setVisible(false);
						contentPane.add(label_121);
						
						label_122 = new JLabel("43");
						label_122.setBounds(585, 741, 35, 35);
						label_122.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_122);
						label_122.setVisible(false);
						contentPane.add(label_122);
						
						label_123 = new JLabel("44");
						label_123.setBounds(523, 741, 35, 35);
						label_123.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_123);
						label_123.setVisible(false);
						contentPane.add(label_123);
						
						label_124 = new JLabel("45");
						label_124.setBounds(462, 741, 35, 35);
						label_124.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_124);
						label_124.setVisible(false);
						contentPane.add(label_124);
						
						label_125 = new JLabel("46");
						label_125.setBounds(382, 741, 35, 35);
						label_125.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_125);
						label_125.setVisible(false);
						contentPane.add(label_125);
						
						label_126 = new JLabel("47");
						label_126.setBounds(328, 741, 35, 35);
						label_126.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_126);
						label_126.setVisible(false);
						contentPane.add(label_126);
						
						label_127 = new JLabel("48");
						label_127.setBounds(263, 741, 35, 35);
						label_127.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_127);
						label_127.setVisible(false);
						contentPane.add(label_127);
						
						label_128 = new JLabel("49");
						label_128.setBounds(198, 741, 35, 35);
						label_128.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_128);
						label_128.setVisible(false);
						contentPane.add(label_128);
						
						label_129 = new JLabel("410");
						label_129.setBounds(118, 741, 35, 35);
						label_129.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_129);
						label_129.setVisible(false);
						contentPane.add(label_129);
						
						label_130 = new JLabel("411");
						label_130.setBounds(33, 723, 35, 35);
						label_130.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_130);
						label_130.setVisible(false);
						contentPane.add(label_130);
						
						label_131 = new JLabel("412");
						label_131.setBounds(40, 616, 35, 35);
						label_131.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_131);
						label_131.setVisible(false);
						contentPane.add(label_131);
						
						label_132 = new JLabel("413");
						label_132.setBounds(40, 540, 35, 35);
						label_132.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_132);
						label_132.setVisible(false);
						contentPane.add(label_132);
						
						label_133 = new JLabel("414");
						label_133.setBounds(40, 486, 35, 35);
						label_133.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_133);
						label_133.setVisible(false);
						contentPane.add(label_133);
						
						label_134 = new JLabel("415");
						label_134.setBounds(40, 422, 35, 35);
						label_134.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_134);
						label_134.setVisible(false);
						contentPane.add(label_134);
						
						label_135 = new JLabel("416");
						label_135.setBounds(40, 358, 35, 35);
						label_135.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_135);
						label_135.setVisible(false);
						contentPane.add(label_135);
						
						label_136 = new JLabel("417");
						label_136.setBounds(40, 299, 35, 35);
						label_136.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_136);
						label_136.setVisible(false);
						contentPane.add(label_136);
						
						label_137 = new JLabel("418");
						label_137.setBounds(40, 239, 35, 35);
						label_137.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_137);
						label_137.setVisible(false);
						contentPane.add(label_137);
						
						label_138 = new JLabel("419");
						label_138.setBounds(40, 174, 35, 35);
						label_138.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_138);
						label_138.setVisible(false);
						contentPane.add(label_138);
						
						label_139 = new JLabel("420");
						label_139.setBounds(40, 108, 35, 35);
						label_139.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_139);
						label_139.setVisible(false);
						contentPane.add(label_139);
						
						label_140 = new JLabel("421");
						label_140.setBounds(40, 14, 35, 35);
						label_140.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_140);
						label_140.setVisible(false);
						contentPane.add(label_140);
						
						label_141 = new JLabel("422");
						label_141.setBounds(147, 14, 35, 35);
						label_141.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_141);
						label_141.setVisible(false);
						contentPane.add(label_141);
						
						label_142 = new JLabel("423");
						label_142.setBounds(213, 14, 35, 35);
						label_142.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_142);
						label_142.setVisible(false);
						contentPane.add(label_142);
						
						label_143 = new JLabel("424");
						label_143.setBounds(280, 14, 35, 35);
						label_143.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_143);
						label_143.setVisible(false);
						contentPane.add(label_143);
						
						label_144 = new JLabel("425");
						label_144.setBounds(342, 14, 35, 35);
						label_144.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_144);
						label_144.setVisible(false);
						contentPane.add(label_144);
						
						label_145 = new JLabel("426");
						label_145.setBounds(408, 14, 35, 35);
						label_145.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_145);
						label_145.setVisible(false);
						contentPane.add(label_145);
						
						label_146 = new JLabel("427");
						label_146.setBounds(478, 14, 35, 35);
						label_146.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_146);
						label_146.setVisible(false);
						contentPane.add(label_146);
						
						label_147 = new JLabel("428");
						label_147.setBounds(530, 14, 35, 35);
						label_147.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_147);
						label_147.setVisible(false);
						contentPane.add(label_147);
						
						label_148 = new JLabel("429");
						label_148.setBounds(600, 14, 35, 35);
						label_148.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_148);
						label_148.setVisible(false);
						contentPane.add(label_148);
						
						label_149 = new JLabel("430");
						label_149.setBounds(672, 14, 35, 35);
						label_149.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_149);
						label_149.setVisible(false);
						contentPane.add(label_149);
						
						label_150 = new JLabel("431");
						label_150.setBounds(754, 33, 35, 35);
						label_150.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_150);
						label_150.setVisible(false);
						contentPane.add(label_150);
						
						label_151 = new JLabel("432");
						label_151.setBounds(754, 128, 35, 35);
						label_151.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_151);
						label_151.setVisible(false);
						contentPane.add(label_151);
						
						label_152 = new JLabel("433");
						label_152.setBounds(754, 196, 35, 35);
						label_152.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_152);
						label_152.setVisible(false);
						contentPane.add(label_152);
						
						label_153 = new JLabel("434");
						label_153.setBounds(754, 259, 35, 35);
						label_153.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_153);
						label_153.setVisible(false);
						contentPane.add(label_153);
						
						label_154 = new JLabel("435");
						label_154.setBounds(754, 317, 35, 35);
						label_154.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_154);
						label_154.setVisible(false);
						contentPane.add(label_154);
						
						label_155 = new JLabel("436");
						label_155.setBounds(754, 379, 35, 35);
						label_155.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_155);
						label_155.setVisible(false);
						contentPane.add(label_155);
						
						label_156 = new JLabel("437");
						label_156.setBounds(754, 449, 35, 35);
						label_156.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_156);
						label_156.setVisible(false);
						contentPane.add(label_156);
						
						label_157 = new JLabel("438");
						label_157.setBounds(750, 503, 35, 35);
						label_157.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_157);
						label_157.setVisible(false);
						contentPane.add(label_157);
						
						label_158 = new JLabel("439");
						label_158.setBounds(754, 589, 35, 35);
						label_158.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_158);
						label_158.setVisible(false);
						contentPane.add(label_158);
						
						label_159 = new JLabel("440");
						label_159.setBounds(754, 638, 35, 35);
						label_159.setIcon(new ImageIcon("img/hammer.png"));
						labels.add(label_159);
						label_159.setVisible(false);
						contentPane.add(label_159);
				
				
				
		
	}

	
	/*
	 * this method receive the id of the player and the next square 
	 * then it hide the current place to the next place**/
	//ConsiderNumberOfPlayers !!!
	public void initLabel(String playerNumber,String nextquare,int numberofplayers) {
		String id = playerNumber+""+nextquare;
		for(JLabel j :labels ) {
			if(j.getText().equals(id)) {
				if(numberofplayers == 2) {
					if(playerNumber.equals(String.valueOf(firstCurrentLabel.getText().charAt(0)))) {
						firstCurrentLabel.setVisible(false);
						firstCurrentLabel=j;
						firstCurrentLabel.setVisible(true);
					}
					if(playerNumber.equals(String.valueOf(secondCurrentLabel.getText().charAt(0)))) {
						secondCurrentLabel.setVisible(false);
						secondCurrentLabel=j;
						secondCurrentLabel.setVisible(true);
						
					}
					
				}
				if(numberofplayers == 3) {
					
					if(playerNumber.equals(String.valueOf(firstCurrentLabel.getText().charAt(0)))) {
						firstCurrentLabel.setVisible(false);
						firstCurrentLabel=j;
						firstCurrentLabel.setVisible(true);
					}
					if(playerNumber.equals(String.valueOf(secondCurrentLabel.getText().charAt(0)))) {
						secondCurrentLabel.setVisible(false);
						secondCurrentLabel=j;
						secondCurrentLabel.setVisible(true);
						
					}
					if(playerNumber.equals(String.valueOf(thirdCurrentLabel.getText().charAt(0)))) {
						thirdCurrentLabel.setVisible(false);
						thirdCurrentLabel=j;
						thirdCurrentLabel.setVisible(true);
						
					}
					
				}
				if(numberofplayers == 4) {
					
					if(playerNumber.equals(String.valueOf(firstCurrentLabel.getText().charAt(0)))) {
						firstCurrentLabel.setVisible(false);
						firstCurrentLabel=j;
						firstCurrentLabel.setVisible(true);
					}
					if(playerNumber.equals(String.valueOf(secondCurrentLabel.getText().charAt(0)))) {
						secondCurrentLabel.setVisible(false);
						secondCurrentLabel=j;
						secondCurrentLabel.setVisible(true);
						
					}
					if(playerNumber.equals(String.valueOf(thirdCurrentLabel.getText().charAt(0)))) {
						thirdCurrentLabel.setVisible(false);
						thirdCurrentLabel=j;
						thirdCurrentLabel.setVisible(true);
						
					}
					if(playerNumber.equals(String.valueOf(fourthCurrentLabel.getText().charAt(0)))) {
						fourthCurrentLabel.setVisible(false);
						fourthCurrentLabel=j;
						fourthCurrentLabel.setVisible(true);
						
					}
					
				}
			
			
			}
		}
		
		
	}
	/*
	 * this method receive the number of players and set the first icons at the start */
	
	public void FirstInitForLabels(int numberofplayers,ArrayList<Player> players) {
		System.out.println("number of players : "+numberofplayers);
		System.out.println("the size of the array : "+players.size());
		if(numberofplayers == 2) {
			firstCurrentLabel=label_0;
			firstCurrentLabel.setVisible(true);
			FirstPlayerLabel.setText(players.get(0).getName());
			FirstPlayerLabel.setVisible(true);
			FirstIcon.setVisible(true);
			
			lblBalanceFirst.setVisible(true);
			FirstPlayerBalance.setText(String.valueOf(players.get(0).getBalance()));
			FirstPlayerBalance.setVisible(true);
			lblStrikesFirst.setVisible(true);
			FirstPlayerStrikes.setVisible(true);
			
			
			
			secondCurrentLabel=label_40;
			SecondPlayerLabel.setText(players.get(1).getName());
			SecondPlayerLabel.setVisible(true);
			SecondIcon.setVisible(true);
			secondCurrentLabel.setVisible(true);
			
			lblBalanceSecond.setVisible(true);
			SecondPlayerBalance.setText(String.valueOf(players.get(1).getBalance()));
			SecondPlayerBalance.setVisible(true);
			lblStrikesSecond.setVisible(true);
			SecondPlayerStrikes.setVisible(true);
			
		}
		if(numberofplayers == 3) {
			firstCurrentLabel=label_0;
			firstCurrentLabel.setVisible(true);
			FirstPlayerLabel.setText(players.get(0).getName());
			FirstPlayerLabel.setVisible(true);
			FirstIcon.setVisible(true);
			
			lblBalanceFirst.setVisible(true);
			FirstPlayerBalance.setText(String.valueOf(players.get(0).getBalance()));
			FirstPlayerBalance.setVisible(true);
			lblStrikesFirst.setVisible(true);
			FirstPlayerStrikes.setVisible(true);
			
			
			secondCurrentLabel=label_40;
			secondCurrentLabel.setVisible(true);
			SecondPlayerLabel.setText(players.get(1).getName());
			SecondPlayerLabel.setVisible(true);
			SecondIcon.setVisible(true);
			
			lblBalanceSecond.setVisible(true);
			SecondPlayerBalance.setText(String.valueOf(players.get(1).getBalance()));
			SecondPlayerBalance.setVisible(true);
			lblStrikesSecond.setVisible(true);
			SecondPlayerStrikes.setVisible(true);
			
			thirdCurrentLabel=label_80;
			thirdCurrentLabel.setVisible(true);
			ThirdPlayerLabel.setText(players.get(2).getName());
			ThirdPlayerLabel.setVisible(true);
			ThirdIcon.setVisible(true);
			
			lblBalanceThird.setVisible(true);
			ThirdPlayerBalance.setText(String.valueOf(players.get(2).getBalance()));
			ThirdPlayerBalance.setVisible(true);
			lblStrikesThird.setVisible(true);
			ThirdPlayerStrikes.setVisible(true);
		}
		if(numberofplayers == 4) {
			firstCurrentLabel=label_0;
			firstCurrentLabel.setVisible(true);
			FirstPlayerLabel.setText(players.get(0).getName());
			FirstPlayerLabel.setVisible(true);
			FirstIcon.setVisible(true);
			
			lblBalanceFirst.setVisible(true);
			FirstPlayerBalance.setText(String.valueOf(players.get(0).getBalance()));
			FirstPlayerBalance.setVisible(true);
			lblStrikesFirst.setVisible(true);
			FirstPlayerStrikes.setVisible(true);
			
			secondCurrentLabel=label_40;
			secondCurrentLabel.setVisible(true);
			SecondPlayerLabel.setText(players.get(1).getName());
			SecondPlayerLabel.setVisible(true);
			SecondIcon.setVisible(true);
			
			lblBalanceSecond.setVisible(true);
			SecondPlayerBalance.setText(String.valueOf(players.get(1).getBalance()));
			SecondPlayerBalance.setVisible(true);
			lblStrikesSecond.setVisible(true);
			SecondPlayerStrikes.setVisible(true);
			
			thirdCurrentLabel=label_80;
			thirdCurrentLabel.setVisible(true);
			ThirdPlayerLabel.setText(players.get(2).getName());
			ThirdPlayerLabel.setVisible(true);
			ThirdIcon.setVisible(true);
			
			lblBalanceThird.setVisible(true);
			ThirdPlayerBalance.setText(String.valueOf(players.get(2).getBalance()));
			ThirdPlayerBalance.setVisible(true);
			lblStrikesThird.setVisible(true);
			ThirdPlayerStrikes.setVisible(true);
			
			fourthCurrentLabel=label_120;
			fourthCurrentLabel.setVisible(true);
			FourthPlayerLabel.setText(players.get(3).getName());
			FourthPlayerLabel.setVisible(true);
			FourthIcon.setVisible(true);
			
			lblBalanceFourth.setVisible(true);
			FourthPlayerBalance.setText(String.valueOf(players.get(2).getBalance()));
			FourthPlayerBalance.setVisible(true);
			lblStrikesFourth.setVisible(true);
			FourthPlayerStrikes.setVisible(true);
			
		}
	}
	public void eliminatePlayer(int position) {
		if(position == 1 ) {
			FirstJail.setIcon(new ImageIcon("img/x.png"));
			FirstJail.setVisible(true);
		}
		if(position ==2 ) {
			SecondJail.setIcon(new ImageIcon("img/x.png"));
			SecondJail.setVisible(true);
		}
		if(position == 3) {
			ThirdJail.setIcon(new ImageIcon("img/x.png"));
			ThirdJail.setVisible(true);
		}
		if(position == 4 ) {
			FourthJail.setIcon(new ImageIcon("img/x.png"));
			FourthJail.setVisible(true);
		}
	}
	
	public void setPlayerBalance(int position,Player p) {
		if(position == 1 ) {
			FirstPlayerBalance.setText(String.valueOf(p.getBalance()));
		}
		if(position ==2 ) {
			SecondPlayerBalance.setText(String.valueOf(p.getBalance()));
		}
		if(position == 3) {
			ThirdPlayerBalance.setText(String.valueOf(p.getBalance()));
		}
		if(position == 4 ) {
			FourthPlayerBalance.setText(String.valueOf(p.getBalance()));
		}
	}
	public void setPlayerStrikes(int position,Player p ) {
		if(position == 1 ) {
			FirstPlayerStrikes.setText(String.valueOf(p.getStrikes()));
		}
		if(position ==2 ) {
			SecondPlayerStrikes.setText(String.valueOf(p.getStrikes()));
		}
		if(position == 3) {
			ThirdPlayerStrikes.setText(String.valueOf(p.getStrikes()));
		}
		if(position == 4 ) {
			FourthPlayerStrikes.setText(String.valueOf(p.getStrikes()));
		}
	}
	
	
	// add asset rows to table
	public void addRow(Asset a){
		Object[] row = new Object[]{a.getName() +" " + a.getCatagory().getColor(), a.getCost() ,a.getOwner().getName()};
		
		dTableModel.addRow(row);
	}
	public void setPlayerTurn(Player p) {
		textField.setText(p.getName()+" Turn");
	}
	// update asset details in table
	public void updateRow(Asset a){
		String s;
		for(int i = 0; i < AssetTable.getRowCount(); i++){
			s = (String) AssetTable.getValueAt(i, 0);
			if(s.equals(a.getName() +" " + a.getCatagory().getColor())){
				AssetTable.setValueAt(a.getCost(), i, 1);
				AssetTable.setValueAt(a.getOwner().getName(), i, 2);
				break;
			}
		}
	}
	public void goToJail(int index) {
		if(index == 1) {
			firstCurrentLabel.setVisible(false);
			firstCurrentLabel = label_10;
			firstCurrentLabel.setVisible(true);
			FirstJail.setVisible(true);
		}
		if(index == 2) {
			secondCurrentLabel.setVisible(false);
			secondCurrentLabel = label_50;
			secondCurrentLabel.setVisible(true);
			SecondJail.setVisible(true);
		}
		if(index == 3) {
			thirdCurrentLabel.setVisible(false);
			thirdCurrentLabel = label_90;
			thirdCurrentLabel.setVisible(true);
			ThirdJail.setVisible(true);
		}
		if(index == 4) {
			fourthCurrentLabel.setVisible(false);
			fourthCurrentLabel = label_130;
			fourthCurrentLabel.setVisible(true);
			FourthJail.setVisible(true);
		}
	}
	
	public void outOfJail(int index) {
		if(index == 1) {
			FirstJail.setVisible(false);
		}
		if(index == 2) {
			SecondJail.setVisible(false);
		}
		if(index == 3) {
			ThirdJail.setVisible(false);
		}
		if(index == 4) {
			FourthJail.setVisible(false);
		}
	}
	
	public void setEndGameAction(ActionListener li){
		btnNewButton.addActionListener(li);
	}
	
	public void setRollAction(ActionListener li){
		button.addActionListener(li);
	}
	
	public JTextArea getArea(){
		return textArea;
}
}