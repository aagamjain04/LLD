package FlipBnplLLD;


import FlipBnplLLD.enums.PaymentType;
import FlipBnplLLD.repository.InventoryRepository;
import FlipBnplLLD.model.User;
import FlipBnplLLD.repository.*;
import FlipBnplLLD.service.InventoryService;
import FlipBnplLLD.service.OrderService;
import FlipBnplLLD.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {


            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

            InventoryRepository invRepo = new InMemoryInventoryRepository();
            UserRepository userRepo = new InMemoryUserRepository();
            OrderRepository orderRepo = new InMemoryOrderRepository();

            InventoryService inventory = new InventoryService(invRepo);
            UserService users = new UserService(userRepo);
            OrderService orders = new OrderService(inventory, orderRepo);

            inventory.seedInventory("Shoes",5,200);
            inventory.seedInventory("Watch",10,1000);
            inventory.seedInventory("T-Shirt",14,2000);

            inventory.viewInventory();

            User u = users.registerUser("Akshay",5000);
            System.out.println("\nUser Registered: " + u.getId());

            Map<String,Integer> items = new HashMap<>();
            items.put("Shoes",2);
            items.put("Watch",1);

            String orderId_1 = orders.buy(u, items, PaymentType.BNPL, LocalDate.parse("20-Oct-2021", fmt));

            inventory.viewInventory();

            orders.orderStatus(u);
            orders.viewDues(u, LocalDate.parse("19-Nov-2021", fmt));
            orders.viewDues(u, LocalDate.parse("21-Nov-2021", fmt));

            orders.clearDues(u, List.of(orderId_1),LocalDate.parse("22-Nov-2021", fmt));

            orders.viewDues(u, LocalDate.parse("20-Nov-2021", fmt));


            inventory.seedInventory("pen",10,2);
            Map<String,Integer> itemPen = new HashMap<>();
            itemPen.put("pen",1);


            //BONUS
            inventory.viewInventory();
            inventory.removeInventory("Shoes");
            inventory.viewInventory();


            //BLACKLIST CHECK
            String orderId_2 = orders.buy(u, itemPen, PaymentType.BNPL, LocalDate.parse("20-Oct-2021", fmt));
            orders.clearDues(u, List.of(orderId_2),LocalDate.parse("22-Nov-2021", fmt));
            String orderId_3 = orders.buy(u, itemPen, PaymentType.BNPL, LocalDate.parse("20-Oct-2021", fmt));
            orders.clearDues(u, List.of(orderId_3),LocalDate.parse("22-Nov-2021", fmt));


            String orderId_4 = orders.buy(u, itemPen, PaymentType.BNPL, LocalDate.parse("20-Oct-2024", fmt));





        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}