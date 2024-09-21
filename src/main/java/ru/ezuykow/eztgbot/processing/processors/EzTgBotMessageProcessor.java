package ru.ezuykow.eztgbot.processing.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.utils.update.UpdateContentType;

@Component
@RequiredArgsConstructor
public class EzTgBotMessageProcessor implements EzTgBotProcessor {

    @Override
    public UpdateContentType suitFor() {
        return UpdateContentType.MESSAGE;
    }

    @Override
    public void process() {
        //For future features
    }
}
