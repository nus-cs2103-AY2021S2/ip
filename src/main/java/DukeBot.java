import java.util.ArrayList;

public class DukeBot {
    private String name;
    private ArrayList<Task> list;

    public DukeBot(String name) {

        this.name = name;
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("\t\tadded: " + task);
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

    public void markAsDone(int num) {
        //TODO consider adding assertion here(to prevent indexing issues)
        list.get(num-1).markAsDone();
        System.out.println("\t\tOkay! I've marked this task as done:\n\t\t" + list.get(num-1));
    }

    @Override
    public String toString() {
        return "\t\tHello! I'm " + this.name + "\n" + "\t\tWhat can I do for you?\n";
    }
}
