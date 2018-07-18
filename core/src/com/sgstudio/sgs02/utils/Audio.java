package com.sgstudio.sgs02.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Audio {
	private static Music music;
	
	private static int play = 0;
	
	private static boolean created = false;
	
	public Audio() {
		if(!created) {
			music = Gdx.audio.newMusic(Gdx.files.internal("audio/01.Porth_Wen.mp3"));
			
			created = true;
		}
	}
	
	public static void randomStart() {
		int ran = Random.randomInt(1, 9);
		play = ran;
		if(!music.isPlaying())
			music.play();
	}
	
	public static void startNewRandomMusic() {
		if(music.isPlaying()) {
			stopMusic();
			randomStart();
		}
	}
	
	public static void setVolume(int volume) {
		if(volume < 0)
			volume = 0;
		else if(volume > 100)
			volume = 100;
		Settings.setProperty("volume", volume+"");
		music.setVolume(volume());
	}
	
	public static void stopMusic() {
		if(music.isPlaying())
			music.stop();
	}
	
	private static float volume() {
		return Variables.stringToInt(Settings.getProperty("volume"))/100f;
	}
}
