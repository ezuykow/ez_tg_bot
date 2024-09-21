package ru.ezuykow.eztgbot.processing.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.configs.EzTgBotPropertiesHolder;
import ru.ezuykow.eztgbot.context.EzTgBotContext;
import ru.ezuykow.eztgbot.processing.executors.BotCommandExecutor;
import ru.ezuykow.eztgbot.utils.EzTgBotLogger;
import ru.ezuykow.eztgbot.utils.update.UpdateContentType;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EzTgBotCommandProcessor implements EzTgBotProcessor {

    private final EzTgBotPropertiesHolder propertiesHolder;
    private final List<BotCommandExecutor> botCommandExecutors;

    @Override
    public UpdateContentType suitFor() {
        return UpdateContentType.COMMAND;
    }

    @Override
    public void process() {
        String messageText = EzTgBotContext.getUpdate().message().text();
        String commandText = parseCommandText(messageText);

        if (commandText == null) {
            EzTgBotLogger.info("Command for other bot [{}]", messageText);
            return;
        }

        Optional<BotCommandExecutor> suitableExecutor =
                botCommandExecutors.stream()
                        .filter(e -> e.suitForCommand().equals(commandText))
                        .findAny();

        suitableExecutor.ifPresentOrElse(
                BotCommandExecutor::execute,
                () -> EzTgBotLogger.warn("Unknown command [{}]", commandText)
        );
    }

    private String parseCommandText(String messageText) {
        if (messageText.contains("@")) {
            String targetUsername = messageText.substring(messageText.indexOf("@") + 1);
            String myUsername = propertiesHolder.getTelegramBotConfigs().getUsername();
            if (!targetUsername.equals(myUsername)) {
                return null;
            }
            return messageText.substring(1, messageText.indexOf("@"));
        } else {
            return messageText.substring(1);
        }
    }
}
