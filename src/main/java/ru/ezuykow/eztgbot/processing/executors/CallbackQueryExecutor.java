package ru.ezuykow.eztgbot.processing.executors;

/**
 * Интерфейс, который должны реалтзовывать исполнители нажатий на кнопки Inline-клавиатуры
 * @author ezuykow
 */
public interface CallbackQueryExecutor extends EzTgBotExecutor {

    /**
     * @return CallbackData, для исполнения которого предназначен
     */
    String suitForCallbackData();
}
