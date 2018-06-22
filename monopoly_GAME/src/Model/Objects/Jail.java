package Model.Objects;

import java.util.ArrayList;

import Utills.Type;

public class Jail extends Square{
	
	private ArrayList<Player> jailedPlayers; // list of players that in jail
	
	public Jail(int pos){
		super(pos,Type.valueOf("JAIL"));
		jailedPlayers = new ArrayList<>();
	}

	public ArrayList<Player> getJailedPlayers() {
		return jailedPlayers;
	}
	
	public boolean goToJail(Player p){
		if(!jailedPlayers.contains(p)){
			p.setInJail(true);
			p.setJailPosition(getPos());
			return jailedPlayers.add(p);
		}
		return false;
	}
	
	public boolean getOutOfJail(Player p){
		if(jailedPlayers.contains(p)){
			p.setInJail(false);
			p.setRoundsInJail(0);
			return jailedPlayers.remove(p);
		}
		return false;
	}
}
