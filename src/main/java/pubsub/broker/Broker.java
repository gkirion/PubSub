package pubsub.broker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Broker implements Brokerable {

    private Map<String, Set<Receivable>> subscribers;
    private ExecutorService executorService;

    public Broker() {
        this(8);
    }

    public Broker(int nThreads) {
        subscribers = new HashMap<>();
        executorService = Executors.newFixedThreadPool(nThreads);
    }

    public synchronized void publish(final Message message) {
        if (subscribers.containsKey(message.getTopic())) {
            for (final Receivable receivable : subscribers.get(message.getTopic())) {
                executorService.submit(() -> receivable.receive(message));
            }
        }
    }

    public synchronized void publish(String topic, String text) {
        final Message message = new Message(topic, text);
        publish(message);
    }

    public synchronized boolean subscribe(String topic, Receivable receivable) {
        if (!subscribers.containsKey(topic)) {
            subscribers.put(topic, new HashSet<>());
        }
        return subscribers.get(topic).add(receivable);
    }

    public synchronized void unsubscribe(String topic, Receivable receivable) {
        if (subscribers.containsKey(topic)) {
            subscribers.get(topic).remove(receivable);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

}
