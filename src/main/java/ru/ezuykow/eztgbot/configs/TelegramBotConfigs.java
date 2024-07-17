package ru.ezuykow.eztgbot.configs;

import lombok.Getter;
import lombok.Setter;

/**
 * Конфигурация Телеграм бота
 * @author ezuykow
 */
@Getter
@Setter
public class TelegramBotConfigs {

    /**
     * URL-адрес сервера Telegram Bot API (при использовании своего сервера)
     */
    private String apiServerUrl;

    /**
     * URL-адрес для регистрации Webhook-a (для ботов на Webhook)
     */
    private String webhookUrl;

    /**
     * Таймаут запроса обновлений с сервера Telegram Bot API (для ботов на LongPolling)
     */
    private Integer longPollingTimeout;

    /**
     * username бота в Телеграм
     */
    private String username;

    /**
     * Токен бота
     */
    private String token;

    /**
     * Имя бота
     */
    private String name;

    /**
     * Описание бота
     */
    private String description;

    /**
     * Короткое описание бота
     */
    private String shortDescription;
}
