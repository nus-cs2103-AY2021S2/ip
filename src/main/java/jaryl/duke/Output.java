package jaryl.duke;

import java.util.ArrayList;

/**
 * Output handles I/O, interactions with user
 */
public class Output {
    /**
     * Prints a line as a separator
     * @return returns the line as a String
     */
    public String addLine() {
        return "    ----------------------------------------------------------------------------------------------------------------------------------------------";
    }

    /**
     * Prints welcome message
     */
    public void printWelcomeMsg() {
        System.out.println(addLine());
        System.out.println("    Hello! I'm Duke, your friendly chatbot.\n    What can I do for you today?\n");
        System.out.println("    1. list                                   Lists out all existing tasks                       (e.g. list)");
        System.out.println("    2. done <task number>                     Marks the specified task number as done/undone     (e.g. done 2)");
        System.out.println("    3. todo <todo message>                    Adds the specified todo to the list                (e.g. todo homework)");
        System.out.println("    4. deadline <deadline message> <date>     Adds the specified deadline to the list            (e.g. deadline return book /by 02/12/2020 4pm)");
        System.out.println("    5. event <event message> <date>           Adds the specified event to the list               (e.g. event project meeting /at 02/12/2020 4pm)");
        System.out.println("    6. delete <task number>                   Deletes the specified task number from the list    (e.g. delete 2)");
        System.out.println("    7. bye                                    Terminate Cimori                                   (e.g. bye)");
        System.out.println(addLine());
        System.out.println();
    }

    /**
     * Prints message upon successfully adding task
     * @param task      the task to be added
     * @param numTasks  total number of existing tasks
     */
    public void printAddedTask(Task task, int numTasks) {
        System.out.println(addLine());
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
        System.out.println(addLine());
        System.out.println();
    }

    /**
     * Prints message upon successfully marking a task done
     * @param task  the task to be marked done
     */
    public void printDoneMsg(Task task) {
        System.out.println(addLine());
        if(task.getStatusIcon().equals("\u2713"))
            System.out.println("    Nice! I've marked this task as done:");
        else
            System.out.println("    Noted. I've marked this task as undone:");
        System.out.println("      " + task);
        System.out.println(addLine());
        System.out.println();
    }

    /**
     * Prints message upon successfully deleting a task
     * @param task      the task to be deleted
     * @param numTasks  total number of existing tasks
     */
    public void printDeleteMsg(Task task, int numTasks) {
        System.out.println(addLine());
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
        System.out.println(addLine());
        System.out.println();
    }

    /**
     * Prints message upon terminating the program
     */
    public void printByeMsg() {
        System.out.println(addLine() + "\n    Bye. Hope to see you again soon!\n" + addLine());
    }

    /**
     * Prints message when user inputs an illegal argument
     */
    public void printIllegalArgumentError() {
        System.out.println(addLine() + "\n    ☹ OOPS! I'm sorry, but I don't know what that means :(" + "\n" + addLine());
    }

    /**
     * Handles list command
     * @param tasksList list of tasks
     */
    public void listAction(ArrayList<Task> tasksList) throws DukeException {
        if(tasksList.size() == 0) {
            throw new EmptyListException();
        }

        System.out.println(addLine());
        for(int i = 1; i <= tasksList.size(); i++) {
            Task task = tasksList.get(i - 1);
            System.out.println("    " + i + ". " + task);
        }
        System.out.println(addLine() + "\n");
    }

    /**
     * Handles done command
     * @param tasksList     list of tasks
     * @param input         user input
     * @param dataManager   data manager which handles reading/writing data
     */
    public void doneAction(ArrayList<Task> tasksList, String input, DataManager dataManager) throws DukeException {
        String[] numArgs = input.split(" ");

        if (numArgs.length < 2) {
            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to mark done");
        } else if (Integer.parseInt(numArgs[1]) <= 0 || Integer.parseInt(numArgs[1]) > tasksList.size()) {
            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to mark done");
        }
        Task done = tasksList.get(Integer.parseInt(numArgs[1]) - 1);
        done.toggleStatus();

        printDoneMsg(done);
        dataManager.writeToFile(tasksList);
    }

    /**
     * Handles todo, deadline and event commands
     * @param tasksList     list of tasks
     * @param input         user input
     * @param dataManager   data manager which handles reading/writing data
     */
    public void addAction(ArrayList<Task> tasksList, String input, DataManager dataManager) throws DukeException {
        String taskDesc = "", dateTime = "";
        String[] checkFormat;
        String[] numArgs = input.split(" ");
        if(numArgs[0].equals("todo")) {
            if(numArgs.length > 1) {
                taskDesc = input.split("todo ")[1];
            }

            Todo todo = new Todo(taskDesc);

            tasksList.add(todo);
            printAddedTask(todo, tasksList.size());
            dataManager.writeToFile(tasksList);
        } else if(numArgs[0].equals("deadline")) {
            checkFormat = input.split(" /by ");

            if(numArgs.length >= 4 && checkFormat.length > 1) {
                taskDesc = input.split("deadline ")[1].split(" /by ")[0];
                dateTime = input.split(" /by ")[1];
            }

            Deadline deadline = new Deadline(taskDesc, dateTime);

            tasksList.add(deadline);
            printAddedTask(deadline, tasksList.size());
            dataManager.writeToFile(tasksList);
        } else if(numArgs[0].equals("event")) {
            checkFormat = input.split(" /at ");

            if(numArgs.length >= 4 && checkFormat.length > 1) {
                taskDesc = input.split("event ")[1].split(" /at ")[0];
                dateTime = input.split(" /at ")[1];
            }

            Event event = new Event(taskDesc, dateTime);

            tasksList.add(event);
            printAddedTask(event, tasksList.size());
            dataManager.writeToFile(tasksList);
        }
    }

    /**
     * Handles delete command
     * @param tasksList     list of tasks
     * @param input         user input
     * @param dataManager   data manager which handles reading/writing data
     */
    public void deleteAction(ArrayList<Task> tasksList, String input, DataManager dataManager) throws DukeException {
        String[] numArgs = input.split(" ");

        if (numArgs.length < 2) {
            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to delete");
        } else if (Integer.parseInt(numArgs[1]) <= 0 || Integer.parseInt(numArgs[1]) > tasksList.size()) {
            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to delete");
        }
        Task delete = tasksList.remove(Integer.parseInt(numArgs[1]) - 1);

        printDeleteMsg(delete, tasksList.size());
        dataManager.writeToFile(tasksList);
    }
}
