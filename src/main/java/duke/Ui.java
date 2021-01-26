package duke;

public class Ui {
    public Ui() {
    }

    public void showError(Throwable e) {
        System.out.println("Error: " + e);
    }
    public void print(String message) {
        System.out.println(message);
    }
}
