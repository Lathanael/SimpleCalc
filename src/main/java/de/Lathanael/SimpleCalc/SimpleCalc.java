/***************************************************************************
 * Copyright (C) 2011  Philippe Leipold
 *
 * SimpleCalc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SimpleCalc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SimpleCalc. If not, see <http://www.gnu.org/licenses/>.
 *
 ***************************************************************************/

package de.Lathanael.SimpleCalc;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.player.SpoutPlayer;

import de.Lathanael.SimpleCalc.Exceptions.MathSyntaxMismatch;
import de.Lathanael.SimpleCalc.Listeners.SCPluginListener;
import de.Lathanael.SimpleCalc.Listeners.SCPlayerListener;
import de.Lathanael.SimpleCalc.Listeners.SCSpoutScreenListener;
import de.Lathanael.SimpleCalc.Parser.MathExpParser;
import de.Lathanael.SimpleCalc.Tools.Functions;
import de.Lathanael.SimpleCalc.gui.CalcWindow;

/**
* @author Lathanael (aka Philippe Leipold)
* https://github.com/Lathanael
**/

public class SimpleCalc extends JavaPlugin{

	public static Logger log;
	public static PluginManager pm;
	private static SimpleCalc instance;
	private static Map<SpoutPlayer, CalcWindow> popups = new HashMap<SpoutPlayer, CalcWindow>();
	public static Map<String, Double> answer = new HashMap<String, Double>();
	private static SCPluginListener SCPluginListener = new SCPluginListener();
	private static SCPlayerListener SCPlayerListener;
	private static DecimalFormat format = new DecimalFormat("#0.00");

	public void onDisable(){
		log.info("Version " + this.getDescription().getVersion() + " disabled.");
	}

	public void onEnable(){
		instance = this;
		log = getLogger();
		SCPlayerListener = new SCPlayerListener(this);
		pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(SCPlayerListener, this);
		pm.registerEvents(SCPluginListener, this);
		SCPluginListener.spoutHook(pm);
		if (SCPluginListener.spout != null){
			pm.registerEvents(new SCSpoutScreenListener(this), this);
		}
		log.info("Version " + this.getDescription().getVersion() + " enabled.");
	}

	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args){

		// args was initialised as null
		if (args == null)
			return false;
		// No arguments given open clac window if Spout is enabled
		if (args.length == 0){
			if (sender instanceof ConsoleCommandSender)
				return false;
			if (SCPluginListener.spout != null){
				((SpoutPlayer) sender).closeActiveWindow();
				openWindow((SpoutPlayer) sender);
				return true;
			}
			else {
				return false;
			}
		}
		else if (args.length >=1){
			// Cocatenate the String Array if user did place whitespaces in it and remove them if needed.
			String calc = Functions.arrayConcat(args);
			// Create a new parser object and let itparse the input String
			try {
				MathExpParser equation = null;
				double result = 0;
				if (sender instanceof ConsoleCommandSender) {
					equation = new MathExpParser(calc, "Admin");
					result = equation.compute();
					answer.put("Admin", result);
				}
				else {
					equation = new MathExpParser(calc, ((Player) sender).getName());
					result = equation.compute();
					answer.put(((Player) sender).getName(), result);
				}

				if (sender instanceof ConsoleCommandSender){
					log.info("The result of your expression is: " + format.format(result));
				}
				else {
					sender.sendMessage(ChatColor.GREEN + "The result of your expression is: " + format.format(result));
				}
			}
			// The equation given is incorrect!
			catch(MathSyntaxMismatch mismatch){
				if (sender instanceof ConsoleCommandSender){
					log.info(" " + mismatch.getMessage());
				}
				else {
					sender.sendMessage(ChatColor.RED + mismatch.getMessage());
				}
			}
			return true;
		}
		return false;
	}

	public static SimpleCalc getInstance() {
		return instance;
	}

	public void openWindow(SpoutPlayer player) {
		CalcWindow popup = null;
		if (!popups.containsKey(player)) {
			popups.put(player, new CalcWindow(player, getInstance()));
		}
		popup = popups.get(player);
		popup.open();
	}

	public void removePopup(SpoutPlayer player) {
		popups.remove(player);
	}

	public void closeWindow(SpoutPlayer player, boolean remove) {
		CalcWindow popup = null;
		if (!popups.containsKey(player)) {
			log.info("No window for " + player.getName() + " was found!");
			return;
		}
		popup = popups.get(player);
		popup.close();
		if (remove)
			removePopup(player);
	}
}
