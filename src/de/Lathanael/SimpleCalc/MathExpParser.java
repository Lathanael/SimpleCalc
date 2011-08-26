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

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author Lathanael (aka Philippe Leipold)
* https://github.com/Lathanael
**/

public class MathExpParser{

	// Declaration of needed variables and anything else
	// Private variables
	private Stack<Object> operator;
	private Stack<Object> result; // This will have the parsed result
	private Object current;
	private Object previous;
	private String equation;
	private int counter;
	private boolean done;

	// Numbers to be recognized(e.g.: 1.0 | .34 | 123 | 1,12 | ,646)
	private static Pattern rNumbers = Pattern.compile("(\\d*[.|\\.]?\\d+)" + "|(\\d+)"); // Compiling patterns is faster if it is called more often
	private Matcher mNumbers;

	// Operators variables
	private Pattern rOperators = Pattern.compile("([\\Q()+-*/%^\\E]{1})");
	private Matcher mOperators;
	// Operator precedence, e.g. * > + (the higher the index, the higher the precedence. same index = same precedence
	private String[] operPrecedence = {"bl+ bl-", "bl* bl% bl/", "un-", "br^"};

	// Constants declaring the type of operator
	private static final char INVALID = 'i';
	private static final char DIGITS = 'd';
	private static final char OPERATOR = 'o';
	private static final char LEFTPAREN = 'l';
	private static final char RIGHTPAREN = 'r';

	// Creates empty Parser if nothing is given
	public MathExpParser(){
	}

	// Creates a new parser for the given equation
	public MathExpParser(String equation){
		// Set the variables
		mNumbers = rNumbers.matcher(equation);
		mOperators = rOperators.matcher(equation);
		this.equation = equation;

		// Now call the parser to build the RPN equivalent of the equation
		infixToRPN();
		done = true; // If infixToRPN was successfully preformed confirm it
	}

	// Convert the Math expression to RPN so it can be calculated
	public void infixToRPN(){
		// initialize
		current = null;
		previous = null;
		result = new Stack<Object>();
		operator = new Stack<Object>();
		for (counter = 0; counter < equation.length(); counter++){
			// Get the first/next token
			char token = getNext();
			switch (token){
				// If the token is a number, then add it to the result queue.
				case DIGITS:
					result.push(current);
					break;

				// If the token is an operator, then:
				case OPERATOR:
					// while there is an operator at the top of the stack
					while (!operator.isEmpty() && getType(operator.peek()) == OPERATOR){
						Operator top = (Operator) operator.peek();
						// if current is associative or left-associative and has a lower or equal precedence to the top or
						// current is right-associative and has lower precedence
						if (checkPrecedence((Operator) current, top)){
							// pop top onto result
							result.push(operator.pop());
						}
						else{
							// Stop loop as precedence is not given
							break;
						}
					}
					// push current onto the stack.
					operator.push(current);
					break;
				// If token == left parenthesis push it onto the stack
				case LEFTPAREN:
					operator.push(current);
					break;
				// If token == right parenthesis:
				case RIGHTPAREN:
					try{
						while (getType(operator.peek()) != LEFTPAREN){
							// pop operators off the stack onto the result queue
							result.push(operator.pop());
						}
						// Pop the left parenthesis from the stack
						operator.pop();
					}
					catch (EmptyStackException e){
						// Misplaced parenthesis
						throw new MathSyntaxMismatch();
					}
					break;

			}
		}
		// While there are still operator tokens in the stack
		while (!operator.empty()){
			current = operator.pop();
			if (getType(current) == LEFTPAREN){
				// There are misplaced parenthesis
				throw new MathSyntaxMismatch();
			}
			// Pop the operator onto the result queue
			result.push(current);
		}
	}

	// Return which operator has precedence
	private boolean checkPrecedence(Operator oper1, Operator oper2){
		// Precedence of Operator 1 and 2
		int prec1 = -1;
		int prec2 = -1;
		for (int i = 0; i < operPrecedence.length; i++){
			Scanner scanner = new Scanner(operPrecedence[i]);
			while (scanner.hasNext()){
				String comparing = scanner.next();
				if (comparing.equals(oper1.getOperator(true))){
					// found a match!
					prec1 = i;
				}
				if (comparing.equals(oper2.getOperator(true))){
					// found a match!
					prec2 = i;
				}
			}
		}
		if (prec1 == -1 || prec2 == -1){
			// Operator mismatch
			throw new MathSyntaxMismatch();
		}
		// Is operator left associative
		if (oper1.isLeft()){
			if (prec1 <= prec2){
				return true;
			}
			else{
				return false;
			}
		}
		// Is operator right associative
		else if (oper1.isRight()){
			if (prec1 < prec2){
				return true;
			}
			else{
				return false;
			}
		}
		// Operator is not associative
		else{
			if (prec1 < prec2){
				return true;
			}
			else{
				return false;
			}
		}
	}

