// bad example - violating OCP
class NotificationService1 {
    public void sendNotification(String type,String message)
    {
        if(type.equals("email")){
            System.out.println("Sending email: " + message);
        }
        else if(type.equals("sms")){
            System.out.println("Sending sms: " + message);
        }
        // need to modify this class to add more notification types

    }
}




// good example - following OCP
interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier{
    @Override
    public void send(String message){
        System.out.println("Sending email: "+ message);
    }
}

class SmsNotifier implements Notifier{
    @Override
    public void send(String message){
        System.out.println("Sending sms: "+message);
    }
}

// Adding new notification type without changing existing code
class PushNotifier implements Notifier{
    @Override
    public void send(String message){
        System.out.println("Sending push notification: "+message);
    }
}

class NotificationService {

    final Notifier notifier;
    public NotificationService(Notifier notifier){
        this.notifier = notifier;
    }

    public void send(String message)
    {
        notifier.send(message);
    }
}


public class OpenClosedPrinciple {
    public static void main(String[] args) {
        NotificationService emailService = new NotificationService(new EmailNotifier());
        emailService.send("Hello via email");
        NotificationService smsService = new NotificationService(new SmsNotifier());
        smsService.send("Hello via sms");
        NotificationService pushService = new NotificationService(new PushNotifier());
        pushService.send("Hello via push");
    }
}
