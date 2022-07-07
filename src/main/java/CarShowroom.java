import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom {
    static final Deque<Car> showcase = new LinkedList<>();
    static final ReentrantLock lock = new ReentrantLock(true);
    static final Condition condition = lock.newCondition();
    static final int numCars = 10;
    static final int numCustomers = 10;
    static final int carDeliveryTime = 1000;
    static final int timeToMakePurchaseDecision = 1000;
    static final long pause = 500;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Автосалон открылся");
        Thread supplier = new Thread(new CarSupplier(showcase, lock, condition,"Toyota", numCars, carDeliveryTime));
        supplier.start();
        Thread.sleep(pause);
        for (int i = 0; i < numCustomers; i++) {
            Thread customer = new Thread(new Customer(showcase, lock, condition, timeToMakePurchaseDecision));
            customer.setDaemon(true);
            customer.start();
            Thread.sleep(pause);
        }
        supplier.join();
        Thread.sleep(pause);
        System.out.println("Автосалон закрылся");
    }
}
