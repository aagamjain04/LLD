package FlipOrderNotification.service;

import FlipOrderNotification.channel.AppNotification;
import FlipOrderNotification.channel.EmailNotification;
import FlipOrderNotification.channel.NotificationChannel;
import FlipOrderNotification.channel.SMSNotification;
import FlipOrderNotification.enums.ChannelType;
import FlipOrderNotification.enums.OrderEventType;
import FlipOrderNotification.models.Order;

import java.util.*;

public class NotificationService {

    private final Map<String, Set<OrderEventType>> userEventSubscription = new HashMap<>();
    private final Map<String, Set<ChannelType>> userChannelMode = new HashMap<>();
    private final Map<ChannelType, NotificationChannel> channels = new HashMap<>();

    public NotificationService(){
        registerChannel(ChannelType.APP,new AppNotification());
        registerChannel(ChannelType.SMS,new SMSNotification());
        registerChannel(ChannelType.EMAIL,new EmailNotification());
    }

    void registerChannel(ChannelType type,NotificationChannel notificationChannel){
        channels.put(type,notificationChannel);
    }


    public void subscribe(String id,OrderEventType eventType){
        userEventSubscription.computeIfAbsent(id,k -> new HashSet<>()).add(eventType);

        // default all channels enabled
        userChannelMode.putIfAbsent(id,new HashSet<>(Arrays.asList(ChannelType.values())));
    }

    public void unsubscribe(String id,OrderEventType eventType){
        if(userEventSubscription.containsKey(id)){
            userEventSubscription.get(id).remove(eventType);
            System.out.printf("[SUBSCRIPTION] %s unsubscribed from %s",id,eventType.name());
        }
    }

    public void updateChannelPreference(String id,List<ChannelType> channelTypes){
        userChannelMode.put(id,new HashSet<>(channelTypes));
    }


    public void onOrderEvent(Order order,OrderEventType eventType,String message){

        //get stakeholder ids from order
        List<String> stakeHolderIDs = List.of(order.customerId,order.sellerId,order.deliveryId);

        for(String id : stakeHolderIDs){
            if(isSubscribed(id,eventType)){
                sendNotification(id,eventType,message,order.orderId);
            }
        }
    }

    boolean isSubscribed(String id,OrderEventType eventType){
        return userEventSubscription.containsKey(id) && userEventSubscription.get(id).contains(eventType);
    }

    void sendNotification(String userId,OrderEventType eventType, String message,String orderId){
        Set<ChannelType> channelTypes = userChannelMode.getOrDefault(userId,Set.of());
        if(channelTypes.isEmpty()){
            System.out.println("User has not listed any means of communication");
            return;
        }

        for(ChannelType channel : channelTypes){
            channels.get(channel).send(userId,message);
        }
    }

}
