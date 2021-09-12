package org.pubsub.main;

import org.pubsub.broker.Message;
import org.pubsub.broker.MessageBroker;
import org.pubsub.broker.Subscriber;
import org.pubsub.exception.AlreadySubscribedException;

public class Main {

    public static void main(String[] args) throws AlreadySubscribedException {
        MessageBroker messageBroker = new MessageBroker();
        messageBroker.subscribe("computers", new Subscriber() {
            public void receive(Message message) {
                System.out.println(message);
            }
        });
        messageBroker.subscribe("supermarket", new Subscriber() {
            public void receive(Message message) {
            System.out.println(message);
            }
        });
        messageBroker.subscribe("computers", new Subscriber() {
            public void receive(Message message) {
                System.out.println(message);
            }
        });
        messageBroker.publish("supermarket", "get some eggs!");
        messageBroker.publish("audio", "denon amplifier");
        messageBroker.publish("computers", "psu 650W");
        messageBroker.publish("computers", "build a workstation!");
    }

}
