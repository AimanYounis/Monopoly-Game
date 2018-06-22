package Model.Objects;

import org.json.simple.JSONObject;

public class Answer {
	
	private int id; // added to identify the answers
	private String answer;
	private boolean isTrue;
	
	public Answer(int id) {
		this.id = id;
	}

	public Answer(int id, String answer, boolean isTrue) {
		this.id  = id;
		this.answer = answer;
		this.isTrue = isTrue;
	}
	
	public String getAnswer() {
		return answer;
	}
	public boolean isTrue() {
		return isTrue;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Answer other = (Answer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  answer;
	}

	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("text", answer);
		obj.put("isCorrect", isTrue);
		return obj;
	}
	
	
}
