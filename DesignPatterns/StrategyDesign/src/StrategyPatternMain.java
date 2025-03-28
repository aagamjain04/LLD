class StrategyPatternMain {
    public static void main(String[] args) {
        System.out.println("Hello");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setPaymentStrategy(new UPIPaymentImpl());
        shoppingCart.checkOut(100);
        shoppingCart.setPaymentStrategy(new BankTransferPaymentImpl());
        shoppingCart.checkOut(100);
        shoppingCart.setPaymentStrategy(new CreditCardPaymentImpl());
        shoppingCart.checkOut(100);
    }
}