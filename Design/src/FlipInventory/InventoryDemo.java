package FlipInventory;

import java.util.*;

/*
 Flipkart Daily - In Memory Grocery Ordering System
 Single file demo version (Interview Friendly)
*/

public class InventoryDemo {

    /* ===================== MODELS ===================== */

    static class ItemKey {
        String brand;
        String category;

        ItemKey(String brand, String category) {
            this.brand = brand;
            this.category = category;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ItemKey)) return false;
            ItemKey k = (ItemKey) o;
            return brand.equals(k.brand) && category.equals(k.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(brand, category);
        }
    }

    static class Item {
        int price;
        int quantity;

        Item(int price, int quantity) {
            this.price = price;
            this.quantity = quantity;
        }
    }

    static class User {
        String name;
        String address;
        int wallet;
        Map<ItemKey, Integer> cart = new HashMap<>();

        User(String name, String address, int wallet) {
            this.name = name;
            this.address = address;
            this.wallet = wallet;
        }
    }

    /* ===================== SERVICES ===================== */

    static class InventoryService {

        private final Map<ItemKey, Item> inventory = new HashMap<>();

        void setPrice(String brand, String category, int price) {
            ItemKey key = new ItemKey(brand, category);
            inventory.putIfAbsent(key, new Item(price, 0));
            inventory.get(key).price = price;
        }

        void addInventory(String brand, String category, int qty) {
            ItemKey key = new ItemKey(brand, category);
            inventory.putIfAbsent(key, new Item(0, 0));
            inventory.get(key).quantity += qty;
        }

        Item getItem(ItemKey key) {
            return inventory.get(key);
        }

        void reduceStock(ItemKey key, int qty) {
            inventory.get(key).quantity -= qty;
        }

        void printInventory() {
            System.out.println("\nFinal View Inventory:");
            for (Map.Entry<ItemKey, Item> e : inventory.entrySet()) {
                System.out.println(e.getKey().brand + " -> " +
                        e.getKey().category + " -> " + e.getValue().quantity);
            }
        }
    }

    static class UserService {
        private final Map<String, User> users = new HashMap<>();

        void addUser(String name, String address, int wallet) {
            users.put(name, new User(name, address, wallet));
        }

        User getUser(String name) {
            return users.get(name);
        }
    }

    static class CartService {

        InventoryService inventoryService;

        CartService(InventoryService inv) {
            this.inventoryService = inv;
        }

        void addToCart(User user, String brand, String category, int qty) {

            ItemKey key = new ItemKey(brand, category);
            Item item = inventoryService.getItem(key);

            if (item == null || item.quantity < qty) {
                System.out.println("Not enough inventory for " + brand + " " + category);
                return;
            }

            user.cart.put(key, user.cart.getOrDefault(key, 0) + qty);
            inventoryService.reduceStock(key, qty);
        }

        void viewCart(User user) {

            if (user.cart.isEmpty()) {
                System.out.println("Empty Cart");
                return;
            }

            int total = 0;
            for (Map.Entry<ItemKey, Integer> e : user.cart.entrySet()) {
                Item item = inventoryService.getItem(e.getKey());
                int price = item.price * e.getValue();
                total += price;

                System.out.println(e.getKey().brand + " -> " +
                        e.getKey().category + " -> " +
                        e.getValue() + " -> Total Price: " + price);
            }
            System.out.println("Cart Total = " + total);
        }

        int cartTotal(User user) {
            int total = 0;
            for (Map.Entry<ItemKey, Integer> e : user.cart.entrySet()) {
                Item item = inventoryService.getItem(e.getKey());
                total += item.price * e.getValue();
            }
            return total;
        }

        void clearCart(User user) {
            user.cart.clear();
        }
    }

    static class CheckoutService {

        CartService cartService;

        CheckoutService(CartService cs) {
            this.cartService = cs;
        }

        void checkout(User user) {

            if (user.cart.isEmpty()) {
                System.out.println("No order to checkout");
                return;
            }

            int total = cartService.cartTotal(user);

            if (user.wallet < total) {
                System.out.println("Checkout failed: Insufficient wallet balance");
                return;
            }

            user.wallet -= total;
            cartService.clearCart(user);

            System.out.println("Successful checkout, new wallet amount: " + user.wallet);
        }
    }

    /* ===================== DRIVER ===================== */

    public static void main(String[] args) {

        InventoryService inventory = new InventoryService();
        UserService users = new UserService();
        CartService cart = new CartService(inventory);
        CheckoutService checkout = new CheckoutService(cart);

        // Set Price
        inventory.setPrice("Amul", "Milk", 100);
        inventory.setPrice("Amul", "Curd", 50);
        inventory.setPrice("Nestle", "Milk", 60);
        inventory.setPrice("Nestle", "Curd", 90);

        // Add Inventory
        inventory.addInventory("Amul", "Milk", 10);
        inventory.addInventory("Nestle", "Milk", 15);
        inventory.addInventory("Nestle", "Curd", 10);
        inventory.addInventory("Amul", "Milk", 10);
        inventory.addInventory("Amul", "Curd", 5);

        inventory.printInventory();

        // Add Users
        users.addUser("Jhonny", "Bellandur", 600);
        users.addUser("Naruto", "BTM", 500);
        users.addUser("Goku", "Indore", 3000);

        // Add to Cart
        cart.addToCart(users.getUser("Jhonny"), "Nestle", "Milk", 5);
        cart.addToCart(users.getUser("Goku"), "Nestle", "Milk", 10);

        // View Cart
        System.out.println("\nGetCart(Goku)");
        cart.viewCart(users.getUser("Goku"));

        System.out.println("\nGetCart(Jhonny)");
        cart.viewCart(users.getUser("Jhonny"));

        System.out.println("\nGetCart(Naruto)");
        cart.viewCart(users.getUser("Naruto"));

        // Checkout
        System.out.println("\nCheckout(Goku)");
        checkout.checkout(users.getUser("Goku"));

        System.out.println("Checkout(Jhonny)");
        checkout.checkout(users.getUser("Jhonny"));

        System.out.println("Checkout(Naruto)");
        checkout.checkout(users.getUser("Naruto"));
    }
}
