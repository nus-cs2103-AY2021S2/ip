package main.java;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * An interactive chat bot made for CS2103 individual project.
 */
public class Duke {
    public static void main(String[] args) {
        // Initialise Duke using saved tasks in text file
        List<Task> tasks = new ArrayList<>();
        try {
            initialise(tasks);
        } catch (IOException ex) {
            System.out.println(ex);
            return;
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Please input a command.");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println("  ~~~");
            try {
                if (input.equals("bye")) {
                    System.out.println("  See you next time.");
                    System.out.println("  ~~~");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("  Tasks in list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("  " + (i + 1) + "." + tasks.get(i));
                    }
                } else if (input.split(" ")[0].equals("done")) {
                    int i;
                    try {
                        i = Integer.parseInt(input.split(" ")[1]);
                    } catch (Exception ex) {
                        throw new DukeException("  Please provide an integer.");
                    }
                    if (i > tasks.size() || i <= 0) {
                        throw new DukeException("  That number is invalid.");
                    }
                    tasks.get(i - 1).markAsDone();
                    System.out.println("  Successfully marked as done:");
                    System.out.println("  " + tasks.get(i - 1));
                } else if (input.split(" ")[0].equals("delete")){
                    int i;
                    try {
                        i = Integer.parseInt(input.split(" ")[1]);
                    } catch (Exception ex) {
                        throw new DukeException("  Please provide an integer.");
                    }
                    if (i > tasks.size() || i <= 0) {
                        throw new DukeException("  That number is invalid.");
                    }
                    Task taskToRemove = tasks.get(i - 1);
                    tasks.remove(i - 1);
                    System.out.println("  Successfully removed:");
                    System.out.println("    " + taskToRemove);
                    System.out.println("  Total tasks in list: " + tasks.size());
                } else {
                    Task task;
                    switch (input.split(" ")[0]) {
                    case "todo":
                        if (input.split(" ").length <= 1) {
                            throw new DukeException("  Please describe the task.");
                        }
                        task = new Todo(input.substring(5));
                        break;
                    case "deadline": {
                        if (input.split(" ").length <= 1) {
                            throw new DukeException("  Please describe the task.");
                        }
                        String[] inputs = input.substring(9).split("/by");
                        String name = inputs[0] + "(by:" + inputs[1] + ")";
                        task = new Deadline(name);
                        break;
                    }
                    case "event": {
                        if (input.split(" ").length <= 1) {
                            throw new DukeException("  Please describe the task.");
                        }
                        String[] inputs = input.substring(6).split("/at");
                        String name = inputs[0] + "(at:" + inputs[1] + ")";
                        task = new Event(name);
                        break;
                    }
                    default:
                        throw new DukeException("  That command is invalid.");
                    }
                    tasks.add(task);
                    System.out.println("  Task added:");
                    System.out.println("    " + task);
                    System.out.println("  Total tasks in list: " + tasks.size());
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }
            try {
                save(tasks);
            } catch (IOException ex) {
                System.out.println(ex);
                return;
            }
            System.out.println("  ~~~");
        }
    }

    /**
     * Loads the list of tasks from pre-made text file.
     * Creates the text file if it does not exist.
     *
     * @param tasks list of tasks.
     */
    public static void initialise(List<Task> tasks) throws IOException {
        Path path = Paths.get("data/TaskList.txt");
        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Files.createFile(path);
            return;
        }
        BufferedReader br = Files.newBufferedReader(path);
        String taskType = br.readLine();
        while (taskType != null) {
            Task newTask;
            String taskName = br.readLine();
            switch (taskType) {
            case "T": {
                newTask = new Todo(taskName);
                break;
            }
            case "D": {
                newTask = new Deadline(taskName);
                break;
            }
            case "E": {
                newTask = new Event(taskName);
                break;
            }
            default:
                return;
            }
            boolean isDone = Boolean.parseBoolean(br.readLine());
            if (isDone) {
                newTask.markAsDone();
            }
            tasks.add(newTask);
            taskType = br.readLine();
        }
    }

    /**
     * Saves the list of tasks into existing text file.
     *
     * @param tasks list of tasks.
     */
    public static void save(List<Task> tasks) throws IOException {
        Path path = Paths.get("data/TaskList.txt");
        BufferedWriter bw = Files.newBufferedWriter(path);
        for (Task task : tasks) {
            bw.write(task.toString().charAt(1));
            bw.newLine();
            bw.write(task.name);
            bw.newLine();
            bw.write(Boolean.toString(task.isDone));
            bw.newLine();
        }
        bw.flush();
    }
}
