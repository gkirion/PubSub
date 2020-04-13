package pubsub.broker;

public interface Brokerable {

    public void publish(String topic, String text);
    public boolean subscribe(String topic, Receivable receivable);

}
