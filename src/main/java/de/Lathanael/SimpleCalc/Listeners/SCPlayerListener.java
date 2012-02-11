package de.Lathanael.SimpleCalc.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

import de.Lathanael.SimpleCalc.SimpleCalc;

public class SCPlayerListener implements Listener {

	private SimpleCalc plugin;

	public SCPlayerListener(SimpleCalc instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.removePopup((SpoutPlayer) event.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerKick(PlayerKickEvent event) {
		plugin.removePopup((SpoutPlayer) event.getPlayer());
	}

}
