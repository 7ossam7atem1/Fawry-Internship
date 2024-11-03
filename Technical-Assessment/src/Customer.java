public class Customer {
    private double balance;

    public Customer(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void checkBalance(double amount) throws Exception {
        if (balance < amount)
            throw new Exception("Insufficient balance");
        balance = balance - amount;
    }
}
