package ru.ezuykow.eztgbot.processing.processors;

import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.utils.update.UpdateContentType;

/**
 * Обработчик обычных сообщений
 * @author ezuykow
 */
@Component
public interface EzTgBotMessageProcessor extends EzTgBotProcessor {

    /**
     * @return {@link UpdateContentType#MESSAGE}
     */
    @Override
    default UpdateContentType suitFor() {
        return UpdateContentType.MESSAGE;
    }
}
