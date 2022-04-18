package org.pubsub.core.broker.service;

public class MessageBrokerBuilder {

    private int nThreads;

    public MessageBrokerBuilder() {}

    public MessageBrokerBuilder nThreads(int nThreads) {
        this.nThreads = nThreads;
        return this;
    }

    public MessageBroker build() {
        return new MessageBroker(nThreads);
    }

}
