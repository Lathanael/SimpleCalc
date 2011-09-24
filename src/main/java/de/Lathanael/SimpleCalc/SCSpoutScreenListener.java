package de.Lathanael.SimpleCalc;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenCloseEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.Screen;

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

//	public void onScreenClose(ScreenCloseEvent event) {
//		Screen screen = event.getScreen();
//		if(screen instanceof CalcWindow) {
//			CalcWindow window = ((CalcWindow)screen);
//			window.close();
//		}
//	}
}
