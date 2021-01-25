import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String PARTING_LINE = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String FILE_PATH = System.getProperty("user.dir") + "/duke.txt";

    public static void main(String[] args) {
        printGreeting();
        syncTasksFromFile();

        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver) {
            String input = sc.nextLine();
            System.out.println(PARTING_LINE);
            try {
                Command command = Parser.parseCommand(input);
                switch (command) {
                case LIST:
                    listTasks();
                    break;
                case DONE:
                    markAsComplete(Parser.getDoneIndex(input));
                    break;
                case TODO:
                    addThisTask(Parser.getTodo(input));
                    break;
                case DEADLINE:
                    addThisTask(Parser.getDeadline(input));
                    break;
                case EVENT:
                    addThisTask(Parser.getEvent(input));
                    break;
                case DELETE:
                    removeTask(Parser.getDeleteIndex(input));
                    break;
                case BYE:
                    isOver = true;
                    farewell();
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(PARTING_LINE);
        }
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(PARTING_LINE);
        System.out.println("Sup. I am Duke.");
        System.out.println("How can I help you?");
        System.out.println(PARTING_LINE);
    }

    public static void farewell() {
        System.out.println(" See you.");
    }

    public static void listTasks() {
        System.out.println(" Here are the tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + tempTask);
        }
    }

    public static void markAsComplete(int index) throws TaskIndexOutOfBoundException {
        if (index < tasks.size()) {
            Task completedTask = tasks.get(index);
            completedTask.complete();
            updateTasksInFile();
            System.out.println(" Marked. How cool is that?");
            System.out.println("  " + completedTask);
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }
    }

    public static void removeTask(int index) throws TaskIndexOutOfBoundException {
        if (index < tasks.size()) {
            Task removingTask = tasks.get(index);
            tasks.remove(index);
            updateTasksInFile();
            System.out.println(" Following task is removed:");
            System.out.println("  " + removingTask);
            System.out.println(" Now you have " + tasks.size() + " tasks.");
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }
    }

    public static void addThisTask(Task task) {
        System.out.println(" Added: ");
        tasks.add(task);
        updateTasksInFile();
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    public static void syncTasksFromFile() {
        try {
            File f = new File(FILE_PATH);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] keyWords = line.split(" \\| ");
                String taskType = keyWords[0].strip();
                String taskName;
                String taskStatus;
                Task thisTask;

                if (taskType.equals("T")) {
                    taskName = keyWords[2];
                    thisTask = new Todo(taskName);
                } else if (taskType.equals("D")) {
                    taskName = keyWords[2];
                    LocalDateTime cutOffDate = Parser.parseDateTime(keyWords[3]);
                    thisTask = new Deadline(taskName, cutOffDate);
                } else {
                    taskName = keyWords[2];
                    LocalDateTime startDate = Parser.parseDateTime(keyWords[3]);
                    thisTask = new Event(taskName, startDate);
                }
                taskStatus = keyWords[1].strip();
                if (taskStatus.equals("1")) {
                    thisTask.complete();
                }
                tasks.add(thisTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        } catch (InvalidDateTimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTasksInFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Has no targeted file in: " + e.getMessage());
        }
    }
}
