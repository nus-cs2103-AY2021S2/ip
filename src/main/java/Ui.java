/**
 * Deals with the interactions with the user.
 */
public class Ui {
    public Ui(){
    }

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

    public void tasksLeft(TaskList list) {
        System.out.println("You currently have " + list.getNumItems() + " tasks.");
    }

    public void finalise() {
        System.out.println("Bye friend, see you soon!");
    }
}
