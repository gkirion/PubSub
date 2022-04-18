package org.pubsub.core;

import org.pubsub.core.broker.exception.AlreadySubscribedException;
import org.pubsub.core.broker.exception.TopicNotExistsException;
import org.pubsub.core.broker.service.MessageBroker;

public class Main {

    public static void main(String[] args) throws AlreadySubscribedException, TopicNotExistsException {
        MessageBroker messageBroker = new MessageBroker();
        messageBroker.subscribe("computers", message -> System.out.println(message));
        messageBroker.subscribe("supermarket", message -> System.out.println(message));
        messageBroker.subscribe("computers", message -> System.out.println(message));
        messageBroker.publish("supermarket", "get some eggs!");
        messageBroker.publish("computers", "psu 650W");
        messageBroker.publish("computers", "build a workstation!");

    }

}
