package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.sql.rowset.Joinable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Model.Logic.I_ModelLogic;
import Model.Logic.ModelLogic;
import Model.Objects.Answer;
import Model.Objects.Asset;
import Model.Objects.Player;
import Model.Objects.Question;
import Model.Objects.Square;
import Utills.MFileWriter;
import View.AddQuestion;
import View.EditQuestion;
import View.GameBoard;
import View.GamePlayers;
import View.GsmeLogScreen;
import View.HandleQuestionsScreen;
import View.Login;
import View.MainMenu;
import View.QuestionScreen;
import View.RegisterScreen;
import View.RemoveQuestion;
import View.SelectField;

public class Controller implements I_Controller{

	private static I_Controller ctrl;
	private I_ModelLogic model;
	private MainMenu mainmenu;
	private GameBoard game;
	private int rounds=0;
	private String GameLog = "";
	private Login loginScreen;
	private RegisterScreen registerScreen;
	private GamePlayers gamePlayers;
	private HandleQuestionsScreen hq;
	private SelectField sf;
	private boolean waiting = true;
	private int answerid =-1;
    boolean FirstPlayer = false;
	boolean SecondPlayer = false;	
	boolean ThirdPlayer = false;
	boolean FourthPlayer = false;
	private ArrayList<Player> PlayersOut;

	

	private Controller() {
		MFileWriter.initializeMyFileWriter();
		model = ModelLogic.getInstance();
		PlayersOut = new ArrayList<>();

	}

	public static I_Controller getInstance() {
		if (ctrl == null) {
			ctrl = new Controller();
		}
		return ctrl;
	}
	
	private void setPlayersOff() {
		this.FirstPlayer=false;
		this.SecondPlayer=false;
		this.ThirdPlayer=false;
		this.FourthPlayer=false;
	}
	
	@Override
	public void startGui() {
		
		mainmenu  = new MainMenu();
		mainmenu.setVisible(true);
		mainmenu.setResizable(false);
		mainmenu.SetNewGameActionListener(setAction());
		mainmenu.setManageQuestionListener(OpenManageQuestion());
		mainmenu.setHistoryListener(OpenHistory());
        mainmenu.setLogoutListener(new WindowClosed());
        
        // added exit game button listener no need to add it
        mainmenu.addWindowListener(new WindowClosed());
        
	/*	mainmenu = new MainMenu();
		mainmenu.setVisible(true);
		mainmenu.setResizable(false);
		mainmenu.SetNewGameActionListener(setAction());*/
        
        
	}
	/**
	 * when window is main menu window is closed save 
	 * game history and question files that was edited
	 */
	private class WindowClosed extends WindowAdapter implements ActionListener {
        @Override
        public void windowClosing(WindowEvent e) {
        	System.out.println("window closed");
        	CloseInput();
            System.exit(1);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	CloseInput();
            System.exit(1);

        }
    }
	
	private void CloseInput() {
		model.writeQuestionsToFile();
		MFileWriter.saveLogFile();
	}
	/**
	 * launch history screen 
	 */
	private void launchHistoryScreen() {
		MFileWriter.writeToLogFile("User chose to view game history.", true);
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File("Logs"));
		
		int rv = jfc.showOpenDialog(null);
		
