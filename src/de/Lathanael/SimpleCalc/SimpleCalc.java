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

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
* @author Lathanael (aka Philippe Leipold)
* https://github.com/Lathanael
**/

public class SimpleCalc extends JavaPlugin{

	public static Logger log = Logger.getLogger("Minecraft");

	public void onDisable(){
		log.info("[SimpleCalc] disabled.");
	}

	public void onEnable(){
		log.info("[SimpleCalc] Version " + this.getDescription().getVersion() + " enabled.");
	}

	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args){

		// No arguments given or somehow args was initialised as null
		if (args.length == 0 || args == null){
			return false;
		}
		else if (args.length >=1){
			// Cocatenate the String Array if user did place whitespaces in it and remove them if needed.
			String calc = Functions.arrayConcat(args);
			// Create a new parser object and let itparse the input String
			try {
				MathExpParser eqaution = new MathExpParser(calc);
				double result = eqaution.compute();
				if (sender instanceof ConsoleCommandSender){
					log.info("[SimpleCalc] The result of your equation is: " + String.valueOf(result));
				}
				else {
					sender.sendMessage(ChatColor.GREEN + "The result of your equation is: " + String.valueOf(result));
				}
			}
			// The equation given is incorrect!
			catch(MathSyntaxMismatch mismatch){
				if (sender instanceof ConsoleCommandSender){
					log.info("[SimpleCalc] Could not parse your equation.");
				}
				else {
					sender.sendMessage(ChatColor.RED + "Could not parse your equation.");
				}
			}
			return true;
		}
		return false;
	}

}
