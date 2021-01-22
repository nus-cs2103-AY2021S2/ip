import task.Task;

public class Ui {
    public void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------");
        System.out.println("Hello! I'm Duke's friend, Ekud." +
                "\nDuke's dead, so I'm here to take his job." +
                "\nYou want to jot down some tasks?");
    }

    public void printOnListChange(String preMessage, Task t, int size) {
        System.out.println(preMessage);
        System.out.println(t);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void exit() {
        System.out.println("Bye Bye. Please give me 5-star rating, I still need this job." +
                "\nMuch thanks.");
    }
}
