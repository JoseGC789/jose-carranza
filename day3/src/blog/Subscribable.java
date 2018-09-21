package blog;

public interface Subscribable {
    public void subscribe (Subscribable target);
    public void unsubscribe (Subscribable target);
}
