package FlipOrderNotification.channel;

import FlipOrderNotification.enums.ChannelType;

public class SMSNotification implements NotificationChannel{
    @Override
    public void send(String id, String msg) {
        System.out.println("[SMS] To " + id + " -> " + msg);
    }

    @Override
    public ChannelType getType() {
        return ChannelType.SMS;
    }
}
