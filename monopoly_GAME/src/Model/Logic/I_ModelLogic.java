package Model.Logic;


import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import Model.Objects.Asset;
import Model.Objects.Player;
import Model.Objects.Question;
import Model.Objects.Square;


public interface I_ModelLogic {
	
	public Square getSquare(Player p, int pos);
	public int rollDice(Player p);
	public boolean gameOver();
	public Player getPlayer(int i);
	public void sendToJail(Player p, boolean Strikes);
	public void getOoutOfJail(Player p);
	public boolean buyAsset(Square sq, Player p);
	public void payToOwner(Player p, Asset a);
	public void PayFine(Player p,Asset a);
	public int NumberOfPlayers();
	public int NumberOfAssetsForPlayer(Player p);
	public boolean CanBuyFromOwner(Player p, Asset a);
	public void answerLuckyCardQuestions(Player p);
	public void answerQuestionCard(Player p);
	public String handleHistoryScreen(File file);
	public void writeQuestionsToFile();
	public Player getWinner();
	public int getPlayerPosition(int i);
	public void AddPlayerToTheGame(Player p);
	public int getNumberOfPlayers();
	public ArrayList<Player> getPlayers();
	public Question getQuestion(Long difficultyone);
	public ArrayList<String> getAllQuestionsTags();
	public Question getQuestionByTag(String tag);
	public void reInit();
	public boolean addQuestion(long id, String question, Map<String,Boolean> map, 
			boolean multi, int diff, String[] tags, String team); //changed
	public long[] getQuestionIds();
	boolean removeQuestion(long id);
	public ArrayList<String> getAnswersForQuestion(Question q);
	boolean checkAnswer(Map<String, Boolean> map, Question q, Player p);
	ArrayList<Question> getLuckyQuestions();
	boolean editQuestion(long id, String question, Map<String, Boolean> map, boolean multi, int diff, String[] tags,
			String team);
	public Question getQueationById(long slected);
	void eliminate(Player p);

	
}
