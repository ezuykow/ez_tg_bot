package ru.ezuykow.eztgbot.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import ru.ezuykow.eztgbot.utils.EzTgBotLogger;

/**
 * Конфигурация фреймворка EzTgBot - загружается с файла свойств использующего фреймворк проекта
 * @author ezuykow
 */
@ConfigurationProperties(prefix = "ez-tg-bot")
@Getter
@Setter
public class EzTgBotPropertiesHolder {

    /**
     * Конфигурация Телеграм бота
     */
    @NestedConfigurationProperty
    private TelegramBotConfigs telegramBotConfigs;

    /**
     * Конфигурация меню бота с командами.
     * Включено - true, выключено - false
     */
    private boolean botMenuEnabled;

    /**
     * Пропустить апдейты, которые были отправлены до запуска бота. Включено - true, выключено - false
     */
    private boolean skipOldUpdates;

    /**
     * Проверяет наличие токена бота в файле конфигурации
     */
    public void validateProperties() {
        if (telegramBotConfigs.getToken() == null || telegramBotConfigs.getToken().isBlank()) {
            EzTgBotLogger.error("Invalid Telegram Bot token!");
        }
    }
}
