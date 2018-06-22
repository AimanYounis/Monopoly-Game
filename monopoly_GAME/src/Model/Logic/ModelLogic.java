package Model.Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import Model.Objects.Answer;
import Model.Objects.Asset;
import Model.Objects.Jail;
import Model.Objects.Player;
import Model.Objects.Square;
import Model.Objects.SysData;
import Model.Objects.Question;
import Utills.Constants;
import Utills.Type;
import Utills.MFileWriter;

public class ModelLogic implements I_ModelLogic {

	private static I_ModelLogic model;
	private SysData data;

	private ModelLogic() {
		data = SysData.getInstance();
	}
	//
	// PLEASE READ THIS ******************************************************************: 
	/*
	 * WE USE CONSOLE TO MAKE SURE THAT EVERYTHING IS WORKING FINE
	 * PLEASE IGNORE THE SCANNER ! IT'S FOR PERSONAL DEBUGGING 
	 * */
	public static I_ModelLogic getInstance() {
		if (model == null){
			model = new ModelLogic();
			
		}

		return model;
	}
	
	
	/*
	 * This Method return the square after the player roll the dice
	 * @param  */
	@Override
	public Square getSquare(Player p, int pos) {

		Square sq;
		
		p.setBoardPosition(pos);
		sq = data.getSquares()[p.getBoardPosition()];
		
		return sq;

	}
	/*
	 * This method return true/false if the player 
	 * bought the asset and update 
	 * all the relevant classes
	 */
/*	@Override
	public boolean buyAsset(Square sq, Player p) {
		final String MainMsg = "This asset cost : " + ((Asset) sq).getCost() + ", Are you interested in :"
							+ "\n1 - Buying this asset"
							+ "\n2 - Skipping this asset",
					playerBought = "Player : " + p.getName() + " has bought the asset",
					playerCant = "You can't buy this asset - don't have enough money",
					playerSkipped = "The Player :" + p.getName() + ", Skipped buying this asset",
					choose = "there is not such option , please choose again :";
					
		Scanner in = scannner;
		int num;
		boolean status = true;
		boolean bought = false;
		
		System.out.println(MainMsg);
		MFileWriter.writeToLogFile(MainMsg + "\n", false);
		while (status) {
			
			num = in.nextInt();
			switch (num) {
			case 1:
				if (p.getBalance() - ((Asset) sq).getCost() >= 0) {
					boolean correct = false;
					Question q;
					// find a question randomly
					while(true){
						q = getQuestion();
						if(((Asset) sq).getPart() == q.getDifficulty())
							break;
					}
					System.out.println(Constants.ANSWERQ + q);
					MFileWriter.writeToLogFile(Constants.ANSWERQ + q, false);
					if (q.isMultiple()) {
						// in case the question has muliple answers
						System.out.println(Constants.ANSWEREXAMPLE);
						
						scannner.nextLine();
						String[] ans = scannner.nextLine().split(",");
						// check each answer input
						for (String str : ans) {
							int choice = Integer.parseInt(str);
				//			correct = checkAnswer(q, choice);
							if(!correct) // found a wrong answer
								break;
						}
					} else
				//		correct = checkAnswer(q, scannner.nextInt());
					
					if (!correct) {
						p.AddStrike();
						handleWrongAnswer(q,p);
					} else 
						handleCorrectAnswer(q,p);
					
					
					bought = p.buyAsset((Asset) sq, correct);
					
					System.out.println(playerBought);
					MFileWriter.writeToLogFile(playerBought, true);
					return bought;
					
				} else {
					System.out.println(playerCant);
					MFileWriter.writeToLogFile(playerCant, true);
					return false;
				}
				
			case 2:
				System.out.println(playerSkipped);
				MFileWriter.writeToLogFile(playerSkipped, true);
				return false;
				
			default:
				System.out.println(choose);
				break;

			}
		}

		return bought;
	}*/
	
	/*
	 * This Method check if the Game is over*/
	@Override
	public boolean gameOver() {
		//only one player left 
		if (SysData.playersNum == 1){
			return true;
		}
		//Number of throws is max 
		else if (SysData.numThrows >= Constants.MAXROUNDS){
			return true;
		}
		return false;
	}
	
