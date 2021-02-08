package jaryl.duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Output handles I/O, interactions with user
 */
public class Output {
    /**
     * Prints welcome message
     */
    public String printWelcomeMsg() {
        return "Hello! I'm Duke, your friendly chatbot. What can I do for you today? Type help for more information.";
    }

    /**
     * Prints message upon successfully adding task
     * @param task      the task to be added
     * @param numTasks  total number of existing tasks
     */
    public String printAddedTask(Task task, int numTasks) {
        return "Got it. I've added this task:\n\t" + task + "\nNow you have " + numTasks + " tasks in the list.";
    }

    /**
     * Handles help command
     */
    public String sendHelp() {
        return ("Duke has sent help! Here are a list of commands you can use:\n1. list\n2. done\n" +
                "3. find\n4. todo\n5. deadline\n6. event\n7. delete\n8. update\n9. help\n10. exit");
    }

    /**
     * Prints message upon successfully marking a task done
     * @param task  the task to be marked done
     */
    public String printDoneMsg(Task task) {
        if(task.getStatusIcon().equals("\u2713")) {
            return "Nice! I've marked this task as done: \n\t" + task;
        }
        else {
            return "Noted. I've marked this task as undone: \n\t" + task;
        }
    }

    public String printUpdateMsg(Task task) {
        return "Great! Here is your newly edited task: \n\t" + task;
    }

    /**
     * Prints message upon successfully deleting a task
     * @param task      the task to be deleted
     * @param numTasks  total number of existing tasks
     */
    public String printDeleteMsg(Task task, int numTasks) {
        return "Noted. I've removed this task: \n\t" + task + "\nNow you have " + numTasks + " tasks in the list.";
    }

    /**
     * Prints message for user query
     */
    public String printFind(ArrayList<Task> foundTasks, String query) {
        StringBuilder sb = new StringBuilder();
        if (foundTasks.size() == 0) {
            return "☹ OOPS! No tasks found for the query: \n\t" + query;
        }
        for (Task t : foundTasks) {
            sb.append(t);
        }
        return sb.toString();
    }

    /**
     * Prints message when user inputs an illegal argument
     */
    public String printIllegalArgumentError() {
        return "☹ OOPS! I'm sorry, but I don't know what that means :(";
    }

    /**
     * Handles list command
     * @param tasksList list of tasks
     */
    public String listAction(ArrayList<Task> tasksList) throws DukeException {
        StringBuilder sb = new StringBuilder();
        if(tasksList.size() == 0) {
            throw new EmptyListException();
        }

        for(int i = 1; i <= tasksList.size(); i++) {
            Task task = tasksList.get(i - 1);
            sb.append(i + ". " + task + "\n");
        }

        return sb.toString();
    }

    /**
     * Handles done command
     * @param tasksList     list of tasks
     * @param input         user input
     * @param dataManager   data manager which handles reading/writing data
     */
    public String doneAction(ArrayList<Task> tasksList, String input, DataManager dataManager) throws DukeException {
        String[] params = input.split(" ");

        if (params.length < 2) {
            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to mark done");
        } else if (Integer.parseInt(params[1]) <= 0 || Integer.parseInt(params[1]) > tasksList.size()) {
            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to mark done");
        }
        Task done = tasksList.get(Integer.parseInt(params[1]) - 1);
        done.toggleStatus();

        dataManager.writeToFile(tasksList);
        return(printDoneMsg(done));
    }

