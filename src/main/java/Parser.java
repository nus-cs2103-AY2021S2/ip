import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {
    private TaskList taskList;
    private ArrayList<Task> tasks;
    public enum Command {
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.tasks = this.taskList.getTasks();
    }

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
            default:
                throw (new IllegalArgumentException());
            }
        }
        catch(IllegalArgumentException e) {
            throw new DukeException("Please enter a legit command...");
        }
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("----------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("----------------------------------------------");
    }
    public static void markTaskDone(ArrayList<Task> tasks, int index) throws IndexOutOfBoundsException{
        System.out.println("----------------------------------------------");
        Task t = tasks.get(index - 1);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        System.out.println("----------------------------------------------");
    }
    public static void deleteTask(ArrayList<Task> tasks, int index) {
        System.out.println("----------------------------------------------");
        Task t = tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createTodo(ArrayList<Task> tasks, String[] com) {
        System.out.println("----------------------------------------------");
        String todo = "";
        for (int i = 1; i < com.length; i++) {
            todo += " " + com[i];
        }
        ToDoTask newTodo = new ToDoTask(todo, false);
        tasks.add(newTodo);
        System.out.println("Added this:");
        System.out.println(newTodo);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createDeadline(ArrayList<Task> tasks, String[] com) {
        System.out.println("----------------------------------------------");
        String description = "";
        String by = "";
        String time = "";
        for (int i = 1; i < com.length; i++) {
            if (com[i].equals("/by")) {
                for (int j = i + 1; j < com.length; j++) {
                    if (com[j].equals("time:")) {
                        for (int k = j + 1; k < com.length; k++) {
                            time += " " + com[k];
                        }
                        break;
                    }
                    by += com[j];
                }
                break;
            }
            description += " " + com[i];
        }
        DeadlineTask deadLine = new DeadlineTask(description, false, LocalDate.parse(by), time);
        tasks.add(deadLine);
        System.out.println("Added this:");
        System.out.println(deadLine);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createEvent(ArrayList<Task> tasks, String[] com) {
        System.out.println("----------------------------------------------");
        String description = "";
        String at = "";
        String time = "";
        for (int i = 1; i < com.length; i++) {
            if (com[i].equals("/at")) {
                for (int j = i + 1; j < com.length; j++) {
                    if (com[j].equals("time:")) {
                        for (int k = j + 1; k < com.length; k++) {
                            time += " " + com[k];
                        }
                        break;
                    }
                    at += com[j];
                }
                break;
            }
            description += com[i];
        }
        EventTask event = new EventTask(description, false, LocalDate.parse(at), time);
        tasks.add(event);
        System.out.println("Added this:");
        System.out.println(event);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
}
