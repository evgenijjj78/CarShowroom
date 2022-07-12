import java.util.Deque;
import java.util.LinkedList;

public class CarShowroom {
    private final Deque<Car> cars = new LinkedList<>();
    private final static int numCustomers = 10;
    private final static int pause = 500;
    final int numCars = 10; // переменная не приватная для доступа к ней из класса CarSupplier
    final int deliveryTime = 1000; // переменная не приватная для доступа к ней из класса CarSupplier
    final int decisionMakingTime = 1000; // переменная не приватная для доступа к ней из класса Customer

    public static void main(String[] args) throws InterruptedException {
        CarShowroom showroom = new CarShowroom();
        System.out.println("Автосалон открылся");
        Thread supplier = new Thread(new CarSupplier(showroom, "Toyota"));
        supplier.start();
        Thread.sleep(pause);
        for (int i = 0; i < numCustomers; i++) {
            Thread customer = new Thread(new Customer(showroom));
            customer.setDaemon(true);
            customer.start();
            Thread.sleep(pause);
        }
        supplier.join();
        Thread.sleep(pause);
        System.out.println("Автосалон закрылся");
    }

    public synchronized void putCar(Car car) {
        cars.addLast(car);
        notify();
    }

    public synchronized void buyCar() {
        String name = Thread.currentThread().getName();
        try {
            while (cars.isEmpty()) {
                System.out.println("Посетилель " + name + " ожидает в очереди");
                wait();
            }
        } catch (InterruptedException ignored) {
        }
        Car car = cars.pollFirst();
        System.out.println("Посетилель " + name + " купил авто марки " + car.getBrand() + " и покинул салон");
    }
}
