package com.sgstudio.sgs02.utils.locale;

public class ru_RU {
	private static final String ru[] = {
			"Файл setting.cfg создан",
			"Изменения сохранены: ",
			"Выбран язык: ",
			"Этот язык отсутствует в списке: ",
			"Не допустимое значение для словаря",
			"Вывод всего словаря",
			"Команда",
			"Словарь окончен",
			"Ширина",
			"Высота",
			"Консоль",
			"Номер музыки",
			"Номер дорожки",
			"Громкость",
			"Играет"};
	
	/**
	 * Выдает размер словаря
	 * @return int
	 */
	public static int getQuantityStrings() {
		return ru.length;
	}
	
	/**
	 * Выдет сообщение по ключу из словаря
	 * @param key - номер в словаре
	 * @return String
	 */
	public static String getMessage(int key) {
		if(key < ru.length && key >= 0) {
			return ru[key];
		} else
			return ru[4];
	}
}
