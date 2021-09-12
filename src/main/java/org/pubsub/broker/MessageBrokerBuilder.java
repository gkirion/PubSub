package org.pubsub.broker;

public class MessageBrokerBuilder {

    private int nThreads;

    private MessageBrokerBuilder() {}

    public MessageBrokerBuilder newBroker() {
        return new MessageBrokerBuilder();
    }

    public MessageBrokerBuilder nThreads(int nThreads) {
        this.nThreads = nThreads;
        return this;
    }

    public MessageBroker build() {
        return new MessageBroker(nThreads);
    }

}
