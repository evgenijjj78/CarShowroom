import java.util.Deque;

public class CarSupplier implements Runnable {
    private Deque<Car> showcase;
    private String brand;
    private int numCars;
    private long carDeliveryTime;

    public CarSupplier(Deque<Car> showcase, String brand, int numCars, int carDeliveryTime) {
        this.showcase = showcase;
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
        synchronized (showcase) {
            showcase.addLast(car);
            showcase.notify();
        }
    }
}
