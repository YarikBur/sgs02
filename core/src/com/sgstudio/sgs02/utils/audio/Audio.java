package com.sgstudio.sgs02.utils.audio;

import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Random;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.Variables;

public class Audio {
	@SuppressWarnings("unused")
	private static List audioList;
	
	private static int play = 0;
	
	private static boolean created = false;
	
	public Audio() {
		if(!created) {
			audioList = new List();
			
			created = true;
		}
	}
	
	public static int getPlayed() {
		return play;
	}
	
	public static void update() {
		startNewMusic();
	}
	
	public static void print() {
		if(Settings.out())
			System.out.println(Language.getMessage(11) + ": " + play);
	}
	
	public static void randomStart() {
		play = Random.randomInt(1, 9);
		List.getMusic(play).play();
		print();
	}
	
	public static void startNewMusic() {
		if(!List.getMusic(play).isPlaying()) {
			stopMusic();
			randomStart();
		}
	}
	
	public static void startNewMusic(int number) {
		stopMusic();
		if(number < 1)
			number = 1;
		else if(number > 9)
			number = 9;
		play = number;
		List.getMusic(number).play();
		print();
	}
	
	public static void setVolume(int volume) {
		if(volume < 0)
			volume = 0;
		else if(volume > 100)
			volume = 100;
		Settings.setProperty("volume", volume+"");
		List.getMusic(play).setVolume(volume());
	}
	
	public static void stopMusic() {
		if(List.getMusic(play).isPlaying())
			List.getMusic(play).stop();
	}
	
	private static float volume() {
		return Variables.stringToInt(Settings.getProperty("volume"))/100f;
	}
	
	public static void dispose() {
		List.dispose();
	}
}
