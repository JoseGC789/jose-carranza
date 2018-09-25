package blog;

import java.util.Set;

public interface Subscribable {
    //classes which implement this interface can be subscribed to and have subscriptions
    public void subscribe (Subscribable target);
    public void unsubscribe (Subscribable target);
    public Set<Subscribable> getSubscriptors();

}
