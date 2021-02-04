import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String separator = "____________________________________________________________";
    private static ArrayList<Task> tasksList;
    private static int counter;
    private static TaskType taskType;

    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    private static void replyFormat(String reply) {
        System.out.println(separator + "\n" + reply + "\n" + separator);
    }

    private static void greet() {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        replyFormat(greeting);
    }

    private static void bye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        replyFormat(byeMessage);
    }

    private static void displayPrompts() {
        String promptMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                + "List of recognised user prompts:\n"
                                + "  1. todo - adds a todo (E.g. todo borrow book)\n"
                                + "  2. deadline - adds a deadline (E.g. deadline return book /by 2021-02-04)\n"
                                + "  3. event - adds an event (E.g. event project meeting /at 2021-03-05)\n"
                                + "  4. delete - removes a task from the lists of task\n"
                                + "  5. list - displays the list of tasks\n"
                                + "  6. bye - terminates Duke ☹";

        replyFormat(promptMessage);
    }

    // Adds a task to tasksList
    private static void addTask(String promptDescription) throws DukeException {
        String[] strings;
        String systemMessage;
        String description;
        String period;
        Task newTask;

        // Checks if string is null or empty or contains only spaces
        boolean isDescriptionFilled = (promptDescription != null && !promptDescription.trim().isEmpty());

        switch (taskType) {
            case TODO:
                if (isDescriptionFilled) {
                    counter++;
                    newTask = new ToDo(promptDescription);
                    tasksList.add(newTask);
                    systemMessage = "Got it. I've added this task:\n" + "  " + newTask + "\n" + "Now you have "
                                                + counter + (counter <= 1 ? " task" : " tasks") + " in the list.";
                    replyFormat(systemMessage);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;

            case DEADLINE:
                if (isDescriptionFilled) {
                    try {
                        strings = promptDescription.split(" /by");
                        description = strings[0];
                        period = (strings.length > 1 ? strings[1].split(" ",2)[1] : "");

                        if (period != null && !period.trim().isEmpty()) {
                            newTask = new Deadline(description, period);
                            tasksList.add(newTask);
                            counter++;
                            systemMessage = "Got it. I've added this task:\n" + "  " + newTask + "\n" + "Now you have "
                                                        + counter + (counter <= 1 ? " task" : " tasks") + " in the list.";
                            replyFormat(systemMessage);
                        } else {
                            throw new DukeException("☹ OOPS!!! The date cannot be empty.");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! Please specify the date in this format:\n"
                                                            + "  deadline [task description] /by [yyyy-mm-dd]");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                break;

            case EVENT:
                if (isDescriptionFilled) {
                    try {
                        strings = promptDescription.split(" /at");
                        description = strings[0];
                        period = (strings.length > 1 ? strings[1].split(" ", 2)[1] : "");

                        if (period != null && !period.trim().isEmpty()) {
                            newTask = new Event(description, period);
                            tasksList.add(newTask);
                            counter++;
                            systemMessage = "Got it. I've added this task:\n" + "  " + newTask + "\n" + "Now you have "
                                                        + counter + (counter <= 1 ? " task" : " tasks") + " in the list.";
                            replyFormat(systemMessage);
                        } else {
                            throw new DukeException("☹ OOPS!!! The date cannot be empty.");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! Please specify the date in this format:\n"
                                                            + "  event [task description] /at [yyyy-mm-dd]");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                break;

            default:
                displayPrompts();
        }
    }

    // Marks a task as done and informs the user about it
    private static void completeTask(String promptDescription) throws DukeException {
        try {
            int TaskNum = Integer.valueOf(promptDescription);
            if (TaskNum > 0 && TaskNum <= tasksList.size()) {
                Task taskToComplete = tasksList.get(TaskNum - 1);
                taskToComplete.markAsDone();
                String doneMessage = "Nice! I've marked this task as done:\n" + "  " + taskToComplete;
                replyFormat(doneMessage);
            } else {
                throw new DukeException("Task " + TaskNum + " is not in the task list!");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please specify a task number. (E.g. done 2)");
        }
    }

    // Deletes a task from the taskList
    private static void deleteTask(String promptDescription) throws DukeException {
        try {
            int taskNum = Integer.valueOf(promptDescription);
            if (taskNum > 0 && taskNum <= tasksList.size()) {
                counter--;
                Task taskToDelete = tasksList.get(taskNum - 1);
                String deletedMessage = "Noted. I've removed this task:\n" + "  " + taskToDelete + "\n"
                                                        + "Now you have " + counter + " tasks in the list";
                tasksList.remove(taskNum - 1);
                replyFormat(deletedMessage);
            } else {
                throw new DukeException("Task " + taskNum + " is not in the task list!");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please specify a task number. (E.g. delete 2)");
        }
    }

    // Displays all the tasks in tasksList to the user
    private static void displayTasks(ArrayList<Task> tasksList) {
        if (tasksList.size() <= 0) {
            replyFormat("There are no tasks at the moment.");
        } else {
            System.out.println(separator);
            System.out.println("Here are the tasks in your list:");

            for (int i = 1; i <= tasksList.size(); i++) {
                Task currentTask = tasksList.get(i - 1);
                System.out.println("  " + i + ". " + currentTask);
            }

            System.out.println(separator + "\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tasksList = new ArrayList<>();
        counter = 0;

        greet();
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            String[] inputArray = userInput.split(" ", 2);
            String prompt = inputArray[0];
            String promptDescription = "";

            if (inputArray.length > 1) {
                promptDescription = inputArray[1];
            }

            try {
                if (prompt.equals("list")) {
                    displayTasks(tasksList);
                } else if (prompt.equals("done")) {
                    completeTask(promptDescription);
                } else if (prompt.equals("delete")) {
                    deleteTask(promptDescription);
                } else if (prompt.equals("todo")) {
                    taskType = TaskType.TODO;
                    addTask(promptDescription);
                } else if (prompt.equals("deadline")) {
                    taskType = TaskType.DEADLINE;
                    addTask(promptDescription);
                } else if (prompt.equals("event")) {
                    taskType = TaskType.EVENT;
                    addTask(promptDescription);
                } else {
                    displayPrompts();
                }
            } catch (DukeException e){
                replyFormat(e.getMessage());
            }

            userInput = sc.nextLine();
        }

        bye();
    }
}
