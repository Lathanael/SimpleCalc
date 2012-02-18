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

package de.Lathanael.SimpleCalc.Listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Screen;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.gui.CalcWindow;
import de.Lathanael.SimpleCalc.gui.Extras.ExtrasButton;

/**
* @author Lathanael (aka Philippe Leipold)
*
*/
public class SCSpoutScreenListener implements Listener {

	@SuppressWarnings("unused")
	private SimpleCalc plugin;

	public SCSpoutScreenListener(SimpleCalc instance) {
		plugin = instance;
	}

	@EventHandler (priority = EventPriority.MONITOR)
	public void onButtonClick (ButtonClickEvent event) {
		Screen screen = event.getScreen();
		if(screen instanceof CalcWindow) {
			CalcWindow window = ((CalcWindow) screen);
			if (event.getButton() instanceof ExtrasButton)
				window.extras.onClick(event.getButton(), window);
			else
				window.onClick(event.getButton());
		}
	}
}
