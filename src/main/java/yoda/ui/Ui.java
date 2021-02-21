package yoda.ui;

import yoda.task.Task;

/**
 * Ui class to handle interactions with the user.
 */
public class Ui {
    /** Valid inputs that are available to the user */
    private static final String HELP_LIST = "The commands you can use, these are:\n"
                                          + "\n"
                                          + "To add a task:\n"
                                          + "todo [description]\n"
                                          + "event [description] /at YYYY-MM-DD HHmm\n"
                                          + "deadline [description] /by YYYY-MM-DD HHmm\n"
                                          + "\n"
                                          + "To list tasks:\n"
                                          + "list\n"
                                          + "To list by type of task:\n"
                                          + "list -t/-e/-d\n"
                                          + "\n"
                                          + "To find all tasks related to a keyword:\n"
                                          + "find [keyword]\n"
                                          + "e.g. find book\n"
                                          + "\n"
                                          + "To mark task(s) as done:\n"
                                          + "done [taskNumbers_in_the_list]\n"
                                          + "e.g. done 1 3 5\n"
                                          + "To mark all tasks as done:\n"
                                          + "done all\n"
                                          + "\n"
                                          + "To delete task(s):\n"
                                          + "delete [taskNumbers_in_the_list]\n"
                                          + "e.g. delete 1 3 5\n"
                                          + "To delete all tasks:\n"
                                          + "delete all\n"
                                          + "\n"
                                          + "To exit:\n"
                                          + "bye";

    /**
     * Creates a Ui object.
     */
    public Ui() {}

    /**
     * Greets the user when Yoda is started up.
     * @return Message showing greetings.
     */
    public String greet() {
        return "Greetings, young Padawan!\n"
               + "Yoda, my name is!\n"
               + "How may I help you, hmm?\n"
               + "To get a list of all available commands, type help!";
    }

    /**
     * Bids farewell to the user after user is done using Yoda.
     * @return Message showing farewell.
     */
    public String exit() {
        return "May the force be with you!\n"
             + "See you soon, I will!\n";
    }

    /**
     * Shows user that they have entered an invalid command.
     * @return Message showing user that an invalid command was entered.
     */
    public String showError() {
        return "The greatest teacher, failure is \n"
                + "Recognise your request, I do not\n"
                + "To get a list of all available commands, use 'help'";
    }

    /**
     * Shows the help list which contains all available commands.
     * @return Message showing all available commands.
     */
    public String showHelp() {
        return HELP_LIST;
    }

    /**
     * Shows user that the task entered was added successfully.
     * @param task Task which was added.
     * @return Message showing that the task was added successfully.
     */
    public String printAddedTask(Task task) {
        return "Do or do not. There is no try.\n"
                + "Added this task to the list, I have:\n" + task;
    }

    /**
     * Shows user that the task(s) they requested for deletion have been successfully deleted.
     * @param deletedTasks Task(s) that were deleted.
     * @param numberOfTasksLeft Number of tasks left in the list.
     * @return Message showing that the task(s) were deleted successfully.
     */
    public String printDeletedTask(String deletedTasks, int numberOfTasksLeft) {
        assert numberOfTasksLeft >= 0 : "numberOfTasksLeft cannot be negative";
        return "Deleted these task(s), I have:\n" + deletedTasks
                + "\n" + numberOfTasksLeft + " tasks in the list, you have left!";
    }

    /**
     * Shows user that the task(s) they requested to be marked as done have been successfully done so.
     * @param finishedTasks Task(s) that were marked as done.
     * @param numberOfTasksLeft Number of unfinished tasks in the list.
     * @return Message showing that the task(s) were marked as done successfully.
     */
    public String printFinishedTask(String finishedTasks, int numberOfTasksLeft) {
        assert numberOfTasksLeft >= 0 : "numberOfTasksLeft cannot be negative";
        return "Well done my young Padawan!\n"
                + "Marked these task(s) as done, I have:\n"
                + finishedTasks
                + "\n" + numberOfTasksLeft + " unfinished tasks in the list, you have left!";
    }

    /**
     * Shows user all the tasks that they requested to see based on the filter given.
     * @param identifier Identifier which was used to filter the tasks.
     * @param list List of the tasks that were filtered.
     * @param listSize Number of tasks in the filtered list.
     * @return Message showing the list of tasks the user requested.
     */
    public String printTasks(String identifier, String list, int listSize) {
        assert listSize >= 0 : "listSize cannot be negative";
        if (listSize == 0) {
            return "No tasks in the list, there seem to be!";
        }
        switch(identifier) {
        case "all":
            return "All the tasks in your list, I present to you!\n"
                    + list;
        case "find":
            return "The matching tasks in your list, I present to you!\n"
                    + list;
        case "deadline":
            return "The deadline(s) in your list, I present to you!\n"
                    + list;
        case "event":
            return "The event(s) in your list, I present to you!\n"
                    + list;
        case "todo":
            return "The todo(s) in your list, I present to you!\n"
                    + list;
        case "bad":
            return "To get a specific type of task, use list -d/-t/-e.\n"
                    + "Else, the find request, you can use instead!";
        default:
            return "The greatest teacher failure is.";
        }
    }
}
