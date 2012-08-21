package nl.gjosse.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Party implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2113123897425098048L;
	
	
	ArrayList<String> PlayersIn = new ArrayList<String>();
	int Size;
	int MaxSize;
	String admin;
	String name;
	
	public Party(String player) {
		addPlayer(player);
		this.admin = player;
		this.name = player +"'s Party";
	}

	public void addPlayer(String player)
	{
		PlayersIn.add(player);
	}
	
	public boolean inParty(String i)
	{
		for(String player : PlayersIn )
		{
			if(i.equalsIgnoreCase(player))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getName(){
		return name;
	}
	public String getAdmin()
	{
		return admin;
	}
	
	public void removePlayer(String player)
	{
		PlayersIn.remove(player);
	}
	

}
