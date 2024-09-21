package ru.ezuykow.eztgbot.processing.executors;

/**
 * Интерфейс, уоторый должны реализовывать обработчики нажатия на кнопки клавиатуры
 * {@link com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup}
 * @author ezuykow
 */
public interface KeyboardButtonExecutor extends EzTgBotExecutor {

    /**
     * @return текст обрабатываемой кнопки
     */
    String suitForButton();
}
