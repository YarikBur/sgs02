package com.sgstudio.sgs02.utils.audio;

import com.badlogic.gdx.audio.Music;
import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Random;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.Variables;

/**
 * Класс для управления музыкой
 * @author Yarik
 * @version 0.4
 */
public class Audio {
	@SuppressWarnings("unused")
	private static List audioList;
	
	private static int play = 0;
	
	private static boolean created = false;
	private static boolean played = false;
	private static boolean smooth = false;
	
	/**
	 * Инициализация класса
	 */
	public Audio() {
		if(!created) {
			audioList = new List();
			
			created = true;
		}
	}
	
	/**
	 * Возвращает номер тека, который сейчас играет
	 * @return int
	 */
	public static int getPlayed() {
		return play;
	}
	
	/**
	 * Включает новый трек, если предыдущая закончилась
	 */
	public static void update() {
		if(!smooth)
			if(!List.playedMusic()) {
				played = false;
				startNewMusic();
			}
	}
	
	/**
	 * Выводит номер трека, который сейчас включился
	 */
	private static void play() {
		played = true;
		if(Settings.out())
			System.out.println(Language.getMessage(11) + ": " + play);
	}
	
	/**
	 * Включает рандомный трек
	 */
	public static void randomStart() {
		play = Random.randomInt(1, 9);
		List.getMusic(play).play();
		play();
	}
	
	/**
	 * Плавно включает другой трек
	 * @param number - номер трека, который надо будет включить
	 */
	public static void smoothAttenuation(final int number) {
		Thread smooth = new Thread(new Runnable() {
			private float volume = 1;
			private float minus = 0.025f;
			
			private Music music;
			
			/**
			 * Плавно уменьшает громкость трека
			 */
			private void changeVolume() {
				try {
					while(true) {
						if(volume <= 0)
							break;
						
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
				Audio.smooth = true;
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
	
	/**
	 * Включает рандомно трек
	 */
	private static void startNewMusic() {
		if(!played) {
			played = true;
			randomStart();
		}
	}
	
	/**
	 * Включает другой трек
	 * @param number - номер трека, который надо будет включить
	 */
	private static void startNewMusic(int number) {
		if(!played) {
			if(number < 1)
				number = 1;
			else if(number > 9)
				number = 9;
			play = number;
			if(!List.playedMusic()) {
				List.getMusic(play).play();
				List.getMusic(play).setVolume(volume());
			}
			play();
			smooth = false;
		}
	}
	
	/**
	 * Изменить громкость
	 * @param volume
	 */
	public static void setVolume(int volume) {
		if(volume < 0)
			volume = 0;
		else if(volume > 100)
			volume = 100;
		Settings.setProperty("volume", volume+"");
		List.getMusic(play).setVolume(volume());
	}
	
	/**
	 * Меняет громкость из диапозона [0; 100] в [0; 1]
	 * @return
	 */
	private static float volume() {
		return Variables.stringToInt(Settings.getProperty("volume"))/100f;
	}

	public static void dispose() {
		List.dispose();
	}
}