	//Get specific player by the index generated to him in the beginning of the game
	@Override
	public Player getPlayer(int i) {
		return data.getPlayers().get(i % data.getPlayers().size());
	}
	
	public int getPlayerPosition(int i) {
		return (i % data.getPlayers().size());
	}
	/**
	 * roll the dice and return next int
	 */
	@Override
	public int rollDice(Player p) {
		Random rand = new Random();
		int x = rand.nextInt(Constants.DICE) + 1, y = rand.nextInt(Constants.DICE) + 1;
		int num = x + y;
		if(p != null){
			System.out.println(p + " rolled " + x + " and " + y);
			MFileWriter.writeToLogFile(p + " rolled " + x + " and " + y + "\n", false);
			SysData.numThrows++;
		}
		return num;

	}
	
	/*
	 *This method move the player from his position to the jail , if he got 3 strikes then the boolean with be true
	 *if he went to gotojail sqaure he moved to jail and the boolean set to false
	 **/
	@Override
	public void sendToJail(Player p, boolean Strikes) {
		final String playerArrested = "Player " + p.getName() + " got arrested";
		// Check if the reason was Number of strikes
		if (Strikes) {
			

			for (Square s : data.getSquares()) {
				if (s.getType().equals(Type.valueOf("JAIL"))) {
					if (((Jail) s).goToJail(p)){
			//			System.out.println(playerArrested);
						MFileWriter.writeToLogFile(playerArrested, true);
					}
				}
			}
			p.cleanStrikes();
			
			
		} else {
			
			final int fine = 100000;
			final String Main = "Please Choose an option : \n"
								+ "1 - pay 100,000 and skip going to jail \n"+
								"2 - go to jail !",
						playerToJail = "You can't pay the fine so you are going to Jail !!!",
						pay = "You have payed a fine of "+fine;
			
			if (p.getBalance() - fine < Constants.BROKE_THRESHHOLD) {
				System.out.println(playerToJail);
				MFileWriter.writeToLogFile(playerToJail, true);
			} else {
				
						for (Square s : data.getSquares()) {
							if (s.getType().equals(Type.valueOf("JAIL"))) {
								if (((Jail) s).goToJail(p)){
									MFileWriter.writeToLogFile(playerArrested, true);
								}
							}
						}
				
				}
			}
		}
	
	@Override
	public void eliminate(Player p) {
		SysData.playersNum--;
		MFileWriter.writeToLogFile(p + " got eliminated \n" , true);
		
	}

	
	/*
	 * Set the player free*/
	@Override
	public void getOoutOfJail(Player p) {
		final String out = "Player " + p.getName() + " got out of jail";
		for (Square s : data.getSquares())
			if (s.getType().equals(Type.valueOf("JAIL"))) {
				if (((Jail) s).getOutOfJail(p)){
			//		System.out.println(out);
					MFileWriter.writeToLogFile(out + "\n", false);
				}
			}
	}
	public void reInit() {
		data.initBoard();
	}
	/*
	 * If one of the players has been moved to asset which owned by another player*/
	@Override
	public void payToOwner(Player p, Asset a) {
		/*final String Main = "Please Choose an option : "+
				"\n1 - buying this asset from :"+a.getOwner().getName()+", price : "+a.getCost()*1.5
				+ "\n2 - Pay a fine with the cost of : "+a.getCost()*0.15,
				playerBought = "Player "+p.getName()+" has bought the asset";
		
		int num;
		boolean status = true;

		while(status){
			System.out.println(Main);
			MFileWriter.writeToLogFile(Main + "\n", false);
			
			num = in.nextInt();
			switch(num){
			case 1 : 
				//previous owner balance update
				a.getOwner().setBalance(a.getCost()*1.5);
				// update asset cost to 1.5 of the original cost
				a.setCost(a.getCost()*1.5);
				// player buy asset
				p.buyAsset(a, false);
				System.out.println(playerBought);
				MFileWriter.writeToLogFile(playerBought, true);
				status = false;
				break;
			case 2: 
				PayFine(p,a);
				if(p.isBroke()) {
					eliminate(p);
				}
				status = false;
				break;
			default:
				System.out.println("there is not such option , please choose again :");
				break;
			}
		}*/
	}
	
