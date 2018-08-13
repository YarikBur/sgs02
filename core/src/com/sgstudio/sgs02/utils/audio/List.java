package com.sgstudio.sgs02.utils.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class List {
	private static Music music01;
	private static Music music02;
	private static Music music03;
	private static Music music04;
	private static Music music05;
	
	private static boolean created = false;
	
	/**
	 * Инициализация класса
	 */
	public List() {
		if(!created) {
			music01 = Gdx.audio.newMusic(Gdx.files.internal("audio/Defeat.mp3"));
			music02 = Gdx.audio.newMusic(Gdx.files.internal("audio/GamePlay.mp3"));
			music03 = Gdx.audio.newMusic(Gdx.files.internal("audio/Intro.mp3"));
			music04 = Gdx.audio.newMusic(Gdx.files.internal("audio/Melody.mp3"));
			music05 = Gdx.audio.newMusic(Gdx.files.internal("audio/Outtro.mp3"));
			
			created = true;
		}
	}
	
	/**
	 * Выдет трек
	 * @param number - номер трека
	 * @return Music
	 */
	public static Music getMusic(int number) {
		switch(number) {
		case 1:
			return music01;
		case 2:
			return music02;
		case 3:
			return music03;
		case 4:
			return music04;
		case 5:
			return music05;
		default:
			return music01;
		}
	}
	
	/**
	 * Играет ли музыка
	 * @return
	 */
	public static boolean playedMusic() {
		if(music01.isPlaying())
			return true;
		else if(music02.isPlaying())
			return true;
		else if(music03.isPlaying())
			return true;
		else if(music04.isPlaying())
			return true;
		else if(music05.isPlaying())
			return true;
		else 
			return false;
	}
	
	/**
	 * Выдет трек, который играет
	 * @return
	 */
	public static Music getPlayedMusic() {
		if(music02.isPlaying())
			return music02;
		else if(music03.isPlaying())
			return music03;
		else if(music04.isPlaying())
			return music04;
		else if(music05.isPlaying())
			return music05;
		else 
			return music01;
	}
	
	public static void dispose() {
		music01.dispose();
		music02.dispose();
		music03.dispose();
		music04.dispose();
		music05.dispose();
	}
}
