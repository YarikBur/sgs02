package com.sgstudio.sgs02.utils.audio;

import com.badlogic.gdx.audio.Music;
import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Random;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.Variables;

public class Audio {
	@SuppressWarnings("unused")
	private static List audioList;
	
	private static int play = 0;
	
	private static boolean created = false;
	private static boolean played = false;
	
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
	
	private static void play() {
		played = true;
		if(Settings.out())
			System.out.println(Language.getMessage(11) + ": " + play);
	}
	
	public static void randomStart() {
		play = Random.randomInt(1, 9);
		List.getMusic(play).play();
		play();
	}
	
	public static void smoothAttenuation(final int number) {
		Thread smooth = new Thread(new Runnable() {
			private float volume = 1;
			private float minus = 0.025f;
			
			private Music music;
			
			private void changeVolume() {
				try {
					while(true) {
						if(volume <= 0)
							break;
						
						if(volume-minus < 0.24f)
							minus = volume;
						
						music.setVolume(volume);
						Thread.sleep(200);
						volume-=0.025;

						if(Settings.out())
							System.out.println(Language.getMessage(13) + ": " + volume + ", Minus: " + minus);
						
						if(volume <= 0)
							break;
					}
					if(Settings.out())
						System.out.println(Language.getMessage(14) + ": " + music.isPlaying());
					music.stop();
					if(Settings.out())
						System.out.println(Language.getMessage(14) + ": " + music.isPlaying());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void run() {
				if(Settings.out())
					System.out.println(Language.getMessage(12) + ": " + number);
				music = List.getMusic(play);
				volume = music.getVolume();
				changeVolume();
				played = false;
				startNewMusic(number);
			}
			
		});
		smooth.start();
	}
	
	public static void startNewMusic() {
		if(!played)
			if(!List.playedMusic())
				if(!List.getMusic(play).isPlaying()) {
					played = true;
					stopMusic();
					randomStart();
				}
	}
	
	private static void startNewMusic(int number) {
		if(!played) {
			if(number < 1)
				number = 1;
			else if(number > 9)
				number = 9;
			play = number;
			if(!List.playedMusic())
				List.getMusic(play).play();
			play();
		}
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
