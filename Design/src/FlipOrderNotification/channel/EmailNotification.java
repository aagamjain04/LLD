package FlipOrderNotification.channel;

import FlipOrderNotification.enums.ChannelType;

public class EmailNotification implements NotificationChannel{
    @Override
    public void send(String id, String msg) {
        System.out.println("[EMAIL] To " + id + " -> " + msg);
    }

    @Override
    public ChannelType getType() {
        return ChannelType.EMAIL;
    }
}
