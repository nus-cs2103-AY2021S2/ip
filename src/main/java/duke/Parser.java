package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {
    private TaskList taskList;
    /** An ArrayList of Tasks obtained from TaskList **/
    private ArrayList<Task> tasks;

    /** A collection of commands Tasker can execute **/
    public enum Command {
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }

    /**
     * Constructs a Parser object.
     * @param taskList TaskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.tasks = this.taskList.getTasks();
    }

    /**
     * Generates output in response to the user's command.
     * @param command Command in the form of a String array
     * @throws DukeException If command could not be understood by Tasker.
     */
    public void receive(String[] command) throws DukeException {
        try {
            String task = command[0].toUpperCase();
            Command c = Command.valueOf(task);
            switch (c) {
            case LIST:
                listTasks(tasks);
                break;
            case DONE:
                int index = Integer.parseInt(command[1]);
                markTaskDone(tasks, index);
                break;
            case DELETE:
                int i = Integer.parseInt(command[1]);
                deleteTask(tasks, i);
                break;
            case TODO:
                createTodo(tasks, command);
                break;
            case DEADLINE:
                createDeadline(tasks, command);
                break;
            case EVENT:
                createEvent(tasks, command);
                break;
            case FIND:
                String keyword = command[1];
                search(tasks, keyword);
                break;
            default:
                throw(new IllegalArgumentException());
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Please enter a legit command...");
        }
    }

    public ArrayList<Task> consolidate() {
        return tasks;
    }

    /**
     * Lists all the tasks in the TaskList.
     * @param tasks An ArrayList of Tasks
     */
    public static void search(ArrayList<Task> tasks, String keyword) {
        System.out.println("----------------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (haveWord(task.getDescription(), keyword)) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("----------------------------------------------");
    }

    public static boolean haveWord(String description, String keyword) {
        return description.contains(keyword);
    }

    /**
     * Lists all the tasks in the TaskList.
     * @param tasks An ArrayList of Tasks
     */
    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("----------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Marks a task in the TaskList as "completed".
     * @param tasks An ArrayList of Tasks
     * @param index Index of task in ArrayList
     * @throws IndexOutOfBoundsException If user provides a task number
     * that does not exist
     */
    public static void markTaskDone(ArrayList<Task> tasks, int index)
            throws IndexOutOfBoundsException {
        System.out.println("----------------------------------------------");
        Task t = tasks.get(index - 1);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        System.out.println("----------------------------------------------");
    }

    /**
     * Removes a task from the TaskList.
     * @param tasks An ArrayList of Tasks
     * @param index Index of task in ArrayList
     */
    public static void deleteTask(ArrayList<Task> tasks, int index) {
        System.out.println("----------------------------------------------");
        Task t = tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }

    /**
     * Creates a ToDoTask.
     * @param tasks An ArrayList of Tasks
     * @param command A String array containing the full command
     */
    public static void createTodo(ArrayList<Task> tasks, String[] command) {
        System.out.println("----------------------------------------------");
        String todo = "";
        for (int i = 1; i < command.length; i++) {
            todo += " " + command[i];
        }
        ToDoTask newTodo = new ToDoTask(todo, false);
        tasks.add(newTodo);
        System.out.println("Added this:");
        System.out.println(newTodo);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }

    /**
     * Creates a DeadlineTask.
     * @param tasks An ArrayList of Tasks
     * @param command A String array containing the full command
     */
    public static void createDeadline(ArrayList<Task> tasks,
                                      String[] command) {
        System.out.println("----------------------------------------------");
        String description = "";
        String by = "";
        String time = "";
        for (int i = 1; i < command.length; i++) {
            if (command[i].equals("/by")) {
                for (int j = i + 1; j < command.length; j++) {
                    if (command[j].equals("time:")) {
                        for (int k = j + 1; k < command.length; k++) {
                            time += " " + command[k];
                        }
                        break;
                    }
                    by += command[j];
                }
                break;
            }
            description += " " + command[i];
        }
        DeadlineTask deadLine = new DeadlineTask(description,
                false, LocalDate.parse(by), time);
        tasks.add(deadLine);
        System.out.println("Added this:");
        System.out.println(deadLine);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }

    /**
     * Creates an EventTask.
     * @param tasks An ArrayList of Tasks
     * @param command A String array containing the full command
     */
    public static void createEvent(ArrayList<Task> tasks, String[] command) {
        System.out.println("----------------------------------------------");
        String description = "";
        String at = "";
        String time = "";
        for (int i = 1; i < command.length; i++) {
            if (command[i].equals("/at")) {
                for (int j = i + 1; j < command.length; j++) {
                    if (command[j].equals("time:")) {
                        for (int k = j + 1; k < command.length; k++) {
                            time += " " + command[k];
                        }
                        break;
                    }
                    at += command[j];
                }
                break;
            }
            description += command[i];
        }
        EventTask event = new EventTask(description, false, LocalDate.parse(at), time);
        tasks.add(event);
        System.out.println("Added this:");
        System.out.println(event);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
}
