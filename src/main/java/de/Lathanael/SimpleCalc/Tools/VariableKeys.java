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

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class VariableKeys {
	private String v;
	private String k;

	public VariableKeys(String playerName, String variale) {
		this.v = variale;
		this.k = playerName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() == getClass()) {
			if(((VariableKeys) obj).k.equals(this.k) && ((VariableKeys) obj).v.equals(this.v))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hc = 87;
		int mult = 47;
		hc = hc*mult + v.hashCode();
		hc = hc*mult + k.hashCode();
		return hc;
	}
}
