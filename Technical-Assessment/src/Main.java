//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Product cheese = new ExpirableProducts("cheese", 100, 5, false);
            Product biscuits = new ExpirableProducts("biscuits", 150, 2, false);
            Product tv = new ShippableProducts("TV", 500, 1, 5000);
            Product chair = new ShippableProducts("Chair C120", 2250, 1, 2500);
            Product scratchCard = new NotExpiredProducts("Scratch Card", 50, 10);
            Customer customer = new Customer(5000);

            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);
            cart.add(chair, 1);
            CheckOutService.checkout(customer, cart);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}