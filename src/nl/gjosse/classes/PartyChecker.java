package nl.gjosse.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PartyChecker {
	
	static ArrayList<Party> all = new ArrayList<Party>();
	static File save = new File("plugins/Partys/party.dat");

	public static void savePartys()
	{
		if(!save.exists())
		{
			try {
				save.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileIO.save(all, save);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadPartys()
	{
		if(save.exists())
		{
		try {
			all = (ArrayList<Party>) FileIO.load(save);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	
	public static boolean isInParty(String player)
	{
		for(Party i : all)
		{
			if(i.inParty(player))
			{
				return true;
			}
		}
		return false;
		
	}
	
	public static Party PlayerParty(String player)
	{
		for(Party i : all)
		{
			for(String name : i.PlayersIn)
			{
				if(name.equalsIgnoreCase(player))
				{
					return i;
				}
			}
		}
		return null;
	}
	
	public static Party findParty(String name)
	{
		for(Party I : all)
		{
			if(I.name.equalsIgnoreCase(name))
			{
				return I;
			}
		}
		return null;
	}
	
	
	public static boolean createParty(String player)
	{
		if(isInParty(player))
		{
			return false;
		}
		
		Party newParty = new Party(player);
		all.add(newParty);
		return true;
	}
	
	public static int addPlayer(String player, String name)
	{
		if(isInParty(player))
		{
			return 1;
		}
		
		Party findParty = findParty(name);
		if(findParty==null)
		{
			return 2;
		}
		
		findParty.addPlayer(player);
		return 0;
		
		
	}
	
	public static void removeParty(String player)
	{
		if(isInParty(player))
		{
			Party party = PlayerParty(player);
			all.remove(party);
		}
		
	}

}
