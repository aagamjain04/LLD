package FlipOrderNotification.channel;

import FlipOrderNotification.enums.ChannelType;

public interface NotificationChannel {
    void send(String stakeholderId,String message);

    ChannelType getType();
}
