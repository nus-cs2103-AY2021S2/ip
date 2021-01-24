import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void readDataFromFile(File f, ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            Task task;
            String data = sc.nextLine();
            String[] str = data.split("// ");
            char taskType = str[0].charAt(0);
            boolean isCompleted = Integer.parseInt(str[1].substring(0, 1)) == 1;
            String taskName = str[2].strip();
            if (taskType == 'T') {
                task = new ToDo(isCompleted, taskName);
            } else if (taskType == 'D') {
                task = new Deadline(isCompleted, taskName, str[3].strip());
            } else {
                task = new Event(isCompleted, taskName, str[3].strip());
            }
            tasks.add(task);
        }
    }

    public static void list(ArrayList<Task> tasks) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void blah() throws InvalidInputException {
        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void addTask(File f, String type, ArrayList<Task> tasks, String taskDescription)
            throws InvalidDescriptionException, IOException {
        Task task;
        FileWriter fw = new FileWriter(f, true);
        if (taskDescription.equals("")) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description of " + type + " cannot be empty.");
        } else if (type.equals("todo")) {
            task = new ToDo(false, taskDescription);
        } else if ((type.equals("deadline") && !taskDescription.contains("/by"))
                || (type.equals("event")) && !taskDescription.contains("/at")) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description format of " + type + " is wrong.");
        } else {
            int index = type.equals("deadline") ? taskDescription.indexOf("/by") : taskDescription.indexOf("/at");
            String taskName = taskDescription.substring(0, index).strip();
            String dateTime = taskDescription.substring(index + 4);

            if (type.equals("deadline")) {
                task = new Deadline(false, taskName, dateTime);
            } else {
                task = new Event(false, taskName, dateTime);
            }
        }
        tasks.add(task);
        fw.write(task.generateFileFormatString() + "\n");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task);
        System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
        fw.close();
    }

    public static void overwriteTextFile(File f, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task task : tasks) {
            fw.write(task.generateFileFormatString() + "\n");
        }
        fw.close();
    }

    public static void delete(File f, ArrayList<Task> tasks, String taskDescription)
            throws InvalidDescriptionException, IOException {
        try {
            int index = Integer.parseInt(taskDescription.substring(0, 1)) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("     " + task);
            System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
            overwriteTextFile(f, tasks);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The task description is wrong");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The number you entered is either too big " +
                    "or smaller than 0. There are currently " + tasks.size() + " tasks");
        }
    }

    public static void done(File f, ArrayList<Task> tasks, String taskDescription)
            throws InvalidDescriptionException, IOException {
        try {
            int index = Integer.parseInt(taskDescription.substring(0, 1)) - 1;
            Task task = tasks.get(index);
            task.completeTask();
            System.out.println("     Nice! I've marked this task as done:\n     " + task);
            overwriteTextFile(f, tasks);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The task description is wrong");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The number you entered is either too big " +
                    "or smaller than 0. There are currently " + tasks.size() + " tasks");
        }
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String filePath = "data/duke.txt";
        File f = new File(filePath);
        Scanner sc = new Scanner(System.in);
        String userInput;
        String taskDescription;
        ArrayList<Task> userTasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("     Hello from\n" + logo);

        try {
            readDataFromFile(f, userTasks);
            list(userTasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n     What can I do for you?");
        System.out.println("     _______________________________________\n");

        while (sc.hasNext()) {
            try {
                userInput = sc.next();
                taskDescription = sc.nextLine().strip();
                System.out.println("     _______________________________________");
                if (userInput.equals("list")) {
                    list(userTasks);
                } else if (userInput.equals("bye")) {
                    bye();
                    break;
                } else if (userInput.equals("done")) {
                    done(f, userTasks, taskDescription);
                } else if (userInput.equals("todo") || userInput.equals("deadline") || userInput.equals("event")) {
                    addTask(f, userInput, userTasks, taskDescription);
                } else if (userInput.equals("delete")) {
                    delete(f, userTasks, taskDescription);
                } else {
                    blah();
                }
            } catch (InvalidDescriptionException | InvalidInputException ex) {
                System.out.println("     " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("Something went wrong: " + ex.getMessage());
            } finally {
                System.out.println("     _______________________________________\n");
            }
        }
        sc.close();
    }
}
