package de.Lathanael.SimpleCalc.Listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Screen;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.gui.CalcWindow;
import de.Lathanael.SimpleCalc.gui.ExtrasButton;

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
			if (event.getButton() instanceof ExtrasButton)
				window.extras.onClick(event.getButton(), window);
			else
				window.onClick(event.getButton());
		}
	}
}
