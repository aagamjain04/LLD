package OrderManagementSystemLLD.Product;

import OrderManagementSystemLLD.Controller.OrderController;
import OrderManagementSystemLLD.Controller.UserController;
import OrderManagementSystemLLD.Controller.WarehouseController;
import OrderManagementSystemLLD.Inventory;
import OrderManagementSystemLLD.Order.Order;
import OrderManagementSystemLLD.User.Cart;
import OrderManagementSystemLLD.User.User;
import OrderManagementSystemLLD.Warehouse.Warehouse;
import OrderManagementSystemLLD.Warehouse.WarehouseSelectionStrategy;

import java.util.List;

public class ProductDeliverySystem {

    UserController userController;
    WarehouseController warehouseController;
    OrderController orderController;

    public ProductDeliverySystem(List<User> userList, List<Warehouse> warehouseList){
        userController = new UserController(userList);
        warehouseController = new WarehouseController(warehouseList , null);
        orderController = new OrderController();
    }

    //get user object
    public User getUser(int userId){
        return userController.getUserById(userId);
    }

    //get warehouse
    public Warehouse getWarehouse(WarehouseSelectionStrategy warehouseSelectionStrategy){
        return warehouseController.getWarehouse(warehouseSelectionStrategy);

    }

    //get inventory
    public Inventory getInventory(Warehouse warehouse){
        return warehouse.inventory;

    }

    //add product to cart
    public void addProductToCart(User user, ProductCategory product, int count){
        Cart cart = user.getUserCartDetails();
        cart.addItemToCart(product.productCategoryId, count);
    }

    //place order
    public Order placeOrder(User user, Warehouse warehouse){
        return orderController.createNewOrder(user, warehouse);
    }

    public void checkout(Order order){
        order.checkOut();
    }

}
