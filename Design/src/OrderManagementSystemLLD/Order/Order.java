package OrderManagementSystemLLD.Order;

import OrderManagementSystemLLD.Address;
import OrderManagementSystemLLD.User.User;
import OrderManagementSystemLLD.Warehouse.Warehouse;

import java.util.Map;

public class Order {

    public String orderId;
    Address deliveryAddress;
    User user;
    Map<Integer, Integer> productCategoryAndCountMap;
    Warehouse warehouse;
    Invoice invoice;
    Payment payment;
    OrderStatus orderStatus;

    public Order(User user, Warehouse warehouse){
        this.user = user;
        this.productCategoryAndCountMap = user.getUserCartDetails().productCatIdVsCountMap;
        this.warehouse = warehouse;
        this.deliveryAddress = user.address;
        invoice = new Invoice();
        orderStatus = OrderStatus.CREATED;
        invoice.generateInvoice(this);

    }

    public void checkOut(){
        // 1. update inventory
        // 2. make payment
        // 3. make cart empty


        warehouse.removeItemFromInventory(productCategoryAndCountMap);
        boolean isPaymentDone = new Payment(new UPIPayment()).makePayment();

        if(isPaymentDone){
            user.getUserCartDetails().emptyCart();
        }else{
            warehouse.addItemToInventory(productCategoryAndCountMap);
        }
    }

    public void generateOrderInvoice(){
        invoice.generateInvoice(this);
    }


}
