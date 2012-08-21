package nl.gjosse;

import java.io.File;
import java.util.logging.Logger;

import nl.gjosse.classes.Party;
import nl.gjosse.classes.PartyChecker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Partys extends JavaPlugin {
	
	public static final Logger log= Logger.getLogger("Minecraft");

	public void onEnable()
	{
		File save = new File("plugins/Partys");
		if(!save.exists())
		{
			save.mkdir();
		}
		
		log.info("Party's Enabled");
		PartyChecker.loadPartys();
	
	}
	
	public void onDisable()
	{
		log.info("Party's Disabled");
		PartyChecker.savePartys();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("party")&&args[0].equalsIgnoreCase("create"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				if(sender.hasPermission("Party.create")){
					boolean createParty = PartyChecker.createParty(player.getName());
					if(!createParty)
					{
						sender.sendMessage(ChatColor.RED+"Something went wrong with making your party");
					}
					else
					{
						sender.sendMessage(ChatColor.GOLD+"Party Made!");
					}
				}
			}
			return true;
		}
		if(commandLabel.equalsIgnoreCase("party")&&args[0].equalsIgnoreCase("check"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				boolean inParty = PartyChecker.isInParty(player.getName());
			if(!inParty)
			{
				sender.sendMessage(ChatColor.RED+"You are not in a party");
			}
			else
			{
				String name = PartyChecker.PlayerParty(player.getName()).getName();
				sender.sendMessage(ChatColor.GOLD+"You are in : "+ChatColor.GREEN+name);
			}
			
			}
			return true;
		}
		
		if(commandLabel.equalsIgnoreCase("party")&&args[0].equalsIgnoreCase("join")&&args.length==2)
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(!PartyChecker.isInParty(player.getName()))
				{
				String partyname = args[1];
				Party Party = PartyChecker.PlayerParty(partyname);
				if(Party!=null)
				{
					Party.addPlayer(player.getName());
					sender.sendMessage(ChatColor.GREEN+"You have joined the party!");
				}
				else
				{
					sender.sendMessage(ChatColor.RED+"Party Not Found!");
				}
				}
				else
				{
					sender.sendMessage(ChatColor.RED+"You are already in a party!");
				}
			}
			return true;
		}
		
		if(commandLabel.equalsIgnoreCase("party")&&args[0].equalsIgnoreCase("leave"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				if(PartyChecker.isInParty(player.getName()))
				{
					Party playerParty = PartyChecker.PlayerParty(player.getName());
					if(playerParty.getAdmin().equalsIgnoreCase(player.getName()))
					{
						sender.sendMessage(ChatColor.RED+"You cant leave this party! Only remove it!");
					}
					else
					{
					playerParty.removePlayer(player.getName());
					sender.sendMessage(ChatColor.GREEN+"You left the party!");
					}
				}
			}
			return true;
		}
		
		if(commandLabel.equalsIgnoreCase("party")&&args[0].equalsIgnoreCase("remove"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player)sender;
				boolean inParty = PartyChecker.isInParty(player.getName());
				if(inParty)
				{
				Party part = PartyChecker.PlayerParty(player.getName());
				if(part.getAdmin().equalsIgnoreCase(player.getName()))
				{
					PartyChecker.removeParty(player.getName());
					sender.sendMessage(ChatColor.GREEN+"Removed!");					
				}
				else
				{
					sender.sendMessage(ChatColor.GREEN+"You can't remove this party!");
				}
				}
			}
		return true;	
		}
		return false;
	}

}