	@Override
	public void PayFine(Player p , Asset a ){
		
		final String payFine = "PLayer "+p.getName()+" has payed a fine",
				     must = p.getName() + "doesn't  have the option of buying this asset because of the balance.\n"+
						p.getName() + " must pay a fine"; 
		
		MFileWriter.writeToLogFile(must + "\n", false);
		//update the owner balance
		a.getOwner().setBalance(a.getCost()*0.15);
		//update the payer balance 
		p.setBalance(-a.getCost()*0.15);
		MFileWriter.writeToLogFile(payFine, true);
	}
	
	/*This Method check if the player can Buy the asset from the owner*/
	@Override
	public boolean CanBuyFromOwner(Player p, Asset a){
		if(p.getBalance() - a.getCost()*1.5 >= 0){
			return true;
		}
		return false;
	}
	
	@Override
	public int NumberOfPlayers(){
		return data.GetPlayersNum();
	}
	
	@Override
	public int NumberOfAssetsForPlayer(Player p){
		return p.getAssets().size();
	}
	
	@Override
	public Player getWinner() {
		ArrayList<Player> pl = new ArrayList<>();
		
		for(Player p : data.getPlayers())
			if(!p.isBroke())
				pl.add(p);
		
		if(pl.size() > 1){  // if there are more than one player that is not broke
			pl = data.getPlayers();
			Collections.sort(pl, new Comparator<Player>(){

				@Override
				public int compare(Player arg0, Player arg1) {
					if(arg0.getWorth() > arg1.getWorth())
						return -1;
					else if (arg0.getWorth() < arg1.getWorth())
						return 1;
					
					return 0;
				}
				
			});
		}
		System.out.println(pl);
		
		return pl.get(0);
	}
	
	@Override
	public void answerLuckyCardQuestions(Player p) {
		/*int k = 1;
		final String luck = "Player " + p.getName() + " got a lucky card";
		boolean check = false;
		
		System.out.println(luck);
		MFileWriter.writeToLogFile(luck + "\n", false);
		
		ArrayList<Question> Qs = getLuckyQuestions();
		
		for (Question q : Qs) {
			System.out.println(Constants.ANSWERQ + q);
			MFileWriter.writeToLogFile(Constants.ANSWERQ + q + "\n" , false);
			if (q.isMultiple()) {
				// in case the question has muliple answers
				System.out.println(Constants.ANSWEREXAMPLE);
				String[] ans = line.split(",");
				// check each answer input
				for (String str : ans) {
					int choice = Integer.parseInt(str);
			//		check = checkAnswer(q, choice);
					if(!check) // found a wrong answer
						break;
				}
			} else
			//	check = checkAnswer(q, scannner.nextInt());

			if (!check) {
				luckyCardFine(p, ((int) 50000 / k), k);
				handleWrongAnswer(q, p);
				k--;
				break;
			} else
				handleCorrectAnswer(q, p);
				
			k++;
		}
		if(k == 3){
			System.out.println("Player " + p.getName() + "answered all questions correctly");
			MFileWriter.writeToLogFile("Player " + p.getName() + "answered all questions correctly" + "\n", false);
			
		}else if (k == 1){
			System.out.println("Player " + p.getName() + " only first questions correctly");
			MFileWriter.writeToLogFile("Player " + p.getName() + " only first questions correctly" + "\n", false);
		}else if(k == 0){
			System.out.println("Player " + p.getName() + "answered all questions wrong");
			MFileWriter.writeToLogFile("Player " + p.getName() + "answered all questions wrong" + "\n", false);
		}
		*/
	}
	
