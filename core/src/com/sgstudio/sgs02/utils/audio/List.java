package com.sgstudio.sgs02.utils.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class List {
	private static Music music01;
	private static Music music02;
	private static Music music03;
	private static Music music04;
	private static Music music05;
	private static Music music06;
	private static Music music07;
	private static Music music08;
	private static Music music09;
	
	private static boolean created = false;
	
	/**
	 * Инициализация класса
	 */
	public List() {
		if(!created) {
			music01 = Gdx.audio.newMusic(Gdx.files.internal("audio/01.Porth_Wen.mp3"));
			music02 = Gdx.audio.newMusic(Gdx.files.internal("audio/02.A_New_Day.mp3"));
			music03 = Gdx.audio.newMusic(Gdx.files.internal("audio/03.Abandoned.mp3"));
			music04 = Gdx.audio.newMusic(Gdx.files.internal("audio/04.The_Arrival.mp3"));
			music05 = Gdx.audio.newMusic(Gdx.files.internal("audio/05.Live_Station.mp3"));
			music06 = Gdx.audio.newMusic(Gdx.files.internal("audio/06.Nowhere.mp3"));
			music07 = Gdx.audio.newMusic(Gdx.files.internal("audio/07.Unknown_Station.mp3"));
			music08 = Gdx.audio.newMusic(Gdx.files.internal("audio/08.Walls_of_Metropole.mp3"));
			music09 = Gdx.audio.newMusic(Gdx.files.internal("audio/09.End_of_the_Road.mp3"));
			
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
		case 6:
			return music06;
		case 7:
			return music07;
		case 8:
			return music08;
		case 9:
			return music09;
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
		else if(music06.isPlaying())
			return true;
		else if(music07.isPlaying())
			return true;
		else if(music08.isPlaying())
			return true;
		else if(music09.isPlaying())
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
		else if(music06.isPlaying())
			return music06;
		else if(music07.isPlaying())
			return music07;
		else if(music08.isPlaying())
			return music08;
		else if(music09.isPlaying())
			return music09;
		else 
			return music01;
	}
	
	public static void dispose() {
		music01.dispose();
		music02.dispose();
		music03.dispose();
		music04.dispose();
		music05.dispose();
		music06.dispose();
		music07.dispose();
		music08.dispose();
		music09.dispose();
	}
}
