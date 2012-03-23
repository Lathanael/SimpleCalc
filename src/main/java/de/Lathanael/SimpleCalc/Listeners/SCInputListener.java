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

package de.Lathanael.SimpleCalc.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.keyboard.Keyboard;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.Exceptions.MathSyntaxMismatch;
import de.Lathanael.SimpleCalc.Parser.MathExpParser;
import de.Lathanael.SimpleCalc.Tools.CalcKey;
import de.Lathanael.SimpleCalc.gui.CalcWindow;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class SCInputListener implements Listener {

	@EventHandler (priority = EventPriority.NORMAL)
	public void onKeyPressedEvent(KeyPressedEvent event) {
		Screen screen = event.getPlayer().getMainScreen().getActivePopup();
		if (screen == null)
			return;
		if (!(screen instanceof CalcWindow))
			return;
		CalcWindow w = (CalcWindow) screen;
		if (w.expression.isFocused())
			return;
		Player player = event.getPlayer();
		Keyboard key = event.getKey();
		String keyName = null;
		if ((keyName = CalcKey.getKeyString(key.toString())) != null) {
			if (keyName.equalsIgnoreCase("back")) {
				String text = w.expression.getText();
				if (text.length() > 0)
					text = text.substring(0, text.length()-1);
				w.expression.setText(text);
				w.expression.setDirty(true);
			}
			else if (keyName.equalsIgnoreCase("")) {
				String calc = w.expression.getText();
				calc = calc.replaceAll(" ", "");
				calc = calc.replaceAll(",", ".");
				try {
					MathExpParser eqaution = new MathExpParser(calc, player.getName());
					double result = eqaution.compute();
					SimpleCalc.answer.put(player.getName(), result);
					w.result.setText(w.format.format(result));
					w.result.setDirty(true);
				}
				// The equation given is incorrect!
				catch(MathSyntaxMismatch mismatch){
					w.result.setText(ChatColor.RED + mismatch.getMessage());
					w.result.setDirty(true);
				}
			}
			else {
				w.expression.setText(w.expression.getText() + keyName);
				w.expression.setDirty(true);
			}
		}
	}
}
