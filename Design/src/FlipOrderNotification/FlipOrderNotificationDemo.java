package FlipOrderNotification;

import FlipOrderNotification.enums.ChannelType;
import FlipOrderNotification.enums.OrderEventType;
import FlipOrderNotification.models.Order;
import FlipOrderNotification.service.NotificationService;

import java.util.Arrays;
import java.util.List;

public class FlipOrderNotificationDemo {
    public static void main(String[] args) throws InterruptedException {
        NotificationService service = new NotificationService();

        /* -------- SUBSCRIPTIONS -------- */

        // Customer
        service.subscribe("CUSTOMER-1", OrderEventType.PLACED);
        service.subscribe("CUSTOMER-1", OrderEventType.SHIPPED);
        service.subscribe("CUSTOMER-1", OrderEventType.DELIVERED);
//        service.updateChannelPreference("CUSTOMER-1",
//                List.of(ChannelType.SMS));

        // Seller
        service.subscribe("SELLER-1", OrderEventType.PLACED);
        service.updateChannelPreference("SELLER-1",
                List.of(ChannelType.EMAIL));

        // Logistics
        service.subscribe("DELIVERY-AGENT-1", OrderEventType.SHIPPED);
        service.updateChannelPreference("DELIVERY-AGENT-1",
                Arrays.asList(ChannelType.APP));

        /* -------- ORDER -------- */

        Order order1 = new Order(
                "ORDER-001",
                "CUSTOMER-1",
                "SELLER-1",
                "DELIVERY-AGENT-1"
        );

        /* -------- EVENTS -------- */

        System.out.println("\n--- ORDER PLACED ---");
        service.onOrderEvent(order1, OrderEventType.PLACED,
                "Your package has been placed");

        Thread.sleep(500);

        System.out.println("\n--- ORDER SHIPPED ---");
        service.onOrderEvent(order1, OrderEventType.SHIPPED,
                "Your order is shipped");

        Thread.sleep(500);

        System.out.println("\n--- ORDER DELIVERED ---");
        service.onOrderEvent(order1, OrderEventType.DELIVERED,
                "Your order is delivered");

        /* -------- UNSUBSCRIBE -------- */

        System.out.println("\n--- CUSTOMER UNSUBSCRIBES FROM DELIVERY ---");
        service.unsubscribe("CUSTOMER-1", OrderEventType.DELIVERED);

        System.out.println("\n--- ORDER DELIVERED AGAIN ---");
        service.onOrderEvent(order1, OrderEventType.DELIVERED,
                "Another delivery update");
    }
}
