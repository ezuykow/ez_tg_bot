package ru.ezuykow.eztgbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.DeleteWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.configs.EzTgBotPropertiesHolder;
import ru.ezuykow.eztgbot.initializers.TelegramBotInitializer;
import ru.ezuykow.eztgbot.utils.EzTgBotLogger;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(EzTgBotPropertiesHolder.class)
@RequiredArgsConstructor
public class EzTgBot {

    private final ApplicationContext applicationContext;
    private final EzTgBotPropertiesHolder ezTgBotPropertiesHolder;
    private final TelegramBotInitializer telegramBotInitializer;

    @EventListener(ContextRefreshedEvent.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void contextRefreshEventListener() {
        ezTgBotPropertiesHolder.validateProperties();
        telegramBotInitializer.initialize();
    }

    @EventListener(ContextClosedEvent.class)
    public void contextClosedEventListener() {
        TelegramBot bot = applicationContext.getBean("telegramBot", TelegramBot.class);
        BaseResponse deleteWebhookResponse = bot.execute(new DeleteWebhook());
        if (deleteWebhookResponse.isOk()) {
            EzTgBotLogger.info("Webhook was deleted!");
        }
    }

}
