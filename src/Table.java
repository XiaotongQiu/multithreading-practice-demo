import javafx.scene.control.Tab;

public class Table {
    private String course;
    private boolean isEmpty;
    private final Object lock = new Object();

    public Table() {
        this.course = "";
        this.isEmpty = true;
    }

    public void serve(String course) throws InterruptedException {
        synchronized (lock) {
            while (!this.isEmpty) {
                lock.wait();
            }
            this.isEmpty = false;
            this.course = course;
            lock.notify();
        }
    }

    public String eat() throws InterruptedException {
        synchronized (lock) {
            while (this.isEmpty) {
                lock.wait();
            }
            this.isEmpty = true;
            lock.notify();
        }
        return course;
    }
}
