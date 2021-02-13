package duke;

public class Executor {

    /**
     * Executes the bye command and quit the program. Also save the current taskList into disk.
     * @param taskList the taskList
     * @param storage storage to be written to
     * @return a string containing the goodbye message
     */
    public static String exit(TaskList taskList, Storage storage) {
        storage.write(taskList);
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Executes the add command.
     * @param taskList the taskList for the task
     * @param args arguments of the tasks
     * @return a string signalling a task has been added
     */
    public static String add(TaskList taskList, String ... args) {
        if (args[0].equals("todo")) {
            taskList.add(new ToDo(args[1]));
        } else if (args[0].equals("deadline")) {
            taskList.add(new Deadline(args[1], args[2]));
        } else if (args[0].equals("event")) {
            taskList.add(new Event(args[1], args[2]));
        }
        return ("Got it. Now I have added this "
                + "task:\n" + "  " + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Executes the markDone command.
     * @param taskList the given taskList
     * @param index the specified index
     * @return a string signalling a task has been marked as done
     */
    public static String markDone(TaskList taskList, int index) {
        taskList.get(index).markDone();
        return ("Nice! I've marked this task as done:\n"
                + "  " + taskList.get(index).toString() + "\n");
    }

    /**
     * Executes the delete command.
     * @param taskList the given taskList
     * @param index the specified index
     * @return a string signalling a task has been deleted
     */
    public static String delete(TaskList taskList, int index) {
        Task task = taskList.delete(index);
        return ("Noted. I've removed this "
                + "task:\n" + "  " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Executes the empty command.
     * @param taskList the given taskList
     * @return a string signalling the list has been emptied
     */
    public static String empty(TaskList taskList) {
        taskList.empty();
        return ("Noted. I've emptied the list.\n");
    }
    /**
     * Executes the find command.
     * @param taskList the given taskList
     * @param keywords the specified keywords
     * @return a string containing found tasks
     */
    public static String find(TaskList taskList, String ... keywords) {
        return taskList.find(keywords).toString();
    }

    /**
     * Executes the markDone command.
     * @param taskList the given taskList
     * @return a string containing all tasks from the list
     */
    public static String list(TaskList taskList) {
        return taskList.toString();
    }

}
