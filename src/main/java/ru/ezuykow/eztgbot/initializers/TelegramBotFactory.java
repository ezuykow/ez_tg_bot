package ru.ezuykow.eztgbot.initializers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteWebhook;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SetWebhook;
import ru.ezuykow.eztgbot.configs.EzTgBotPropertiesHolder;
import ru.ezuykow.eztgbot.processing.UpdateHandler;

/**
 * Фабрика для создания Telegram bot по конфигурации из конфиг-файла
 * @author ezuykow
 */
public final class TelegramBotFactory {

    /**
     * Устанавливает вебхук или слушатель апдейтов (если URL вебхука не указан в конфигурации) созданному инстансу бота
     * @param propertiesHolder конфигурация бота
     * @param updateHandler обработчик апдейтов
     * @return инстанс бота
     */
    public static TelegramBot createTelegramBot(EzTgBotPropertiesHolder propertiesHolder, UpdateHandler updateHandler) {
        TelegramBot bot = createTelegramBotInstance(propertiesHolder);

        String webhookUrl = propertiesHolder.getTelegramBotConfigs().getWebhookUrl();
        if (webhookUrl != null && !webhookUrl.isBlank()) {
            bot.execute(new SetWebhook().url(webhookUrl));
        } else {
            bot.execute(new DeleteWebhook());
            setUpdatesListener(bot, propertiesHolder, updateHandler);
        }

        return bot;
    }

    /**
     * Создает инстанс TelegramBot по токену и с кастомным сервером (если указан)
     * @param propertiesHolder конфигурация бота
     * @return инстанс TelegramBot
     */
    private static TelegramBot createTelegramBotInstance(EzTgBotPropertiesHolder propertiesHolder) {
        String apiServerUrl = propertiesHolder.getTelegramBotConfigs().getApiServerUrl();
        String token = propertiesHolder.getTelegramBotConfigs().getToken();

        return (apiServerUrl != null && !apiServerUrl.isBlank())
                ? new TelegramBot.Builder(token).apiUrl(apiServerUrl).build()
                : new TelegramBot(token);
    }

    /**
     * Устанавливает слушатель апдейтов боту с указанным в конфигурации таймаутом (100мс, если не указан)
     * @param bot инстанс бота
     * @param propertiesHolder конфигурация бота
     * @param updateHandler обработчик апдейтов
     */
    private static void setUpdatesListener(TelegramBot bot, EzTgBotPropertiesHolder propertiesHolder,
                                           UpdateHandler updateHandler) {
        Integer timeout = propertiesHolder.getTelegramBotConfigs().getLongPollingTimeout();
        if (timeout == null || timeout < 100) {
            timeout = 100;
        }
        bot.setUpdatesListener(
                updateListener(updateHandler),
                new GetUpdates().timeout(timeout)
        );
    }

    /**
     * Возвращает дефолтный слушатель апдейтов
     * @param updateHandler обработчик апдейтов
     * @return дефолтный слушатель апдейтов
     */
    private static UpdatesListener updateListener(UpdateHandler updateHandler) {
        return updates -> {
            for (Update update : updates) {
                updateHandler.submitForProcessing(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        };
    }
}
