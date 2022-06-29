public class Main {
    static int numCars = 10;
    static int numCustomers = 10;
    static int carManufacturingTIme = 1000;
    static int timeToMakePurchaseDecision = 1000;
    static long showroomWorkingTime = 5000;
    static long timeIntervalBetweenVisits = 500;

    public static void main(String[] args) throws InterruptedException {
        CarShowroom showroom = new CarShowroom();
        showroom.addSupplierOrCustomer(new CarManufacturer(showroom, "Toyota", numCars, carManufacturingTIme));
        Thread.sleep(timeIntervalBetweenVisits);
        for (int i = 0; i < numCustomers; i++) {
            showroom.addSupplierOrCustomer(new Customer(showroom, timeToMakePurchaseDecision));
            Thread.sleep(timeIntervalBetweenVisits);
        }
        Thread.sleep(showroomWorkingTime);
        System.out.println("Магазин закрылся");
    }
}
