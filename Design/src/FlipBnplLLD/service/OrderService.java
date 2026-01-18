package FlipBnplLLD.service;

import FlipBnplLLD.enums.PaymentStatus;
import FlipBnplLLD.enums.PaymentType;
import FlipBnplLLD.model.Order;
import FlipBnplLLD.model.OrderItem;
import FlipBnplLLD.model.Product;
import FlipBnplLLD.model.User;
import FlipBnplLLD.repository.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OrderService {

    private final InventoryService inventoryService;
    private final OrderRepository  orderDb;
    int orderSeq = 1;

    public OrderService(InventoryService inventoryService, OrderRepository orderDb) {
        this.inventoryService = inventoryService;
        this.orderDb = orderDb;
    }

    public String buy(User user, Map<String, Integer> items, PaymentType paymentType, LocalDate date) {

        int total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        if(user.isBlacklisted){
            System.out.println("User is blacklisted from buying");
            return null;
        }


        for (Map.Entry<String, Integer> e : items.entrySet()) {
            Product p = inventoryService.getProduct(e.getKey());
            if (p == null || p.getQuantity() < e.getValue()) {
                System.out.println("Insufficient stock for " + e.getKey());
                return null;
            }
            total += p.getPrice() * e.getValue();
            orderItems.add(new OrderItem(p.getName(), e.getValue(), p.getPrice()));
        }

        if (paymentType.equals(PaymentType.BNPL) && user.getAvailableCredit() < total) {
            System.out.println("BNPL credit exhausted");
            return null;
        }


        for (Map.Entry<String, Integer> e : items.entrySet()) {
            inventoryService.getProduct(e.getKey()).quantity -= e.getValue();
        }

        String orderId = "OD_" + orderSeq++;
        Order order = new Order(orderId, date, orderItems, total);

        if (paymentType.equals(PaymentType.PREPAID)) {
            order.setIsPaid(true);
        } else {
            user.availableCredit -= total;
        }

        user.getOrders().add(order);
        orderDb.save(order);
        System.out.println("Order placed successfully "+orderId  );
        return orderId;
    }


    public void orderStatus(User user) {

        System.out.println("\nORDER STATUS");
        System.out.println("BNPL Credit Available: " + user.availableCredit);

        user.orders.sort(Comparator.comparing(a -> a.purchaseDate));

        for (Order o : user.orders) {
            System.out.print(o.purchaseDate + " ");
            System.out.print(o.isPaid() ? PaymentType.PREPAID.name() : PaymentType.BNPL.name());
            System.out.print(" Amount: " + o.getTotalAmount() + " Items: ");
            for (OrderItem i : o.getItems()) {
                System.out.print("<" + i.getProductName() + "," + i.getQuantity() + "> ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void viewDues(User user, LocalDate date) {

        System.out.println("DUE STATUS");
        List<Order> dues = new ArrayList<>();
        for (Order o : user.orders) {
            if (!o.isPaid() && !o.purchaseDate.isAfter(date)) dues.add(o);
        }

        dues.sort(Comparator.comparing(a -> a.purchaseDate));

        if (dues.isEmpty()) {
            System.out.println("No dues");
            return;
        }

        for (Order o : dues) {
            LocalDate dueDate = o.purchaseDate.plusDays(30);
            PaymentStatus status = date.isAfter(dueDate) ? PaymentStatus.DELAYED : PaymentStatus.PENDING;
            System.out.println(o.purchaseDate + " Amount: " + o.getTotalAmount() +
                    " Due By: " + dueDate + " Status: " + status.name() + "\n");
        }
    }

    public void clearDues(User user, List<String> orderIds,LocalDate date) {


        for (String id : orderIds) {
            Order o = orderDb.find(id);
            if (o != null && !o.isPaid()) {

                //default check

                LocalDate dueDate = o.purchaseDate.plusDays(30);
                if (date.isAfter(dueDate)) {
                    user.defaulted++;
                    if(user.defaulted>=3){
                        user.isBlacklisted = true;
                    }
                }

                o.setIsPaid(true);
                user.availableCredit += o.getTotalAmount();
            }
        }
    }
}
