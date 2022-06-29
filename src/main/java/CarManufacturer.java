public class CarManufacturer implements Runnable {
    private CarShowroom showroom;
    private String brand;
    private int numCars;
    private long carManufacturingTime;

    public CarManufacturer(CarShowroom showroom, String brand, int numCars, int carManufacturingTime) {
        this.showroom = showroom;
        this.brand = brand;
        this.numCars = numCars;
        this.carManufacturingTime = carManufacturingTime;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("Производитель " + name + " заключил контракт на поставку " + numCars + " автомобилей марки " + brand);
        System.out.println("Производитель " + name + " приступил к выполнению контракта");
        try {
            for (int i = 0; i < numCars; i++) {
                Thread.sleep(carManufacturingTime);
                showroom.addCar(new Car(brand));
                System.out.println("Производитель " + name + " поставил автомобиль");
            }
        } catch (InterruptedException ignored) { }
        System.out.println("Производитель " + name + " выполнил условие контракта");
    }
}
