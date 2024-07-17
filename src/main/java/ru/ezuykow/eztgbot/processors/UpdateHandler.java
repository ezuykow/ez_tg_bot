package ru.ezuykow.eztgbot.processors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.configs.EzTgBotPropertiesHolder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class UpdateHandler {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void process(TelegramBot bot, Update update, EzTgBotPropertiesHolder propertiesHolder) {

    }
}
