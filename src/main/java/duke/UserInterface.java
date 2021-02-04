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
    public void logo() throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.output.write("Hello from\n" + logo + "\n");
        this.output.flush();
    }

    /*
     * Greets the user.
     *
     * @throws IOException
     */
    public void greet() throws IOException {
        this.output.write("Hello! I'm Duke\n");
        this.output.flush();
    }

    /*
     * Bids the user farewell.
     *
     * @throws IOException
     */
    public void goodBye() throws IOException {
        this.output.write("Goodbye!\n");
        this.output.flush();
    }

    /*
     * Prompts the user to input a task.
     *
     * @throws IOException
     */
    public void prompt() throws IOException{
        this.output.write("What can I do for you?" + "\n");
        this.output.flush();
    }

    /*
     * Lets the user know that a task has been added.
     *
     * @throws IOException
     */
    public void addedTask() throws IOException {
        this.output.write("Added task!\n");
        this.output.flush();
    }

    /*
     * Lets the user know that a task has been removed.
     *
     * @throws IOException
     */
    public void removedTask() throws IOException {
        this.output.write("Removed task.\n");
        this.output.flush();
    }

    /*
     * Lets the user know that a task has been marked as complete.
     *
     * @throws IOException
     */
    public void completedTask() throws IOException {
        this.output.write("Marked task as complete!\n");
        this.output.flush();
    }

    /*
     * Lets the user know that a task has been removed.
     *
     * @throws IOException
     */
    public void foundPrompt() throws IOException {
        this.output.write("Would you like to:\n" +
                "[1] Mark a task as complete\n" +
                "[2] Remove a task\n" +
                "from the found tasks?\n");
        this.output.flush();
    }

    /*
     * Lists all the tasks currently in the task list.
     *
     * @throws IOException
     */
    public void listTasks() throws IOException {
        int taskNumber = 1;
        for(Task t : TaskList.taskList) {
            this.output.write(taskNumber + ": " + t + "\n");
            this.output.flush();
            taskNumber++;
        }
    }

    /*
     * Lists all the tasks found from the search query provided by the user.
     *
     * @throws IOException
     */
    public void listTasks(List<Task> foundTasks) throws IOException {
        int taskNumber = 1;
        for(Task t : foundTasks){
            this.output.write(taskNumber + ": " + t + "\n");
            this.output.flush();
            taskNumber++;
        }
    }
}
