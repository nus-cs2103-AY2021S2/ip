import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    private static final String BORDER = "-------------------------------------";

    public static void main(String[] args) {
        Duke.printWithBorders("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        List<Task> tasks = Duke.loadTasks();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine().strip();
            StringBuilder response = new StringBuilder();
            try {
                if (userInput.equals("bye")) {
                    response.append("Bye. Hope to see you soon!");
                    Duke.printWithBorders(response.toString());
                    return;
                } else if (userInput.equals("list")) {
                    response.append("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        response.append(i + 1);
                        response.append(".");
                        response.append(task);
                        if (i != tasks.size() - 1) {
                            response.append("\n");
                        }
                    }
                } else if (userInput.startsWith("done")) {
                    int userChoice = Integer.parseInt(userInput.split(" ")[1]);
                    if (userChoice > tasks.size()) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but there is no such task :-(");
                    }
                    Task task = tasks.get(userChoice - 1);
                    task.markComplete();
                    response.append("Nice! I've marked this task as done:\n");
                    response.append(task);
                } else if (userInput.startsWith("delete")) {
                    int userChoice = Integer.parseInt(userInput.split(" ")[1]);
                    if (userChoice > tasks.size()) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but there is no such task :-(");
                    }
                    Task task = tasks.remove(userChoice - 1);
                    response.append("Noted. I've removed this task:\n  ");
                    response.append(task);
                    response.append("\nNow you have ");
                    response.append(tasks.size());
                    response.append(" tasks in the list.");
                } else if (userInput.startsWith("event") ||
                        userInput.startsWith("todo") ||
                        userInput.startsWith("deadline")) {
                    Task task = Task.parseTask(userInput);
                    tasks.add(task);
                    response.append("Got it. I've added this task:\n  added: ");
                    response.append(task);
                    response.append("\nNow you have ");
                    response.append(tasks.size());
                    response.append(" tasks in the list.");
                } else if (userInput.startsWith("on")) {
                    // TODO: Implement a command that fetches all deadlines on a given date
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                Duke.printWithBorders(response.toString());
                Duke.saveTasks(tasks);
            } catch (DukeException | IOException e) {
                Duke.printWithBorders(e.getMessage());
            }
        }
    }

    public static void saveTasks(List<Task> tasks) throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File("data/tasks.txt");
        if (file.exists()) {
            file.delete();
        }

        FileWriter writer = new FileWriter("data/tasks.txt");
        writer.flush();

        for (Task task : tasks) {
            writer.write(task.storageEntry());
            writer.write(System.lineSeparator());
        }

        writer.flush();
    }

    public static List<Task> loadTasks() {
        File file = new File("data/tasks.txt");
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            File myObj = new File("data/tasks.txt");
            Scanner myReader = new Scanner(myObj);
            List<Task> taskList = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                taskList.add(Task.parseRecord(data));
            }
            myReader.close();
            return taskList;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void printWithBorders(String message) {
        System.out.println(Duke.BORDER);
        System.out.println(message);
        System.out.println(Duke.BORDER);
    }
}
