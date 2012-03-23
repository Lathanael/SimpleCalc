/*************************************************************************
 * Copyright (C) 2011 Philippe Leipold
 *
 * This file is part of SimpleCalc.
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
 ******************************************************************************/

package de.Lathanael.SimpleCalc.Parser;

import org.bukkit.entity.Player;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.Exceptions.MathSyntaxMismatch;

/**
* @author Lathanael (aka Philippe Leipold)
* https://github.com/Lathanael
**/
public class UnaryFunctions extends Operator {
	private String name;
	private int countArg;
	private String playerName;
	private boolean argLess;

	/**
	* Class to create a function with a name and a list of arguments
	*
	* @param name
	* @param args
	*/
	public UnaryFunctions (String name, String playerName){
		super("unFunc");
		this.name = name;
		this.playerName = playerName;
		if (name.equalsIgnoreCase("ans"))
			argLess = true;
		else if (name.equalsIgnoreCase("pi"))
			argLess = true;
		else if (name.toLowerCase().contains("spawn"))
			argLess = true;
		else if (name.toLowerCase().contains("loc"))
			argLess = true;
		else if (name.toLowerCase().contains("distance"))
			argLess = true;
		else
			argLess = false;
		countArg = 0;
	}

	public double compute (double[] args) throws MathSyntaxMismatch {
		if (name.equalsIgnoreCase("ans")) {
			try {
				double value = SimpleCalc.answer.get(playerName);
				return value;
			} catch (NullPointerException e) {
				throw new MathSyntaxMismatch("Could not retrieve 'answer' variable.");
			}
		} else if (name.equalsIgnoreCase("sin")) {
			return Math.sin(args[0]);
		} else if (name.equalsIgnoreCase("cos")) {
			return Math.cos(args[0]);
		} else if (name.equalsIgnoreCase("sqrt")) {
			return Math.sqrt(args[0]);
		} else if (name.equalsIgnoreCase("log")) {
			return Math.log10(args[0]);
		} else if (name.equalsIgnoreCase("ln")) {
			return Math.log(args[0]);
		} else if (name.equalsIgnoreCase("pi")) {
			return Math.PI;
		} else if (name.equalsIgnoreCase("spawnx")) {
			if (playerName.equalsIgnoreCase("admin"))
				return 0;
			Player p = SimpleCalc.getInstance().getServer().getPlayer(playerName);
			if (p == null)
				return 0;
			return p.getWorld().getSpawnLocation().getX();
		} else if (name.equalsIgnoreCase("locx")) {
			if (playerName.equalsIgnoreCase("admin"))
				return 0;
			Player p = SimpleCalc.getInstance().getServer().getPlayer(playerName);
			if (p == null)
				return 0;
			return p.getLocation().getX();
		} else if (name.equalsIgnoreCase("spawny")) {
			if (playerName.equalsIgnoreCase("admin"))
				return 0;
			Player p = SimpleCalc.getInstance().getServer().getPlayer(playerName);
			if (p == null)
				return 0;
			return p.getWorld().getSpawnLocation().getY();
		} else if (name.equalsIgnoreCase("locy")) {
			if (playerName.equalsIgnoreCase("admin"))
				return 0;
			Player p = SimpleCalc.getInstance().getServer().getPlayer(playerName);
			if (p == null)
				return 0;
			return p.getLocation().getY();
		} else if (name.equalsIgnoreCase("spawnz")) {
			if (playerName.equalsIgnoreCase("admin"))
				return 0;
			Player p = SimpleCalc.getInstance().getServer().getPlayer(playerName);
			if (p == null)
				return 0;
			return p.getWorld().getSpawnLocation().getZ();
		} else if (name.equalsIgnoreCase("locz")) {
			if (playerName.equalsIgnoreCase("admin"))
				return 0;
			Player p = SimpleCalc.getInstance().getServer().getPlayer(playerName);
			if (p == null)
				return 0;
			return p.getLocation().getZ();
		} else if (name.equalsIgnoreCase("distance2d")) {
			if (playerName.equalsIgnoreCase("admin"))
				return 0;
			Player p = SimpleCalc.getInstance().getServer().getPlayer(playerName);
			return Math.sqrt(Math.pow(p.getLocation().getX() - p.getWorld().getSpawnLocation().getX(),2)
					+ Math.pow(p.getLocation().getZ() - p.getWorld().getSpawnLocation().getZ(),2));
		} else if (name.equalsIgnoreCase("distance3d")) {
			if (playerName.equalsIgnoreCase("admin"))
				return 0;
			Player p = SimpleCalc.getInstance().getServer().getPlayer(playerName);
			return Math.sqrt(Math.pow(p.getLocation().getX() - p.getWorld().getSpawnLocation().getX(),2)
					+ Math.pow(p.getLocation().getZ() - p.getWorld().getSpawnLocation().getZ(),2)
					+ Math.pow(p.getLocation().getY() - p.getWorld().getSpawnLocation().getY(),2));
		} else {
			throw new MathSyntaxMismatch("Object(" + name + ") was declared as a function but could not be matched to any known function.");
		}
	}

	public void incArgCount () {
		countArg++;
	}

	public void setArgCount (int numArgs) {
		this.countArg = numArgs;
	}

	public int getArgCount () {
		return countArg;
	}

	public boolean isArgLess() {
		return argLess;
	}
}
