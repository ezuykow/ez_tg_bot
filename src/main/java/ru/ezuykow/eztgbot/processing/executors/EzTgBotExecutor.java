package ru.ezuykow.eztgbot.processing.executors;

/**
 * Родительский интерфейс, который должны реализовавать интерфейсы исполнителей определенных апдейтов
 * @author ezuykow
 */
public interface EzTgBotExecutor {

    /**
     * Метод исполнения
     */
    void execute();

    /**
     * @return описание исполнителя
     */
    String getDescription();
}