    public String updateAction(ArrayList<Task> tasksList, String input, DataManager dataManager) throws DukeException {
        String[] params = input.split(" ");

        if (params.length < 4) {
            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to update");
        } else if (Integer.parseInt(params[1]) <= 0 || Integer.parseInt(params[1]) > tasksList.size()) {
            throw new InvalidFormatException("Invalid event number. Please specify a valid event you would like to update");
        }

        String taskType = params[2];
        String taskDesc = "";
        String dateTime = "";
        if (taskType.equals("todo")) {
            taskDesc = input.split(" todo ")[1];
            Todo updatedTodo = new Todo(taskDesc);
            tasksList.set(Integer.parseInt(params[1]) - 1, updatedTodo);
            dataManager.writeToFile(tasksList);
            return(printUpdateMsg(updatedTodo));
        } else if (taskType.equals("deadline")) {
            taskDesc = input.split(" deadline ")[1].split(" /by ")[0];
            dateTime = input.split(" deadline ")[1].split(" /by ")[1];
            Deadline updatedDeadline = new Deadline(taskDesc, dateTime);
            tasksList.set(Integer.parseInt(params[1]) - 1, updatedDeadline);
            dataManager.writeToFile(tasksList);
            return(printUpdateMsg(updatedDeadline));
        } else if (taskType.equals("event")) {
            taskDesc = input.split(" event ")[1].split(" /at ")[0];
            dateTime = input.split(" event ")[1].split(" /by ")[1];
            Event updatedEvent = new Event(taskDesc, dateTime);
            tasksList.set(Integer.parseInt(params[1]) - 1, updatedEvent);
            dataManager.writeToFile(tasksList);
            return(printUpdateMsg(updatedEvent));
        } else {
            throw new InvalidFormatException("Invalid command. Please specify the type of task you would like to update");
        }
    }

    /**
     * Handles todo, deadline and event commands
     * @param tasksList     list of tasks
     * @param input         user input
     * @param dataManager   data manager which handles reading/writing data
     */
    public String addAction(ArrayList<Task> tasksList, String input, DataManager dataManager) throws DukeException {
        String taskDesc = "", dateTime = "";
        String[] checkFormat;
        String[] params = input.split(" ");
        if(params[0].equals("todo")) {
            if(params.length > 1) {
                taskDesc = input.split("todo ")[1];
            }

            Todo todo = new Todo(taskDesc);

            tasksList.add(todo);
            dataManager.writeToFile(tasksList);
            return printAddedTask(todo, tasksList.size());
        } else if(params[0].equals("deadline")) {
            checkFormat = input.split(" /by ");

            if(params.length >= 4 && checkFormat.length > 1) {
                taskDesc = input.split("deadline ")[1].split(" /by ")[0];
                dateTime = input.split(" /by ")[1];
            }

            Deadline deadline = new Deadline(taskDesc, dateTime);

            tasksList.add(deadline);
            dataManager.writeToFile(tasksList);
            return printAddedTask(deadline, tasksList.size());
        } else if(params[0].equals("event")) {
            checkFormat = input.split(" /at ");

            if(params.length >= 4 && checkFormat.length > 1) {
                taskDesc = input.split("event ")[1].split(" /at ")[0];
                dateTime = input.split(" /at ")[1];
            }

            Event event = new Event(taskDesc, dateTime);

            tasksList.add(event);
            dataManager.writeToFile(tasksList);
            return printAddedTask(event, tasksList.size());
        }
        return null;
    }

    /**
     * Handles delete command
     * @param tasksList     list of tasks
     * @param input         user input
     * @param dataManager   data manager which handles reading/writing data
     */
    public String deleteAction(ArrayList<Task> tasksList, String input, DataManager dataManager) throws DukeException {
        String[] params = input.split(" ");

        if (params.length < 2) {
            throw new InvalidFormatException("Please specify a valid event you would like to delete");
        } else if (Integer.parseInt(params[1]) <= 0 || Integer.parseInt(params[1]) > tasksList.size()) {
            throw new InvalidFormatException("Please specify a valid event you would like to delete");
        }
        Task delete = tasksList.remove(Integer.parseInt(params[1]) - 1);

        dataManager.writeToFile(tasksList);
        return printDeleteMsg(delete, tasksList.size());
    }

    /**
     * Handles find command
     * @param tasksList     list of tasks
     * @param input         user input
     */
    public String findAction(ArrayList<Task> tasksList, String input) throws DukeException {
        String[] params = input.split(" ");
        String toFind = "";
        ArrayList<Task> foundTasks = new ArrayList<>();

        if (params.length < 2) {
            throw new InvalidFormatException("Please specify a valid query.");
        } else {
            toFind = input.split("find ")[1];
            for (Task t : tasksList) {
                if(t.getDescription().contains(toFind)) {
                    foundTasks.add(t);
                }
            }
        }

        return printFind(foundTasks, toFind);
    }
}
