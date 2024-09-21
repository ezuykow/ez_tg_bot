package ru.ezuykow.eztgbot.processing.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.utils.update.UpdateContentType;

/**
 * Обработчик обычных сообщений
 * @author ezuykow
 */
@Component
@RequiredArgsConstructor
public class EzTgBotMessageProcessor implements EzTgBotProcessor {

    /**
     * @return {@link UpdateContentType#MESSAGE}
     */
    @Override
    public UpdateContentType suitFor() {
        return UpdateContentType.MESSAGE;
    }

    /**
     * Обработка сообщения
     */
    @Override
    public void process() {
        //For future features
    }
}
