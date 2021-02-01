package ip.src.main.java;

/**
 * Returns a Duke bot that stores the tasks given by the user.
 */
public class Duke {
    /** Tasks stored in this task list object */
    TaskList list;

    public Duke(){
        this.list = new TaskList();
    }

    /**
     * Sends a greeting to the user.
     */
    public void greet(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
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

    public void addToList(Task task){
        this.list.addTask(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + task);
        System.out.println("Now you have " + String.valueOf(this.list.size()) + " tasks in the list.");
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
    public void printList(){

        if(this.list.Empty()){
            System.out.println("There are no tasks!");
        }
        this.list.printTasks();
    }

    /**
     * Marks a task specified in the bot list as done.
     *
     * @param id The position ,1-th based, of the task to be marked done in the bot list.
     */
    public void markTaskAsDone(int id){
        Task task = this.list.getTask(id-1);
        task.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] "+ task.content);
    }

    /**
     * Deletes a task specified in the bot list.
     *
     * @param id The position ,1-th based, of the task to be marked done in the bot list.
     */
    public void deleteTask(int id){
        Task task = this.list.getTask(id-1);
        this.list.remove(id-1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("    " + task);
        System.out.println("Now you have " + String.valueOf(this.list.size()) + " tasks in the list.");

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
