import main.java.EmptyDescriptionException;
import main.java.InvalidCommandException;
import main.java.Event;
import main.java.Deadline;
import main.java.Todo;
import main.java.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String INDENTATION = "        ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SEPARATOR = INDENTATION + HORIZONTAL_LINE;

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        loadTasks(tasks);

        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            try {
                handleInput(tasks, cmd);
            } catch (InvalidCommandException e) {
                System.out.println(e);
            }
            cmd = sc.nextLine();
        }
        sc.close();
        displayExitMsg();
    }

    public static void handleInput(ArrayList<Task> tasks, String cmd) throws InvalidCommandException {
        if (cmd.equals("list")) {
            printTasks(tasks);
        } else {
            String[] arr = cmd.split(" ", 2);
            switch (arr[0]) {
            case "done":
                int doneIndex = Integer.parseInt(arr[1]) - 1;
                Task task = tasks.get(doneIndex);
                task.markAsDone();
                displayMarkTaskAsDoneMsg(task);
                break;
            case "delete":
                int deleteIndex = Integer.parseInt(arr[1]) - 1;
                Task toDelete = tasks.get(deleteIndex);
                deleteTask(tasks, deleteIndex);
                displayDeleteTaskMsg(tasks.size(), toDelete);
                break;
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                try {
                    addTask(tasks, cmd);
                    displayAddTaskMsg(tasks);
                } catch (EmptyDescriptionException e) {
                    System.out.println(e);
                }
                break;
            default:
                throw new InvalidCommandException();
            }
            writeTasksToDisk(tasks);
        }
    }

    public static void displayWelcomeMsg() {
        String logo = "         ____        _        \n"
            + "        |  _ \\ _   _| | _____ \n"
            + "        | | | | | | | |/ / _ \\\n"
            + "        | |_| | |_| |   <  __/\n"
            + "        |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(SEPARATOR);
        System.out.println(logo);
        System.out.println(INDENTATION + "Hello! I'm Duke\n" + INDENTATION + "What can I do for you?");
        System.out.println(SEPARATOR);
    }

    public static void displayExitMsg() {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    public static void addTask(ArrayList<Task> tasks, String cmd) throws EmptyDescriptionException {
        String[] arr = cmd.split(" ", 2);
        if (arr.length == 1) {
            throw new EmptyDescriptionException();
        }
        String type = arr[0];
        String rest = arr[1];
        Task task;
        if (type.equals("todo")) {
            task = new Todo(rest);
        } else if (type.equals("deadline")) {
            String[] temp = rest.split(" /by ");
            String description = temp[0];
            String by = temp[1];
            task = new Deadline(description, by);
        } else {
            String[] temp = rest.split(" /at ");
            String description = temp[0];
            String at = temp[1];
            task = new Event(description, at);
        }
        tasks.add(task);
    }

    public static void displayAddTaskMsg(ArrayList<Task> tasks) {
        int size = tasks.size();
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + "    " + tasks.get(size - 1));
        System.out.println(INDENTATION + "Now you have " + size + " task" + (size > 1 ? "s" : "") + " in the list.");
        System.out.println(SEPARATOR);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(SEPARATOR);
        if (tasks.size() == 0) {
            System.out.println(INDENTATION + "You have no tasks currently.");
        } else {
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(INDENTATION + "    " + i + ". " + tasks.get(i - 1));
            }
        }
        System.out.println(SEPARATOR);
    }

    public static void displayMarkTaskAsDoneMsg(Task task) {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:\n"+ INDENTATION + "    " + task);
        System.out.println(SEPARATOR);
    }

    public static void deleteTask(ArrayList<Task> tasks, int index) {
        tasks.remove(index);
    }

    public static void displayDeleteTaskMsg(int size, Task task) {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + "    " + task);
        System.out.println(INDENTATION + "Now you have " + size + " task" + (size > 1 ? "s" : "") + " in the list.");
        System.out.println(SEPARATOR);
    }

    public static void loadTasks(ArrayList<Task> tasks) {
        String filePath = "tasks.txt";
        File file = new File(filePath);
        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    if (line.charAt(0) == '#') {
                        line = line.substring(1);
                        addTask(tasks, line);
                        markLatestTaskAsDone(tasks);
                    } else {
                        addTask(tasks, line);
                    }
                    line = br.readLine();
                }
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmptyDescriptionException e) {
            System.out.println(e);
        }
    }

    public static void writeTasksToDisk(ArrayList<Task> tasks) {
        try {
            FileWriter myWriter = new FileWriter("tasks.txt");
            for (Task task : tasks) {
                if (task.isDone()) {
                    myWriter.write("#"); // Use # to represent done tasks
                }
                myWriter.write(task.getTaskCommand() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void markLatestTaskAsDone(ArrayList<Task> tasks) {
        tasks.get(tasks.size() - 1).markAsDone();
    }
}
