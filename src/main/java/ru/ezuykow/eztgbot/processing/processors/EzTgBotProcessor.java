package ru.ezuykow.eztgbot.processing.processors;

import ru.ezuykow.eztgbot.utils.update.UpdateContentType;

/**
 * Интерфейс, который должны реализивывать обработчики апдейтов
 * @author ezuykow
 */
public interface EzTgBotProcessor {

    /**
     * @return {@link UpdateContentType}, для обработки которого предназначен
     */
    UpdateContentType suitFor();

    /**
     * Обработка апдейта
     */
    void process();
}
