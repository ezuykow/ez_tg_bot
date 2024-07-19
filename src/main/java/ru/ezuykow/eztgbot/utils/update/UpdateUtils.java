package ru.ezuykow.eztgbot.utils.update;

import com.pengrad.telegrambot.model.Update;

/**
 * Полезные методы для работы с update-ми
 * @author ezuykow
 */
public class UpdateUtils {

    private UpdateUtils() {}

    /**
     * Определяет и возвращает тип контента update-a {@link UpdateContentType}
     * @param update обрабатываемый update
     * @return тип контента update-a {@link UpdateContentType}
     */
    public static UpdateContentType resolveUpdateContentType(Update update) {
        if (update.message() != null) {
            return UpdateContentType.MESSAGE;
        }
        if (update.callbackQuery() != null) {
            return UpdateContentType.CALLBACK_QUERY;
        }
        if (update.editedMessage() != null) {
            return UpdateContentType.EDITED_MESSAGE;
        }
        if (update.channelPost() != null) {
            return UpdateContentType.CHANNEL_POST;
        }
        if (update.editedChannelPost() != null) {
            return UpdateContentType.EDITED_CHANNEL_POST;
        }
        if (update.inlineQuery() != null) {
            return UpdateContentType.INLINE_QUERY;
        }
        if (update.chosenInlineResult() != null) {
            return UpdateContentType.CHOSEN_INLINE_RESULT;
        }
        if (update.shippingQuery() != null) {
            return UpdateContentType.SHIPPING_QUERY;
        }
        if (update.preCheckoutQuery() != null) {
            return UpdateContentType.PRE_CHECKOUT_QUERY;
        }
        if (update.poll() != null) {
            return UpdateContentType.POLL;
        }
        if (update.pollAnswer() != null) {
            return UpdateContentType.POLL_ANSWER;
        }
        if (update.myChatMember() != null) {
            return UpdateContentType.MY_CHAT_MEMBER;
        }
        if (update.chatMember() != null) {
            return UpdateContentType.CHAT_MEMBER;
        }
        if (update.chatJoinRequest() != null) {
            return UpdateContentType.CHAT_JOIN_REQUEST;
        }
        if (update.businessConnection() != null) {
            return UpdateContentType.BUSINESS_CONNECTION;
        }
        if (update.businessMessage() != null) {
            return UpdateContentType.BUSINESS_MESSAGE;
        }
        if (update.editedBusinessMessage() != null) {
            return UpdateContentType.EDITED_BUSINESS_MESSAGE;
        }
        if (update.deletedBusinessMessages() != null) {
            return UpdateContentType.DELETED_BUSINESS_MESSAGE;
        }
        if (update.messageReaction() != null) {
            return UpdateContentType.MESSAGE_REACTION;
        }
        if (update.messageReactionCount() != null) {
            return UpdateContentType.MESSAGE_REACTION_COUNT;
        }
        if (update.chatBoost() != null) {
            return UpdateContentType.CHAT_BOOST;
        }
        if (update.removedChatBoost() != null) {
            return UpdateContentType.REMOVED_CHAT_BOOST;
        }
        return UpdateContentType.UNRESOLVED;
    }
}
