package pubsub.broker;

public interface Brokerable {

    public void publish(Message message);
    public void publish(String topic, String text);
    public boolean subscribe(String topic, Receivable receivable);
    public void unsubscribe(String topic, Receivable receivable);

}
