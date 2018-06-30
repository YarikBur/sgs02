package com.sgstudio.sgs02.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sgstudio.sgs02.MyGame;
import com.sgstudio.sgs02.utils.Settings;

public class DesktopLauncher {
	static Settings cfg = new Settings();
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SGS02";
		config.width = Integer.parseInt(cfg.getProperty("width"));
		config.height = Integer.parseInt(cfg.getProperty("height"));
		new LwjglApplication(new MyGame(), config);
	}
}
