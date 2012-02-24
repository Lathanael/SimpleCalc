/*************************************************************************
 * Copyright (C) 2012 Philippe Leipold
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
 **************************************************************************/

package de.Lathanael.SimpleCalc.Parser;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.Exceptions.MathSyntaxMismatch;
import de.Lathanael.SimpleCalc.Tools.VariableKeys;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Variable extends Operator {
	private String name;
	private String playerName;

	public Variable(String name, String playerName) {
		super("unVar");
		this.playerName = playerName;
		this.name = name;
	}

	public double compute() throws MathSyntaxMismatch {
		if(name.equals("e"))
			return Math.E;
		try {
			VariableKeys key = new VariableKeys(playerName, name);
			double value = SimpleCalc.variables.get(key);
			return value;
		} catch (NullPointerException e) {
			throw new MathSyntaxMismatch("Failed to retrieve Variable: " + name);
		}
	}
}
