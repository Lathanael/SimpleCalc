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

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.gui.Screen;

import de.Lathanael.SimpleCalc.SimpleCalc;
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
		SimpleCalc.log.info("Key pressed: " + event.getKey().toString());
	}
}
