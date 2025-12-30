package CouponsLLD;

public interface Coupon {
    boolean isApplicable(ShoppingCart cart);
    double applyDiscount(ShoppingCart cart);
    String getCode();
}
