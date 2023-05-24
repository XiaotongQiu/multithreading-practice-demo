import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
    private final static int N_COURSES = 3;
    public static void main(String[] args) {
        System.out.println("Please input name of file to load: ");
//        Scanner scanner = new Scanner(System.in);
//        String fileName = scanner.next();

//        File general = new File("resources/"+fileName);
        File general = new File("resources/general.txt");
        try {
            Scanner reader = new Scanner(general);
            int waiters = reader.nextInt();

            while (reader.hasNextLine()) {
//                String[] data = reader.nextLine().split(" ");
                String waiterName = reader.next();
                int customers = reader.nextInt();

                Table[] tables = new Table[customers];
                String[] customerNames = new String[customers];
                String[][] courses = new String[customers][N_COURSES];

                for (int i = 0; i < customers; i++) {
                    String customerName = reader.next();

                    Table table = new Table();

                    new Thread(new Customer(table, customerName)).start();

                    tables[i] = table;
                    customerNames[i] = customerName;
                    int j = 0;
                    while (j < N_COURSES) {
                        courses[i][j++] = reader.next();
                    }

                }

                new Thread(new Waiter(tables, waiterName, customerNames, courses)).start();



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
