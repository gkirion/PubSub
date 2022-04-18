package org.pubsub.core.message;

import java.util.Date;

public class Message {

    private String topic;
    private String text;
    private Date timestamp;

    public Message() {

    }

    public Message(String topic, String text) {
        this.topic = topic;
        this.text = text;
        this.timestamp = new Date();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
