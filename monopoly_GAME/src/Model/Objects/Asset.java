package Model.Objects;

import Utills.Catagory;
import Utills.Type;

public class Asset extends Square{
	
	private Catagory catagory;
	private String name;
	private long part;
	private double cost;
	private Player owner;
	
	
	public Asset(int pos, String name, Catagory catagory, long part, long cost) {
		super(pos, Type.valueOf("ASSET")); // init type of square
		this.name = name;
		this.catagory = catagory;
		this.part = part;
		this.cost = cost;
	}
	//Trying some shit
	public boolean hasOwner() {
		return owner != null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Catagory getCatagory() {
		return catagory;
	}

	public long getPart() {
		return part;
	}

	public double getCost() {
		return cost;
	}

	public Player getOwner() {
		return owner;
	}

	public void setCatagory(Catagory catagory) {
		this.catagory = catagory;
	}

	public void setPart(long part) {
		this.part = part;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	

	@Override
	public String toString() {
		return "Asset "+ name + " " + catagory + ", part: " + part;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catagory == null) ? 0 : catagory.hashCode());
		result = (int) (prime * result + part);
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
		Asset other = (Asset) obj;
		if (catagory != other.catagory)
			return false;
		if (part != other.part)
			return false;
		return true;
	}

	
	
	
	
}
