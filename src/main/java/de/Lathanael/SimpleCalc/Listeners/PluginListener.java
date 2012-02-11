package de.Lathanael.SimpleCalc.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import de.Lathanael.SimpleCalc.SimpleCalc;

public class PluginListener implements Listener {

	public static Plugin spout = null;

	public static void spoutHook(PluginManager pm){
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
