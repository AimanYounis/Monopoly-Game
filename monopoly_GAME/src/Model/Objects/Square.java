package Model.Objects;

import Utills.Type;

// this class represnts the board's squares
/*
 * Represent the factory design pattern*/
public class Square {
	
	private final Type type;
	private final int pos;
	
	public Square(int pos, Type type){
		this.pos = pos;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public int getPos() {
		return pos;
	}
	
	
}
