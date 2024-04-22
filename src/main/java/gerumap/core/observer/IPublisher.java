package gerumap.core.observer;

public interface IPublisher {
    void notifySubs(Object notification);
    void addSubs(ISubscriber sub);
    void removeSubs(ISubscriber sub);
}
