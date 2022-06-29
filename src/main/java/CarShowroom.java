import java.util.ArrayList;
import java.util.List;

public class CarShowroom {
    private List<Car> showcase = new ArrayList<>();

    public synchronized void addCar(Car car) {
        showcase.add(car);
        notify();
    }

    public synchronized Car buyCar() {
        String name = Thread.currentThread().getName();
        try {
            while (showcase.size() < 1) {
                System.out.println("Посетилель " + name + " ожидает в очереди");
                wait();
            }
        } catch (InterruptedException ignored) { }
        return showcase.remove(showcase.size() - 1);
    }

    public void addSupplierOrCustomer(Runnable task) {
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
