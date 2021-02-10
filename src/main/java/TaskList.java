import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class that handles the backend of the program,
 * storing all the tasklists of the user and handles
 * each command.
 */

public class TaskList {

    private ArrayList<Task> storage;
    private Set<Task> duplicateChecker;

    public TaskList() {
        this.storage = new ArrayList<Task>();
        this.duplicateChecker = new HashSet<Task>();
        duplicateChecker.addAll(storage);
    }

    String add(Task task) throws DukeException {
        String output = "";
        boolean hasDuplicate = checkForDuplicate(duplicateChecker, task);
        if (!hasDuplicate) {
            storage.add(task);
            output += "ALRIGHT. I HAVE ALREADY ADDED THE TASK!!!\n"
                    + task.toString()
                    + "\n"
                    + "Now you have " + storage.size()
                    + " tasks in the list.";
        } else {
            output += "Item is a duplicate and has already been added.\n"
                    + "Please check again.";
        }
        assert output != "" : "output should not be empty.";
        return output;
    }

    String done(int value) throws DukeException {
        String output = "";
        if (value <= 0 || value > storage.size()) {
            throw new DukeException("No such list item.");
        }
        storage.get(value - 1).setDone();
        System.out.println("ALRIGHT. THIS TASK HAS BEEN MARKED AS COMPLETE");
        System.out.println(storage.get(value - 1));
        System.out.println();
        output += "ALRIGHT. THIS TASK HAS BEEN MARKED AS COMPLETE\n"
                + storage.get(value - 1)
                + "\n";
        assert output != "" : "output should not be empty.";
        return output;
    }

    /**
     * Method that deletes the item from the tasklist.
     *
     * @param value the position of the item to be deleted.
     * @throws DukeException
     */
    String delete(int value) throws DukeException {
        String output = "";
        int sizeAfterDelete = storage.size() - 1;
        if (value <= 0 || value > storage.size()) {
            throw new DukeException("No such list item.");
        }
        output += "OK. TASK REMOVED.\n"
                + storage.get(value - 1)
                + "\n"
                + "Now you have "
                + sizeAfterDelete
                + " tasks in the list.";

        duplicateChecker.remove(storage.get(value - 1));
        storage.remove(value - 1);

        assert sizeAfterDelete >= 0 : "size cannot be negative.";
        assert output != "" : "output should not be empty.";
        return output;
    }

    /**
     * Method that lists out all items in the current tasklist.
     */
    String listItems() {
        String output = "";
        if (storage.isEmpty()) {
            output += "Empty List\n";
        } else {
            output += "Here are the tasks.\n";
            for (int j = 0; j < storage.size(); j++) {
                output += (j + 1) + ". "
                        + storage.get(j)
                        + "\n";
            }
        }
        assert output != "" : "output should not be empty.";
        return output;
    }

    ArrayList<Task> getStorage() {
        return this.storage;
    }

    boolean checkForDuplicate(Set<Task> tasks, Task task) {
        int originalCount = tasks.size();
        tasks.add(task);
        int postCount = tasks.size();
        return originalCount == postCount;
    }

    String clearList() {
        storage.clear();
        duplicateChecker.clear();
        return "List has been cleared.";
    }
}
