/*************************************************************************
 * Copyright (C) 2011  Philippe Leipold
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

package de.Lathanael.SimpleCalc;

/**
* @author Lathanael (aka Philippe Leipold)
* https://github.com/Lathanael
**/

public class Functions {

	@SuppressWarnings("unused")
	private static SimpleCalc plugin;

	// constructors
	public Functions (SimpleCalc plug) {
		plugin = plug;
	}
	// Empty functions if nothing is given.
	public Functions (){
	}

	public double add(double x, double y){
		return x+y;
	}

	public double subract(double x, double y){
		return x-y;
	}
	public double multiply(double x, double y){
		return x*y;
	}

	public double divide(double x, double y){
		if (y == 0)
			return Double.NaN;
		return x/y;
	}

	public double remainder(double x, double y){
		return x%y;
	}

	public double power(double x, double y){
		return Math.pow(x, y);
	}

	public double parseDouble(String s){
		return Double.parseDouble(s);
	}

	// Converts an array of strings to one string.
	public static String arrayConcat(String[] a) {
		String result;
		StringBuffer buffer = new StringBuffer();
		buffer.append(a[0]);
		for (int i=1; i<a.length; i++) {
			buffer.append(a[i]);
		}
		result = buffer.toString();
		// Lets remove/replace all the unwanted whitespaces and commas too
		result = result.replaceAll(" ", "");
		result = result.replaceAll(",", ".");
		return result;
	}
	public static String StingConcat(String a, String b) {
		String result;
		StringBuffer buffer = new StringBuffer();
		buffer.append(a);
		buffer.append(b);
		result = buffer.toString();
		// Lets remove all the unwanted whitespaces too
		result.replaceAll(" ", "");
		return result;
	}
}
