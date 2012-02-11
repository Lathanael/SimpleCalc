/*************************************************************************
 * Copyright (C) 2012 Philippe Leipold
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

package de.Lathanael.SimpleCalc.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public enum ArgLessFunctions {
	ANS("ans"),
	PI("pi");

	private ArgLessFunctions(String name) {
		this.name = name;
	}

	String name;
	private static final List<String> list = new ArrayList<String>();

	public static boolean containsFunction(String func) {
		return list.contains(func);
	}

	static {
		for (ArgLessFunctions func : values()) {
			list.add(func.name);
		}
	}
}
