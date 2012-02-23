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

import org.bukkit.ChatColor;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Widget;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.Tools.VariableKeys;
import de.Lathanael.SimpleCalc.gui.CalcWindow;
import de.Lathanael.SimpleCalc.gui.Geometry;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class ExtrasWindow extends GenericContainer {
	private ExtrasTexture tex;
	private ExtrasButton cos, sin, set;
	private ExtrasLabel label;
	private ExtrasComboBox box;
	private String playerName;

	public ExtrasWindow(Geometry edges, String playerName) {
		this.playerName = playerName;
		tex = new ExtrasTexture("http://dl.dropbox.com/u/42731731/CalcBackground.png");
		tex.setHeight(166).setWidth(100).setX(edges.getRight() + 15).setY(edges.getTop() - 10);
		tex.setPriority(RenderPriority.High);
		tex.setVisible(false);
		label = new ExtrasLabel("Extra functions");
		label.setHeight(10).setWidth(70).setX(edges.getRight() + 20).setY(edges.getTop());
		label.setVisible(false);
		sin = new ExtrasButton("sin");
		sin.setWidth(20).setHeight(10).setX(edges.getRight() + 25).setY(edges.getTop() + 20);
		sin.setVisible(false);
		cos = new ExtrasButton("cos");
		cos.setWidth(20).setHeight(10).setX(edges.getRight() + 50).setY(edges.getTop() + 20);
		cos.setVisible(false);
		box = new ExtrasComboBox();
		box.setMaxWidth(60).setHeight(15).setX(edges.getRight() + 20).setY(edges.getTop() + 35);
		box.setVisible(false);
		box.setItems(SimpleCalc.alphabet);
		set = new ExtrasButton("set");
		set.setWidth(20).setHeight(10).setX(edges.getRight() +  85).setY(edges.getTop() + 35);
		set.setVisible(false);
		addChildren(new Widget[] {label, tex, cos, sin, box, set});
		setWidth(0).setHeight(0);
	}

	public void onClick(Button button, CalcWindow window) {
		if (button.equals(cos)) {
			window.expression.setText(window.expression.getText() + "COS(");
			window.expression.setDirty(true);
		} else if (button.equals(sin)) {
			window.expression.setText(window.expression.getText() + "SIN(");
			window.expression.setDirty(true);
		} else if (button.equals(set)) {
			String number = window.expression.getText();
			if (number == null || number.isEmpty()) {
				window.result.setText(ChatColor.RED + "Input empty!");
				return;
			}
			double value = 0;
			try {
				value = Double.parseDouble(number);
			} catch (NumberFormatException e) {
				window.result.setText(ChatColor.RED + "Could not parse your input as a number!");
				return;
			}
			VariableKeys key = new VariableKeys(playerName, box.getSelectedItem());
			SimpleCalc.variables.put(key, value);
		}
	}
}
