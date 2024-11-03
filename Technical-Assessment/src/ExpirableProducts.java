public class ExpirableProducts extends Product {
    private boolean expired;

    public ExpirableProducts(String name, double price, int quantity, boolean expired) {
        super(name, price, quantity);
        this.expired = expired;

    }

    @Override
    public boolean isExpired() {
        return expired;
    }
}
