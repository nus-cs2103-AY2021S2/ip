package duke.system;

public class Ui {
    public void showLoadingError() {
        System.out.println("Error in loading the list");
    }

    public void showText(String input) {
        System.out.println(input);
    }

    public void showError(String input) {
        System.out.println("Error: " + input);
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }
}
