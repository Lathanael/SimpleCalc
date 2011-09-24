package de.Lathanael.SimpleCalc;

import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SCPlayerListener extends PlayerListener {

	private SimpleCalc plugin;

	public SCPlayerListener(SimpleCalc instance) {
		plugin = instance;
	}

	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.removePopup((SpoutPlayer) event.getPlayer());
	}

	@Override
	public void onPlayerKick(PlayerKickEvent event) {
		plugin.removePopup((SpoutPlayer) event.getPlayer());
	}

}
