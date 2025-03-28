import java.util.ArrayList;
import java.util.List;

public class NewsAgency implements NewsPublisher {

    private List<NewsSubscriber> subscribers = new ArrayList<>();
    private String latestNews;
    @Override
    public void addSubscriber(NewsSubscriber newsSubscriber) {
        subscribers.add(newsSubscriber);
    }

    @Override
    public void removeSubscriber(NewsSubscriber newsSubscriber) {
        subscribers.remove(newsSubscriber);
    }

    @Override
    public void notifySubscribers() {
        for(NewsSubscriber newsSubscriber:subscribers){
            newsSubscriber.update(latestNews);
        }
    }

    // Method to update news and trigger notification
    public void setNews(String news){
        this.latestNews = news;
        notifySubscribers();
    }
}
