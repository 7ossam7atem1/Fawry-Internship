import java.util.ArrayList;
import java.util.List;

public class CheckOutService {
    public static void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) throw new Exception("Cart is empty");
        double subTotal = cart.calculateSubtotal();
        List<ShippingService.ShippingsInterface> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            if (item.getProduct() instanceof ShippingService.ShippingsInterface) {
                shippableItems.add((ShippingService.ShippingsInterface) item.getProduct());

            }
        }

        double shippingFees = ShippingService.calculateFees(shippableItems);
        double totalAmount = subTotal + shippingFees;
        customer.checkBalance(totalAmount);
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s    %.1f%n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.printf("---------------------------%nSubtotal         %.1f%nShipping         %.1f%nAmount           %.1f%n", subTotal, shippingFees, totalAmount);
        System.out.printf("Your Current balance: %.1f%n", customer.getBalance());

    }
}
