package ru.ezuykow.eztgbot.controllers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import java.lang.annotation.*;

/**
 * Условие для создания бина Spring'ом - про присутствии параметра webhook-url
 * @author ezuykow
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnExpression("!T(org.springframework.util.StringUtils).isEmpty('${ez-tg-bot.telegram-bot-configs.webhook-url}')")
public @interface ConditionWebhookOn {
}
