package duke;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;

public class UserInterface {
    public BufferedWriter output;

    public UserInterface() {
        this.output = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    /*
     * Prints the Duke logo and greets the user with an initial greeting.
     *
     * @throws IOException
     */
    public String logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return ("Hello from\n" + logo + "\n");
    }

    /*
     * Bids the user farewell.
     */
    public String goodBye() {
        return "Goodbye\n";
    }

    /*
     * Lets the user know that a task has been added.
     */
    public String addedTask() {
        return "Added task!\n";
    }

    /*
     * Lists all the tasks currently in the task list.
     *
     * @throws IOException
     */
    public String listTasks() throws IOException {
        int taskNumber = 1;
        String result = "All tasks: \n";
        for (Task t : TaskList.taskList) {
            result += (taskNumber + ": " + t + "\n");
            this.output.flush();
            taskNumber++;
        }
        return result;
    }

    public String helpMenu() {
        String help = "Enter one of the following commands:\n" +
                "[1] Hello\n" +
                "[2] Todo <Description of Task>\n" +
                "[3] Deadline <Description of Task>/by <DD.MM.YYYY HH:MM>\n" +
                "[4] Event <Description of Task>/from <DD.MM.YYYY HH:MM> to <DD.MM.YYYY HH:MM>\n" +
                "[5] List\n" +
                "[6] Find <Key Word>\n" +
                "[7] Complete <Task Type> <Description>\n" +
                "[8] Remove <Task Type> <Description>\n" +
                "[9] Help\n" +
                "[10] Bye";
        return help;
    }

    /*
     * Lists all the tasks found from the search query provided by the user.
     *
     * @throws IOException
     */
    public String listTasks(List<Task> foundTasks) throws IOException {
        int taskNumber = 1;
        String result = "There were " + foundTasks.size() + " tasks containing your keyword:\n";
        for (Task t : foundTasks) {
            result += (taskNumber + ": " + t + "\n");
            taskNumber++;
        }
        return result;
    }
}
