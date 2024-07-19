package ru.ezuykow.eztgbot.processing;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.ezuykow.eztgbot.context.EzTgBotContext;
import ru.ezuykow.eztgbot.utils.EzTgBotLogger;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class UpdateHandler {

    private final ApplicationContext applicationContext;
    private final ProcessorSwitcher processorSwitcher;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void submitForProcessing(Update update) {
        executorService.submit(() ->
        {
            EzTgBotContext.setContext(update);
            try {
                startProcessing();
            } catch (Exception e) {
                EzTgBotLogger.error("Exception while processing update:");
                EzTgBotLogger.stackTrace(e + "\n" + String.join("\n",
                        Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toList()));
            }
            EzTgBotContext.clearContext();
            return null;
        });
    }

    private void startProcessing() {
        try {
            UpdatePreProcessor preProcessor = applicationContext.getBean(UpdatePreProcessor.class);
            preProcessor.preProcess();
        } catch (NoUniqueBeanDefinitionException e) {
            //Если предварительный процессор не объявлен или их несколько - сразу переходим к основной обработке
        } finally {
            processorSwitcher.callSuitableProcessor();
        }
    }
}
