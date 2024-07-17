package ru.ezuykow.eztgbot.initializers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteWebhook;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SetWebhook;
import ru.ezuykow.eztgbot.configs.EzTgBotPropertiesHolder;
import ru.ezuykow.eztgbot.processors.UpdateHandler;

public final class TelegramBotFactory {

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

    private static TelegramBot createTelegramBotInstance(EzTgBotPropertiesHolder propertiesHolder) {
        String apiServerUrl = propertiesHolder.getTelegramBotConfigs().getApiServerUrl();
        String token = propertiesHolder.getTelegramBotConfigs().getToken();

        return (apiServerUrl != null && !apiServerUrl.isBlank())
                ? new TelegramBot.Builder(token).apiUrl(apiServerUrl).build()
                : new TelegramBot(token);
    }

    private static void setUpdatesListener(TelegramBot bot, EzTgBotPropertiesHolder propertiesHolder,
                                           UpdateHandler updateHandler) {
        Integer timeout = propertiesHolder.getTelegramBotConfigs().getLongPollingTimeout();
        if (timeout == null || timeout < 100) {
            timeout = 100;
        }
        bot.setUpdatesListener(
                updateListener(bot, propertiesHolder, updateHandler),
                new GetUpdates().timeout(timeout)
        );
    }

    private static UpdatesListener updateListener(TelegramBot bot, EzTgBotPropertiesHolder propertiesHolder,
                                                  UpdateHandler updateHandler) {
        return updates -> {
            for (Update update : updates) {
                updateHandler.process(bot, update, propertiesHolder);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        };
    }
}
