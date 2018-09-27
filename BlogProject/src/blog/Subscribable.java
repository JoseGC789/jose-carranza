package blog;

import java.util.Set;

public interface Subscribable {
    //classes which implement this interface can be subscribed to and have subscriptions
    void subscribe (Subscribable target);
    void unsubscribe (Subscribable target);
    Set<Subscribable> getSubscriptors();

}
