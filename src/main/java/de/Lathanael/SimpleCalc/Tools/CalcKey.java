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

package de.Lathanael.SimpleCalc.Tools;

import java.util.HashMap;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public enum CalcKey {
	KEY_1("1"), KEY_2("2"), KEY_3("3"), KEY_4("4"), KEY_5("5"),
	KEY_6("6"), KEY_7("7"), KEY_8("8"), KEY_9("9"), KEY_0("0"),
	KEY_A("a"), KEY_B("b"), KEY_C("c"), KEY_D("d"), KEY_E("e"),
	KEY_F("f"), KEY_G("g"), KEY_H("h"), KEY_I("i"), KEY_J("k"),
	KEY_K("k"), KEY_L("l"), KEY_M("m"), KEY_N("n"), KEY_O("o"),
	KEY_P("p"), KEY_Q("q"), KEY_R("r"), KEY_S("s"), KEY_T("t"),
	KEY_U("u"), KEY_V("v"), KEY_W("w"), KEY_X("x"), KEY_Y("y"),
	KEY_Z("z"), KEY_ADD("+"), KEY_MINUS("-"), KEY_EQUALS("="),
	KEY_LBRACKET("("), KEY_RBRACKET(")"), KEY_SEMICOLON(";"),
	KEY_COMMA(","), KEY_PERIOD("."), KEY_MULTIPLY("*"), KEY_NUMPAD7("7"),
	KEY_NUMPAD8("8"), KEY_NUMPAD9("9"), KEY_SUBTRACT("-"), KEY_NUMPAD4("4"),
	KEY_NUMPAD5("5"), KEY_NUMPAD6("6"), KEY_NUMPADEQUALS("="), KEY_NUMPAD1("1"),
	KEY_NUMPAD2("2"), KEY_NUMPAD3("3"), KEY_NUMPAD0("0"), KEY_NUMPADCOMMA(","),
	KEY_DIVIDE("/"), KEY_SLASH("/"), KEY_SPACE(" "), KEY_NUMPADENTER(""),
	KEY_DECIMAL(","), KEY_RETURN(""), KEY_BACK("back"), KEY_BACKSLASH("^");

	private String name;
	private static HashMap<String, String> values = new HashMap<String, String>();

	private CalcKey(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static String getKeyString(String key) {
		if (values.containsKey(key)) {
			return values.get(key);
		} else
			return null;
	}

	static {
		for (CalcKey key : values()) {
			values.put(key.toString(), key.getName());
		}
	}
}
