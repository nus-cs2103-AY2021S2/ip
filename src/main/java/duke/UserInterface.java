package duke;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;

public class UserInterface {
    public BufferedWriter output;

    public UserInterface(){
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
     * Greets the user.
     */
    public String greet() {
        return "Hello! I'm Duke\n";
    }

    /*
     * Bids the user farewell.
     */
    public String goodBye() {
        return "Goodbye\n";
    }

    /*
     * Prompts the user to input a task.
     */
    public String prompt() {
        return "What can I do for you?\n";
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
        for(Task t : TaskList.taskList) {
            result += (taskNumber + ": " + t + "\n");
            this.output.flush();
            taskNumber++;
        }
        return result;
    }

    /*
     * Lists all the tasks found from the search query provided by the user.
     *
     * @throws IOException
     */
    public String listTasks(List<Task> foundTasks) throws IOException {
        int taskNumber = 1;
        String result = "There were " + foundTasks.size() + " tasks containing your keyword:\n";
        for(Task t : foundTasks){
            result += (taskNumber + ": " + t + "\n");
            taskNumber++;
        }
        return result;
    }
}
