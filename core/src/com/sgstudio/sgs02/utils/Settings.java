package com.sgstudio.sgs02.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс позволяет:
 *  сохранять настройки игры в файл settings.cfg
 *  считывать настройки игры из файла settings.cfg
 * @author Yarik
 * @version 0.1
 */

public class Settings {
	
	private static Properties cfg;
	
	//Путь до файла settings.cfg
	private static String path = null;
	
	//Стандартные настройки
	private static String key[] = {"console", "width", "height", "language", "volume"};
	private static String value[] = {"true", "800", "600", "en_UK", "100"};
	
	//Переменная используется, если изначально файла settings.cfg не существовало
	private static boolean loaded = false;
	
	/**
	 * Инициализация класса
	 */
	public Settings() {
		cfg = new Properties();
		try {
			path = "settings.cfg";
			cfg.load(new FileInputStream(path));
		} catch (IOException e) {
			//Если файла с настройками не существовало - создаем новый и проверяем, удачно ли он создался, иначе выдаем ошибку
			setProperty(key, value);
			load();
			if(!loaded)
				e.printStackTrace();
			if(out())
				System.out.println(Language.getMessage(0));
		}
	}
	
	public static boolean stringToBoolean(String str) {
		if(str.equals("true"))
			return true;
		else
			return false;
	}
	
	 /**
	  * Функция создает новый файл с настройками
	  */
	private static void load() {
		if (!loaded) {
			try {
				cfg.load(new FileInputStream(path));
				loaded = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Функция возращает значение нужного нам параметра
	 * @param key - название параметра
	 * @return String - значение параметра
	 */
	public static String getProperty(String key) {
		return cfg.getProperty(key);
	}
	
	/**
	 * Добавляет в настройки новые параметры, если они отсутствуют, иначе идет в метод для обновления параметров
	 * @param key - название параметра
	 * @param value - значение параметра
	 */
	public static void setProperty(String key, String value) {
		boolean equals = false;
		for(int i=0; i<Settings.key.length; i++) {
			if(Settings.key[i].equals(key)) {
				equals = true;
				break;
			}
		}
		
		if(!equals) {
			String newKey[] = new String[Settings.key.length+1];
			String newValue[] = new String[Settings.value.length+1];
			for(int i=0; i<newKey.length; i++) {
				if(i!=newKey.length-1) {
					newKey[i] = Settings.key[i];
					newValue[i] = getProperty(Settings.key[i]);
				} else {
					newKey[i] = key;
					newValue[i] = value;
				}
			}
			Settings.key = newKey;
			Settings.value = newValue;
			setProperty(Settings.key, Settings.value);
		} else
			updateProperty(key, value);
	}
	
	/**
	 * Функция заполняет файл settings.cfg настройками
	 * @param key[] - массив с названиями параметров
	 * @return String[] - массив со значениями параметров
	 */
	private static void setProperty(String key[], String value[]) {
		try {
			for(int i=0; i<key.length; i++)
				cfg.setProperty(key[i], value[i]);
			cfg.store(new FileOutputStream(path), null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Функция обновляет значение у параметра, если названия параметров совпадали
	 * @param key - название параметра
	 * @return String - значение параметра
	 */
	private static void updateProperty(String key, String value) {
		for(int i=0; i<Settings.key.length; i++) {
			if(Settings.key[i].equals(key)) {
				Settings.value[i] = value;
				setProperty(Settings.key, Settings.value);
				if(out())
					System.out.println(Language.getMessage(1) + key + " = " + value);
			} else
				Settings.value[i] = getProperty(Settings.key[i]);
		}
	}
	
	/**
	 * 
	 * @return boolean
	 */
	public static boolean out() {
		if(stringToBoolean(getProperty("console")))
			return true;
		else
			return false;
	}
}
