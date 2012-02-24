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

import de.Lathanael.SimpleCalc.Exceptions.MathSyntaxMismatch;
import de.Lathanael.SimpleCalc.Tools.Functions;

/**
* @author Lathanael (aka Philippe Leipold)
* https://github.com/Lathanael
**/

public class Operator{

	// Class variables
	private String sOperator;
	// Math functions
	private Functions func = new Functions();
	// What operator is it and what is his associative status?
	private boolean unary;
	private boolean binary;
	private boolean rightAs;
	private boolean leftAs;

	// Order of Precedence (the higher the index the higher the precedence)
	// private String[] operPrecedence = {"bl+ bl-", "bl* bl% bl/", "un-", "br^"};

	// Creates an Operator with the given parameters: (u)nary or (b)inary, (r)ight or (l)eft associative
	// or (n)one
	public Operator(String operatorText){
		// Check unary/binary status
		switch (operatorText.charAt(0)){
			case 'u':
				unary = true;
				binary = false;
				break;
			case 'b':
				unary = false;
				binary = true;
				break;
			default:
				// It is not an Operator!
				throw new MathSyntaxMismatch("Token " + operatorText.charAt(0) + " is not a known operator!");
		}
		// Check associative status
		switch (operatorText.charAt(1)){
			case 'r':
				rightAs = true;
				leftAs = false;
				break;
			case 'l':
				rightAs = false;
				leftAs = true;
				break;
			case 'n':
				rightAs = false;
				leftAs = false;
				break;
			default:
				// It is not an Operator!
				throw new MathSyntaxMismatch("Token " + operatorText.charAt(0) + " is not a known operator!");
		}
		this.sOperator = operatorText;
	}

	// Return the operator String with or without flags
	public String getOperator(boolean withFlags){
		if (withFlags){
			return this.sOperator;
		}
		else{
			return getOperator();
		}
	}

	// Return the operator String without flags
	public String getOperator(){
		return this.sOperator.substring(2, sOperator.length());
	}

	//Return which type of Operator it is
	public boolean isRight(){
		return this.rightAs;
	}

	public boolean isLeft(){
		return this.leftAs;
	}

	public boolean isUnary(){
		return this.unary;
	}

	public boolean isBinary(){
		return this.binary;
	}

	// Check the Operator type and execute its operation
	public double execute(double[] operands){
		if (sOperator.equals("bl+")){
			return func.add(operands[0], operands[1]);
		}
		else if (sOperator.equals("bl-")){
			return func.subract(operands[0],operands[1]);
		}
		else if (sOperator.equals("bl*")){
			return func.multiply(operands[0],operands[1]);
		}
		else if (sOperator.equals("bl/")){
			return func.divide(operands[0],operands[1]);
		}
		else if (sOperator.equals("bl%")){
			return func.remainder(operands[0], operands[1]);
		}
		else if (sOperator.equals("br^")){
			return func.power(operands[0], operands[1]);
		}
		else if (sOperator.equals("un-")){
			return -operands[0];
		}
		else{
			throw new MathSyntaxMismatch("Can't compute the result for the unkown operator!");
		}
	}
}