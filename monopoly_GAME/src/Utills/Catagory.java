package Utills;

public enum Catagory {
	
	BROWN ("BROWN")
	,ORANGE("ORANGE")
	,RED("RED")
	,GREEN("GREEN")
	,BLUE("BLUE")
	,WHITE("WHITE")
	,PINK("PINK")
	,YELLOW("YELLOW");
	
	private final String color;
	
	Catagory(String color){
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	
	
}
