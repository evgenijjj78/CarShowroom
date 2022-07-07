import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarSupplier implements Runnable {
    private Deque<Car> showcase;
    private ReentrantLock lock;
    private Condition condition;
    private String brand;
    private int numCars;
    private long carDeliveryTime;

    public CarSupplier(Deque<Car> showcase, ReentrantLock lock, Condition condition, String brand, int numCars, int carDeliveryTime) {
        this.showcase = showcase;
        this.lock = lock;
        this.condition = condition;
        this.brand = brand;
        this.numCars = numCars;
        this.carDeliveryTime = carDeliveryTime;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("Поставщик " + name + " заключил контракт на поставку " + numCars + " автомобилей марки " + brand);
        System.out.println("Поставщик " + name + " приступил к выполнению контракта");
        try {
            for (int i = 0; i < numCars; i++) {
                Thread.sleep(carDeliveryTime);
                putCar(new Car(brand));
                System.out.println("Поставщик " + name + " поставил автомобиль");
            }
        } catch (InterruptedException ignored) { }
        System.out.println("Поставщик " + name + " выполнил условие контракта");
    }

    private void putCar(Car car) {
        lock.lock();
        try {
            showcase.addLast(car);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
