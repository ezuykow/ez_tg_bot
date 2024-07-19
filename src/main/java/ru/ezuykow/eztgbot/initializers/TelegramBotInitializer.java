package ru.ezuykow.eztgbot.initializers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.configs.EzTgBotPropertiesHolder;
import ru.ezuykow.eztgbot.processing.UpdateHandler;
import ru.ezuykow.eztgbot.utils.EzTgBotLogger;

@Component
@RequiredArgsConstructor
public class TelegramBotInitializer {

    private final EzTgBotPropertiesHolder propertiesHolder;
    private final UpdateHandler updateHandler;
    private final GenericApplicationContext genericApplicationContext;

    public void initialize() {
        EzTgBotLogger.info("Trying to initialize {}", propertiesHolder.getTelegramBotConfigs().getUsername());

        TelegramBot bot = TelegramBotFactory.createTelegramBot(propertiesHolder, updateHandler);
        genericApplicationContext.registerBean("telegramBot", TelegramBot.class, () -> bot);

        EzTgBotLogger.info("Success");

        setBotName(bot, propertiesHolder);
        setBotDescription(bot, propertiesHolder);
        setBotShortDescription(bot, propertiesHolder);
    }

    private void setBotName(TelegramBot bot, EzTgBotPropertiesHolder propertiesHolder) {
        String currentName = bot.execute(new GetMyName()).botName().name();
        String newName = propertiesHolder.getTelegramBotConfigs().getName();

        if (newName != null && !newName.isBlank() && (currentName == null || !currentName.equals(newName))) {
            EzTgBotLogger.info("Trying to set bot name");
            checkResponse(bot.execute(new SetMyName().name(newName)));
        }
    }

    private void setBotDescription(TelegramBot bot, EzTgBotPropertiesHolder propertiesHolder) {
        String currentDesc = bot.execute(new GetMyDescription()).description();
        String newDesc = propertiesHolder.getTelegramBotConfigs().getDescription();

        if (newDesc != null && !newDesc.isBlank() && (currentDesc == null || !currentDesc.equals(newDesc))) {
            EzTgBotLogger.info("Trying to set bot description");
            checkResponse(bot.execute(new SetMyDescription().description(newDesc)));
        }
    }

    private void setBotShortDescription(TelegramBot bot, EzTgBotPropertiesHolder propertiesHolder) {
        String currentDesc = bot.execute(new GetMyShortDescription()).description();
        String newDesc = propertiesHolder.getTelegramBotConfigs().getShortDescription();

        if (newDesc != null && !newDesc.isBlank() && (currentDesc == null || !currentDesc.equals(newDesc))) {
            EzTgBotLogger.info("Trying to set bot short description");
            checkResponse(bot.execute(new SetMyShortDescription().description(newDesc)));
        }
    }

    private void checkResponse(BaseResponse response) {
        if (response.isOk()) {
            EzTgBotLogger.info("Success");
        }
    }
}
