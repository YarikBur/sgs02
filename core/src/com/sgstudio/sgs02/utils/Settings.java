package com.sgstudio.sgs02.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	
	private static Properties cfg;
	
	private static String path = null;
	private static final String key[] = {"width", "height", "audio"};
	private static final String value[] = {"800", "600", "true"};
	
	private static boolean loaded = false;
	
	public Settings() {
		cfg = new Properties();
		try {
			path = new File(".").getCanonicalFile() + "\\settings.cfg";
			
			cfg.load(new FileInputStream(path));
		} catch (IOException e) {
			this.setProperty(key, value);
			Load();
			if(!loaded)
				e.printStackTrace();
		}
	}
	
	private static void Load() {
		if (!loaded) {
			try {
				cfg.load(new FileInputStream(path));
				loaded = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getProperty(String key) {
		String value = cfg.getProperty(key);
		return value;
	}
	
	public void setProperty(String key[], String value[]) {
		try {
			for(int i=0; i<key.length; i++) {
				cfg.setProperty(key[i], value[i]);
			}
			cfg.store(new FileOutputStream(path), null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void updateProperty(String key, String value) {
		for(int i=0; i<Settings.key.length; i++) {
			if(Settings.key[i].equals(key)) {
				System.out.println("sus");
				Settings.value[i] = value;
				setProperty(Settings.key, Settings.value);
				break;
			}
		}
	}
}
