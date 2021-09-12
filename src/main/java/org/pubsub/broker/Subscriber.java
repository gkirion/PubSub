package org.pubsub.broker;

public interface Subscriber {

    void receive(Message message);

}
