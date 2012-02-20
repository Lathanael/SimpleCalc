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
		else if (name.equalsIgnoreCase("ans"))
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

	 public String toString () {
		return name;
	}
}
