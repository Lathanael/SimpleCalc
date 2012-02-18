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

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import de.Lathanael.SimpleCalc.SimpleCalc;

/**
* @author Lathanael (aka Philippe Leipold)
*
*/
public class SCPluginListener implements Listener {

	public Plugin spout = null;

	public void spoutHook(PluginManager pm){
		spout = pm.getPlugin("Spout");
		if (spout != null){
			SimpleCalc.log.info("Spout enabled, hooking in!");
		}
	}

	@EventHandler (priority = EventPriority.NORMAL)
	public void onPluginEnable(PluginEnableEvent event){
		if (event.getPlugin().getDescription().getName().equals("Spout") && spout == null){
			spout = event.getPlugin();
			SimpleCalc.log.info("Spout enabled, hooking in!");
		}
	}

	@EventHandler (priority = EventPriority.NORMAL)
	public void onPluginDisable(PluginDisableEvent event){
		if (event.getPlugin().getDescription().getName().equals("Spout") && spout != null){
			spout = null;
			SimpleCalc.log.info("Spout disabled. Disabling Spout features.");
		}
	}
}
