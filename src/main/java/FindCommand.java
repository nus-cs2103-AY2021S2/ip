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

    public void find(TaskList tasks, String keyword) {
        for (int i = 0; i < tasks.storage.size(); i++) {
            if (tasks.storage.get(i).getDescription().contains(keyword)) {
                findList.add(tasks.storage.get(i));
            }
        }
        System.out.println("------------------------------");
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < findList.size(); i++) {
            System.out.println((i + 1) + "." + findList.get(i));
        }
        System.out.println("------------------------------");
    }
}
