package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Parser {
    private TaskList taskList;
    /** ArrayList of Tasks obtained from TaskList **/
    private ArrayList<Task> tasks;

    /** A collection of commands Tasker understands **/
    public enum Command {
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        BYE
    }

    public static Command parseCommand(String input) {
        Command c =  Command.valueOf(input.toUpperCase(Locale.ROOT));
//        System.out.println(c);
        return c;
    }

    /**
     * Credit to QY-H00 - who inspired me to implement a parser for specific commands.
     *
     * Returns a Task created after parsing the description after a command.
     *
     * @param command command type
     * @param description description after command
     * @return the task corresponding to the input
     * @throws ParseException if parsing fails.
     */
    public static Task parseDescription(Command command, String description) throws ParseException {
        switch (command) {
        case TODO:
            return parseToDo(description);
        case DEADLINE:
            return parseDeadline(description);
        case EVENT:
            return parseEvent(description);
        default:
            throw new ParseException("Invalid command!");
        }
    }

    /**
     * Returns a ToDoTask by parsing the input behind the command.
     * Used when the command is 'todo_[description]'.
     * This function works for non-empty inputs.
     *
     * @param input user input
     * @return a ToDoTask
     * @throws ParseException if description is empty
     */
    private static ToDoTask parseToDo(String input) throws ParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("Invalid input! Description of a ToDoTask cannot be empty.\n");
        }
        if (input.charAt(0) == ' ') {
            input = input.substring(1);
        }
        return new ToDoTask(input, false);
    }

    /**
     * Returns a DeadlineTask by parsing the input behind the command.
     * Used when the command is 'deadline_[description]_/by_[YYYY-MM-DD]_/time_[HH:mm:ss]'.
     *
     * @param input input of users.
     * @return a task of Deadline corresponding to the input.
     * @throws ParseException if the description is empty.
     * @throws DateTimeParseException if the format of data time is not correct.
     */
    private static DeadlineTask parseDeadline(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("Invalid input! Description of a DeadlineTask cannot be empty.\n");
        }
        if (input.contains("/by ")) {
            if (input.charAt(0) == ' ') {
                input = input.substring(1);
            }
            int indexOfEndOfDescription = input.indexOf("/by ");
            String description = input.substring(0, indexOfEndOfDescription);
            int indexOfTime = input.indexOf("/time ");
            String deadline = input.substring(indexOfEndOfDescription + 4, indexOfTime - 1);
            LocalDate date = LocalDate.parse(deadline);
            String deadTime = input.substring(indexOfTime + 6);
            LocalTime localTime = LocalTime.parse(deadTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

            return new DeadlineTask(description, false, date, localTime);
        } else {
            throw new ParseException("Invalid input! Please enter using format: 'deadline'_[description]_" +
                    "'/by YYYY-MM-DD'_'/time HH:mm:ss'\n");
        }
    }

    /**
     * Returns an Event by parsing the input behind the command.
     * Used when the command is event.
     * Works when the input is "{description} /at YYYY-MM-DD".
     *
     * @param input input of users.
     * @return a task of Event corresponding to the input.
     * @throws ParseException if the description is empty.
     * @throws DateTimeParseException if the format of data time is not correct.
     */
    private static EventTask parseEvent(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("Invalid input! Description of an EventTask cannot be empty.\n");
        }
        if (input.contains("/at ")) {
            if (input.charAt(0) == ' ') {
                input = input.substring(1);
            }
            int indexOfEndOfDescription = input.indexOf("/at ");
            String description = input.substring(0, indexOfEndOfDescription);
            int indexOfTime = input.indexOf("/time ");
            String deadline = input.substring(indexOfEndOfDescription + 4, indexOfTime - 1);
            LocalDate date = LocalDate.parse(deadline);
            String deadTime = input.substring(indexOfTime + 6);
            LocalTime localTime = LocalTime.parse(deadTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return new EventTask(description, false, date, localTime);
        } else {
            throw new ParseException("Invalid input! Please enter using format: 'event'_[description]_" +
                    "'/by YYYY-MM-DD'_'/time HH:mm:ss'\n");
        }
    }

    /**
     * Constructs a Parser object.
     * @param taskList TaskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.tasks = this.taskList.getTasks();
    }

//    /**
//     * Generates output in response to the user's command.
//     * @param command Command in the form of a String array
//     * @throws DukeException If command could not be understood by Tasker.
//     */
//    public void receive(String[] command) throws DukeException {
//        try {
//            String task = command[0].toUpperCase();
//            Command c = Command.valueOf(task);
//            switch (c) {
//            case LIST:
//                listTasks(tasks);
//                break;
//            case DONE:
//                int index = Integer.parseInt(command[1]);
//                markTaskDone(tasks, index);
//                break;
//            case DELETE:
//                int i = Integer.parseInt(command[1]);
//                deleteTask(tasks, i);
//                break;
//            case TODO:
//                createTodo(tasks, command);
//                break;
//            case DEADLINE:
//                createDeadline(tasks, command);
//                break;
//            case EVENT:
//                createEvent(tasks, command);
//                break;
//            case FIND:
//                String keyword = command[1];
//                search(tasks, keyword);
//                break;
//            default:
//                throw(new IllegalArgumentException());
//            }
//        } catch (IllegalArgumentException e) {
//            throw new DukeException("Please enter a legit command...");
//        }
//    }
//
//    public ArrayList<Task> consolidate() {
//        return tasks;
//    }
//
//    /**
//     * Lists all the tasks in the TaskList.
//     * @param tasks An ArrayList of Tasks
//     */
//    public static void search(ArrayList<Task> tasks, String keyword) {
//        System.out.println("----------------------------------------------");
//        System.out.println("Here are the matching tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//            if (haveWord(task.getDescription(), keyword)) {
//                System.out.println((i + 1) + ". " + tasks.get(i));
//            }
//        }
//        System.out.println("----------------------------------------------");
//    }

