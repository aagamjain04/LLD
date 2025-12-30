package CouponsLLD;

import CouponsLLD.CouponImpl.CategoryCoupon;
import CouponsLLD.CouponImpl.FlatCoupon;
import CouponsLLD.CouponImpl.PercentageCoupon;

import java.util.List;

public class CouponDemo {
    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("iPhone", "ELECTRONICS", 80000.0));
        cart.addItem(new Item("Shoes", "FASHION", 5000.0));

        List<Coupon> coupons = List.of(
                new PercentageCoupon("NEWUSER10", 10),
                new FlatCoupon("FLAT500", 500),
                new CategoryCoupon("ELEC20", "ELECTRONICS", 20)
        );

        CouponEngine engine = new CouponEngine();
        double discount = engine.applyCoupons(cart, coupons);

        System.out.println("Cart Total: " + cart.getTotalAmount());
        System.out.println("Discount Applied: " + discount);
        System.out.println("Final Amount: " + (cart.getTotalAmount() - discount));


    }
}
