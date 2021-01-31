package main.java;

public class Ui {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {

    }

    public static void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
