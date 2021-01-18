import java.sql.SQLOutput;
import java.util.ArrayList;

public class DukeBot {
    private String name;
    private ArrayList<String> list;

    public DukeBot(String name) {

        this.name = name;
        this.list = new ArrayList<>();
    }

    public void addTask(String task) {
        this.list.add(task);
        System.out.println("\t\tadded: " + task);
    }

    public void displayTasks() {
        String text = "";
        if (list.size() == 0) {
            System.out.println("There are no tasks");
        } else {
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

    @Override
    public String toString() {
        return "\t\tHello! I'm " + this.name + "\n" + "\t\tWhat can I do for you?\n";
    }
}