	/*		@Override
	public void answerQuestionCard(Player p) {
		Question q = getQuestion();
		boolean check = false;
		System.out.println(p.getName() + " got a question card.");
		MFileWriter.writeToLogFile(p.getName() + " got a question card.\n", false);
		
		System.out.println(Constants.ANSWERQ + q);
		MFileWriter.writeToLogFile(Constants.ANSWERQ + q + "\n", false);
		
		if (q.isMultiple()) {
			// in case the question has muliple answers
			System.out.println(Constants.ANSWEREXAMPLE);
			String line = scannner.nextLine();
			String[] ans = line.split(",");
			// check each answer input
			for (String str : ans) {
				int choice = Integer.parseInt(str);
		//		check = checkAnswer(q, choice);
				if(!check) // found a wrong answer
					break;
			}

		} else
		//	check = checkAnswer(q, scannner.nextInt()); // get checking result

		if (!check) {
			if(q.getWrongPlayers().isEmpty())
				p.setBalance(-50000);
			
			if(p.isBroke()) {
				eliminate(p);
			}
			else{
				p.AddStrike();
			
				handleWrongAnswer(q, p);
			
				// check for strikes and act accordingly
				if (p.getStrikes() == Constants.MAXSTRIKES)
					sendToJail(p, true);
			}
		} else{
			// if the player answered correctly
			// handle each state
			// if all players, some or none answered correclty
			if(q.getCorrectPlayers().isEmpty() && (q.getWrongPlayers().size() == SysData.playersNum-1)){
				p.setBalance(250000);
				
			}else if((!q.getCorrectPlayers().isEmpty()) && (q.getCorrectPlayers().size() != SysData.playersNum-1)){
				p.setBalance(50000);
				
				for(Player other : q.getCorrectPlayers()){
					other.setBalance(75000);
				}
				
			}else if(q.getCorrectPlayers().size() == SysData.playersNum-1){
				
				for(Player other : q.getCorrectPlayers()){
					other.setBalance(10000);
				}
			}
			handleCorrectAnswer(q, p);
			
			// add strike to each player who got this question wrong
			for(Player other : q.getWrongPlayers()){
				other.AddStrike();
				
				if (other.getStrikes() == Constants.MAXSTRIKES){
					if(!other.isInJail())
						sendToJail(other, true);
				}
			}
		}
	}*/
	
	@Override
	public String handleHistoryScreen(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		StringBuilder sb;
		
		try {
			File dir = new File("Logs");
		
			if (dir.exists()) {
				String line;
					
				sb = new StringBuilder();
				fr = new FileReader(file);
				br = new BufferedReader(fr);
					
				while((line = br.readLine()) != null){
					sb.append(line+"\n");
				}
				
				return sb.toString();
			
			} else 
				System.out.println("There arae no log files.");
			
			return "";
			
		} catch (IOException e) {
			return "";
			
		}finally{
			try{
				if (fr != null)
					fr.close();
				if(br != null)
					br.close();
				
			}catch(IOException e){
				return "";
			}
		}
	}
	
	
	@Override
	public void writeQuestionsToFile(){
		data.writeQuestionToDisk();
	}
	
	/**
	 * add question to database
	 * @return if addtion was successful
	 */
	@Override
	public boolean addQuestion(long id, String question, Map<String,Boolean> map, boolean multi, int diff, String[] tags, String team) {
			Question q;
			int i = 1;
			try{
				if(id >= 0)
					q = new Question(id, question, diff, multi, team);
				else
					q = new Question(Question.count+1, question, diff, multi, team);
	
				for(Map.Entry<String, Boolean> m : map.entrySet()){
					q.addAnswer(new Answer(i, m.getKey(), m.getValue()));
					i++;
				}
				for(String str : tags){
					q.addTags(str);
				}
				return data.addQuestion(q);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				return false;
			}
	}
	/**
	 * remove question from database
	 */
	@Override
	public boolean removeQuestion(long id) {
		
		return data.removeQuestionById(id);
		
	}
	
