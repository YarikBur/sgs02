package com.sgstudio.sgs02.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Утилтита которая упрощает вывод текста по углам
 * @author Yarik
 * @version 0.2
 */
public class Text {
	private static BitmapFont fontEN;
	private static BitmapFont fontRU;
	private static BitmapFont font;
	
	private static boolean created = false;
	
	private static final String FONT_NAME = "fonts/Imperial.ttf";
	private static final String RUSSIAN_CHARACTERS = 
			"АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "1234567890.,:;_¡!¿?\"'+-*/()[]={}";

	
	public Text() {
		if(!created) {
			fontEN = generateFont(FONT_NAME, FreeTypeFontGenerator.DEFAULT_CHARS);
			fontRU = generateFont(FONT_NAME, RUSSIAN_CHARACTERS);
			
			font = getFont();
			
			created = true;
		}
	}
	
	/**
	 * Выдает шрифт в зависимости от локализации
	 * @return BitmapFont
	 */
	private BitmapFont getFont() {
	    if(Language.getLanguage().equals("ru_RU"))
	    	return fontRU;
	    else 
	    	return fontEN;
	}
	
	/**
	 * Генерирует шрийт
	 * @param fontName - нашвание файла с шрифтом
	 * @param characters - символы, которые будут входить в шрийт
	 * @return BitmapFont
	 */
	private BitmapFont generateFont(String fontName, String characters) {
	    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	    parameter.characters = characters;
	    parameter.size = 24;
	 
	    FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal(fontName) );
	    BitmapFont font = generator.generateFont(parameter);
	    generator.dispose();
	 
	    return font;
	}
	
	/**
	 * Выводит текст слева вверху
	 * @param batch
	 * @param strings
	 */
	public void writeUpperleft(SpriteBatch batch, String... strings) {
		int indentation = 20;
		int i = indentation;
		for(String str : strings) {
			font.draw(batch, str, indentation, Integer.parseInt(Settings.getProperty("height")) - i);
			i += indentation;
		}
	}
	
	/**
	 * Выводит текст слева внизу
	 * @param batch
	 * @param strings
	 */
	public void writeLowerleft(SpriteBatch batch, String... strings) {
		int indentation = 20;
		int i = indentation;
		for(String str : strings) {
			font.draw(batch, str, indentation, i);
			i += indentation;
		}
	}
}
