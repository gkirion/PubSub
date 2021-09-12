package org.pubsub.exception;

public class TopicNotExistsException extends Exception {

    public TopicNotExistsException() {
        super();
    }

    public TopicNotExistsException(String message) {
        super(message);
    }

}
