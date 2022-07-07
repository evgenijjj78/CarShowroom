import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Customer implements Runnable {
    private final Deque<Car> showcase;
    private final ReentrantLock lock;
    private final Condition condition;
    private final long timeToMakePurchaseDecision;

    public Customer(Deque<Car> showcase, ReentrantLock lock, Condition condition, int timeToMakePurchaseDecision) {
        this.showcase = showcase;
        this.lock = lock;
        this.condition = condition;
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
        lock.lock();
        try {
            while (showcase.isEmpty()) {
                System.out.println("Посетилель " + name + " ожидает в очереди");
                condition.await();
            }
            Car car = showcase.pollFirst();
            System.out.println("Посетилель " + name + " купил авто марки " + car.getBrand() + " и покинул салон");
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }
}
