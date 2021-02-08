import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Records the list of Tasks that is to be done.
 */
public class TaskList {
    private static final ArrayList<Task> storage = new ArrayList<>();
    private static int count = 0;

    /**
     * Getter that returns the number of tasks in the Array List.
     * @return The variable count.
     */
    static int getCount() {
        return count;
    }

    /**
     * Getter that returns the Array List of tasks.
     * @return The variable storage.
     */
    static ArrayList<Task> getStorage() {
        return storage;
    }

    /**
     * Checks if the folder and file to store the data are present.
     * Creates the folder and/or the file in case they are not there.
     */
    static void checkFileFolderSpecifications() {
        try {
            File dir = new File("./data");
            dir.mkdir();
            File f = new File("./data/tasks.txt");
            if (!f.createNewFile()) {
                count = Storage.uploadFromHardDrive();
            }

        } catch (IOException e) {
            System.out.println("error in making folder/file");
        }
    }

    static String processTaskOutput(String task, String description, LocalDate date, LocalTime time) {
        if (task.equals("todo")) {
            storage.add(new Todo(description));
        } else if (task.equals("deadline")) {
            storage.add(new Deadline(description, date, time));
        } else if (task.equals("event")) {
            storage.add(new Event(description, date, time));
        } else {
            return "Error in Task processing.";
        }
        count++;
        return Ui.outputMessageTask(task, storage.get(count - 1));
    }

    /**
     * Process the done command by updating the Task to reflect that it has been completed.
     * It also prints an output message.
     * @param doneWithIndexNumber The index number of the Task which has been done by the user.
     */
    static String processDoneOutput(int doneWithIndexNumber) {
        Task current = storage.get(doneWithIndexNumber - 1);
        current.finished();
        return Ui.outputMessageDone(current);
    }

    /**
     * Prints the output message for a list of all the tasks which contain the keyword.
     * @param spl Contains the keyword and the word that is being searched in the array.
     */
    static String processFindOutput(String[] spl) {
        String description = spl[1];
        return Ui.outputMessageFind(storage, description);
    }

    /**
     * Prints the output message for a list of all the tasks to be printed out.
     */
    static String processListOutput() {
        return Ui.outputMessageList(storage, count);
    }

    /**
     * Deletes the n-numbered Task from the task list, where n is the number given by the user.
     * @param deleteThisIndexNumber The index number of the Task which the user wants to delete.
     */
    static String processDeleteOutput(int deleteThisIndexNumber) {
        Task toDelete = storage.get(deleteThisIndexNumber - 1);
        storage.remove(deleteThisIndexNumber - 1);
        count--;
        return Ui.outputMessageDelete(toDelete);
    }

    /**
     * Stores the task list once the user wants to leave the program.
     * Prints an output message as well.
     */
    static String processBye() {
        Storage.uploadToHardDrive();
        return Ui.outputMessageBye();
    }

}
