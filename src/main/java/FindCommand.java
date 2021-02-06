import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * class that handles the command "find"
 * that searches the storage list and stores
 * them in the find list.
 */
public class FindCommand {

    public ArrayList<Task> findList;

    public FindCommand() {
        this.findList = new ArrayList<>();
    }

    public String find(TaskList tasks, String keyword) {
        String output = "";
        output += "-------------------------- +\n";
        output += "Here are the matching tasks in your list: +\n";
        for (int i = 0; i < tasks.storage.size(); i++) {
            if (tasks.storage.get(i).getDescription().contains(keyword)) {
                findList.add(tasks.storage.get(i));
                output += (i + 1) + " " + findList.get(i) + "\n";
            }
        }
        return output;
    }
}
