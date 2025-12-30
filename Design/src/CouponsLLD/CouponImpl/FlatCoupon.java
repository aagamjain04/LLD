package CouponsLLD.CouponImpl;

import CouponsLLD.Coupon;
import CouponsLLD.ShoppingCart;

public class FlatCoupon implements Coupon {

    private final String code;
    private final double flatAmount;

    public FlatCoupon(String code, double flatAmount) {
        this.code = code;
        this.flatAmount = flatAmount;
    }

    @Override
    public boolean isApplicable(ShoppingCart cart) {
        return cart.getTotalAmount() >= flatAmount;
    }

    @Override
    public double applyDiscount(ShoppingCart cart) {
        return flatAmount;
    }

    @Override
    public String getCode() {
        return code;
    }
}
