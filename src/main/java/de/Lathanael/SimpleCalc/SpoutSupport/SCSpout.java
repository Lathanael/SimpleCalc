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

package de.Lathanael.SimpleCalc.SpoutSupport;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.Listeners.SCInputListener;
import de.Lathanael.SimpleCalc.Listeners.SCKeyBinding;
import de.Lathanael.SimpleCalc.Listeners.SCPlayerListener;
import de.Lathanael.SimpleCalc.Listeners.SCPluginListener;
import de.Lathanael.SimpleCalc.Listeners.SCSpoutScreenListener;
import de.Lathanael.SimpleCalc.gui.CalcWindow;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class SCSpout {

	private static Map<SpoutPlayer, CalcWindow> popups = new HashMap<SpoutPlayer, CalcWindow>();
	private static SCPlayerListener SCPlayerListener;

	public void openWindow(Player player) {
		CalcWindow popup = null;
		SpoutPlayer sp = (SpoutPlayer) player;
		if (!popups.containsKey(player)) {
			popups.put(sp, new CalcWindow(sp, SimpleCalc.getInstance()));
		}
		popup = popups.get(player);
		popup.open();
	}

	public void removePopup(SpoutPlayer player) {
		popups.remove(player);
	}

	public void closeWindow(SpoutPlayer player, boolean remove) {
		if (!popups.containsKey(player)) {
			SimpleCalc.log.info("No window for " + player.getName() + " was found!");
			return;
		}
		player.getMainScreen().closePopup();
		if (remove)
			removePopup(player);
	}

	public void onEnable(SimpleCalc plugin, PluginManager pm, SCPluginListener SCPluginListener, YamlConfiguration config) throws ClassNotFoundException, NoClassDefFoundError {
		if (SCPluginListener.spout != null) {
			SCPlayerListener = new SCPlayerListener();
			pm.registerEvents(new SCSpoutScreenListener(plugin), plugin);
			pm.registerEvents(SCPlayerListener, plugin);
			try {
				SpoutManager.getKeyBindingManager().registerBinding("SimpleCalc GUI", Keyboard.KEY_C, "Open SimpleCalc GUI", new SCKeyBinding(), plugin);
			} catch(IllegalArgumentException e) {
				SimpleCalc.log.info("Binding already registered!");
			}
		}
		if (SimpleCalc.keysEnabled && SCPluginListener.spout != null) {
			SimpleCalc.log.info("Listening to keystrokes while CalcWindow is open enabled");
			pm.registerEvents(new SCInputListener(), plugin);
		} else if (SCPluginListener.spout == null && SimpleCalc.keysEnabled) {
			config.set("EnableKeys", false);
			SimpleCalc.log.config("Disabled keys in the config because Spout is not installed!");
		}
	}
}
