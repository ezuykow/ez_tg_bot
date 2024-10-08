package ru.ezuykow.eztgbot.processing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.context.EzTgBotContext;
import ru.ezuykow.eztgbot.processing.processors.EzTgBotProcessor;
import ru.ezuykow.eztgbot.utils.EzTgBotLogger;

import java.util.List;
import java.util.Optional;

/**
 * Выбирает подходящий обработчик для текущего апдейта
 * @author ezuykow
 */
@Component
@RequiredArgsConstructor
public class ProcessorSwitcher {

    private final List<EzTgBotProcessor> processors;

    /**
     * Вызывает подходящий обработчик для текущего апдейта
     */
    public void callSuitableProcessor() {
        Optional<EzTgBotProcessor> suitableProcessor =
                processors.stream()
                        .filter(p -> p.suitFor() == EzTgBotContext.getUpdateContentType())
                        .findAny();

        suitableProcessor.ifPresentOrElse(
                EzTgBotProcessor::process,
                () -> EzTgBotLogger.info("Suitable processor for [{}] update not found!",
                        EzTgBotContext.getUpdateContentType().toString())
        );
    }
}
