package ru.ezuykow.eztgbot.controllers;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ezuykow.eztgbot.processing.UpdateHandler;

/**
 * Контроллер для получения update'ов от сервера Telegram по webhook
 * @author ezuykow
 */
@ConditionWebhookOn
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WebhookController {

    private final UpdateHandler updateHandler;

    @PostMapping
    public ResponseEntity<Void> newUpdate(@RequestBody Update update) {
        updateHandler.submitForProcessing(update);
        return ResponseEntity.ok().build();
    }
}
