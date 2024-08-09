package com.demo.social.exception;

import java.io.Serial;

public class InvalidException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = 5477619054099558741L;

    public enum InvalidExceptionType implements ExceptionType {

        
        INVALID_TOKEN("error.server.invalid.title.token",
                "error.server.invalid.msg.token", "Invalid token : {0}");

        private final String messageKey;
        private final String titleKey;
        private final String messageCause;

        InvalidExceptionType(String titleKey, String messageKey, String messageCause) {
            this.messageKey = messageKey;
            this.titleKey = titleKey;
            this.messageCause = messageCause;
        }

        @Override
        public String getTitleKey() {
            return titleKey;
        }

        @Override
        public String getMessageKey() {
            return messageKey;
        }

        @Override
        public String getMessageCause() {
            return messageCause;
        }
    }

    public InvalidException(InvalidExceptionType type) {
        super(type);
    }

    public InvalidException(InvalidExceptionType type, Throwable cause) {
        super(type, cause);
    }

    public InvalidException(InvalidExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public InvalidException(InvalidExceptionType type, String message, Throwable cause, Object... keyParams) {
        super(type, message, cause, keyParams);
    }

    public InvalidException(InvalidExceptionType type, Object[] valueParams, Object... keyParams) {
        super(type, valueParams, keyParams);
    }

    public InvalidException(InvalidExceptionType type, Object... valueParams) {
        super(type, valueParams);
    }
}
