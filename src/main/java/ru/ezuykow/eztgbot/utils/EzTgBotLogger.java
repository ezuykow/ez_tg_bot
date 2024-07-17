package ru.ezuykow.eztgbot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Логгер фреймворка
 * @author ezuykow
 */
@Slf4j
@Component
public final class EzTgBotLogger {

    private EzTgBotLogger() {}

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String EZ_TG_BOT = "Ez_tg_bot: ";

    /**
     * Записывает лог уровня INFO
     */
    public static void info(String message, Object... args) {
        log.info(GREEN + EZ_TG_BOT + message + RESET, args);
    }

    /**
     * Записывает лог уровня WARN
     */
    public static void warn(String message, Object... args) {
        log.info(YELLOW + EZ_TG_BOT + message + RESET, args);
    }

    /**
     * Записывает лог уровня ERROR
     */
    public static void error(String message, Object... args) {
        log.info(RED + EZ_TG_BOT + message + RESET, args);
    }

    /**
     * Записывает в лог stack trace на уровне INFO
     */
    public static void stackTrace(String message) {
        log.info(RED + message + RESET);
    }
}
