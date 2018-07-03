package com.sgstudio.sgs02.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Утилтита которая упрощает вывод текста по углам
 * @author Yarik
 * @version 0.1
 */
public class Text {
	private static Settings cfg;
	
	private static BitmapFont font;
	
	private static boolean created = false;
	
	public Text() {
		if(!created) {
			font = new BitmapFont();
			cfg = new Settings();
			
			created = true;
		}
	}
	
	/**
	 * Выводит текст слева вверху
	 * @param batch
	 * @param strings
	 */
	public void writeUpperleft(SpriteBatch batch, String... strings) {
		int indentation = 15;
		int i = indentation;
		for(String str : strings) {
			font.draw(batch, str, indentation, Integer.parseInt(cfg.getProperty("height")) - i);
			i += indentation;
		}
	}
	
	/**
	 * Выводит текст слева внизу
	 * @param batch
	 * @param strings
	 */
	public void writeLowerleft(SpriteBatch batch, String... strings) {
		int indentation = 15;
		int i = indentation;
		for(String str : strings) {
			font.draw(batch, str, indentation, i);
			i += indentation;
		}
	}
}
