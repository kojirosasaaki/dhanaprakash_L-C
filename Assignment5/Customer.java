public class Customer {
    private String firstName, lastName;
    private Wallet wallet;

    public Customer(String firstName, String lastName, float initialMoney) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wallet = new Wallet(initialMoney);
    }

    public boolean makePayment(float amount) {
        return wallet.subtractMoney(amount);
    }

    public void depositMoney(float amount) {
        wallet.addMoney(amount);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

class Wallet {
    private float balance;

    public Wallet(float initialMoney) {
        this.balance = initialMoney;
    }

    public boolean subtractMoney(float amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addMoney(float amount) {
        balance += amount;
    }
}
