package org.pubsub.core.broker.exception;

public class AlreadySubscribedException extends Exception {

    public AlreadySubscribedException() {
        super();
    }

    public AlreadySubscribedException(String message) {
        super(message);
    }

}
