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

package de.Lathanael.SimpleCalc.gui;

import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Widget;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class SienceWindow extends GenericContainer {
	private ExtrasTexture tex;
	private ExtrasButton cos, sin;

	public SienceWindow(Geometry edges) {
		tex = new ExtrasTexture("http://dl.dropbox.com/u/42731731/CalcBackground.png");
		tex.setHeight(166).setWidth(100).setX(edges.getRight() + 15).setY(edges.getTop() - 10);
		tex.setPriority(RenderPriority.High);
		tex.setVisible(false);
		sin = new ExtrasButton("sin");
		sin.setWidth(20).setHeight(10).setX(edges.getRight() + 25).setY(edges.getTop());
		sin.setVisible(false);
		cos = new ExtrasButton("cos");
		cos.setWidth(20).setHeight(10).setX(edges.getRight() + 50).setY(edges.getTop());
		cos.setVisible(false);
		addChildren(new Widget[] {tex, cos, sin});
		setWidth(0).setHeight(0);
	}

	public void onClick(Button button, CalcWindow window) {
		if (button.equals(cos)) {
			window.expression.setText(window.expression.getText() + "COS(");
			window.expression.setDirty(true);
		} else if (button.equals(sin)) {
			window.expression.setText(window.expression.getText() + "SIN(");
			window.expression.setDirty(true);
		}
	}
}
