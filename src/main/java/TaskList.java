import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the backend of the program,
 * storing all the tasklists of the user and handles
 * each command.
 */

public class TaskList {

    public ArrayList<Task> storage;

    public TaskList() {
        this.storage = new ArrayList<Task>();
    }

    String add(Task task) throws DukeException {
        String output = "";
        storage.add(task);
        output += "ALRIGHT. I HAVE ALREADY ADDED THE TASK!!!\n"
                + task.toString() + "Now you have \n" + storage.size()
                + " tasks in the list.";
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
        if (value <= 0 || value > storage.size()) {
            throw new DukeException("No such list item.");
        }
        System.out.println("OK. TASK REMOVED.");
        System.out.println(storage.get(value - 1));
        storage.remove(value - 1);
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        System.out.println();
        output += "OK. TASK REMOVED.\n"
                + storage.get(value - 1)
                + "Now you have " + storage.size() + " tasks in the list.";
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
                System.out.println((j + 1) + ". " + storage.get(j));
                output += (j + 1) + ". " + storage.get(j) + "\n";
            }
        }
        return output;
    }
}
