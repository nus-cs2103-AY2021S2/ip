/**
 * Deals with the interactions with the user.
 */
public class Ui {
    /**
     * Constructor for this Ui object.
     */
    public Ui () {
    }

    /**
     * Greets the user.
     */
    public void initialise() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");
    }

    /**
     * Tells the user how many tasks they have currently.
     * @param  tasks The TaskList object containing all tasks.
     */
    public void tasksLeft(TaskList tasks) {
        System.out.println("You currently have " + tasks.getNumItems() + " tasks.");
    }

    /**
     * Bids farewell to the user.
     */
    public void finalise() {
        System.out.println("Bye friend, see you soon!");
    }
}
