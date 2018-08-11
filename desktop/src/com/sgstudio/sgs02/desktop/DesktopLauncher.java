package com.sgstudio.sgs02.desktop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sgstudio.sgs02.game.MyGame;
import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.main.Main;

public class DesktopLauncher {
	static String locale;
	
	static Language lan;
	static Settings cfg;
	
	
	public static void main (String[] arg) throws FileNotFoundException, IOException  {
		locale = Locale.getDefault() + "";
		
		lan = new Language(locale);
		cfg = new Settings();
		Settings.setProperty("language", locale);
		if(Settings.out())
			System.out.println(Language.getMessage(2) + locale);
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SGS02";
		config.width = Integer.parseInt(Settings.getProperty("width"));
		config.height = Integer.parseInt(Settings.getProperty("height"));
		new LwjglApplication(new Main(), config);
	}
}
