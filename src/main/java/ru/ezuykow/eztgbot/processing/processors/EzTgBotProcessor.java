package ru.ezuykow.eztgbot.processing.processors;

import ru.ezuykow.eztgbot.utils.update.UpdateContentType;

public interface EzTgBotProcessor {

    UpdateContentType suitFor();

    void process();
}
