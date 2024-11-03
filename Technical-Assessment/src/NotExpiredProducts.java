public class NotExpiredProducts extends Product {
    public NotExpiredProducts(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired() {
        return false;
    }

}
