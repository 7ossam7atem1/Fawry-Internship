import java.util.List;

public class ShippingService {
    public static double calculateFees(List<ShippingsInterface> shippableProducts) {

        double totalWeightInGrams = shippableProducts.stream()
                .mapToDouble(ShippingsInterface::getWeight)
                .sum();


        double totalWeightInKg = totalWeightInGrams / 1000.0;

        System.out.println("** Shipment notice **");
        for (ShippingsInterface item : shippableProducts) {
            System.out.printf("1x %s    %.0f g%n", item.getName(), item.getWeight());
        }
        System.out.printf("Total package weight: %.2f kg%n%n", totalWeightInKg);


        return totalWeightInKg * 10;
    }
}
