# E-Commerce System

This project is an implementation of a simple e-commerce system, where customers can add products to a cart, check out, and receive shipping information for items requiring shipment. The system handles product details, cart management, checkout process, and calculates shipping fees for shippable items.

## Features

- **Product Management**:
    - Products are defined with attributes like `name`, `price`, and `quantity`.
    - Some products are expirable (like food items) and cannot be added to the cart if expired.
    - Some products require shipping and thus have a defined weight, while others are virtual or intangible items that don’t require shipping.

- **Cart and Checkout**:
    - Customers can add products to their cart with specified quantities, limited by the available stock.
    - Checkout calculates the subtotal, shipping fees, and total amount.
    - During checkout, shipping information is displayed for items that require shipping.
    - Balance checks ensure the customer has sufficient funds to complete the purchase.

- **Error Handling**:
    - Throws errors if a product is out of stock, expired, or if the customer’s balance is insufficient.
    - Custom exceptions are used for specific error messages, ensuring clear communication of issues during cart operations or checkout.

## Classes Overview

1. **`Product`**: Abstract class representing a product with `name`, `price`, and `quantity`.
    - **`ExpirableProducts`**: Extends `Product` to handle products that can expire.
    - **`NotExpiredProducts`**: Extends `Product` for non-expiring items like digital goods.
    - **`ShippableProducts`**: Extends `Product` and implements `ShippingsInterface` for items that require shipping.

2. **`Cart`**: Manages cart items, including adding items, calculating the subtotal, and handling validations.

3. **`CartItem`**: Represents a single item in the cart, including the product and its quantity.

4. **`Customer`**: Manages customer balance, with a method to check and update the balance during checkout.

5. **`CheckOutService`**: Handles the checkout process, including subtotal calculation, shipping fee calculation, and balance validation.

6. **`ShippingService`**: Calculates shipping fees for items implementing `ShippingsInterface`. It converts total weight from grams to kilograms and applies a fixed shipping rate per kilogram.

7. **`ShippingsInterface`**: Defines methods `getName()` and `getWeight()` to ensure consistent handling of shippable items.

## Usage Example

The main workflow is demonstrated in the `Main` class. Here’s how you can run the code:

```java
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

```

### Expected Output

Upon running the `Main` class, the following output is expected:

```plaintext
** Shipment notice **
1x TV             5000 g
1x Chair C120     2500 g
Total package weight: 7.50 kg

** Checkout receipt **
2x cheese          200.0
1x biscuits        150.0
1x TV              500.0
1x Scratch Card    50.0
1x Chair C120      2250.0
---------------------------
Subtotal           3150.0
Shipping           75.0
Amount             3225.0
Your Current balance: 1775.0

```
### Error Handling

This system includes the following error handling scenarios:

1. **Insufficient Product Quantity**: 
   - If a customer tries to add a product to the cart in a quantity exceeding what is available in stock, the system throws an error.
   - Example error: `"Insufficient quantity for the product cheese"`

2. **Expired Products**: 
   - If a customer attempts to add an expired product to the cart, the system prevents the addition and throws an error.
   - Example error: `"cheese is expired"`

3. **Insufficient Customer Balance**: 
   - During checkout, if the customer's balance is insufficient to cover the total amount (subtotal + shipping fees), the system throws an error.
   - Example error: `"Insufficient balance"`

4. **Empty Cart**: 
   - Trying to checkout when the cart is empty results in an error.
   - Example error: `"Cart is empty"`

These checks ensure that the system is robust and handles typical errors gracefully, providing informative feedback to the user.

---

### Setup and Installation

To set up and run this project locally, follow these steps:

1. **Clone the Repository**:  
   Open a terminal and clone the repository to your local machine using:
   ```bash
   git clone https://github.com/7ossam7atem1/Fawry-SWE-Internship.git
   ```
2. **Open the Project in an IDE**:
   -Open the project in a Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse) to easily edit, build, and run the code.

3. **Compile and Run**:
- Ensure Java 8 or higher is installed on your machine. You can verify the installation by running: 
 ```bash
 Java --version
  ``` 
- In your IDE, navigate to the Main.java file and run it.
- The console will display output, including the checkout receipt and any error messages for handled exceptions.

### Requirements
- Java 8 or higher
- Make sure Java is installed and properly configured in your system environment.

### Assumptions

- **Weight Conversion**: Item weights are given in grams, but shipping calculations are displayed in kilograms.
- **Fixed Shipping Rate**: Shipping fees are calculated at a fixed rate of `10.0` per kilogram.
- **Product Expiry**: Expiry status for items is predefined; expired products are blocked from being added to the cart.
- **Product Types**: Products are either `ExpirableProducts` (for perishable items) or `NotExpiredProducts` (for durable items), supporting flexible product categorization.
