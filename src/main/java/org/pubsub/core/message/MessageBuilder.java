package org.pubsub.core.message;

public class MessageBuilder {

    private String topic;
    private String text;

    public MessageBuilder topic(String topic) {
        this.topic = topic;
        return this;
    }

    public MessageBuilder text(String text) {
        this.text = text;
        return this;
    }

    public Message build() {
        return new Message(topic, text);
    }

}
