import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Start up greeting message
        String greetingMessage = "Hello! I'm a Chat bot and my name "
                + "is Joe" + "\nHow may I help you?";
        System.out.println(formatMessage(greetingMessage));

        // Task list
        List<Task> taskList = new ArrayList<>();
        loadTasksIntoTaskList(taskList);
        Scanner sc = new Scanner(System.in);

        boolean isChatBotOnline = true;
        while (isChatBotOnline) {
            // Listen to input
            String input = sc.nextLine();
            String inputCommand = formatCommand(input);
            Commands command = Commands.get(inputCommand);
            String additionalText = formatCommandText(input);

            // Echoing the input
            switch (command) {
                case TODO:
                    try {
                        Todos todos = new Todos(additionalText);
                        taskList.add(todos);
                        saveTasksToInternalStorage(taskList);
                        printTaskAddedMessage(todos);
                    } catch (EmptyTaskDukeException e) {
                        System.out.println(formatMessage(e.getMessage()));
                    }
                    break;
                case DEADLINE:
                    try {
                        Deadlines deadlines = new Deadlines(additionalText);
                        taskList.add(deadlines);
                        saveTasksToInternalStorage(taskList);
                        printTaskAddedMessage(deadlines);
                    } catch (EmptyTaskDukeException e) {
                        System.out.println(formatMessage(e.getMessage()));
                    } catch (DateTimeParseException e) {
                        System.out.println(formatMessage(e.getMessage()
                                + "\nError! Invalid time/date format."
                                + "\nPlease input a valid time/date format!"));
                    }
                    break;
                case EVENT:
                    try {
                        Events events = new Events(additionalText);
                        taskList.add(events);
                        saveTasksToInternalStorage(taskList);
                        printTaskAddedMessage(events);
                    } catch (EmptyTaskDukeException e) {
                        System.out.println(formatMessage(e.getMessage()));
                    } catch (DateTimeParseException e) {
                        System.out.println(formatMessage(e.getMessage()
                                + "\nError! Invalid time/date format."
                                + "\nPlease input a valid time/date format!"));
                    }
                    break;
                case LIST:
                    System.out.println(formatMessage(getTaskListString(taskList)));
                    break;
                case DONE:
                    try {
                        int taskNumber = Integer.parseInt(additionalText);
                        int arrayNumber = taskNumber - 1;
                        Task task = taskList.get(arrayNumber);
                        String doneMessage = task.setDone();
                        saveTasksToInternalStorage(taskList);
                        System.out.println(formatMessage(doneMessage));
                    } catch (NumberFormatException e) {
                        System.out.println(formatMessage(e
                                + "\nError! Invalid task number."
                                + "\nPlease input a valid task number!"));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e
                                + "\nError! Task number does not exist."
                                + "\nPlease input a valid task number!");
                    }
                    break;
                case DELETE:
                    try {
                        int taskNumber = Integer.parseInt(additionalText);
                        int arrayNumber = taskNumber - 1;
                        Task task = taskList.get(arrayNumber);
                        taskList.remove(arrayNumber);
                        decrementNumOfTask();
                        saveTasksToInternalStorage(taskList);
                        System.out.println(formatMessage("The following task has been removed:\n"
                                + task + "\n" + Task.getNumOfTasksString()));
                    } catch (NumberFormatException e) {
                        System.out.println(formatMessage(e
                                + "\nError! Invalid task number."
                                + "\nPlease input a valid task number!"));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e
                                + "\nError! Task number does not exist."
                                + "\nPlease input a valid task number!");
                    }
                    break;
                case HELP:
                    String allCommands = "todo\n"
                            + "deadline *text* /by yyyy-mm-dd\n"
                            + "event *text* /at yyyy-mm-dd\n"
                            + "done *number*\n"
                            + "list\n"
                            + "delete *number*\n"
                            + "bye";
                    System.out.println(formatMessage(allCommands));
                    break;
                case BYE:
                    String byeMessage = "Goodbye, hope you had a great time!";
                    System.out.println(formatMessage(byeMessage));
                    isChatBotOnline = false;
                    break;
                default:
                    System.out.println(formatMessage("Please enter a valid command! \n"
                            + "Type help for a list of commands"));
            }
        }
        sc.close();
    }


    static String formatMessage(String str) {
        return "____________________________________________________________"
                + "\n" + str + "\n"
                + "____________________________________________________________\n";
    }

    // prints all of the tasks in the taskList
    static String getTaskListString(List<Task> taskList) {
        String taskListString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String taskString = (i + 1) + ". " + taskList.get(i);
            taskListString = taskListString + taskString
                    + (i == taskList.size() - 1 ? "" : "\n");
        }
        return taskListString;
    }

    // extracts the command from the input
    static String formatCommand(String input) {
        return input.split(" ")[0];
    }

    // extracts the additional text after the command from the input
    static String formatCommandText(String input) {
        if (input.split(" ").length == 1) {
            return "";
        }
        return input.substring(formatCommand(input).length() + 1);
    }

    static void printTaskAddedMessage(Task task) {
        System.out.println(formatMessage("Got it. I've added this task:\n"
                + task + "\n" + Task.getNumOfTasksString()));
    }

    static void saveTasksToInternalStorage(List<Task> taskList) {
        DukeFileWriter.saveTaskListInInternalStorage(taskList);
    }

    static void loadTasksIntoTaskList(List<Task> taskList) {
        DukeFileReader.loadTasksIntoTaskList(taskList);
    }

    static void decrementNumOfTask() {
        Task.decrementNumOfTask();
    }
}
