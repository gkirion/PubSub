package pubsub.broker;

public interface Receivable {
    public void receive(Message message);
}
