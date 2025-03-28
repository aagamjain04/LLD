public class EmailSubscriber implements NewsSubscriber{
    private String name;

    public EmailSubscriber(String name){
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news via email: " + news);
    }
}
