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
	private ExtrasButton cos, sin, pi, e, set, log, ln, get, ins;
	private ExtrasLabel label;
	public ExtrasComboBox box, locs;
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
		sin.setWidth(20).setHeight(10).setX(edges.getRight() + 20).setY(edges.getTop() + 20);
		sin.setVisible(false);
		cos = new ExtrasButton("cos");
		cos.setWidth(20).setHeight(10).setX(edges.getRight() + 42).setY(edges.getTop() + 20);
		cos.setVisible(false);
		pi = new ExtrasButton("Pi");
		pi.setTooltip("Pi");
		pi.setWidth(20).setHeight(10).setX(edges.getRight() + 64).setY(edges.getTop() + 20);
		pi.setVisible(false);
		e = new ExtrasButton("e");
		e.setTooltip("e");
		e.setWidth(20).setHeight(10).setX(edges.getRight() + 86).setY(edges.getTop() + 20);
		e.setVisible(false);
		ln = new ExtrasButton("ln");
		ln.setWidth(20).setHeight(10).setX(edges.getRight() + 20).setY(edges.getTop() + 35);
		ln.setVisible(false);
		log = new ExtrasButton("log");
		log.setWidth(20).setHeight(10).setX(edges.getRight() + 42).setY(edges.getTop() + 35);
		log.setVisible(false);
		box = new ExtrasComboBox();
		box.setWidth(60).setHeight(20).setX(edges.getRight() + 20).setY(edges.getBottom() - 50);
		box.setVisible(false);
		box.setItems(SimpleCalc.alphabet);
		box.setText("Variable");
		set = new ExtrasButton("Set");
		set.setWidth(20).setHeight(10).setX(edges.getRight() +  85).setY(edges.getBottom() - 50);
		set.setVisible(false);
		get = new ExtrasButton("Get");
		get.setWidth(20).setHeight(10).setX(edges.getRight() +  85).setY(edges.getBottom() - 38);
		get.setVisible(false);
		locs = new ExtrasComboBox();
		locs.setWidth(60).setHeight(20).setX(edges.getRight() + 20).setY(edges.getBottom() - 20);
		locs.setVisible(false);
		locs.setItems(SimpleCalc.locs);
		locs.setText("Locations");
		ins = new ExtrasButton("INS");
		ins.setWidth(20).setHeight(10).setX(edges.getRight() +  85).setY(edges.getBottom() - 15);
		ins.setVisible(false);
		addChildren(new Widget[] {label, tex, cos, sin, box, set, pi, e, log, ln, get, locs, ins});
		setWidth(0).setHeight(0);
	}

	public void onClick(Button button, CalcWindow window) {
		if (button.equals(cos)) {
			window.expression.setText(window.expression.getText() + "COS(");
			window.expression.setDirty(true);
		} else if (button.equals(sin)) {
			window.expression.setText(window.expression.getText() + "SIN(");
			window.expression.setDirty(true);
		} else if (button.equals(log)) {
			window.expression.setText(window.expression.getText() + "LOG(");
			window.expression.setDirty(true);
		} else if (button.equals(ln)) {
			window.expression.setText(window.expression.getText() + "LN(");
			window.expression.setDirty(true);
		} else if (button.equals(e)) {
			window.expression.setText(window.expression.getText() + "e");
			window.expression.setDirty(true);
		} else if (button.equals(pi)) {
			window.expression.setText(window.expression.getText() + "PI()");
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
			window.result.setText(ChatColor.GREEN + "Set variable " + ChatColor.GOLD + box.getSelectedItem()
					+ ChatColor.GREEN + " to : " + ChatColor.AQUA + value);
		} else if (button.equals(get)) {
			window.expression.setText(window.expression.getText() + box.getSelectedItem());
			window.expression.setDirty(true);
		} else if (button.equals(ins)) {
			window.expression.setText(window.expression.getText() + locs.getSelectedItem());
			window.expression.setDirty(true);
		} else if (button.equals(box)) {
			box.setText(box.getSelectedItem());
			box.setDirty(true);
		} else if (button.equals(locs)) {
			locs.setText(locs.getSelectedItem());
			locs.setDirty(true);
		}
	}
}
