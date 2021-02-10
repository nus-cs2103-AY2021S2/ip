package yoda.ui;

import yoda.task.Task;

/**
 * Ui class to handle interactions with the user.
 */
public class Ui {
    /** Valid inputs that are available to the user */
    private static final String HELP_LIST = "The commands you can use, these are:\n"
                                            + "To add a task:\n"
                                            + "todo [description]\n"
                                            + "event [description] /at YYYY-MM-DD HHmm\n"
                                            + "deadline [description] /by YYYY-MM-DD HHmm\n"
                                            + "To list tasks:\n"
                                            + "list (-t/-e/d)\n"
                                            + "To find all tasks related to a keyword:\n"
                                            + "find [keyword]\n"
                                            + "To mark a task as done:\n"
                                            + "done [taskNumber_in_the_list]\n"
                                            + "To delete a task:\n"
                                            + "delete [taskNumber_in_the_list]";

    /**
     * Creates a Ui object.
     */
    public Ui() {}

    /**
     * Greets the user when the Yoda chatbot is started up.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
    }

    /**
     * Bids farewell to the user after user is done using the Yoda chatbot.
     */
    public String exit() {
        return "See you soon, I will!";
    }


    public String showError() {
        return "The greatest teacher, failure is \n"
                + "Recognise your request, I do not\n"
                + showHelp();
    }

    public String showHelp() {
        return HELP_LIST;
    }

    public String printAddedTask(Task task) {
        return "Do or do not. There is no try.\n"
                + "Added this task to the list, I have:\n" + task;
    }

    public String printDeletedTask(String deletedTasks, int numberOfTasksLeft) {
        return "Deleted these task(s), I have:\n" + deletedTasks
                + "\n" + numberOfTasksLeft + " tasks in the list, you have left!";
    }

    public String printFinishedTask(String finishedTasks, int numberOfTasksLeft) {
        return "Well done my young Padawan!\n"
                + "Marked these task(s) as done, I have:\n"
                + finishedTasks
                + "\n" + numberOfTasksLeft + " unfinished tasks in the list, you have left!";
    }

    public String printTasks(String identifier, String list, int listSize) {
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
