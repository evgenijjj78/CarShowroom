public class SupplierCars implements Runnable {
    private CarShowroom showroom;
    private int numCars;
    private String brand;
    private long carManufacturingTime;

    public SupplierCars(CarShowroom showroom, String brand, int numCars, int carManufacturingTime) {
        this.showroom = showroom;
        this.brand = brand;
        this.numCars = numCars;
        this.carManufacturingTime = carManufacturingTime;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("Поставщик " + name + " заключил контракт на поставку " + numCars + " автомобилей марки " + brand);
        System.out.println("Поставщик " + name + " приступил к выполнению контракта");
        try {
            for (int i = 0; i < numCars; i++) {
                Thread.sleep(carManufacturingTime);
                showroom.addCar(new Car(brand));
            }
        } catch (InterruptedException ignored) { }
        System.out.println("Поставщик " + name + " выполнил условие контракта");
    }
}
