package de.Lathanael.SimpleCalc.Listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Screen;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.Window.CalcWindow;

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
			CalcWindow window = ((CalcWindow)screen);
			window.onClick(event.getButton());
		}
	}
}