	/**
	 * edit question and all of it's aspects
	 */
	@Override
	public boolean editQuestion(long id, String question, Map<String,Boolean> map, boolean multi, int diff, String[] tags, String team) {
		
		try {
			if(data.removeQuestionById(id)){
				return addQuestion(id, question, map, multi, diff, tags, team);
			}
			return false;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	private void handleWrongAnswer(Question q, Player p){
		final String ans = "Question wasn't answered correctly.";
		System.out.println(ans);
		MFileWriter.writeToLogFile(ans + "\n", false);
		
		// if the player answered the question agian 
		//and was wrong remove him from the list of players 
		//who answered correctly and add him to the other list
		if(!q.getWrongPlayers().contains(p))
			q.getWrongPlayers().add(p);
		
		if(q.getCorrectPlayers().contains(p))
			q.getCorrectPlayers().remove(p);

		
	}
	
	private void handleCorrectAnswer(Question q, Player p){
		// if the player answered the question agian 
		//and was correct remove him from the list of players 
		//who answered wrong and add him to the other list
		final String ans = "Question was answered correctly.";
		System.out.println(ans);
		MFileWriter.writeToLogFile(ans + "\n", false);
		
		if(q.getWrongPlayers().contains(p))
			q.getWrongPlayers().remove(p);
		
		if(!q.getCorrectPlayers().contains(p))
			q.getCorrectPlayers().add(p);

	}
	
	// get questions for luck cards
	@Override
	public ArrayList<Question> getLuckyQuestions(){
		
		Question q1;
		Question q2;
		boolean check1=true;
		boolean check2=true;
		ArrayList<Question> list = new ArrayList<>();
		
		while(true){
			q1 = getQuestion((long) 1);
			q2 = getQuestion((long) 2);
			
			if(!list.contains(q1) && check1 ){
				list.add(q1);
				check1=false;
			}
			if(!list.contains(q2) && check2) {
				list.add(q2);
				check2=false;
			}
			if(list.size() == 2)
				return list;
		}
	}
	
	@Override
	public boolean checkAnswer(Map<String, Boolean> map, Question q, Player p) {
		int i = 0;
		for(Map.Entry<String, Boolean> mp : map.entrySet()){
			if(!mp.getValue().equals(q.getAnswerByText(mp.getKey()))){
				handleWrongAnswer(q, p);
				return false;
			}
		}
		handleCorrectAnswer(q, p);
		return true;
	}
	
	public Question getQuestion(Long difficultyone){
		Question q=null;
		//Random r = new Random();
		
		int size = data.getQuestions().size();
		Random r = new Random();
		//int index = r.nextInt(size);
		while(true) {
			int index = r.nextInt(size);
			q = data.getQuestions().get(index);
			if(q.getDifficulty() == difficultyone) {
				break;
			}
		}
		
		return q;
	}
	
	private void luckyCardFine(Player p, int fine, int doStrike) {
		p.setBalance(-fine);
		
		if(p.isBroke()) {
			eliminate(p);
		}else if(doStrike == 1)
			p.AddStrike();
		
	}

	@Override
	public long[] getQuestionIds() {
		long[] arr = new long[data.getQuestions().size()];
		for(int i = 0; i < arr.length; i++)
			arr[i] = (i+1);
		return arr;
	}

	
	@Override
	public void AddPlayerToTheGame(Player p) {
		data.AddPlayerToTheGame(p);	
	}
	
	@Override
	public int getNumberOfPlayers() {
		return data.GetPlayersNum();
	}
	
	@Override
	public ArrayList<String> getAllQuestionsTags() {
		return data.getAllTags();
	}
	

	
	@Override
	public ArrayList<Player> getPlayers(){
		return data.getPlayers();
	}
	
	@Override
	public ArrayList<String> getAnswersForQuestion(Question q) {
		ArrayList<String> ans = new ArrayList<>();
		for(Answer a : q.getAnswers()){
			ans.add(a.getAnswer());
		}
		return ans;
	}
	
	@Override
	public boolean buyAsset(Square sq, Player p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void answerQuestionCard(Player p) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Question getQuestionByTag(String tag) {
        for(Question q : data.getQuestions()) {
            if(q.getTags().contains(tag)) {
                return q;
            }
        }
        return null;
    }
	
	@Override
	public Question getQueationById(long slected) {
		return data.getQuestionById(slected);
	}
	
}
