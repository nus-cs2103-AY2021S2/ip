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
    public static String initialise() {
        String output = "";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        output += "Hello I am\n" + logo + "\n";
        output += "What can I do for you?" + "\n";
        output += "Type \"list\" to see your tasks!" + "\n";
        output += "---------------------------------";
        return output;
    }

    /**
     * Tells the user how many tasks they have currently.
     * @param  tasks The TaskList object containing all tasks.
     */
    public static String tasksLeft(TaskList tasks) {
        String output = "";
        output += "You currently have " + tasks.getNumItems() + " tasks.";
        return output;
    }

    /**
     * Bids farewell to the user.
     */
    public void finalise() {
        System.out.println("Bye friend, see you soon!");
    }
}
