package pubsub.broker;

public class BrokerBuilder {

    private int nThreads;

    private BrokerBuilder() {}

    public BrokerBuilder newBroker() {
        return new BrokerBuilder();
    }

    public BrokerBuilder nThreads(int nThreads) {
        this.nThreads = nThreads;
        return this;
    }

    public Broker build() {
        return new Broker(nThreads);
    }

}
