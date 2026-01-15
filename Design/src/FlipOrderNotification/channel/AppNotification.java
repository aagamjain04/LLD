package FlipOrderNotification.channel;

import FlipOrderNotification.enums.ChannelType;

public class AppNotification implements NotificationChannel{
    @Override
    public void send(String id, String msg) {
        System.out.println("[APP_PUSH] To " + id + " -> " + msg);
    }

    @Override
    public ChannelType getType() {
        return ChannelType.APP;
    }
}
