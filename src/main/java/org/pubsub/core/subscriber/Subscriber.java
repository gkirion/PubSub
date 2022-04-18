package org.pubsub.core.subscriber;

import org.pubsub.core.message.Message;

public interface Subscriber {

    void receive(Message message);

}
