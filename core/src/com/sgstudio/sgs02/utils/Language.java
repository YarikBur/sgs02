package com.sgstudio.sgs02.utils;

import com.sgstudio.sgs02.utils.locale.en_UK;
import com.sgstudio.sgs02.utils.locale.ru_RU;

/**
 * Утилита для управления языками в программе
 * @author Yarik
 * @version 0.2
 */
public class Language {
	private static int quantity;
	private static boolean created = false;
	
	/**
	 * Инициализация класа
	 * @param language - язык
	 */
	public Language(String language) {
		if(!created) {
			quantity = ru_RU.getQuantityStrings();
			
			created = true;
		}
	}
	
	/**
	 * Выдет сообщение из словаря в зависимости от языка
	 * @param key - номер из словаря
	 * @return
	 */
	public static String getMessage(int key) {
		if(Settings.getProperty("language").equals("ru_RU"))
			return getMessageRU(key);
		else
			return getMessageEN(key);
	}
	
	/**
	 * Выводит в консоль все команды словаря
	 */
	public static void getAllStrings() {
		if(Settings.out()) {
			System.out.println("\n------" + getMessage(5) + "------");
			for(int i=0; i<quantity; i++)
				System.out.println(getMessage(6) + " " + i + ": " + getMessage(i));
			System.out.println("------" + getMessage(7) + "------\n");
		}
	}
	
	/**
	 * Выдает сообщение из словаря на русском
	 * @param key  - номер из словаря
	 * @return
	 */
	private static String getMessageRU(int key) {
		return ru_RU.getMessage(key);
	}
	
	/**
	 * Выдает сообщение из словаря на английском
	 * @param key  - номер из словаря
	 * @return
	 */
	private static String getMessageEN(int key) {
		return en_UK.getMessage(key);
	}
}
