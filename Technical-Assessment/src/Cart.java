import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) throws Exception {
        if (product.getQuantity() < quantity) {
            throw new Exception("Insufficient quantity For the Product" + product.getName());
        }
        if (product.isExpired()) {
            throw new Exception(product.getName() + " is Expired");
        }
        items.add(new CartItem(product, quantity));
        product.reduceQuantity(quantity);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

}
