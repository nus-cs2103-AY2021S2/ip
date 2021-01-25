package main.java.subfiles;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Greets the user upon execution of the program.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Bids the user farewell before termination of the program.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public String readCommand() {
        String s = sc.nextLine();
        return s;
    }

    public void showLine() {
        System.out.println("__________");
    }

    public void showError(String s) {
        System.out.println(s);
    }

}
