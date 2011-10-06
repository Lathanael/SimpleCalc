package de.Lathanael.SimpleCalc.Listeners;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.Screen;

import de.Lathanael.SimpleCalc.SimpleCalc;
import de.Lathanael.SimpleCalc.Window.CalcWindow;

public class SCSpoutScreenListener extends ScreenListener {

	@SuppressWarnings("unused")
	private SimpleCalc plugin;

	public SCSpoutScreenListener(SimpleCalc instance) {
		plugin = instance;
	}

	public void onButtonClick (ButtonClickEvent event) {
		Screen screen = event.getScreen();
		if(screen instanceof CalcWindow) {
			CalcWindow window = ((CalcWindow)screen);
			window.onClick(event.getButton());
		}
	}
}
