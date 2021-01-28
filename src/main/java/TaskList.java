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
    static ArrayList<Task> getStorage() { return storage; }

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

    /**
     * Process the Todo task by adding it to the Task list, updating the number of tasks,
     * and printing the output message.
     * @param spl Contains the keyword and description in the array.
     */
    static void processTodo(String[] spl) {
        storage.add(new Todo(spl[1]));
        count++;
        Ui.outputMessageTask(spl[0], storage.get(count - 1));
    }

    /**
     * Process the Deadline task by adding it to the Task list, updating the number of tasks,
     * and printing the output message.
     * @param spl Contains the keyword, description and date in the array.
     */
    static void processDeadline(String[] spl, String[] spl2) {
        storage.add(new Deadline(spl[0], LocalDate.parse(spl2[0]), LocalTime.parse(spl2[1])));
        count++;
        Ui.outputMessageTask(spl[0], storage.get(count - 1));
    }

    /**
     * Process the Event task by adding it to the Task list, updating the number of tasks,
     * and printing the output message.
     * @param spl Contains the keyword, description and date in the array.
     */
    static void processEvent(String[] spl, String[] spl2) {
        storage.add(new Event(spl[0], LocalDate.parse(spl2[0]), LocalTime.parse(spl2[1])));
        count++;
        Ui.outputMessageTask(spl[0], storage.get(count - 1));
    }

    /**
     * Process the done command by updating the Task to reflect that it has been completed.
     * It also prints an output message.
     * @param spl Contains the keyword and the number of the task which has been completed.
     */
    static void processDone(String[] spl) {
        int number = Integer.parseInt(spl[1]);
        Task current = storage.get(number - 1);
        current.finished();
        Ui.outputMessageDone(current);
    }

    /**
     * Prints the output message for a list of all the tasks which contain the keyword.
     * @param spl Contains the keyword and the word that is being searched in the array.
     */
    static void processFind(String[] spl) {
        Ui.outputMessageFind(storage, spl);
    }

    /**
     * Prints the output message for a list of all the tasks to be printed out.
     */
    static void processList() {
        Ui.outputMessageList(storage, count);
    }

    /**
     * Deletes the n-numbered Task from the task list, where n is the number given by the user.
     * @param spl Contains the keyword, and the Task number that has to be deleted (n) in the array.
     */
    static void processDelete(String[] spl) {
        int num = Integer.parseInt(spl[1]);
        Task t = storage.get(num-1);
        storage.remove(num-1);
        count--;
        Ui.outputMessageDelete(t);
    }

    /**
     * Stores the task list once the user wants to leave the program.
     * Prints an output message as well.
     */
    static void processBye() {
        Storage.uploadToHardDrive();
        Ui.outputMessageBye();
    }
}