		if(rv == JFileChooser.APPROVE_OPTION){
			GsmeLogScreen gls = new GsmeLogScreen();
			gls.setText(model.handleHistoryScreen(jfc.getSelectedFile()));
		
			gls.setVisible(true);
		}
		
	}
	/*
	 * launch edit questions screen 
	 */
	private void launchManageQuestionsScreen() {
		MFileWriter.writeToLogFile("User chose to manage questions.", true);
		hq = new HandleQuestionsScreen();
		hq.setBtnAddQuestionListener(launchAddScreen());
		hq.setBtnEditQuestionListener(launchEditScreen());
		hq.setBtnRemoveQuestionListener(launchRemoveScreen());
		hq.setVisible(true);
	}
	
	/**
	 * open add question screen
	 * @return ActionListener for add question button
	 */
	private ActionListener launchAddScreen() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddQuestion aq = new AddQuestion();
				aq.setOnCheckMulipleListener();
				aq.setAddClickListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						for(String s : aq.getAnswers().keySet()){
							if(aq.getQuestion().isEmpty() || s.equals("")){
								JOptionPane.showMessageDialog(aq, "There are some few missing fields", "Missing Fields!", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						if(model.addQuestion(-1, aq.getQuestion(), aq.getAnswers(), aq.isMultiple(),  aq.getComboBox(), aq.getTags().split(" "), aq.getTeam())) {
								
							JOptionPane.showMessageDialog(aq, "Question added to system", "Success!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				aq.setCancelClickListener();
				aq.setVisible(true);				
			}
		};
		return listener;
	}
	/**
	 * open edit question screen
	 * @return ActionListener for add question button
	 */
	private ActionListener launchEditScreen() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EditQuestion aq = new EditQuestion();
				aq.setOnCheckMulipleListener();
				aq.setqIDCombobox(model.getQuestionIds());
				aq.setPickQuestionIDListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						long slected = aq.intgetqIDCombobox();
						Question q = model.getQueationById(slected);		
						aq.setQuestionObj(q);
					}
				});
				
				aq.setAddClickListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						long slected = aq.intgetqIDCombobox();
						for(String s : aq.getAnswers().keySet()){
							if(aq.getQuestion().isEmpty() || s.equals("")){
								JOptionPane.showMessageDialog(aq, "There are some few missing fields", "Missing Fields!", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						if(model.editQuestion(slected, aq.getQuestion(), aq.getAnswers(), aq.isMultiple(),  aq.getComboBox(), aq.getTags().split(" "), aq.getTeam())) {
								
							JOptionPane.showMessageDialog(aq, "Question Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				aq.setCancelClickListener();
				aq.setVisible(true);				
			}
			
		};
		return listener;
	}
	/**
	 * launch remove question screen
	 * @return ActionListener for remove question button
	 */
	private ActionListener launchRemoveScreen() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveQuestion rq = new RemoveQuestion();
				long[] arr = model.getQuestionIds();
				rq.setqIDCombobox(arr);
				rq.setPickQuestionIDListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						rq.setQuestionObj(model.getQueationById(rq.intgetqIDCombobox()));
					
					}
					
				});
				rq.setRemoveActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(model.removeQuestion(rq.intgetqIDCombobox()))
							JOptionPane.showMessageDialog(rq, "Question removed", "Success!", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				rq.setVisible(true);
			}
		};
		return listener;
	}
	/**
	 * get a question that was picked by the system (modelLogic)
	 * and show it on screen and handle answer input
	 * @param q a question that was picked by the system
	 * @return if the question was answered currectly
	 */
	private void showQuestionForLuckyCard(Player p, ArrayList<Question> list,int index) {
		
		QuestionScreen q1 = new QuestionScreen();
		QuestionScreen q2 = new QuestionScreen();
		
		 q1.initButtonGroup(!list.get(0).isMultiple());
    	 q1.initLabelsText(list.get(0).getText(), model.getAnswersForQuestion(list.get(0)));

		ActionListener answerButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				q1.setVisible(false);
				// TODO Auto-generated method stub
				 if(model.checkAnswer(q1.getAllAnswers(), list.get(0), p)){
					 //Player Answered Correctly 
					 q2.initButtonGroup(!list.get(1).isMultiple());
			    	 q2.initLabelsText(list.get(1).getText(), model.getAnswersForQuestion(list.get(1)));
					 
					 ActionListener answerButton = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							q2.dispose();
							// TODO Auto-generated method stub
							if(model.checkAnswer(q2.getAllAnswers(), list.get(1), p)) {
								
								
								
								//Player Answered Correctly 
								Random rm = new Random();
								int range = 5000000-10000+1;
								double random = (double)rm.nextInt(range)+10000;
								
								JOptionPane.showMessageDialog(null,"Good job , you answered correctly on both question \n , you get now "+random+" :)", "Done", JOptionPane.INFORMATION_MESSAGE);
								p.setBalance(random);
								game.setPlayerBalance(index, p);
								
								
								
								
							}else {
								
								JOptionPane.showMessageDialog(null,"Oops Wrong Answer , you pay 25,000:(", "Done", JOptionPane.INFORMATION_MESSAGE);
							
								p.setBalance(-25000);
								game.setPlayerBalance(index, p);
						
							
							}
							
							
						}
					};
					q2.setAnswerBtnListener(answerButton);
					 q2.setVisible(true);
					 q1.dispose();
					 
				 }else {
					 //Answered Wrong
					 JOptionPane.showMessageDialog(null,"Oops Wrong Answer , you pay 50,000 and get a strike :(", "Done", JOptionPane.INFORMATION_MESSAGE);
						p.AddStrike();
						p.setBalance(-50000);
						game.setPlayerBalance(index, p);
						game.setPlayerStrikes(index, p);
						if(p.getStrikes() == 3) {
							StrikesJail(index, p);
						}
					
				 }
			}
		};
		
		 q1.setAnswerBtnListener(answerButtonListener);
         q1.setVisible(true);
		
	}

	private void ShowQuestionForQuestion(Question q,int indexforplayer,Player play) {
		int x=0;
		int n = model.getNumberOfPlayers();
		
		
		
		 QuestionScreen q1 = new QuestionScreen();
		
		ActionListener answerButtonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(model.checkAnswer(q1.getAllAnswers(), q, model.getPlayer(0))){
						FirstPlayer = true;
						
						
				}
				q1.setVisible(false);
				
				QuestionScreen q2 = new QuestionScreen();
				
				ActionListener answerbuttontwo = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					
					if(model.checkAnswer(q2.getAllAnswers(), q, model.getPlayer(1))){
							SecondPlayer = true;
						
							
					}
						
						q2.setVisible(false);

						if(model.getNumberOfPlayers() > 2 ) {
							
							
							QuestionScreen q3 = new QuestionScreen();
							
							ActionListener answerbuttonthree = new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									
									if(model.checkAnswer(q3.getAllAnswers(), q, model.getPlayer(2))){
										ThirdPlayer = true;
										
										
								}
									q3.setVisible(false);
									
									if(model.getNumberOfPlayers() > 3 ) {
										
										QuestionScreen q4 = new QuestionScreen();
										
										ActionListener answerbuttonfour = new ActionListener() {
											
											@Override
											public void actionPerformed(ActionEvent e) {
												
												// TODO Auto-generated method stub
												
												if(model.checkAnswer(q4.getAllAnswers(), q, model.getPlayer(3))){
													FourthPlayer = true;
													}
												q4.setVisible(false);
												
												
												//Should  handle the Answeres ,
												
												HandleTheAnswersOfTheQuestionFourPlayers(indexforplayer,play);
												
												
												
												
											}
										};
										
										q4.SetPlayer( model.getPlayer(3).getName());
										 q4.initButtonGroup(!q.isMultiple());
								    	 q4.initLabelsText(q.getText(), model.getAnswersForQuestion(q));
								    	 q4.setAnswerBtnListener(answerbuttonfour);
								         q4.setVisible(true);
										
										
										
									}else {
										//Case of only 3 players !! 
										
										HandleTheAnswersOfTheQuestionThreePlayers(indexforplayer,play);
										
									}
									
									
									
								}
							};
							
							q3.SetPlayer( model.getPlayer(2).getName());
							 q3.initButtonGroup(!q.isMultiple());
					    	 q3.initLabelsText(q.getText(), model.getAnswersForQuestion(q));
					    	 q3.setAnswerBtnListener(answerbuttonthree);
					         q3.setVisible(true);
							
							
							
							
							
							
						}else {
							// Case of two Players in the game!!! 
							
							HandleTheAnswersOfTheQuestionTwoPlayers(indexforplayer,play);
							
							
						}
						
					
						
						
					}
				};
				
				
				
				 q2.SetPlayer( model.getPlayer(1).getName());
				 q2.initButtonGroup(!q.isMultiple());
		    	 q2.initLabelsText(q.getText(), model.getAnswersForQuestion(q));
		    	 q2.setAnswerBtnListener(answerbuttontwo);
		         q2.setVisible(true);
				
				
			}
		};
	   	q1.SetPlayer( model.getPlayer(0).getName());
		 q1.initButtonGroup(!q.isMultiple());
    	 q1.initLabelsText(q.getText(), model.getAnswersForQuestion(q));
    	 q1.setAnswerBtnListener(answerButtonListener);
         q1.setVisible(true);
		
	}
	private void HandleTheAnswersOfTheQuestionThreePlayers(int MainPlayerIndex,Player mainplayer) {
			if(model.getNumberOfPlayers() == 3) {
				
				if(MainPlayerIndex == 0 ) {
					
					if(FirstPlayer) {
							if(SecondPlayer && ThirdPlayer) {
								 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! but you don't get anything\n"+model.getPlayer(1).getName()+" - Correct answer! , You get 10,000 \n "+model.getPlayer(2).getName()+" - Correct answer! , You get 10,000 \n", "Done", JOptionPane.INFORMATION_MESSAGE);
								 model.getPlayer(1).setBalance(10000);
								 game.setPlayerBalance(2, model.getPlayer(1));    
								 model.getPlayer(2).setBalance(10000);
								 game.setPlayerBalance(3, model.getPlayer(2));    
								 
	
							}
							if(!SecondPlayer && ThirdPlayer) {
								 
								 
								JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n"+model.getPlayer(1).getName()+" - Wrong answer! , You gained a strike \n"+model.getPlayer(2).getName()+" - Correct answer! , You get 75,000 \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
								 
								 model.getPlayer(2).setBalance(75000);
								 game.setPlayerBalance(3, model.getPlayer(2));    
								 model.getPlayer(MainPlayerIndex).setBalance(50000);
								 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));    
								 model.getPlayer(1).AddStrike();
								 game.setPlayerStrikes(2, model.getPlayer(1));
								
							}
							if(SecondPlayer && !ThirdPlayer) {
								JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n"+model.getPlayer(1).getName()+" - Correct answer! , You get 75,000 \n "+model.getPlayer(2).getName()+" - Wrong answer! , You gained a strike \n", "Done", JOptionPane.INFORMATION_MESSAGE);
								 
								 model.getPlayer(1).setBalance(75000);
								 game.setPlayerBalance(2, model.getPlayer(1));    
								 model.getPlayer(MainPlayerIndex).setBalance(50000);
								 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));    
								 model.getPlayer(2).AddStrike();
								 game.setPlayerStrikes(3, model.getPlayer(2));
								
								
							}
							if(!SecondPlayer && !ThirdPlayer) {								
								JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 250,000 \n"+model.getPlayer(1).getName()+" - Wrong answer! , You have gained a strike \n "+model.getPlayer(2).getName()+" - Wrong answer! , You have gained a strike \n", "Done", JOptionPane.INFORMATION_MESSAGE);
								model.getPlayer(MainPlayerIndex).setBalance(250000);
								 model.getPlayer(1).AddStrike();
								 model.getPlayer(2).AddStrike();
								 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
								 game.setPlayerStrikes(2, model.getPlayer(1));	
								 game.setPlayerStrikes(3, model.getPlayer(2));		
											
							}
						
					}else {
						
						if(SecondPlayer && ThirdPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Correct answer! , You get nothing \n "+model.getPlayer(2).getName()+" - Correct answer! , You get nothing \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							
							
						}
						if(!SecondPlayer && ThirdPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(2).getName()+" - Correct answer! , You get nothing \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(1).AddStrike();
							
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(2, model.getPlayer(1));
						}
						if(SecondPlayer && !ThirdPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Correct answer! , You get nothing \n"+model.getPlayer(2).getName()+" - Wrong answer ! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(2).AddStrike();
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(3, model.getPlayer(2));
							 
							 
						}
						if(!SecondPlayer && !ThirdPlayer) {		
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(2).AddStrike();
							 model.getPlayer(1).AddStrike();
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(2, model.getPlayer(1));
							 game.setPlayerStrikes(3, model.getPlayer(2));
						
							
							
						}
						
						
						
						
					}
					
					
					
				}
				
				if(MainPlayerIndex == 1 ) {
					if(SecondPlayer) {
						
						if(FirstPlayer && ThirdPlayer) {
							 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! but you don't get anything\n"+model.getPlayer(0).getName()+" - Correct answer! , You get 10,000 \n "+model.getPlayer(2).getName()+" - Correct answer! , You get 10,000 \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(0).setBalance(10000);
							 game.setPlayerBalance(1, model.getPlayer(0));    
							 model.getPlayer(2).setBalance(10000);
							 game.setPlayerBalance(3, model.getPlayer(2)); 
						

						}
						if(!FirstPlayer && ThirdPlayer) {
							 
							 
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n"+model.getPlayer(0).getName()+" - Wrong answer! , You gained a strike \n"+model.getPlayer(2).getName()+" - Correct answer! , You get 75,000 \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 
							 model.getPlayer(2).setBalance(75000);
							 game.setPlayerBalance(3, model.getPlayer(2));    
							 model.getPlayer(MainPlayerIndex).setBalance(50000);
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));    
							 model.getPlayer(0).AddStrike();
							 game.setPlayerStrikes(1, model.getPlayer(0));
							 
						}
						if(FirstPlayer && !ThirdPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n"+model.getPlayer(0).getName()+" - Correct answer! , You get 75,000 \n "+model.getPlayer(2).getName()+"Wrong! , You gained a strike \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 
							 model.getPlayer(0).setBalance(75000);
							 game.setPlayerBalance(1, model.getPlayer(0));    
							 model.getPlayer(MainPlayerIndex).setBalance(50000);
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));    
							 model.getPlayer(2).AddStrike();
							 game.setPlayerStrikes(3, model.getPlayer(2));
							 
						}
						if(!FirstPlayer && !ThirdPlayer) {								
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 250,000 \n"+model.getPlayer(0).getName()+"Wrong! , You have gained a strike \n "+model.getPlayer(2).getName()+"Wrong! , You have gained a strike \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							model.getPlayer(MainPlayerIndex).setBalance(250000);
							 model.getPlayer(0).AddStrike();
							 model.getPlayer(2).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(1, model.getPlayer(0));	
							 game.setPlayerStrikes(3, model.getPlayer(2));		
								
						}
					
					}else {
						
						if(FirstPlayer && ThirdPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Correct answer! , You get nothing \n "+model.getPlayer(2).getName()+" - Correct answer! , You get nothing \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							
							
						}
						if(!FirstPlayer && ThirdPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(2).getName()+" - Correct answer! , You get nothing \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(0).AddStrike();
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(1, model.getPlayer(0));
						
						}
						if(FirstPlayer && !ThirdPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Correct answer! , You get nothing \n"+model.getPlayer(2).getName()+" - Wrong answer ! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(2).AddStrike();
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(3, model.getPlayer(2));
					
							 
						}
						if(!FirstPlayer && !ThirdPlayer) {		
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(2).AddStrike();
							 model.getPlayer(0).AddStrike();
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(1, model.getPlayer(0));
							 game.setPlayerStrikes(3, model.getPlayer(2));
					
							
							
						}
						
					}
					
					
					
				}
				if(MainPlayerIndex == 2 ) {
					if(ThirdPlayer) {
						if(FirstPlayer && SecondPlayer) {
							 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! but you don't get anything\n"+model.getPlayer(0).getName()+" - Correct answer! , You get 10,000 \n "+model.getPlayer(1).getName()+" - Correct answer! , You get 10,000 \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(0).setBalance(10000);
							 game.setPlayerBalance(1, model.getPlayer(0));    
							 model.getPlayer(1).setBalance(10000);
							 game.setPlayerBalance(2, model.getPlayer(1));    
							

						}
						if(!FirstPlayer && SecondPlayer) {
							 
							 
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n"+model.getPlayer(0).getName()+" - Wrong answer! , You gained a strike \n"+model.getPlayer(1).getName()+" - Correct answer! , You get 75,000 \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 
							 model.getPlayer(1).setBalance(75000);
							 game.setPlayerBalance(2, model.getPlayer(1));    
							 model.getPlayer(MainPlayerIndex).setBalance(50000);
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));    
							 model.getPlayer(0).AddStrike();
							 game.setPlayerStrikes(1, model.getPlayer(0));
							
						}
						if(FirstPlayer && !SecondPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n"+model.getPlayer(0).getName()+" - Correct answer! , You get 75,000 \n "+model.getPlayer(1).getName()+" - Wrong answer! , You gained a strike \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 
							 model.getPlayer(0).setBalance(75000);
							 game.setPlayerBalance(1, model.getPlayer(0));    
							 model.getPlayer(MainPlayerIndex).setBalance(50000);
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));    
							 model.getPlayer(1).AddStrike();
							 game.setPlayerStrikes(2, model.getPlayer(1));
							
							
						}
						if(!FirstPlayer && !SecondPlayer) {								
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 250,000 \n"+model.getPlayer(0).getName()+" - Wrong answer! , You have gained a strike \n "+model.getPlayer(1).getName()+" - Wrong answer! , You have gained a strike \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							model.getPlayer(MainPlayerIndex).setBalance(250000);
							 model.getPlayer(0).AddStrike();
							 model.getPlayer(1).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(1, model.getPlayer(0));	
							 game.setPlayerStrikes(2, model.getPlayer(1));	
							 				
						}
						
						
					}else {
						

						if(FirstPlayer && SecondPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Correct answer! , You get nothing \n "+model.getPlayer(1).getName()+" - Correct answer! , You get nothing \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
								
							
						}
						if(!FirstPlayer && SecondPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(1).getName()+" - Correct answer! , You get nothing \n", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(0).AddStrike();
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(1, model.getPlayer(0));
						
						}
						if(FirstPlayer && !SecondPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Correct answer! , You get nothing \n"+model.getPlayer(1).getName()+" - Wrong answer ! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(1).AddStrike();
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(2, model.getPlayer(1));

					
						}
						if(!FirstPlayer && !SecondPlayer) {		
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(1).AddStrike();
							 model.getPlayer(0).AddStrike();
							 
							 
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 game.setPlayerStrikes(1, model.getPlayer(0));
							 game.setPlayerStrikes(2, model.getPlayer(1));

					
							
						}
						
						
						
					}
	
	
	
				}
			
			
				setPlayersOff();
				if(model.getPlayer(0).getStrikes() == 3) {
					JOptionPane.showMessageDialog(null,model.getPlayer(0).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
					StrikesJail(1,model.getPlayer(0));
				}
				if(model.getPlayer(1).getStrikes() == 3) {
					JOptionPane.showMessageDialog(null,model.getPlayer(1).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
					StrikesJail(2,model.getPlayer(1));
				}
				if(model.getPlayer(2).getStrikes() == 3) {
					JOptionPane.showMessageDialog(null,model.getPlayer(2).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
					StrikesJail(3,model.getPlayer(2));
				}
				
			}
		
		
	}
	
	private void HandleTheAnswersOfTheQuestionFourPlayers(int MainPlayerIndex,Player mainplayer) {
		
		if(model.getNumberOfPlayers() == 4) {
			if(MainPlayerIndex == 0 ) {
				if(!FirstPlayer) {
						if(SecondPlayer && ThirdPlayer && FourthPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Correct answer! , you get nothing \n "+model.getPlayer(2).getName()+" - Correct answer! , you get nothing \n "+model.getPlayer(3).getName()+" - Correct answer! , you get nothing \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
					
						}else if(!SecondPlayer && !ThirdPlayer && !FourthPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer ! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n "+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 model.getPlayer(1).AddStrike();
							 model.getPlayer(2).AddStrike();
							 model.getPlayer(3).AddStrike();
							 game.setPlayerStrikes(2,  model.getPlayer(1));
							 game.setPlayerStrikes(3,  model.getPlayer(2));	
							 game.setPlayerStrikes(4,  model.getPlayer(3));	
					
						}else {
							String AllPlayers =model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n";
							model.getPlayer(MainPlayerIndex).setBalance(-50000);
							game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							if(!SecondPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(1).AddStrike();
								 game.setPlayerStrikes(2,  model.getPlayer(1));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get nothing \n ";
								
							}
							if(!ThirdPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(2).AddStrike();
								 game.setPlayerStrikes(3,  model.getPlayer(2));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get nothing \n ";
							}
							if(!FourthPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(3).AddStrike();
								 game.setPlayerStrikes(4,  model.getPlayer(3));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Correct answer! , you get nothing \n ";
							}

							JOptionPane.showMessageDialog(null, AllPlayers, "Done", JOptionPane.INFORMATION_MESSAGE);
							

						}
					}else {
						if(SecondPlayer && ThirdPlayer && FourthPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get nothing\n"+model.getPlayer(1).getName()+" - Correct answer! , you get 10,000 \n "+model.getPlayer(2).getName()+" - Correct answer! , you get 10,000 \n "+model.getPlayer(3).getName()+" - Correct answer! , you get 10,000\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							model.getPlayer(1).setBalance(10000);
							 model.getPlayer(2).setBalance(10000);
							 model.getPlayer(3).setBalance(10000);
							 game.setPlayerBalance(2, model.getPlayer(1));
							 game.setPlayerBalance(3, model.getPlayer(2));
							 game.setPlayerBalance(4, model.getPlayer(3));
					
							
						}else if(!SecondPlayer && !ThirdPlayer && !FourthPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 250,000\n"+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n "+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(1).AddStrike();
							 game.setPlayerStrikes(2,  model.getPlayer(1));
							 model.getPlayer(2).AddStrike();
							 game.setPlayerStrikes(3,  model.getPlayer(2));
							 model.getPlayer(3).AddStrike();
							 game.setPlayerStrikes(4,  model.getPlayer(3));
							 model.getPlayer(MainPlayerIndex).setBalance(250000);
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						
							
						}else {	
							String AllPlayers =model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n";
							model.getPlayer(MainPlayerIndex).setBalance(50000);
							game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							if(!SecondPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(1).AddStrike();
								 game.setPlayerStrikes(2,  model.getPlayer(1));
								 
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get 75,000 \n ";
							
								model.getPlayer(1).setBalance(75000);
								 game.setPlayerBalance(2, model.getPlayer(1));
							}
							if(!ThirdPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(2).AddStrike();
								 game.setPlayerStrikes(3,  model.getPlayer(2));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get 75,000 \n ";
								model.getPlayer(2).setBalance(75000);
								game.setPlayerBalance(3, model.getPlayer(2));
							}
							if(!FourthPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(3).AddStrike();
								 game.setPlayerStrikes(4,  model.getPlayer(3));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Correct answer! , you get 75,000 \n ";
								model.getPlayer(3).setBalance(75000);
								 game.setPlayerBalance(4, model.getPlayer(3));
							}

				
							JOptionPane.showMessageDialog(null, AllPlayers, "Done", JOptionPane.INFORMATION_MESSAGE);

							
						}
						
					}
				}
			if(MainPlayerIndex == 1 ) {
				if(!SecondPlayer) {
					if(FirstPlayer && ThirdPlayer && FourthPlayer) {
						JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Correct answer! , you get nothing \n "+model.getPlayer(2).getName()+" - Correct answer! , you get nothing \n "+model.getPlayer(3).getName()+" - Correct answer! , you get nothing \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(MainPlayerIndex).setBalance(-50000);
						 model.getPlayer(MainPlayerIndex).AddStrike();
						 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
						 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
				
					}else if(!SecondPlayer && !ThirdPlayer && !FourthPlayer) {
						JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer ! you lost 50,000 and gained a strike \n"+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike \n "+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(MainPlayerIndex).setBalance(-50000);
						 model.getPlayer(MainPlayerIndex).AddStrike();
						 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
						 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
						 model.getPlayer(0).AddStrike();
						 model.getPlayer(2).AddStrike();
						 model.getPlayer(3).AddStrike();
						 game.setPlayerStrikes(1,  model.getPlayer(0));
						 game.setPlayerStrikes(3,  model.getPlayer(2));	
						 game.setPlayerStrikes(4,  model.getPlayer(3));	
				
					}else {
						String AllPlayers =model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n";
						model.getPlayer(MainPlayerIndex).setBalance(-50000);
						game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						if(!FirstPlayer) {
							AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike \n ";
							 model.getPlayer(0).AddStrike();
							 game.setPlayerStrikes(1,  model.getPlayer(0));
						}else {
							AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Correct answer! , you get nothing \n ";
							
						}
						if(!ThirdPlayer) {
							AllPlayers = AllPlayers+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike \n ";
							 model.getPlayer(2).AddStrike();
							 game.setPlayerStrikes(3,  model.getPlayer(2));
						}else {
							AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get nothing \n ";
						}
						if(!FourthPlayer) {
							AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike \n ";
							 model.getPlayer(3).AddStrike();
							 game.setPlayerStrikes(4,  model.getPlayer(3));
						}else {
							AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Correct answer! , you get nothing \n ";
						}

						JOptionPane.showMessageDialog(null, AllPlayers, "Done", JOptionPane.INFORMATION_MESSAGE);
						

					}
				}else {
					if(FirstPlayer && ThirdPlayer && FourthPlayer) {
						JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get nothing\n"+model.getPlayer(0).getName()+" - Correct answer! , you get 10,000 \n "+model.getPlayer(2).getName()+" - Correct answer! , you get 10,000 \n "+model.getPlayer(3).getName()+" - Correct answer! , you get 10,000\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						model.getPlayer(0).setBalance(10000);
						 model.getPlayer(2).setBalance(10000);
						 model.getPlayer(3).setBalance(10000);
						 game.setPlayerBalance(1, model.getPlayer(0));
						 game.setPlayerBalance(3, model.getPlayer(2));
						 game.setPlayerBalance(4, model.getPlayer(3));
				
						
					}else if(!FirstPlayer && !ThirdPlayer && !FourthPlayer) {
						JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 250,000\n"+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike \n "+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(0).AddStrike();
						 game.setPlayerStrikes(1,  model.getPlayer(0));
						 model.getPlayer(2).AddStrike();
						 game.setPlayerStrikes(3,  model.getPlayer(2));
						 model.getPlayer(3).AddStrike();
						 game.setPlayerStrikes(4,  model.getPlayer(3));
						 model.getPlayer(MainPlayerIndex).setBalance(250000);
						 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						
						
					}else {	
						String AllPlayers =model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n";
						model.getPlayer(MainPlayerIndex).setBalance(50000);
						game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						if(!FirstPlayer) {
							AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike \n ";
							 model.getPlayer(0).AddStrike();
							 game.setPlayerStrikes(1,  model.getPlayer(0));
							 
						}else {
							AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get 75,000 \n ";
							
							model.getPlayer(0).setBalance(75000);
							 game.setPlayerBalance(1, model.getPlayer(0));
						}
						if(!ThirdPlayer) {
							AllPlayers = AllPlayers+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike \n ";
							 model.getPlayer(2).AddStrike();
							 game.setPlayerStrikes(3,  model.getPlayer(2));
						}else {
							AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get 75,000 \n ";
							model.getPlayer(2).setBalance(75000);
							game.setPlayerBalance(3, model.getPlayer(2));
						}
						if(!FourthPlayer) {
							AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike \n ";
							 model.getPlayer(3).AddStrike();
							 game.setPlayerStrikes(4,  model.getPlayer(3));
						}else {
							AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get 75,000 \n ";
							model.getPlayer(3).setBalance(75000);
							 game.setPlayerBalance(4, model.getPlayer(3));
						}

				
						JOptionPane.showMessageDialog(null, AllPlayers, "Done", JOptionPane.INFORMATION_MESSAGE);

					}
					
				}
				
				
			}
				
			if(MainPlayerIndex == 2 ) {
				if(!ThirdPlayer) {
						if(SecondPlayer && FirstPlayer && FourthPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Correct answer ! , you get nothing \n "+model.getPlayer(0).getName()+" - Correct answer! , you get nothing \n "+model.getPlayer(3).getName()+" - Correct answer! , you get nothing \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							
						}else if(!SecondPlayer && !FirstPlayer && !FourthPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer ! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n "+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 model.getPlayer(1).AddStrike();
							 model.getPlayer(0).AddStrike();
							 model.getPlayer(3).AddStrike();
							 game.setPlayerStrikes(2,  model.getPlayer(1));
							 game.setPlayerStrikes(1,  model.getPlayer(0));	
							 game.setPlayerStrikes(4,  model.getPlayer(3));	
					
						}else {
							String AllPlayers =model.getPlayer(MainPlayerIndex).getName()+" Wrong! you lost 50,000 and gained a strike \n";
							model.getPlayer(MainPlayerIndex).setBalance(-50000);
							game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							if(!SecondPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(1).AddStrike();
								 game.setPlayerStrikes(2,  model.getPlayer(1));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get nothing \n ";
								
							}
							if(!FirstPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Correct answer! , you have gained a strike \n ";
								 model.getPlayer(0).AddStrike();
								 game.setPlayerStrikes(1,  model.getPlayer(0));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get nothing \n ";
							}
							if(!FourthPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(3).AddStrike();
								 game.setPlayerStrikes(4,  model.getPlayer(3));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Correct answer! , you get nothing \n ";
							}

							
							JOptionPane.showMessageDialog(null, AllPlayers, "Done", JOptionPane.INFORMATION_MESSAGE);
							

						}
					}else {
						if(SecondPlayer && FirstPlayer && FourthPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get nothing\n"+model.getPlayer(1).getName()+" - Correct answer! , you get 10,000 \n "+model.getPlayer(0).getName()+" - Correct answer! , you get 10,000 \n "+model.getPlayer(3).getName()+" - Correct answer! , you get 10,000\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							model.getPlayer(1).setBalance(10000);
							 model.getPlayer(0).setBalance(10000);
							 model.getPlayer(3).setBalance(10000);
							 game.setPlayerBalance(2, model.getPlayer(1));
							 game.setPlayerBalance(1, model.getPlayer(0));
							 game.setPlayerBalance(4, model.getPlayer(3));
							
							
						}else if(!SecondPlayer && !FirstPlayer && !FourthPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 250,000\n"+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n "+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(1).AddStrike();
							 game.setPlayerStrikes(2,  model.getPlayer(1));
							 model.getPlayer(0).AddStrike();
							 game.setPlayerStrikes(1,  model.getPlayer(0));
							 model.getPlayer(3).AddStrike();
							 game.setPlayerStrikes(4,  model.getPlayer(3));
							 model.getPlayer(MainPlayerIndex).setBalance(250000);
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							
							
						}else {	
							String AllPlayers =model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n";
								model.getPlayer(MainPlayerIndex).setBalance(50000);
								game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							if(!SecondPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(1).AddStrike();
								 game.setPlayerStrikes(2,  model.getPlayer(1));
								 
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get 75,000 \n ";	
								model.getPlayer(1).setBalance(75000);
								 game.setPlayerBalance(2, model.getPlayer(1));
							}
							if(!FirstPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(0).AddStrike();
								 game.setPlayerStrikes(1,  model.getPlayer(0));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Correct answer! , you get 75,000 \n ";
								model.getPlayer(0).setBalance(75000);
								game.setPlayerBalance(1, model.getPlayer(0));
							}
							if(!FourthPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(3).AddStrike();
								 game.setPlayerStrikes(4,  model.getPlayer(3));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(3).getName()+" - Correct answer! , you get 75,000 \n ";
								model.getPlayer(3).setBalance(75000);
								 game.setPlayerBalance(4, model.getPlayer(3));
							}

						
							JOptionPane.showMessageDialog(null, AllPlayers, "Done", JOptionPane.INFORMATION_MESSAGE);

							
						}
						
					}
				}
			if(MainPlayerIndex == 3 ) {
				if(!FourthPlayer) {
						if(SecondPlayer && ThirdPlayer && FirstPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Correct answer! , you get nothing \n "+model.getPlayer(2).getName()+" - Correct answer! , you get nothing \n "+model.getPlayer(0).getName()+" - Correct answer! , you get nothing \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
						
						}else if(!SecondPlayer && !ThirdPlayer && !FirstPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Wrong answer ! you lost 50,000 and gained a strike \n"+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n "+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));	
							 model.getPlayer(1).AddStrike();
							 model.getPlayer(2).AddStrike();
							 model.getPlayer(0).AddStrike();
							 game.setPlayerStrikes(2,  model.getPlayer(1));
							 game.setPlayerStrikes(3,  model.getPlayer(2));	
							 game.setPlayerStrikes(1,  model.getPlayer(0));	
					
						}else {
							String AllPlayers =model.getPlayer(MainPlayerIndex).getName()+" Wrong! you lost 50,000 and gained a strike \n";
							model.getPlayer(MainPlayerIndex).setBalance(-50000);
							game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							if(!SecondPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(1).AddStrike();
								 game.setPlayerStrikes(2,  model.getPlayer(1));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get nothing \n ";
								
							}
							if(!ThirdPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(2).AddStrike();
								 game.setPlayerStrikes(3,  model.getPlayer(2));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(2).getName()+" - Correct answer! , you get nothing \n ";
							}
							if(!FirstPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(0).AddStrike();
								 game.setPlayerStrikes(1,  model.getPlayer(0));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Correct answer! , you get nothing \n ";
							}

							
							JOptionPane.showMessageDialog(null, AllPlayers, "Done", JOptionPane.INFORMATION_MESSAGE);
							

						}
					}else {
						if(SecondPlayer && ThirdPlayer && FirstPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get nothing\n"+model.getPlayer(1).getName()+" - Correct answer! , you get 10,000 \n "+model.getPlayer(2).getName()+" - Correct answer! , you get 10,000 \n "+model.getPlayer(0).getName()+" - Correct answer! , you get 10,000\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							model.getPlayer(1).setBalance(10000);
							 model.getPlayer(2).setBalance(10000);
							 model.getPlayer(0).setBalance(10000);
							 game.setPlayerBalance(2, model.getPlayer(1));
							 game.setPlayerBalance(3, model.getPlayer(2));
							 game.setPlayerBalance(1, model.getPlayer(0));
							
		
							
						}else if(!SecondPlayer && !ThirdPlayer && !FirstPlayer) {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 250,000\n"+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n "+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike\n "+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike\n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(1).AddStrike();
							 game.setPlayerStrikes(2,  model.getPlayer(1));
							 model.getPlayer(2).AddStrike();
							 game.setPlayerStrikes(3,  model.getPlayer(2));
							 model.getPlayer(0).AddStrike();
							 game.setPlayerStrikes(1,  model.getPlayer(0));
							 model.getPlayer(MainPlayerIndex).setBalance(250000);
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						
							
						}else {	
							String AllPlayers =model.getPlayer(MainPlayerIndex).getName()+" - Correct answer! you get 50,000 \n";
							model.getPlayer(MainPlayerIndex).setBalance(50000);
							game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							if(!SecondPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(1).AddStrike();
								 game.setPlayerStrikes(2,  model.getPlayer(1));
								 
							}else {
								AllPlayers = AllPlayers+model.getPlayer(1).getName()+" - Correct answer! , you get 75,000 \n ";
								model.getPlayer(1).setBalance(75000);
								 game.setPlayerBalance(2, model.getPlayer(1));
							}
							if(!ThirdPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(2).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(2).AddStrike();
								 game.setPlayerStrikes(3,  model.getPlayer(2));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(2).getName()+" - Correct answer! , you get 75,000 \n ";
								model.getPlayer(2).setBalance(75000);
								game.setPlayerBalance(3, model.getPlayer(2));
							}
							if(!FirstPlayer) {
								AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Wrong answer! , you have gained a strike \n ";
								 model.getPlayer(0).AddStrike();
								 game.setPlayerStrikes(1,  model.getPlayer(0));
							}else {
								AllPlayers = AllPlayers+model.getPlayer(0).getName()+" - Correct answer! , you get 75,000 \n ";
								model.getPlayer(0).setBalance(75000);
								 game.setPlayerBalance(1, model.getPlayer(0));
							}

						
							JOptionPane.showMessageDialog(null, AllPlayers, "Done", JOptionPane.INFORMATION_MESSAGE);

							
						}
						
					}
				}
			
			
			
			
			setPlayersOff();
			if(model.getPlayer(0).getStrikes() == 3) {
				JOptionPane.showMessageDialog(null,model.getPlayer(0).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
				StrikesJail(1,model.getPlayer(0));
			}
			if(model.getPlayer(1).getStrikes() == 3) {
				JOptionPane.showMessageDialog(null,model.getPlayer(1).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
				StrikesJail(2,model.getPlayer(1));
			}
			if(model.getPlayer(2).getStrikes() == 3) {
				JOptionPane.showMessageDialog(null,model.getPlayer(2).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
				StrikesJail(3,model.getPlayer(2));
			}
			if(model.getPlayer(3).getStrikes() == 3) {
				JOptionPane.showMessageDialog(null,model.getPlayer(3).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
				StrikesJail(4,model.getPlayer(3));
			}
			
		}
		
		
		
	}
	private void HandleTheAnswersOfTheQuestionTwoPlayers(int MainPlayerIndex,Player mainplayer) {
		
		if(model.getNumberOfPlayers() == 2) {
			
			if(MainPlayerIndex == 0 ) {
				if(!FirstPlayer) {
					//The first player is the main player
					if(SecondPlayer) {	
						 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+"  - Wrong answer! You lose 50,000 and gain a strike\n"+model.getPlayer(1).getName()+"Right! , but you don't get anything \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(MainPlayerIndex).setBalance(-50000);
						 model.getPlayer(MainPlayerIndex).AddStrike();
						 //Check for strikes !~!
						 //also update the fucking GameBoard ! ! 
						 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
					
						 	 
						 
					}else {
						 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+"  - Wrong answer! You lose 50,000 and gain a strike\n"+model.getPlayer(1).getName()+" - Wrong answer! , You have gained a strike \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(MainPlayerIndex).setBalance(-50000);
						 model.getPlayer(MainPlayerIndex).AddStrike();
						 model.getPlayer(1).AddStrike();
						 
						 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						 game.setPlayerStrikes(1+1, model.getPlayer(1));			
					
						 }
					
				}else {
					if(SecondPlayer) {
						 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" Right! but you don't get anything\n"+model.getPlayer(1).getName()+"Right! , You get 10,000 \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(1).setBalance(10000);
						 game.setPlayerBalance(2, model.getPlayer(1));     	
						 setPlayersOff();
					}else {
						 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" Right!  you get 250,000 \n"+model.getPlayer(1).getName()+" - Wrong answer! , You have gained a strike \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(MainPlayerIndex).setBalance(250000);
						 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
						 model.getPlayer(1).AddStrike();
						 game.setPlayerStrikes(1+1, model.getPlayer(1));	
						
					}
				
					
				}
			}
			
			if(MainPlayerIndex == 1) {
				
				if(!SecondPlayer) {
						if(FirstPlayer) {
							
							 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+"  - Wrong answer! You lose 50,000 and gain a strike\n"+model.getPlayer(0).getName()+"Right! , but you don't get anything \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							
							 	 
							
						}else {
							JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+"  - Wrong answer! You lose 50,000 and gain a strike\n"+model.getPlayer(0).getName()+" - Wrong answer! , You have gained a strike \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
							 model.getPlayer(MainPlayerIndex).setBalance(-50000);
							 model.getPlayer(MainPlayerIndex).AddStrike();
							 model.getPlayer(0).AddStrike();
							 
							 game.setPlayerBalance(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(MainPlayerIndex+1, model.getPlayer(MainPlayerIndex));
							 game.setPlayerStrikes(0+1, model.getPlayer(0));	
					
							
							
							
						}
				}else {
					if(FirstPlayer) {
						 JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" Right! but you don't get anything\n"+model.getPlayer(1).getName()+"Right! , You get 10,000 \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(0).setBalance(10000);
						 game.setPlayerBalance(2, model.getPlayer(0)); 
				
					}else {
						JOptionPane.showMessageDialog(null, model.getPlayer(MainPlayerIndex).getName()+" Right!  you get 250,000 \n"+model.getPlayer(0).getName()+" - Wrong answer! , You have gained a strike \n ", "Done", JOptionPane.INFORMATION_MESSAGE);
						 model.getPlayer(MainPlayerIndex).setBalance(250000);
						 model.getPlayer(0).AddStrike();
						 game.setPlayerBalance(MainPlayerIndex+1,model.getPlayer(MainPlayerIndex));
						 game.setPlayerStrikes(1, model.getPlayer(0));	
					
						
						
					}
					
				}
				
				
				
			}
			
			setPlayersOff();
			if(model.getPlayer(0).getStrikes() == 3) {
				JOptionPane.showMessageDialog(null,model.getPlayer(0).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
				StrikesJail(1,model.getPlayer(0));
			}
			if(model.getPlayer(1).getStrikes() == 3) {
				JOptionPane.showMessageDialog(null,model.getPlayer(1).getName()+" have 3 strikes , say hi to jail :)", "Done", JOptionPane.INFORMATION_MESSAGE);
				StrikesJail(2,model.getPlayer(1));
			}
		}
		
		
	}
	


	
/*
	@Override
	public boolean startGame() {

		model.startGame();
		int i = 0, dice;
		Square sq;
		Player p;

		do {
			p = model.getPlayer(i);

			if (p.isBroke()) {
				continue;

			} else if (p.isInJail()) {
				if (p.canLeaveJail())
					model.getOoutOfJail(p);

				else{
					p.setRoundsInJail(1);
					continue;
				}
			}

			dice = model.rollDice(p);
			sq = model.getSquare(p, dice);
			
			switch (sq.getType()) {

			case ASSET:
				if (((Asset) sq).hasOwner()) {
					if (!((Asset) sq).getOwner().equals(p)) {
						if (model.CanBuyFromOwner(p, (Asset) sq)) {
							model.payToOwner(p, (Asset) sq);
						} else {
							model.PayFine(p, (Asset) sq);
							
						}
					} 
				} else
					model.buyAsset(sq, p);
				break;

			case GOTOJAIL:
				model.sendToJail(p, false);
				break;

			case JAIL:
				System.out.println(p + " visited jail");
				MFileWriter.writeToLogFile(p + " visited jail \n", false);
				break;

			case LUCKYCARD:
				model.answerLuckyCardQuestions(p);
				break;

			case QUESTION:
				model.answerQuestionCard(p);
				break;

			case START:
				break;
			}
			if (i == 1)
				break;
			i++;
		} while (!model.gameOver());
		System.out.println("Game is over");
		System.out.println("The winner is: "+ model.getWinner());
		return false;

	}*/
	
	/* Action Listener for Button in the mainMenu view */
	private ActionListener setAction() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				gamePlayers = new GamePlayers();
				gamePlayers.setVisible(true);
				gamePlayers.setResizable(false);
				gamePlayers.SetStartGameActionListener(StartBoard());
				gamePlayers.setBackListener(setBackPlayers());
				mainmenu.setVisible(false);
				
				
			/*	game = new GameBoard();
				game.setVisible(true);
				game.setResizable(false);
				game.setRollAction(setRollAction());
				mainmenu.setVisible(false);*/

			}
		};
	}

	private ActionListener StartBoard() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> validation = new ArrayList<>();
				boolean check = true;
					int numberofplayer = gamePlayers.getNumberOfPlayers();
						if(numberofplayer == 2) {
							if(gamePlayers.getFirstPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("First Player Text Field is empty");	
								check=false;
							}
							if(gamePlayers.getFirstPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("First Player Text should be at least 4 characters");	
								check=false;
							}
							if(gamePlayers.getSecondPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("Second player Text field is empty");
								
								check=false;
							}
							if(gamePlayers.getSecondPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("Second Player Text should be at least 4 characters");	
								check=false;
							}
							
							validation.add(gamePlayers.getFirstPlayerName());
							validation.add(gamePlayers.getSecondPlayerName());
								
						}
						if(numberofplayer == 3) {
							if(gamePlayers.getFirstPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("First Player Text Field is empty");	
								check=false;
							}
							if(gamePlayers.getFirstPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("First Player Text should be at least 4 characters");	
								check=false;
							}
							if(gamePlayers.getSecondPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("Second player Text field is empty");
								check=false;
							}
							if(gamePlayers.getSecondPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("Second Player Text should be at least 4 characters");	
								check=false;
							}
							if(gamePlayers.getThirdPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("Third player Text field is empty");
								check=false;
							}
							if(gamePlayers.getThirdPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("Third Player Text should be at least 4 characters");	
								check=false;
							}
							validation.add(gamePlayers.getFirstPlayerName());
							validation.add(gamePlayers.getSecondPlayerName());
							validation.add(gamePlayers.getThirdPlayerName());
						}
						if(numberofplayer == 4) {
							//first player
							if(gamePlayers.getFirstPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("First Player Text Field is empty");	
								check=false;
							}
							if(gamePlayers.getFirstPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("First Player Text should be at least 4 characters");	
								check=false;
							}
							if(gamePlayers.getSecondPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("Second player Text field is empty");
								check=false;
							}
							if(gamePlayers.getSecondPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("Second Player Text should be at least 4 characters");	
								check=false;
							}
							if(gamePlayers.getThirdPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("Third player Text field is empty");
								check=false;
							}
							if(gamePlayers.getThirdPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("Third Player Text should be at least 4 characters");	
								check=false;
							}
							if(gamePlayers.getFourthPlayerName().equals("")) {
								gamePlayers.SetErrorlbl("Fourth player Text field is empty");
								check=false;
							}
							if(gamePlayers.getFourthPlayerName().length() < 4) {
								gamePlayers.SetErrorlbl("Fourth Player Text should be at least 4 characters");	
								check=false;
							}
							validation.add(gamePlayers.getFirstPlayerName());
							validation.add(gamePlayers.getSecondPlayerName());
							validation.add(gamePlayers.getThirdPlayerName());
							validation.add(gamePlayers.getFourthPlayerName());
						}
						if(check) {
							for(int i =0 ; i < validation.size() ; i++ ) {
								for(int y=0 ; y<validation.size();y++) {
										if(i!=y) {
												if(validation.get(i).equals(validation.get(y))) {
													gamePlayers.SetErrorlbl("There are same Nicknames !!");
													check=false;
													break;
												}
										}
								}
							}
							
							
						}
						
				if(check) {
						if(numberofplayer == 2) {
							Player p1 = new Player(gamePlayers.getFirstPlayerName(),false);
							model.AddPlayerToTheGame(p1);
							Player p2 = new Player(gamePlayers.getSecondPlayerName(), false);
							model.AddPlayerToTheGame(p2);
						}
						if(numberofplayer == 3 ) {
							Player p1 = new Player(gamePlayers.getFirstPlayerName(),false);
							model.AddPlayerToTheGame(p1);
							Player p = new Player(gamePlayers.getSecondPlayerName(), false);
							model.AddPlayerToTheGame(p);
							Player q = new Player(gamePlayers.getThirdPlayerName(), false);
							model.AddPlayerToTheGame(q);
							//Don't forget to change ! 
							// this for Class show
							
						}
						if(numberofplayer == 4) {
							Player p1 = new Player(gamePlayers.getFirstPlayerName(),false);
							model.AddPlayerToTheGame(p1);
							Player p = new Player(gamePlayers.getSecondPlayerName(), false);
							model.AddPlayerToTheGame(p);
							Player q = new Player(gamePlayers.getThirdPlayerName(), false);
							model.AddPlayerToTheGame(q);
							Player d = new Player(gamePlayers.getFourthPlayerName(), false);
							model.AddPlayerToTheGame(d);
						}
					// BOARD SHOULD START HERE !!!!!!!!
						game = new GameBoard();
						game.setVisible(true);
						game.setResizable(false);
						rounds = 0;
						game.FirstInitForLabels(numberofplayer,model.getPlayers());
						game.setRollAction(setRollAction());
						game.setPlayerTurn(model.getPlayer(0));
						// ADDED EXIT GAME LISTENER
						game.setEndGameAction(setEndGameAction());
						gamePlayers.setVisible(false);
					
				}		
			}
		};
	}
	private ActionListener setBackPlayers() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gamePlayers.dispose();
				mainmenu.setVisible(true);
			}
		};
	}
	private ActionListener setEndGameAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int n = JOptionPane.showConfirmDialog(null, "Are you Sure ?","Please choose",JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {
					game.dispose();
					model.reInit();
					RemoveAllPlayers();
					mainmenu.setVisible(true);
					GameLog = "";
				}
			}
		};
	}


	private ActionListener setRollAction() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//this method should take the number of the player !!!!! ( which one is currently playing )
				// and receive the number of the sqaure ( where he should go next ) !!!!!!!!!!!!!!!! 
				// sqaure number should begin from 1 till 40 **************
				// game.initLabel("1", String.valueOf(y));
				
				//DON'T FORGET THE ROUNDS OF THE JAIL !!!!!
				int y =model.rollDice(null);
				
				
				//int y = 10;
				waiting = true;
				Player p = model.getPlayer(rounds);
				
				/*Elimented Players should be here ! ! **/
				if(PlayersOut.contains(p)) {
					
				}else {

				Player ppe = model.getPlayer(rounds+1);
				game.setPlayerTurn(ppe);
				int index = rounds%model.getNumberOfPlayers()+1;
				
				if(rounds == 18 || PlayersOut.size() == model.getNumberOfPlayers()-1) {
					Player pe = model.getWinner();
					JOptionPane.showMessageDialog(null, "Game Over !\n Player : "+pe.getName()+" is The WINNER !!! ", "YAY", JOptionPane.INFORMATION_MESSAGE);
				}else {
				
	
				if(p.isInJail() && p.getRoundsInJail()==0) {
					p.setRoundsInJail(1);
					GameLog ="The Player "+p.getName()+" In Jail he can't play ";
					game.getArea().setText(GameLog);
				}else {
				if(p.isInJail() && p.getRoundsInJail() == 1) {
					model.getOoutOfJail(p);
					p.setStrikes(0);
					game.setPlayerStrikes(index, p);
					game.outOfJail(index);
				}	
				GameLog ="The Player "+p.getName()+" has rolled : " + y + "\n";
				game.getArea().setText(GameLog);
				Square q = model.getSquare(p, y);	
				game.initLabel(String.valueOf(index), String.valueOf(q.getPos()+1),model.getNumberOfPlayers());
				game.getArea().setText(GameLog);
				switch (q.getType()) {
				case ASSET: 
					GameLog = GameLog + p.getName()+ " has moved to "+((Asset)q).getName()+" \n";
					 game.getArea().setText(GameLog);
					 	if(!((Asset)q).hasOwner()) {
							//Check if the Person has enough money ! 
							int n = JOptionPane.showConfirmDialog(null, "This Asset cost "+((Asset)q).getCost()+" with level "+((Asset)q).getPart()+" , Are you intrested in buying it ?","Please choose",JOptionPane.YES_NO_OPTION);
							if(n == JOptionPane.YES_OPTION) {
								if(p.getBalance() <  ((Asset)q).getCost() ) {
									JOptionPane.showMessageDialog(null, "You Don't Have Enought Money to Buy the Asset", "ok", JOptionPane.INFORMATION_MESSAGE);
									break;
								}else {
									Question question = model.getQuestion(((Asset)q).getPart()-1);
									showQuestionForAsset(question, p, null, q, index);
									
								}
							}
						}else {
							//case the player is the same owner 
							if(((Asset)q).getOwner().equals(p)) {
								//update the log 
								JOptionPane.showMessageDialog(null, "You have moved to your own Asset", "ok", JOptionPane.INFORMATION_MESSAGE);
		
							}else {
							
							int x = JOptionPane.showConfirmDialog(null, "Oops,This Asset has an owner : "+((Asset)q).getOwner().getName()+"\n You can either buy the asset with the price of : "+((Asset)q).getCost()*1.5+"\n or pay a fine to the owner : "+((Asset)q).getCost()*0.15+"\n will you buy the asset ?","Please choose",JOptionPane.YES_NO_OPTION);
							if(x == JOptionPane.YES_OPTION && p.getBalance() >=  ((Asset)q).getCost()*1.5) {
								
								Player oldowner = ((Asset)q).getOwner();
								
								int y2=0;
								for(;y2<model.getNumberOfPlayers();y2++) {
									if(  ((Asset)q).getOwner().equals(model.getPlayer(y2))  ) {
										break;
									}
								}
								
								p.getAssets().add(q);
								p.setBalance(-((Asset)q).getCost()*1.5);
								oldowner.setBalance(((Asset)q).getCost()*1.5);
								oldowner.removeAsset(((Asset)q));
								p.getAssets().add(q);
								((Asset)q).setOwner(p);
								
								game.updateRow(((Asset)q));
									game.setPlayerBalance(index, p);
									game.setPlayerBalance(y2+1, oldowner);
									//should update the 
									MFileWriter.writeToLogFile("Player : " + p.getName() + " has bought the " + ((Asset)q) , true);
									JOptionPane.showMessageDialog(null, "congratulation , You now own "+ ((Asset)q), "ok", JOptionPane.INFORMATION_MESSAGE);
									
									
						
								
							}else {
								
								// should add the case of now enought money 
								
								if(p.getBalance() <  ((Asset)q).getCost()*1.5) {

									JOptionPane.showMessageDialog(null, "You must pay a fine beacuse you can't afford the price of this asset", "ok", JOptionPane.INFORMATION_MESSAGE);
								}
								
								
								JOptionPane.showMessageDialog(null, "You have paied : "+((Asset)q).getCost()*0.15+"to the owner :"+((Asset)q).getOwner().getName(), "ok", JOptionPane.INFORMATION_MESSAGE);
								model.PayFine(p, ((Asset)q));
								System.out.println("the Player who paied is : "+p.getName());
								System.out.println("The player who paied Balance is : "+p.getBalance());
								System.out.println("the player who received is : "+((Asset)q).getOwner().getName());
								System.out.println("the player who received balance  is : "+((Asset)q).getOwner().getBalance());
								
								game.setPlayerBalance(index, p);
								int y1=0;
								for(;y1<model.getNumberOfPlayers();y1++) {
									if(  ((Asset)q).getOwner().equals(model.getPlayer(y1))  ) {
										break;
									}
								}
								game.setPlayerBalance(y1+1, ((Asset)q).getOwner());
								MFileWriter.writeToLogFile(p + " paid a fine is nome of " + p.payFine(((Asset)q)), true);
								
								
								
								
							}
							
					   	}
						}			 
					 	CheckIfPlayerIsOut(p,index);
					break;
				case LUCKYCARD:
					GameLog = GameLog + p.getName()+" has moved to Lucky Box \n";
					 game.getArea().setText(GameLog);
					
					showQuestionForLuckyCard( p, model.getLuckyQuestions(),index);
					CheckIfPlayerIsOut(p,index);
					break;
				case QUESTION:
					GameLog = GameLog + p.getName()+" has moved to Question Sqaure \n";
					 game.getArea().setText(GameLog);
					sf = new SelectField(model.getAllQuestionsTags());
					sf.setVisible(true);
					sf.setOkButtonListener(OkInSelectedField(index,p));
					CheckIfPlayerIsOut(p,index);
					break;
					
				case GOTOJAIL:
					GameLog = GameLog + p.getName()+" has moved to Go to jail square \n";
					 game.getArea().setText(GameLog);
					int x = JOptionPane.showConfirmDialog(null, "Oops,you have moved to JAIL you can either \n go to jail or \n pay a fine - 100,000 \n will you pay a fine ?","Please choose",JOptionPane.YES_NO_OPTION);
					if(x == JOptionPane.YES_OPTION && p.getBalance()-100000 >= -100000) {
						JOptionPane.showMessageDialog(null, "You have paied 100000 to get out of jail ", "ok", JOptionPane.INFORMATION_MESSAGE);
						p.setBalance(-100000);
						game.setPlayerBalance(index, p);
					}else {
						if(p.getBalance()-100000 < -100000) {
							//should be elemenated here !! 
							JOptionPane.showMessageDialog(null, "You have can't aford getting out of the jail ", "ok", JOptionPane.INFORMATION_MESSAGE);
						}
						JOptionPane.showMessageDialog(null, "Say hi to jail ", "ok", JOptionPane.INFORMATION_MESSAGE);
						model.sendToJail(p, false);
						game.goToJail(index);
						
					}
					CheckIfPlayerIsOut(p,index);
					break;
				case START:
					GameLog = GameLog + p.getName()+" has moved to Start square \n";
					 game.getArea().setText(GameLog);
					 CheckIfPlayerIsOut(p,index);
					 break;
				case JAIL:
					GameLog = GameLog + p.getName()+" has moved to Just a visit square \n";
					 game.getArea().setText(GameLog);
					 CheckIfPlayerIsOut(p,index);
					break;
				default:
					System.out.println("shity here");
					break;
					
				}
				//THIS SHOULD BE THE LAST THINGS IN THIS FUNCTION !!
				answerid =-1;
				
			
				}
			}
				
				
					
			}
				rounds++;
				
				
			}
			
		};
	}
	private void CheckIfPlayerIsOut(Player p,int IndexInBoard) {
		if(p.getAssets().size() == 0 && p.getBalance() <0) {
			PlayersOut.add(p);
			JOptionPane.showMessageDialog(null, "Player : "+p.getName()+" is eliminated", "ok", JOptionPane.INFORMATION_MESSAGE);
			game.eliminatePlayer(IndexInBoard);
		}
		if(p.getAssets().size() != 0 && p.getBalance() < -100000) {
			PlayersOut.add(p);
			JOptionPane.showMessageDialog(null, "Player : "+p.getName()+" is eliminated", "ok", JOptionPane.INFORMATION_MESSAGE);
			game.eliminatePlayer(IndexInBoard);
		}
		
		
	}
	
	private void showQuestionForAsset(Question q, Player p, ArrayList<Question> list,Square quare,int position){

        QuestionScreen qs = new QuestionScreen();

        ActionListener answerButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(list != null){ // dealing with multible
                    if(model.checkAnswer(qs.getAllAnswers(), list.get(0), p)){
                    	qs.initButtonGroup(!list.get(1).isMultiple());
                        qs.initLabelsText(list.get(1).getText(), model.getAnswersForQuestion(list.get(1)));                      
                    }else {
                    	  JOptionPane.showMessageDialog(qs, "Wrong answer", "Fail!", JOptionPane.ERROR_MESSAGE);
                    	qs.dispose();
                    }
                }else{
                    if(model.checkAnswer(qs.getAllAnswers(), q, p)){
                    	int n = (int) q.getDifficulty();
                    	if(n == 0) {
                    		JOptionPane.showMessageDialog(null, "congratulation , You have answered the Question Correctly !\n you get 5% discount , final price : "+((Asset)quare).getCost()*0.95, "ok", JOptionPane.INFORMATION_MESSAGE);
                    	}
                    	if(n == 1) {
                    		JOptionPane.showMessageDialog(null, "congratulation , You have answered the Question Correctly !\n you get 15% discount , final price : "+((Asset)quare).getCost()*0.85, "ok", JOptionPane.INFORMATION_MESSAGE);
                    	}
                    	if(n == 2) {
                    		JOptionPane.showMessageDialog(null, "congratulation , You have answered the Question Correctly !\n you get 25% discount , final price : "+((Asset)quare).getCost()*0.75, "ok", JOptionPane.INFORMATION_MESSAGE);
                    	}
                    	//Answer Correctly
                    	
                        if(p.buyAsset((Asset)quare, n)) {
                        	//add to the table !!
                        	game.addRow(((Asset)quare));
                        	game.setPlayerBalance(position, p);
							MFileWriter.writeToLogFile("Player : " + p.getName() + " has bought the " + ((Asset)quare) , true);
						}
                        qs.dispose();
                        
                    }else {
                    	 //Wrong answer
                        JOptionPane.showMessageDialog(null, "Wrong Answer ! , You have to pay full price :  " +((Asset)quare).getCost()+", and gained a strike !", "ok", JOptionPane.INFORMATION_MESSAGE);

						if(p.buyAsset((Asset)quare, 3)) {
							game.addRow(((Asset)quare));
								p.AddStrike();
								game.setPlayerStrikes(position, p);
								game.setPlayerBalance(position, p);
								MFileWriter.writeToLogFile("Player : " + p.getName() + " has bought the " + ((Asset)quare) , true);
								
							if(p.getStrikes() == 3) {
								 JOptionPane.showMessageDialog(null, "Oops you have reached 3 strikes , say hi to jail :)", "ok", JOptionPane.INFORMATION_MESSAGE);
								 model.sendToJail(p, true);
								 game.setPlayerStrikes(position, p);
								 game.goToJail(position);
								 
								 // add to the table !! 
							}		
							
						}
                        qs.dispose();
                    }
                }

            }
        };
        // initialize ButtonGroup if the question is a single true answer
        if(q!=null) {
        	 qs.initButtonGroup(!q.isMultiple());
        	 qs.initLabelsText(q.getText(), model.getAnswersForQuestion(q));
        }else {
        	 qs.initButtonGroup(!list.get(0).isMultiple());
        	 qs.initLabelsText(list.get(0).getText(), model.getAnswersForQuestion(list.get(0)));
        }
        qs.setAnswerBtnListener(answerButtonListener);
        qs.setVisible(true); 
    }
	
	private void StrikesJail(int PlayerIndex,Player p) {
		model.sendToJail(p, false);
		game.goToJail(PlayerIndex);
	}
	

	private ActionListener OkInSelectedField(int index,Player p) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sf.setVisible(false);
                
                int Index = sf.SelectedItemIndex();
            	Question qq = model.getQuestionByTag(model.getAllQuestionsTags().get(Index));
            	ShowQuestionForQuestion(qq,index-1,p);
				
            	
            }
        };
    }
	
	
	
	private ActionListener OpenManageQuestion() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				launchManageQuestionsScreen();
			}
		};
	}
	
	private ActionListener OpenHistory() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                launchHistoryScreen();
            }
        };
    }
	
	
	/*
	 * A small example of The Memento pattern 
	 * this function remove all the players from the system data to start new game
	 * */
	private void RemoveAllPlayers() {
		model.getPlayers().clear();
	}
	
	
	private ActionListener OpenLogout() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
               
                mainmenu.dispose();
                System.exit(1);
                
            }
        };
    }
	
	@Override
	public boolean startGame() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