//    public static boolean haveWord(String description, String keyword) {
//        return description.contains(keyword);
//    }

//    /**
//     * Lists all the tasks in the TaskList.
//     * @param tasks An ArrayList of Tasks
//     */
//    public static void listTasks(ArrayList<Task> tasks) {
//        System.out.println("----------------------------------------------");
//        System.out.println("Here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println((i + 1) + ". " + tasks.get(i));
//        }
//        System.out.println("----------------------------------------------");
//    }
//
//    /**
//     * Marks a task in the TaskList as "completed".
//     * @param tasks An ArrayList of Tasks
//     * @param index Index of task in ArrayList
//     * @throws IndexOutOfBoundsException If user provides a task number
//     * that does not exist
//     */
//    public static void markTaskDone(ArrayList<Task> tasks, int index)
//            throws IndexOutOfBoundsException {
//        System.out.println("----------------------------------------------");
//        Task t = tasks.get(index - 1);
//        t.markAsDone();
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println(t);
//        System.out.println("----------------------------------------------");
//    }
//
//    /**
//     * Removes a task from the TaskList.
//     * @param tasks An ArrayList of Tasks
//     * @param index Index of task in ArrayList
//     */
//    public static void deleteTask(ArrayList<Task> tasks, int index) {
//        System.out.println("----------------------------------------------");
//        Task t = tasks.remove(index - 1);
//        System.out.println("Noted. I've removed this task:");
//        System.out.println(t);
//        System.out.println("Now there are " + tasks.size() + " tasks on the list");
//        System.out.println("----------------------------------------------");
//    }
//
//    /**
//     * Creates a ToDoTask.
//     * @param tasks An ArrayList of Tasks
//     * @param command A String array containing the full command
//     */
//    public static void createTodo(ArrayList<Task> tasks, String[] command) {
//        System.out.println("----------------------------------------------");
//        String todo = "";
//        for (int i = 1; i < command.length; i++) {
//            todo += " " + command[i];
//        }
//        ToDoTask newTodo = new ToDoTask(todo, false);
//        tasks.add(newTodo);
//        System.out.println("Added this:");
//        System.out.println(newTodo);
//        System.out.println("Now there are " + tasks.size() + " tasks on the list");
//        System.out.println("----------------------------------------------");
//    }
//
//    /**
//     * Creates a DeadlineTask.
//     * @param tasks An ArrayList of Tasks
//     * @param command A String array containing the full command
//     */
//    public static void createDeadline(ArrayList<Task> tasks,
//                                      String[] command) {
//        System.out.println("----------------------------------------------");
//        String description = "";
//        String by = "";
//        String time = "";
//        for (int i = 1; i < command.length; i++) {
//            if (command[i].equals("/by")) {
//                for (int j = i + 1; j < command.length; j++) {
//                    if (command[j].equals("time:")) {
//                        for (int k = j + 1; k < command.length; k++) {
//                            time += " " + command[k];
//                        }
//                        break;
//                    }
//                    by += command[j];
//                }
//                break;
//            }
//            description += " " + command[i];
//        }
//        DeadlineTask deadLine = new DeadlineTask(description,
//                false, LocalDate.parse(by), time);
//        tasks.add(deadLine);
//        System.out.println("Added this:");
//        System.out.println(deadLine);
//        System.out.println("Now there are " + tasks.size() + " tasks on the list");
//        System.out.println("----------------------------------------------");
//    }
//
//    /**
//     * Creates an EventTask.
//     * @param tasks An ArrayList of Tasks
//     * @param command A String array containing the full command
//     */
//    public static void createEvent(ArrayList<Task> tasks, String[] command) {
//        System.out.println("----------------------------------------------");
//        String description = "";
//        String at = "";
//        String time = "";
//        for (int i = 1; i < command.length; i++) {
//            if (command[i].equals("/at")) {
//                for (int j = i + 1; j < command.length; j++) {
//                    if (command[j].equals("time:")) {
//                        for (int k = j + 1; k < command.length; k++) {
//                            time += " " + command[k];
//                        }
//                        break;
//                    }
//                    at += command[j];
//                }
//                break;
//            }
//            description += command[i];
//        }
//        EventTask event = new EventTask(description, false, LocalDate.parse(at), time);
//        tasks.add(event);
//        System.out.println("Added this:");
//        System.out.println(event);
//        System.out.println("Now there are " + tasks.size() + " tasks on the list");
//        System.out.println("----------------------------------------------");
//    }
}
