import java.util.Deque;

public class Customer implements Runnable {
    private final Deque<Car> showcase;
    private final long timeToMakePurchaseDecision;

    public Customer(Deque<Car> showcase, int timeToMakePurchaseDecision) {
        this.showcase = showcase;
        this.timeToMakePurchaseDecision = timeToMakePurchaseDecision;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println("Посетитель " + name + " вошел в салон");
            Thread.sleep(timeToMakePurchaseDecision);
        } catch (InterruptedException ignored) { }
        buyCar();
    }

    private void buyCar() {
        String name = Thread.currentThread().getName();
        synchronized (showcase) {
            try {
                while (showcase.isEmpty()) {
                    System.out.println("Посетилель " + name + " ожидает в очереди");
                    showcase.wait();
                }
            } catch (InterruptedException ignored) {
            }
            Car car = showcase.pollFirst();
            System.out.println("Посетилель " + name + " купил авто марки " + car.getBrand() + " и покинул салон");
        }
    }
}
