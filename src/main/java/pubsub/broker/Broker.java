package pubsub.broker;

import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Broker implements Brokerable {

    private Map<String, Queue<Receivable>> subscribers;
    private ExecutorService executorService;

    public Broker() {
        subscribers = new HashMap<String, Queue<Receivable>>();
        executorService = Executors.newFixedThreadPool(8);
    }

    public synchronized void publish(final Message message) {
        if (subscribers.containsKey(message.getTopic())) {
            for (final Receivable receivable : subscribers.get(message.getTopic())) {
                executorService.submit(new Runnable() {
                    public void run() {
                        receivable.receive(message);
                    }
                });
            }
        }
    }

    public synchronized void publish(String topic, String text) {
        final Message message = new Message(topic, text);
        publish(message);
    }

    public synchronized boolean subscribe(String topic, Receivable receivable) {
        if (!subscribers.containsKey(topic)) {
            subscribers.put(topic, new LinkedList<Receivable>());
        }
        return subscribers.get(topic).offer(receivable);
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
