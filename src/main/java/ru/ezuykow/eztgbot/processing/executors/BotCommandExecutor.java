package ru.ezuykow.eztgbot.processing.executors;

/**
 * Интерфейс, который должны реализовывать исполнители команд бота
 * @author ezuykow
 */
public interface BotCommandExecutor extends EzTgBotExecutor {

    /**
     * @return возвращает текст команды, для исполнения которой предназначен
     */
    String suitForCommand();
}