	// Returns the type of the Object
	private char getType(Object obj){
		// Object is a Number
		if (obj instanceof Double){
			return DIGITS;
		}
		// Object is an Operator
		else if (obj instanceof Operator){
			return OPERATOR;
		}
		// Object is a (
		else if (obj instanceof String && obj.equals("(")){
			return LEFTPAREN;
		}
		// Object is a )
		else if (obj instanceof String && obj.equals(")")){
			return RIGHTPAREN;
		}
		// Object is none of the accepted mathematical expressions
		else{
			return INVALID;
		}
	}

	// Parse the next token starting at counter and put it into holdng, returns the object being put into current
	private char getNext(){
		// set previous
		previous = current;
		// Token is a left parenthesis
		if (equation.charAt(counter) == '(')		{
			current = "(";
			return LEFTPAREN;
		}
		// Token is a right parenthesis
		else if (equation.charAt(counter) == ')'){
			current = ")";
			return RIGHTPAREN;
		}
		// Token is a number
		else if (mNumbers.find(counter) && mNumbers.start() == counter){
			current = Double.parseDouble(mNumbers.group());
			counter = mNumbers.end() - 1;
			return DIGITS;
		}
		// Token is an operator
		else if (mOperators.find(counter) && mOperators.start() == counter){
			String opFl;
			// Token is an unary operator
			if (getType(previous) != DIGITS && getType(previous) != RIGHTPAREN){
				opFl = "u";
				// Determine the associative status of the operator
				for (int i = 0; i < operPrecedence.length; i++){
					Scanner scanner = new Scanner(operPrecedence[i]);
					while (scanner.hasNext()){
						String temp = scanner.next();
						// Token matches an Operator
						if (temp.substring(2, temp.length()).equals(mOperators.group()) && temp.charAt(0) == 'u'){
							opFl += temp.charAt(1);
						}
					}
				}
			}
			else{
				opFl = "b";
				// Determine the associative status of the operator
				for (int i = 0; i < operPrecedence.length; i++){
					Scanner scanner = new Scanner(operPrecedence[i]);
					while (scanner.hasNext()){
						String temp = scanner.next();
						// Token matches an Operator
						if (temp.substring(2, temp.length()).equals(mOperators.group()) && temp.charAt(0) == 'b' ){
							opFl += temp.charAt(1);
						}
					}
				}
			}
			current = new Operator(opFl + mOperators.group());
			counter = mOperators.end() - 1;
			return OPERATOR;
		}
		else{
			// The given expression can't be parsed as an equation
			throw new MathSyntaxMismatch();
		}
	}

	// Computes the equation if it was parsed successfully
	@SuppressWarnings("unchecked")
	public double compute(){
		if (!done){
			// Equation could not be parsed!
			throw new MathSyntaxMismatch();
		}
		// Clone result to save it
		Stack<Object> temp = (Stack<Object>) result.clone();
		double answer = comp();
		// Set result back to its previous state
		result = temp;
		return answer;
	}

	// Recursive help function to compute the equation
	private double comp(){
		Object token;
		while (!result.isEmpty()){
			token = result.pop();

			// Token is a Number
			if (getType(token) == DIGITS){
				return ((Double) token).doubleValue();
			}
			// Token is an Operator
			else if (getType(token) == OPERATOR){
				if (((Operator) token).isUnary()){
					double[] operands = {this.comp()};
					return ((Operator) token).execute(operands);
				}
				else if (((Operator) token).isBinary()){
					double temp = this.comp();
					double[] operands = {this.comp(), temp};
					return ((Operator) token).execute(operands);
				}
				else{
					// The stack contains an invalid token
					throw new MathSyntaxMismatch();
				}
			}
			// Token is neither, Parser failed to read the equation correctly
			else{
				// The stack contains an invalid token
				throw new MathSyntaxMismatch();
			}
		}
		// The stack is empty as it wasn't parsed correctly
		throw new MathSyntaxMismatch();
	}
}