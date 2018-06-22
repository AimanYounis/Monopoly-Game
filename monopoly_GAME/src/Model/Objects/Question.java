package Model.Objects;


import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Utills.Constants;


public class Question {
	
	private long id;
	private String text;
	private boolean answered;
	private long difficulty;
	private String team;
	private boolean isMultiple;
	private ArrayList<Answer> answers;
	private ArrayList<String> tags;
	private ArrayList<Player> correctPlayers;
	private ArrayList<Player> wrongPlayers;
	public static int count = 0;

	public Question(Long id){
		this.id = id;
	}
	
	public Question(long id, String text, long difficulty, boolean isMultiple, String team){
		this.id = id;
		this.text = text;
		this.difficulty = difficulty;
		this.isMultiple = isMultiple;
		this.team = team;
		tags = new ArrayList<>();
		answers = new ArrayList<>();
		correctPlayers = new ArrayList<>();
		wrongPlayers = new ArrayList<>();
		answered = false;
		count++;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public ArrayList<Player> getCorrectPlayers() {
		return correctPlayers;
	}

	public ArrayList<Player> getWrongPlayers() {
		return wrongPlayers;
	}

	public boolean isMultiple() {
		return !isMultiple;
	}

	public void setMultiple(boolean isMultiple) {
		this.isMultiple = isMultiple;
	}

	public String getText() {
		return text;
	}

	public boolean isAnswered() {
		return answered;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	
	public ArrayList<String> getTags() {
		return tags;
	}

	public void addTags(String tags) throws Exception {
		if(!this.tags.contains(tags)){
			this.tags.add(tags);
		}else
			throw new Exception("tag wasn't added");
	}

	public boolean addAnswer(Answer answer) throws Exception{
		if(answers.size() == Constants.MAXANSWERS) // if the questioin has too many answers
			throw new Exception("question has too many anaswers"); 
		if(!answers.contains(answer))
			return answers.add(answer);
		else
			throw new Exception("answer wasn't added");
	}
	
	public boolean removeAnswer(int id) throws Exception{
		if(answers.size() == Constants.MINANSWERS) // if the questioin has few many answers
			throw new Exception("very few answers, you can't delete any.");
		
		Answer ans = new Answer(id);
		if(answers.contains(ans))
			return answers.remove(ans);
		else
			throw new Exception("answer wasn't removed");
	}
	
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public long getDifficulty() {
		return difficulty;
	}

	public String getTeam() {
		return team;
	}

	public void setDifficulty(long difficulty) throws Exception {
		if (difficulty >= Constants.MINDIFF && difficulty <= Constants.MAXDIFF)
			throw new Exception("difficulty wasn't changed due to invalid input");
		this.difficulty = difficulty;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		String str;
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for(Answer ans : answers){
			sb.append("\n"+i + ") " + ans.toString());
			
		}
		
		str = sb.toString();
		
		return text + "\n"+ str ;
	}

	public JSONObject toJSON(){
		
		JSONObject obj = new JSONObject();
		JSONArray arrt = new JSONArray(),
				arra = new JSONArray();
		
		for(Answer t : answers)
			arra.add(t.toJSON());
		
		for(String t : tags)
			arrt.add(t);
		
		obj.put("id", id);
		obj.put("text", text);
		obj.put("difficulty", difficulty);
		obj.put("isMultipleChoice", isMultiple);
		obj.put("team", team);
		obj.put("answers", arra);
		obj.put("tags",arrt);
		
		
		return obj;
		
	}

	public boolean getAnswerByText(String key) {
		for(Answer a : answers){
			if(a.getAnswer().equals(key))
				return a.isTrue();
		}
		return false;
	}
	
	
}