package ru.ezuykow.eztgbot.processing;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import ru.ezuykow.eztgbot.configs.EzTgBotPropertiesHolder;

import java.util.List;

/**
 * Дефолтный слушательь апдейтов
 * @author ezuykow
 */
public class DefaultUpdatesListener implements UpdatesListener {

    private final UpdateHandler updateHandler;
    private final EzTgBotPropertiesHolder propertiesHolder;

    private boolean isStartup = true;

    public DefaultUpdatesListener(UpdateHandler updateHandler, EzTgBotPropertiesHolder propertiesHolder) {
        this.updateHandler = updateHandler;
        this.propertiesHolder = propertiesHolder;
    }

    @Override
    public int process(List<Update> updates) {
        if (isStartup) {
            isStartup = false;
            if (propertiesHolder.getSkipOldUpdates() != null && propertiesHolder.getSkipOldUpdates()) {
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        }
        updates.forEach(updateHandler::submitForProcessing);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
