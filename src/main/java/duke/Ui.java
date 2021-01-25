package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private static final String BORDER = "\t___________________________________\n";

    public Ui() {
        greetUser();
        sc = new Scanner(System.in);
    }

    public String nextCommand() {
        return sc.nextLine();
    }

    public void greetUser() {
        String output = " Hello! I'm Duke\n" + "\t What can I do for you?";
        display(output);
    }

    public void display(String message) {
        System.out.println(BORDER + "\t" + message + "\n" + BORDER);
    }
}
