package org.pubsub.core.broker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pubsub.core.broker.exception.AlreadySubscribedException;
import org.pubsub.core.broker.exception.SubscriberNotExistsException;
import org.pubsub.core.broker.exception.TopicNotExistsException;
import org.pubsub.core.broker.service.MessageBroker;
import org.pubsub.core.subscriber.Subscriber;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MessageBrokerTest {

    private volatile CountDownLatch countDownLatch = new CountDownLatch(2);

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
        Subscriber subscriber = message -> {

        };
        messageBroker.subscribe("computers", subscriber);
        Assertions.assertThrows(AlreadySubscribedException.class, () -> messageBroker.subscribe("computers", subscriber));
    }

    @Test
    @DisplayName("unsubscribeTopicNotExistsTest")
    public void unsubscribeTopicNotExistsTest() {
        MessageBroker messageBroker = new MessageBroker();
        Assertions.assertThrows(TopicNotExistsException.class, () -> messageBroker.unsubscribe("computers", null));
    }

    @Test
    @DisplayName("unsubscribeSubscriberNotExistsTest")
    public void unsubscribeSubscriberNotExistsTest() throws AlreadySubscribedException {
        MessageBroker messageBroker = new MessageBroker();
        Subscriber subscriber = message -> {

        };
        messageBroker.subscribe("computers", subscriber);
        Assertions.assertThrows(SubscriberNotExistsException.class, () -> messageBroker.unsubscribe("computers", message -> {}));
    }

    @Test
    @DisplayName("messageDeliveredToAllSubscribersTest")
    public void messageDeliveredToAllSubscribersTest() throws AlreadySubscribedException, InterruptedException, TopicNotExistsException {
        MessageBroker messageBroker = new MessageBroker();
        messageBroker.subscribe("computers", message -> {countDownLatch.countDown();});
        messageBroker.subscribe("computers", message -> {countDownLatch.countDown();});
        messageBroker.publish("computers", "");
        countDownLatch.await(1, TimeUnit.SECONDS);
        Assertions.assertEquals(0, countDownLatch.getCount());
    }

}
