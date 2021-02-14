import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * class that handles the command "find"
 * that searches the storage list and stores
 * them in the find list.
 */

public class FindCommand {


    public FindCommand() {
    }

    /**
     * Method that searches through the list of tasks and returns
     * tasks that have similar descriptions.
     *
     * @param tasks   the current tasklist
     * @param keyword the keyword used for searching
     */
    public String find(TaskList tasks, String keyword) {

        String output = "";
        boolean isEmpty = true;
        output += "--------------------------" + "\n";
        output += "Here are the matching tasks in your list:" + "\n";

        ArrayList<Task> storage = tasks.getStorage();
        output += "--------------------------" + "\n"
                + "Here are the matching tasks in your list:" + "\n";

        for (int i = 0; i < storage.size(); i++) {

            Task task = storage.get(i);
            String description = task.getDescription();
            assert description != null : "description should not be empty";

            if (description.contains(keyword)) {
                output += (i + 1) + " " + task + "\n";
                isEmpty = false;
            }
        }
        if (!isEmpty) {
            output += "No similar descriptions found. \n";
        }
        assert output != "" : "output should not be empty.";
        return output;
    }
}
