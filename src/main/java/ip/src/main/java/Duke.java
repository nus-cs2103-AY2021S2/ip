package ip.src.main.java;

import java.io.IOException;

/**
 * Returns a Duke bot that stores the tasks given by the user.
 */
public class Duke {
    /** Tasks stored in this task list object */
    TaskList list;

    public Duke() {
        this.list = new TaskList();
    }

    /**
     * Sends a greeting to the user.
     */
    public String greet() {
        String output = "";
        output += "Hello! I'm Duke! :) " + "\n";
        output += "What can I do for you?";
        return output;
    }

    public String bye(Storage storage) throws DukeException {
        try {
            storage.updateFile();
            return "Bye! See you again";
        } catch (IOException e) {
            throw new DukeException("Can't save to file!");
        }
    }

    /**
     * Echos the user input.
     *
     * @param input User input given.
     */

    public void echo(String input){
        System.out.println(input);

    }

    /**
     * Adds the given task to the Duke bot list, informs the user and prints the number of tasks in the bot list.
     *
     * @param task Task object created from user input.
     */

    public String addToList(Task task){
        this.list.addTask(task);
        String output = "";
        output += "Got it. I've added this task: " + "\n";
        output += "    " + task + "\n";
        output += "Now you have " + String.valueOf(this.list.size()) + " tasks in the list.";
        return output;
    }

    /**
     * Adds the given task to the Duke bot list quietly.
     *
     * @param task Task object created from user input.
     */

    public void addToBot(Task task){
        this.list.addTask(task);
    }

    /**
     * Prints the tasks in the bot list.
     */
    public String printList(){
        String output = "";

        if(this.list.isEmpty()){
            output += "There are no tasks!";
        } else {
            output += this.list.printTasks();
        }
        return output;
    }

    /**
     * Marks a task specified in the bot list as done.
     *
     * @param id The position ,1-th based, of the task to be marked done in the bot list.
     */
    public String markTaskAsDone(int id){
        Task task = this.list.getTask(id-1);
        task.markDone();
        String output = "";
        output += "Nice! I've marked this task as done: \n";
        output += "  [X] "+ task.content;
        return output;
    }

    /**
     * Deletes a task specified in the bot list.
     *
     * @param id The position ,1-th based, of the task to be marked done in the bot list.
     */
    public String deleteTask(int id){
        Task task = this.list.getTask(id-1);
        this.list.remove(id-1);
        String output = "";
        output += "Noted. I've removed this task: \n";
        output += "    " + task + "\n";
        output += "Now you have " + String.valueOf(this.list.size()) + " tasks in the list.";
        return output;
    }

    /**
     * Prints all the tasks with content that contain the keyword.
     *
     * @param keyword The keyword given by the user.
     */
    public String findMatchingTasks(String keyword){
        TaskList matchingTasks = this.list.findTasks(keyword);
        return matchingTasks.printMatchingTasks();
    }

    public Task getTask (int id) {
        return this.list.getTask(id);
    }

    public String editTask(Task newTask, int id) {
        this.list.editTask(newTask , id-1);
        String output = "Task has been edited. \n";
        output += this.list.printEditedTasks();
        return output;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
