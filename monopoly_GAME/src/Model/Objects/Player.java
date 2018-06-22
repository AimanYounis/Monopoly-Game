package Model.Objects;

import java.util.ArrayList;

import Utills.Constants;

public class Player {
	
	private String name;
	private String password;
	private int strikes;
	private double balance;
	private ArrayList<Square> assets;
	private int boardPosition;
	private boolean inJail;
	private int roundsInJail;
	
	

	public Player(String name, boolean search){
		this.name =  name;
		if(!search){
			roundsInJail = 0 ;
			balance = Constants.DEFAULTBALANCE;
			assets = new ArrayList<>();
			boardPosition = 0;
			strikes = 0;
			inJail = false;
		}
	}
	public Player(String name) {
		this.name=name;
	}
	public boolean isBroke(){
		if(balance < Constants.BROKE_THRESHHOLD)
			return true;
		else if(balance < 0 && assets.isEmpty())
			return true;
		
		return false;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void AddStrike(){
		this.strikes += 1;
	}
	
	public void cleanStrikes(){
		this.strikes = 0;
	}
	
	public void setBoardPosition(int boardPosition) {
		int pos = this.boardPosition + boardPosition;
		
		this.boardPosition = pos % Constants.BOARDSIZE;
	}
	
	public double getWorth(){
		double tmp = 0;
		
		for(Square a : assets){
			tmp += ((Asset)a).getCost();
		}
		tmp += balance;
		return tmp;
	}
	
	public boolean buyAsset(Asset asset, int dif) {
		double sum = 0;
		//should be bigger
		if(balance >= asset.getCost()){
			if(!assets.contains(asset)){
				
				//Answered Question with Difficulty 1 , get 5% discount 
				if(dif == 0 ) 
					sum = asset.getCost()*0.95;
				
				//Answered Question with Difficulty 2 , get 15% discount 
				if(dif == 1) 
					sum = asset.getCost()*0.85;
				
				//Answered Question with Difficulty 3 , get 25% discount 
				if(dif == 2) 
					sum = asset.getCost()*0.75;
				
				//Same price
				if(dif == 3)
					sum = asset.getCost();
				
				//Buying from another owner
				if(dif == 4)
					sum = asset.getCost()*1.5;
				
				if(asset.hasOwner()) {
					asset.getOwner().setBalance(sum);
					asset.getOwner().removeAsset(asset);	
				}
				
				asset.setOwner(this);
				asset.setCost(sum);
				setBalance(-sum);
			
				return assets.add(asset);
			}
		}
		return false;
	}
	
	public void removeAsset(Asset asset){
		assets.remove(asset);
	}
	
	public int getNumberOfAssets(){
		return assets.size();
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public ArrayList<Square> getAssets() {
		return assets;
	}

	public int getBoardPosition() {
		return boardPosition;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(double balance) {
		this.balance += balance;
	}

	public int getStrikes() {
		return strikes;
	}

	public void setStrikes(int strikes) {
		this.strikes = strikes;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public int getRoundsInJail() {
		return roundsInJail;
	}

	public void setRoundsInJail(int roundsInJail) {
		this.roundsInJail = roundsInJail;
	}

	@Override
	public String toString() {
		return "Player name : " + name + " with balance = " + balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public boolean canLeaveJail() {
		
		return roundsInJail >= Constants.ROUNDSINJAIL;
	}

	public void setJailPosition(int pos) {
		boardPosition = pos;
	}
	
	public double payFine(Asset asset) {
		double sum = asset.getCost() * Constants.TAXRATE;
		
		setBalance(-(sum));
		asset.getOwner().setBalance(sum);
		
		return sum;
	}
	
	
	
}
