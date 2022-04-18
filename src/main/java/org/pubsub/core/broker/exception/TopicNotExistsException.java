package org.pubsub.core.broker.exception;

public class TopicNotExistsException extends Exception {

    public TopicNotExistsException() {
        super();
    }

    public TopicNotExistsException(String message) {
        super(message);
    }

}
