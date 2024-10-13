// Separate interfaces for each responsibility
interface OrderService {
    void takeOrder();
}

interface CookingService {
    void cookFood();
}

interface PaymentService {
    void processPayment();
}

// Waiter only implements OrderService
class Waiter implements OrderService {
    @Override
    public void takeOrder() {
        System.out.println("Waiter is taking an order.");
    }
}

// Cook only implements CookingService
class Cook implements CookingService {
    @Override
    public void cookFood() {
        System.out.println("Cook is preparing food.");
    }
}

// Cashier only implements PaymentService
class Cashier implements PaymentService {
    @Override
    public void processPayment() {
        System.out.println("Cashier is processing payment.");
    }
}

public class Restaurant {
    public static void main(String[] args) {
        // Use the specific interfaces for each task
        OrderService waiter = new Waiter();
        CookingService cook = new Cook();
        PaymentService cashier = new Cashier();

        waiter.takeOrder();      // Waiter takes the order
        cook.cookFood();         // Cook prepares the food
        cashier.processPayment(); // Cashier processes the payment
    }
}
