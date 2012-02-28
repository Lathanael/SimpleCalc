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

package de.Lathanael.SimpleCalc.gui.Extras;

import org.getspout.spoutapi.gui.ComboBox;
import org.getspout.spoutapi.gui.GenericComboBox;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class ExtrasComboBox extends GenericComboBox implements ComboBox {

	/* (non-Javadoc)
	 * @see org.getspout.spoutapi.gui.GenericComboBox#onSelectionChanged(int, java.lang.String)
	 */
	@Override
	public void onSelectionChanged(int i, String text) {
		this.setText(getSelectedItem());
		this.setDirty(true);
	}

}
