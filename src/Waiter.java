public class Waiter implements Runnable{
    private final static int MAX_WAITER_MILLIS = 4000;
    private final static int N_COURSES = 3;

    private Table[] tables;
    private String waiterName;
    private String[] customerNames;
    private String[][] courses; // courses[i][j] j-th course for the i-th Customer of this Waiter

    public Waiter(Table[] tables, String waiterName, String[] customerNames, String[][] courses) {
        this.tables = tables;
        this.waiterName = waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }

    public void run() {
        for (int i = 0; i < N_COURSES; i++) {
            // serve ith course to customers
            for (int j = 0; j < tables.length; j++) {
                System.out.println(waiterName + " servers " + customerNames[j] + " " + courses[j][i]);
                try {
                    tables[j].serve(courses[j][i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(MAX_WAITER_MILLIS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Notify the customer that everything has been served
//        for (Table table : tables) {
//            try {
//                table.serve("DONE");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
