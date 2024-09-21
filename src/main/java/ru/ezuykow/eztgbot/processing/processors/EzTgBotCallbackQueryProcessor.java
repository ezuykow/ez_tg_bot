package ru.ezuykow.eztgbot.processing.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.context.EzTgBotContext;
import ru.ezuykow.eztgbot.processing.executors.CallbackQueryExecutor;
import ru.ezuykow.eztgbot.utils.EzTgBotLogger;
import ru.ezuykow.eztgbot.utils.update.UpdateContentType;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EzTgBotCallbackQueryProcessor implements EzTgBotProcessor {

    private final List<CallbackQueryExecutor> callbackQueryExecutors;

    @Override
    public UpdateContentType suitFor() {
        return UpdateContentType.CALLBACK_QUERY;
    }

    @Override
    public void process() {
        String callbackData = EzTgBotContext.getUpdate().callbackQuery().data();

        Optional<CallbackQueryExecutor> suitableExecutor =
                callbackQueryExecutors.stream()
                        .filter(e -> e.suitForCallbackData().equals(callbackData))
                        .findAny();

        suitableExecutor.ifPresentOrElse(
                CallbackQueryExecutor::execute,
                () -> EzTgBotLogger.warn("Suitable executor not found for callback data [{}]",
                        callbackData)
        );
    }
}
