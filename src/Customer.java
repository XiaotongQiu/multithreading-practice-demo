
public class Customer implements Runnable{
    private final static int MAX_CUSTOMER_MILLIS = 4000;
    private final Table table;
    private final String customerName;
    private final static int N_COURSES = 3;
    private int count = 0;

    public Customer(Table table, String customerName) {
        this.table = table;
        this.customerName = customerName;
    }

    public void run() {
        String course = null;
        while (count < N_COURSES) {
            try {
                course = table.eat();
            } catch (InterruptedException e) {
//            throw new RuntimeException(e);
            }
            count++;
            System.out.println(customerName + " is eating: " + course);
            try {
                Thread.sleep(MAX_CUSTOMER_MILLIS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
