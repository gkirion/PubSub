package org.pubsub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pubsub.broker.Message;
import org.pubsub.broker.MessageBroker;
import org.pubsub.broker.Subscriber;
import org.pubsub.exception.AlreadySubscribedException;
import org.pubsub.exception.SubscriberNotExistsException;
import org.pubsub.exception.TopicNotExistsException;

public class MessageBrokerTest {

    @Test
    @DisplayName("publishTopicNotExistsTest")
    public void publishTopicNotExistsTest() {
        MessageBroker messageBroker = new MessageBroker();
        Assertions.assertThrows(TopicNotExistsException.class, () -> messageBroker.publish("computers", ""));
    }

    @Test
    @DisplayName("subscriberAlreadyExistsTest")
    public void subscriberAlreadyExistsTest() throws AlreadySubscribedException {
        MessageBroker messageBroker = new MessageBroker();
        Subscriber subscriber = new Subscriber() {
            @Override
            public void receive(Message message) {

            }
        };
        messageBroker.subscribe("computers", subscriber);
        Assertions.assertThrows(AlreadySubscribedException.class, () -> messageBroker.subscribe("computers", subscriber));
    }

    @Test
    @DisplayName("unsubscribeTopicNotExistsTest")
    public void unsubscribeTopicNotExistsTest() throws SubscriberNotExistsException, TopicNotExistsException {
        MessageBroker messageBroker = new MessageBroker();
        Assertions.assertThrows(TopicNotExistsException.class, () -> messageBroker.unsubscribe("computers", null));
    }

    @Test
    @DisplayName("unsubscribeSubscriberNotExistsTest")
    public void unsubscribeSubscriberNotExistsTest() throws SubscriberNotExistsException, TopicNotExistsException, AlreadySubscribedException {
        MessageBroker messageBroker = new MessageBroker();
        Subscriber subscriber = new Subscriber() {
            @Override
            public void receive(Message message) {

            }
        };
        messageBroker.subscribe("computers", subscriber);
        Assertions.assertThrows(SubscriberNotExistsException.class, () -> messageBroker.unsubscribe("computers", null));
    }

}
