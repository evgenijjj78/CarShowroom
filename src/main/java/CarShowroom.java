import java.util.ArrayList;
import java.util.List;

public class CarShowroom {

    private List<Car> showcase = new ArrayList<>();
    private List<AutoSupplier> suppliers = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void addSupplier(AutoSupplier supplier) {
        suppliers.add(supplier);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
