package CouponsLLD;

import java.util.List;

public class CouponEngine {

    public double applyCoupons(ShoppingCart cart, List<Coupon> coupons) {
        double totalDiscount = 0;

        for (Coupon coupon : coupons) {
            if (coupon.isApplicable(cart)) {
                totalDiscount += coupon.applyDiscount(cart);
            }
        }

        return Math.min(totalDiscount, cart.getTotalAmount());
    }
}
