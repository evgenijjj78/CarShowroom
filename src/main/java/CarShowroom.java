import java.util.Deque;
import java.util.LinkedList;

public class CarShowroom {
    static final Deque<Car> showcase = new LinkedList<>();
    static final int numCars = 10;
    static final int numCustomers = 10;
    static final int carDeliveryTime = 1000;
    static final int timeToMakePurchaseDecision = 1000;
    static final long pause = 500;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Автосалон открылся");
        Thread manufacturer = new Thread(new CarSupplier(showcase, "Toyota", numCars, carDeliveryTime));
        manufacturer.start();
        Thread.sleep(pause);
        for (int i = 0; i < numCustomers; i++) {
            Thread customer = new Thread(new Customer(showcase, timeToMakePurchaseDecision));
            customer.setDaemon(true);
            customer.start();
            Thread.sleep(pause);
        }
        manufacturer.join();
        Thread.sleep(pause);
        System.out.println("Автосалон закрылся");
    }
}
