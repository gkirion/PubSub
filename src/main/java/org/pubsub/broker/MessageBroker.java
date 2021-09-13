package org.pubsub.broker;

import org.pubsub.exception.AlreadySubscribedException;
import org.pubsub.exception.SubscriberNotExistsException;
import org.pubsub.exception.TopicNotExistsException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageBroker {

    protected Map<String, Set<Subscriber>> subscribers;
    private ExecutorService executorService;

    public MessageBroker() {
        this(8);
    }

    public MessageBroker(int nThreads) {
        subscribers = new HashMap<>();
        executorService = Executors.newFixedThreadPool(nThreads);
    }

    public void publish(final Message message) throws TopicNotExistsException {
        String topic = message.getTopic();
        if (!subscribers.containsKey(topic)) {
            throw new TopicNotExistsException("Topic " + topic + " does not exist");
        }
        for (final Subscriber subscriber : subscribers.get(topic)) {
            executorService.submit(() -> subscriber.receive(message));
        }
    }

    public void publish(String topic, String text) throws TopicNotExistsException {
        final Message message = new Message(topic, text);
        publish(message);
    }

    public void subscribe(String topic, Subscriber subscriber) throws AlreadySubscribedException {
        if (!subscribers.containsKey(topic)) {
            subscribers.put(topic, new HashSet<>());
        } else if (subscribers.get(topic).contains(subscriber)) {
            throw new AlreadySubscribedException("subscriber " + subscriber + " already subscribed to topic " + topic);
        }
        subscribers.get(topic).add(subscriber);
    }

    public void unsubscribe(String topic, Subscriber subscriber) throws TopicNotExistsException, SubscriberNotExistsException {
        if (!subscribers.containsKey(topic)) {
            throw new TopicNotExistsException("Topic " + topic + " does not exist");
        }
        if (!subscribers.get(topic).contains(subscriber)) {
            throw new SubscriberNotExistsException("Subscriber " + subscriber + " does not exist");
        }
        subscribers.get(topic).remove(subscriber);
        if (subscribers.get(topic).isEmpty()) {
            subscribers.remove(topic);
        }
    }

}
