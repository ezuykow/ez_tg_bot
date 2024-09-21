package ru.ezuykow.eztgbot.context;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import ru.ezuykow.eztgbot.utils.update.UpdateContentType;
import ru.ezuykow.eztgbot.utils.update.UpdateUtils;

/**
 * Хранилище контекста обработки каждого update-a
 * @author ezuykow
 */
public final class EzTgBotContext {

    private static final ThreadLocal<Context> contextHolder = new ThreadLocal<>();

    /**
     * Запись с содержимым контекста
     * @param telegramBot  - инстанс бота
     * @param update            - обрабатываемый в рамках потока update
     * @param updateContentType - тип обрабатываемого update-a
     */
    private record Context(
            TelegramBot telegramBot,
            Update update,
            UpdateContentType updateContentType
    ) {
    }

    private EzTgBotContext() {}

    /**
     * @return возвращает из контекста инстанс бота
     */
    public static TelegramBot getTelegramBot() {
        return contextHolder.get().telegramBot();
    }

    /**
     * @return возвращает из контекста текущий update {@link Update}
     */
    public static Update getUpdate() {
        return contextHolder.get().update();
    }

    /**
     * @return возвращает из контекста тип текущего update-а {@link UpdateContentType}
     */
    public static UpdateContentType getUpdateContentType() {
        return contextHolder.get().updateContentType();
    }

    /**
     * Устанавливает контекст текущему потоку обработки update-a
     *
     * @param telegramBot инсьанс бота
     * @param update      обрабатываемый update
     */
    public static void setContext(TelegramBot telegramBot, Update update) {
        contextHolder.set(
                new Context(
                        telegramBot,
                        update,
                        UpdateUtils.resolveUpdateContentType(update)
                )
        );
    }

    /**
     * Очищает контекст
     */
    public static void clearContext() {
        contextHolder.remove();
    }
}
