import java.util.ArrayList;

public class DukeBot {
    private String name;
    private ArrayList<Task> list;
    public boolean isAlive;

    public DukeBot(String name) {

        this.name = name;
        this.list = new ArrayList<>();
        this.isAlive = true;
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("\t\tOkay I have added this task:\n\t\t\t" + task);
        System.out.println("\t\tNow you have " + list.size() + " tasks in the list\n");
    }

    public void displayTasks() {
        String text = "";
        if (list.size() == 0) {
            System.out.println("\t\tThere are no tasks");
        } else {
            text += "\t\tHere are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                text += "\t\t" +
                        (i+1) +
                        ". " +
                        list.get(i) +
                        "\n";
            }
            System.out.println(text);
        }
    }

    //TODO consider throwing an exception (eg. DukeException)
    public void complain() {
        System.out.println("\t\tSorry I do not understand that!");
    }

    public void exit() {
        System.out.println("\t\t" + "See you again soon!");
        this.isAlive = false;
    }

    public void markAsDone(int num) {
        //TODO consider adding assertion here(to prevent indexing issues)
        list.get(num-1).markAsDone();
        System.out.println("\t\tOkay! I've marked this task as done:\n\t\t" + list.get(num-1) + "\n");
    }

    @Override
    public String toString() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return "\t\tHello! I'm " +
                this.name + "\n" +
                "\t\tWhat can I do for you?\n";
    }
}
