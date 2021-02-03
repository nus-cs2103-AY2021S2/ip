import java.util.ArrayList;
import java.util.Scanner;

public class Seashell {

    private ArrayList<Task> taskList;
    private final static String HELP_TEXT = """
            Welcome to Seashells! You can start by trying out some of these commands
            
            todo <task name> - add a todo task
            deadline <task name> /by <datetime> - add a deadline task
            event <task name> /at <datetime> - add an event task
            done <task index> - mark the task at the specified index as done
            delete <task index> - remove the task at the specified index from the list
            bye - exits the program""";

    public Seashell() {
        this.taskList = new ArrayList<>();
    }

    public Seashell(String saveFileText) {
        this.taskList = new ArrayList<>();
        // add items from saveFile
    }

    public void start() {
        System.out.println("Hello I'm Seashell, a task manager! What can I do for you? Type \"help\" for more " +
                "information on the commands you can give me!");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    listTasks(taskList);
                } else if (command.startsWith("done ")) {
                    done(command, taskList);
                } else if (command.startsWith("delete ")) {
                    delete(command, taskList);
                } else if (command.startsWith("todo")) {
                    createTodo(command, taskList);
                } else if (command.startsWith("deadline")) {
                    createDeadline(command, taskList);
                } else if (command.startsWith("event")) {
                    createEvent(command, taskList);
                } else if (command.stripTrailing().equals("help")) {
                    System.out.println(HELP_TEXT);
                } else {
                    throw new SeashellException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (SeashellException e) {
                System.out.println(e);
            }
        }
    }

    private void listTasks(ArrayList<Task> taskList) throws SeashellException {
        if (taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is currently still empty!");
        } else {
            for (int i = 1; i <= taskList.size(); i++) {
                Task t = taskList.get(i - 1);
                System.out.println(i + ". " + t);
            }
        }
    }

    private void done(String command, ArrayList<Task> taskList) throws SeashellException {
        String num = command.substring(5);
        int taskNum = Integer.parseInt(num);
        if (taskList.size() < taskNum) {
            throw new SeashellException("OOPS!!! Task does not exist!");
        } else {
            Task updated = taskList.get(taskNum - 1).setDone();
            taskList.set(taskNum - 1, updated);
            System.out.println("Nice! I've marked this task as done: \n" + updated);
        }
    }

    private void delete(String command, ArrayList<Task> taskList) throws SeashellException {
        String num = command.substring(7);
        int taskNum = Integer.parseInt(num);
        if (taskList.size() < taskNum) {
            throw new SeashellException("OOPS!!! Task does not exist!");
        } else {
            Task toRemove = taskList.remove(taskNum - 1);
            System.out.println("Noted. I have removed " + toRemove);
            System.out.println("You now have " + taskList.size() + " items in the task list");
        }
    }

    private void createTodo(String command, ArrayList<Task> taskList) throws SeashellException {
        String taskName = command.substring(4).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else {
            Todo newTask = new Todo(taskName);
            taskList.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + taskList.size() + " items in the task list");
        }
    }

    private void createDeadline(String command, ArrayList<Task> taskList) throws SeashellException {
        String taskName = command.substring(8).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else if (command.indexOf("/by") == -1) {
            throw new SeashellException("OOPS!!! The syntax of adding a deadline should be [name] /by [date/time]");
        } else {
            taskName = command.substring(8, command.indexOf("/by") - 1).stripLeading();
            String by = command.substring(command.indexOf("/by") + 4);
            Deadline newTask = new Deadline(taskName, by);
            taskList.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + taskList.size() + " items in the task list");
        }
    }

    private void createEvent(String command, ArrayList<Task> taskList) throws SeashellException {
        String taskName = command.substring(5).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else if (command.indexOf("/at") == -1) {
            throw new SeashellException("OOPS!!! The syntax of adding an event should be [name] /at [date/time]");
        } else {
            taskName = command.substring(5, command.indexOf("/at") - 1).stripLeading();
            String at = command.substring(command.indexOf("/at") + 4);
            Event newTask = new Event(taskName, at);
            taskList.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + taskList.size() + " items in the task list");
        }
    }
}
