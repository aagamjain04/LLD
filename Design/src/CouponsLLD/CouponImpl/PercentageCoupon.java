package CouponsLLD.CouponImpl;

import CouponsLLD.Coupon;
import CouponsLLD.ShoppingCart;

public class PercentageCoupon implements Coupon {

    private final String code;
    private final double percentage;

    public PercentageCoupon(String code, double percentage) {
        this.code = code;
        this.percentage = percentage;
    }

    @Override
    public boolean isApplicable(ShoppingCart cart) {
        return cart.getTotalAmount() > 0;
    }

    @Override
    public double applyDiscount(ShoppingCart cart) {
        return cart.getTotalAmount() * (percentage/100);
    }

    @Override
    public String getCode() {
        return code;
    }
}
