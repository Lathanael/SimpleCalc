package de.Lathanael.SimpleCalc;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PluginListener extends ServerListener {

	public static Plugin spout = null;

	public static void spoutHook(PluginManager pm){
		spout = pm.getPlugin("Spout");
		if (spout != null){
			SimpleCalc.log.info("[SimpleCalc] Spout enabled, hooking in!");
		}
	}

	public void onPluginEnable(PluginEnableEvent event){
		if (event.getPlugin().getDescription().getName().equals("Spout") && spout == null){
			spout = event.getPlugin();
			SimpleCalc.log.info("[SimpleCalc] Spout enabled, hooking in!");
		}
	}

	public void onPluginDisable(PluginDisableEvent event){
		if (event.getPlugin().getDescription().getName().equals("Spout") && spout != null){
			spout = null;
			SimpleCalc.log.info("[SimpleCalc] Spout disabled. Disabling Spout features.");
		}
	}
}
