public class Customer implements Runnable {
    private final CarShowroom showroom;
    private final long timeToMakePurchaseDecision;

    public Customer(CarShowroom showroom, int timeToMakePurchaseDecision) {
        this.showroom = showroom;
        this.timeToMakePurchaseDecision = timeToMakePurchaseDecision;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println("Посетитель " + name + " вошел в магазин");
            Thread.sleep(timeToMakePurchaseDecision);
        } catch (InterruptedException ignored) { }
        Car car = showroom.buyCar();
        System.out.println("Посетилель " + name + " покинул автосалон на новеньком авто марки " + car.getBrand());
    }
}
