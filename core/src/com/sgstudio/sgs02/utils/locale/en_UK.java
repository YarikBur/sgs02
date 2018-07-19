package com.sgstudio.sgs02.utils.locale;

public class en_UK {
	private static final String en[] = {
			"The setting.cfg file is created",
			"Changes saved: ",
			"Selected language: ",
			"This language is not in the list: ",
			"Invalid value for the dictionary",
			"Output of the entire dictionary",
			"Command",
			"The dictionary is over",
			"Width",
			"Height",
			"Console",
			"Music number",
			"Number track",
			"Volume",
			"Played"};
	
	/**
	 * Выдает размер словаря
	 * @return int
	 */
	public static int getQuantityStrings() {
		return en.length;
	}
	
	/**
	 * Выдет сообщение по ключу из словаря
	 * @param key - номер в словаре
	 * @return String
	 */
	public static String getMessage(int key) {
		if(key < en.length && key >= 0) {
			return en[key];
		} else
			return en[4];
	}
}
