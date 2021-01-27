import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    public static ArrayList<Task> currentTasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File dir = new File("data/");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File savedTasks = new File("data/savedTasks.txt");
        if (!savedTasks.exists()) {
            savedTasks.createNewFile();
        }

        Scanner sc = new Scanner(System.in);
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String partition = "\n/**********************************************************/\n";

        System.out.println(logo + "\nHello! I'm Duke.\n" + "What can I do for you?\n" + partition);
        command = sc.next();

        try {
            readTasks(savedTasks);
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage() + "\n" + partition);
        }

        while(!command.equals("bye")) {
            try {
                switch (command) {
                case "list":
                    printTasks();
                    System.out.println(partition);
                    break;
                case "done":
                    int num = sc.nextInt();
                    markAsDone(num);
                    System.out.println(partition);
                    writeTasks(savedTasks);
                    break;
                case "todo":
                    addTask(new Todo(sc.nextLine().trim()));
                    System.out.println(partition);
                    writeTasks(savedTasks);
                    break;
                case "deadline":
                    String inst1 = sc.nextLine();
                    String[] part1 = inst1.split("/by");
                    addTask(new Deadline(part1[0].trim(), LocalDate.parse(part1[1].trim())));
                    System.out.println(partition);
                    writeTasks(savedTasks);
                    break;
                case "event":
                    String inst2 = sc.nextLine();
                    String[] part2 = inst2.split("/at");
                    addTask(new Event(part2[0].trim(), LocalDate.parse(part2[1].trim())));
                    System.out.println(partition);
                    writeTasks(savedTasks);
                    break;
                case "delete":
                    int del = sc.nextInt();
                    deleteTask(del);
                    System.out.println(partition);
                    writeTasks(savedTasks);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means.");
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage() + "\n" + partition);
            } catch (IOException ie) {
                System.out.println("Cannot write to file: " + ie.getMessage() + "\n" + partition);
            } catch (DateTimeParseException de) {
                System.out.println("Input date/time cannot be parsed: " + de.getMessage() + "\n" + partition);
            }

            command = sc.next();
        }

        System.out.println("Bye. Hope to see you again soon!\n" + logo);
        sc.close();
    }

    public static void addTask(Task task) {
        currentTasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task.getStatus());
        printNumTasks();
    }

    public static void printTasks() {
        for (int i = 1; i <= currentTasks.size(); i++) {
            System.out.println("" + i + ". " + currentTasks.get(i - 1).getStatus());
        }
    }

    public static void markAsDone(int taskNum) {
        currentTasks.get(taskNum - 1).markDone();
        System.out.println("This task is marked as done:\n" + currentTasks.get(taskNum - 1).getStatus());
    }

    public static void deleteTask(int taskNum) {
        System.out.println("Noted. This task has been removed:\n" + currentTasks.get(taskNum - 1).getStatus());
        currentTasks.remove(taskNum - 1);
        printNumTasks();
    }

    public static void printNumTasks() {
        System.out.println("Now you have " + currentTasks.size() + " tasks in the list.");
    }

    public static void writeTasks(File destination) throws IOException {
        StringBuilder textToAdd = new StringBuilder();
        FileWriter fw = new FileWriter(destination);
        for (Task curr : currentTasks) {
            if (curr instanceof Todo) {
                textToAdd.append("T ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator());
            } else if (curr instanceof Deadline) {
                textToAdd.append("D ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator())
                        .append(((Deadline) curr).getBy())
                        .append(System.lineSeparator());
            } else if (curr instanceof Event) {
                textToAdd.append("E ")
                        .append(curr.isDone() ? "1 " : "0 ")
                        .append(curr.getName())
                        .append(System.lineSeparator())
                        .append(((Event) curr).getAt())
                        .append(System.lineSeparator());
            }
        }
        fw.write(textToAdd.toString());
        fw.close();
    }

    public static void readTasks(File source) throws FileNotFoundException {
        Scanner sc = new Scanner(source);
        while (sc.hasNext()) {
            String current = sc.nextLine();
            String[] splits = current.split(" ", 3);
            switch (splits[0]) {
            case "T": {
                Task toAdd = new Todo(splits[2], (splits[1].equals("1")));
                currentTasks.add(toAdd);
                break;
            }
            case "E": {
                Task toAdd = new Event(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                currentTasks.add(toAdd);
                break;
            }
            case "D": {
                Task toAdd = new Deadline(splits[2], LocalDate.parse(sc.nextLine()), splits[1].equals("1"));
                currentTasks.add(toAdd);
                break;
            }
            default:
                System.out.println("Problem encountered when reading file: task unknown");
            }
        }
    }
}
