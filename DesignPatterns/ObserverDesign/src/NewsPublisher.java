// Subject (observable)
public interface NewsPublisher {
    void addSubscriber(NewsSubscriber newsSubscriber);
    void removeSubscriber(NewsSubscriber newsSubscriber);
    void notifySubscribers();
}
