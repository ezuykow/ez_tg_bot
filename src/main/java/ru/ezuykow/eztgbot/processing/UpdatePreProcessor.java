package ru.ezuykow.eztgbot.processing;

/**
 * Интерфейс, который должен реализовывать класс для предварительной обработки апдейта (до его отправки на
 * {@link ProcessorSwitcher})
 * @author ezuykow
 */
public interface UpdatePreProcessor {

    /**
     * Предварительная обработка апдейта
     */
    void preProcess();
}
