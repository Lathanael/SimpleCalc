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

package de.Lathanael.SimpleCalc.Exceptions;

/**
* @author Lathanael (aka Philippe Leipold)
* https://github.com/Lathanael
**/

public class MathSyntaxMismatch extends RuntimeException{

	/**
	 * Change this if anything important is changed!
	 **/
	private static final long serialVersionUID = 6190060938824137129L;
	private static final String msg = "Expression is not conform to math Syntax!";

	public MathSyntaxMismatch(){
		super(msg);
	}

	//These should only be used if REALLY needed
	public MathSyntaxMismatch(String msg){
	super(msg);
	}

	public MathSyntaxMismatch(Throwable cause){
		super(cause);
	}

	public MathSyntaxMismatch(String msg, Throwable cause){
		super(msg, cause);
	}
}
