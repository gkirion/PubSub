package pubsub.main;

import pubsub.broker.Broker;
import pubsub.broker.Message;
import pubsub.broker.Receivable;

public class Main {

    public static void main(String[] args) {
        Broker broker = new Broker();
        broker.subscribe("computers", new Receivable() {
            public void receive(Message message) {
                System.out.println(message);
            }
        });
        broker.subscribe("supermarket", new Receivable() {
            public void receive(Message message) {
            System.out.println(message);
            }
        });
        broker.subscribe("computers", new Receivable() {
            public void receive(Message message) {
                System.out.println(message);
            }
        });
        broker.publish("supermarket", "get some eggs!");
        broker.publish("audio", "denon amplifier");
        broker.publish("computers", "psu 650W");
        broker.publish("computers", "build a workstation!");
        broker.shutdown();
    }

}
