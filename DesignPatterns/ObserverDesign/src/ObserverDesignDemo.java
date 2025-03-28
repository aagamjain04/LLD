public class ObserverDesignDemo {

    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();

        NewsSubscriber john = new EmailSubscriber("john");
        NewsSubscriber alex = new MobileSubscriber("alex");
        NewsSubscriber bob = new MobileSubscriber("bob");

        newsAgency.addSubscriber(john);
        newsAgency.addSubscriber(alex);
        newsAgency.addSubscriber(bob);

        // Publish news (all subscribers will be notified)
        newsAgency.setNews("Breaking: Major technological breakthrough!");

        newsAgency.removeSubscriber(alex);

        newsAgency.setNews("Tech conference announced for next month");
    }





}
