package com.sgstudio.sgs02.utils;

import java.io.File;
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
	private static final String key[] = {"width", "height", "audio"};
	private static final String value[] = {"800", "600", "true"};
	
	//Переменная используется, если изначально файла settings.cfg не существовало
	private static boolean loaded = false;
	
	/**
	 * Инициализация класса
	 */
	public Settings() {
		cfg = new Properties();
		try {
			path = new File(".").getCanonicalFile() + "\\settings.cfg";
			
			cfg.load(new FileInputStream(path));
		} catch (IOException e) {
			//Если файла с настройками не существовало - создаем новый и проверяем, удачно ли он создался, иначе выдаем ошибку
			this.setProperty(key, value);
			Load();
			if(!loaded)
				e.printStackTrace();
		}
	}
	 /**
	  * Функция создает новый файл с настройками
	  */
	private static void Load() {
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
	 * @return String
	 */
	public String getProperty(String key) {
		String value = cfg.getProperty(key);
		return value;
	}
	
	/**
	 * Функция создает новый парамерт со значением (функция не доделана)
	 * @param key
	 * @param value
	 */
	public void setProperty(String key[], String value[]) {
		try {
			for(int i=0; i<key.length; i++) {
				cfg.setProperty(key[i], value[i]);
			}
			cfg.store(new FileOutputStream(path), null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Функция обновляет значение у параметра, если названия параметров совпадали
	 * @param key
	 * @param value
	 */
	public void updateProperty(String key, String value) {
		for(int i=0; i<Settings.key.length; i++) {
			if(Settings.key[i].equals(key)) {
				System.out.println("sus");
				Settings.value[i] = value;
				setProperty(Settings.key, Settings.value);
				break;
			}
		}
	}
}
