package Utills;

public enum Type {
	
	START("START"),
	ASSET("ASSET"),
	JAIL("JAIL"),
	GOTOJAIL("GOTOJAIL"),
	LUCKYCARD("LUCKYCARD"),
	QUESTION("QUESTION");
	
	/** square type name*/
	private final String type;
	
	Type(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	
}
