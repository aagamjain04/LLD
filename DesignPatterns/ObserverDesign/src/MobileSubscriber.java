public class MobileSubscriber implements NewsSubscriber{

    private String name;

    public MobileSubscriber(String name){
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news on mobile app: " + news);
    }
}
