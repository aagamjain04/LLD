package CouponsLLD.CouponImpl;

import CouponsLLD.Coupon;
import CouponsLLD.ShoppingCart;

public class CategoryCoupon implements Coupon {

    private final String code;
    private final String category;
    private final double percentage;

    public CategoryCoupon(String code, String category, double percentage) {
        this.code = code;
        this.category = category;
        this.percentage = percentage;
    }

    @Override
    public boolean isApplicable(ShoppingCart cart) {
        return cart.getItems().stream()
                .anyMatch(item -> item.getCategory().equalsIgnoreCase(category));
    }

    @Override
    public double applyDiscount(ShoppingCart cart) {
        return cart.getItems().stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .mapToDouble(item -> item.getPrice() * (percentage/100))
                .sum();
    }

    @Override
    public String getCode() {
        return code;
    }
}
