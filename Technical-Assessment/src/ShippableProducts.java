public class ShippableProducts extends Product implements ShippingService.ShippingsInterface {
    private double weight;

    public ShippableProducts(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
