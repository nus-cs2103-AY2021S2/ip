package duke;

public class Ui {
    private final String logo;

    public Ui(TaskList tasks, String logo) {
        this.logo = logo;
    }

    /**
     * Print the strings for the start of the program
     */
    public void printStart() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}
