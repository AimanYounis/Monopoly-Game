package Model.Objects;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Utills.Catagory;
import Utills.Constants;
import Utills.Type;
import au.com.bytecode.opencsv.CSVReader;

public class SysData {
	
	private static SysData data;
	private Square[] squares;
	private ArrayList<Question> questions;
	private ArrayList<Player> players;
	private ArrayList<String> tags;
	public static int playersNum;
	public static int numThrows;


	
	private SysData(){
		tags=new ArrayList<>();
		initQuestions();
		InitPlayers();
		initData();
		getTags();
	}
	
	public static SysData getInstance(){
		if(data == null)
			data = new SysData();
		
		return data;
	}
	
	private void initData() {
		initBoard();
		numThrows = 0;
	
		
	}

	private void InitPlayers() {
		players =new ArrayList<>();
	}
	

	
	public void AddPlayerToTheGame(Player p) {
		players.add(p);
	}

	
	public boolean writeQuestionToDisk(){
		
		JSONArray ja = new JSONArray();
		JSONObject job = new JSONObject();
		Writer writer = null;
		
		try {
			writer = new FileWriter(new File("questions.json"));
			for(Question q : questions){
				ja.add(q.toJSON());
			}
			
			job.put("questions", ja);
			writer.write(job.toJSONString());
			
		} catch (IOException e) {

			e.printStackTrace();
			return false;
			
		}finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public boolean initQuestions(){
		
		questions = new ArrayList<>();
		int counter = 0;
		JSONArray jArr, answers, str;
		JSONObject jObj;
		String strs;
		Question q;
		Answer answer;
		JSONParser parser = new JSONParser();

		try {
			jObj = (JSONObject) parser.parse(new FileReader("questions.json")); // load json gile 
			jArr = (JSONArray) jObj.get("questions"); // get main array
			
			/* parse array */
			Iterator<JSONObject> iterator = jArr.iterator();
			while (iterator.hasNext()) {
				counter = 0;
				jObj = iterator.next();
				System.out.print((Boolean) jObj.get("isMultipleChoice"));
				q = new Question((long) jObj.get("id"), (String) jObj.get("text"), (long) jObj.get("difficulty"),
						(Boolean) jObj.get("isMultipleChoice"), (String) jObj.get("team"));

				str = (JSONArray) jObj.get("tags");
				
				Iterator<String> tags = str.iterator();
				while (tags.hasNext()) {
					try {
						q.addTags(tags.next());
					} catch (Exception e) {
						return false;
					}
				}
				
				answers = (JSONArray) jObj.get("answers");
				Iterator<JSONObject> ansIt = answers.iterator();
				while (ansIt.hasNext()) {

					jObj = ansIt.next();
					answer = new Answer(counter+1, (String) jObj.get("text"), (Boolean) jObj.get("isCorrect"));
					try{
						q.addAnswer(answer);
					}catch(Exception e){
						return false;
					}
					counter++;
				}
				
				questions.add(q);
				
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean initBoard(){
		int i = 0;
		squares = new Square[Constants.BOARDSIZE];
		
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader("board.csv"));
			String[] line;
			boolean fail = false;

			while ((line = reader.readNext()) != null) {
				switch(line[0]){
				case "START": case "GOTOJAIL": case "LUCKYCARD": case "QUESTION":
					squares[i] = new Square(i,Type.valueOf(line[0]));
					break;
				case "JAIL":
					squares[i] = new Jail(i);
					break;
				case "ASSET":
					squares[i] = new Asset(i, line[1], Catagory.valueOf(line[3]), Long.valueOf(line[2]), Long.valueOf(line[4]));
					break;
				}
				i++;
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			return false;
		} finally {
			try {
				reader.close();
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
				return false;
			}
		}
		return true;
	}
	
	public boolean addQuestion(Question q){
		if(!questions.contains(q)){
			return questions.add(q);
		}
		return false;
	}
	
	public int GetPlayersNum(){
		return players.size();
	}
	
	public Square[] getSquares() {
		return squares;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setSquares(Square[] squares) {
		this.squares = squares;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public Question getQuestionById(long id) {
		Question q = new Question(id);
		int index = questions.indexOf(q);
		
		if(index >= 0){ // found
			return questions.get(index);
		}
		return null;
	}
	
	public boolean removeQuestionById(long id) {
		Question q = new Question(id);
		int index = questions.indexOf(q);
		
		if(index >= 0){ // found
			return questions.remove(q);
		}
		return false;
	}
	
	private void getTags(){
		for(Question q : questions) {
			for(String e : q.getTags()) {
				if(!tags.contains(e)) {
					tags.add(e);
				}
			}
		}
	}
	
	public ArrayList<String> getAllTags(){
		return tags;
	}
	
	
}
