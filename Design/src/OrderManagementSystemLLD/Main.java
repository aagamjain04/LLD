package OrderManagementSystemLLD;

import OrderManagementSystemLLD.Order.Order;
import OrderManagementSystemLLD.Product.Product;
import OrderManagementSystemLLD.Product.ProductCategory;
import OrderManagementSystemLLD.Product.ProductDeliverySystem;
import OrderManagementSystemLLD.User.User;
import OrderManagementSystemLLD.Warehouse.NearestWareHouseSelectionStrategy;
import OrderManagementSystemLLD.Warehouse.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Main mainObj = new Main();

        System.out.println("========== ORDER MANAGEMENT SYSTEM LLD ==========\n");

        // 1. create warehouse in the system
        System.out.println("STEP 1: Creating Warehouse and Inventory...");
        List<Warehouse> warehouseList = new ArrayList<>();
        warehouseList.add(mainObj.addWarehouseAndItsInventory());
        System.out.println("✓ Warehouse created with inventory\n");

        // 2. Create users in the system
        System.out.println("STEP 2: Creating Users...");
        List<User> userList = new ArrayList<>();
        userList.add(mainObj.createUser());
        System.out.println("✓ User created\n");

        // 3. Feed the system with initial information
        System.out.println("STEP 3: Initializing Product Delivery System...");
        ProductDeliverySystem productDeliverySystem = new ProductDeliverySystem(userList,warehouseList);
        System.out.println("✓ Product Delivery System initialized\n");

        System.out.println("STEP 4: Running Delivery Flow...\n");
        mainObj.runDeliveryFlow(productDeliverySystem,1);

        System.out.println("\n========== ORDER PROCESSING COMPLETE ==========");

    }

    private Warehouse addWarehouseAndItsInventory(){
        Warehouse warehouse = new Warehouse();
        Inventory inventory = new Inventory();

        System.out.println("  → Adding product categories to inventory:");
        inventory.addCategory(1,"Pepsi Large Cold Drink",100);
        System.out.println("    - Category: Pepsi Large Cold Drink (Price: 100)");
        inventory.addCategory(4,"Dove small soap",50);
        System.out.println("    - Category: Dove small soap (Price: 50)");

        // create products
        System.out.println("  → Adding products to inventory:");
        Product product1 = new Product();
        product1.id = 1;
        product1.productName = "Pepsi";

        Product product2 = new Product();
        product2.id = 2;
        product2.productName = "Pepsi";

        Product product3 = new Product();
        product3.id = 3;
        product3.productName = "Dove";

        inventory.addProduct(product1,1);
        System.out.println("    - Product ID 1: Pepsi added");
        inventory.addProduct(product2,1);
        System.out.println("    - Product ID 2: Pepsi added");
        inventory.addProduct(product3,4);
        System.out.println("    - Product ID 3: Dove added");

        warehouse.inventory = inventory;
        return  warehouse;
    }
    private User createUser(){
        User user =  new User();
        user.userId = 1;
        user.userName = "AJ";
        user.address = new Address(560037,"cityX","stateY");
        System.out.println("  → User Details:");
        System.out.println("    - User ID: " + user.userId);
        System.out.println("    - User Name: " + user.userName);
        System.out.println("    - Address: " + user.address.pinCode + ", " + user.address.city + ", " + user.address.state);
        return user;
    }

    private void runDeliveryFlow(ProductDeliverySystem productDeliverySystem, int userId){
        //1. get the user object
        System.out.println("  → STEP 4.1: Retrieving User Details");
        User user = productDeliverySystem.getUser(userId);
        System.out.println("    ✓ User retrieved: " + user.userName + " (ID: " + user.userId + ")\n");

        //2. get warehouse based on user preference
        System.out.println("  → STEP 4.2: Selecting Warehouse");
        Warehouse warehouse = productDeliverySystem.getWarehouse(new NearestWareHouseSelectionStrategy());
        System.out.println("    ✓ Warehouse selected\n");

        //3. get all inventory to show user
        System.out.println("  → STEP 4.3: Fetching Inventory from Warehouse");
        Inventory inventory = productDeliverySystem.getInventory(warehouse);
        System.out.println("    ✓ Inventory loaded\n");

        ProductCategory productCategoryIWantToOrder = null;
        for(ProductCategory productCategory : inventory.productCategories){

            if(productCategory.productCategoryName.equals("Pepsi Large Cold Drink")){
                productCategoryIWantToOrder = productCategory;
                break;
            }
        }

        //4. add product to cart
        System.out.println("  → STEP 4.4: Adding Products to Cart");
        if(productCategoryIWantToOrder != null) {
            productDeliverySystem.addProductToCart(user,productCategoryIWantToOrder,2);
            System.out.println("    ✓ Added 2 units of '" + productCategoryIWantToOrder.productCategoryName + "' to cart\n");
        }

        //5. place order
        System.out.println("  → STEP 4.5: Placing Order");
        Order order = productDeliverySystem.placeOrder(user,warehouse);
        System.out.println("    ✓ Order placed successfully (Order ID: " + order.orderId + ")\n");

        //6. checkout
        System.out.println("  → STEP 4.6: Processing Checkout");
        productDeliverySystem.checkout(order);
        System.out.println("    ✓ Checkout completed\n");
    }
}
