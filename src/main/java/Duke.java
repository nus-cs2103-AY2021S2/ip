import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ______________________________________________________________________");
        System.out.println("     Reading saved tasks under User A0183450J ...");

        ArrayList<Task> tasks = TaskInterpreter.readTasks("A0183450J");
        System.out.println("     What can I do for you today?");
        System.out.println("    ______________________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        user_active: while (true) {
            String command = scanner.next();
            String description;
            int subcommandIndex;

            System.out.println("    ______________________________________________________________________");
            switch (command) {
            case "bye":
                break user_active;

            case "list":
                int outstandingTasks = 0;

                for (int i = 0; i < tasks.size(); i ++) {
                    System.out.println("     " + (i + 1) +  ". " 
                            + tasks.get(i).taskInformation());

                    if (! tasks.get(i).isDone()) { 
                        outstandingTasks += 1; 
                    }
                }

                System.out.println("     You have " + outstandingTasks 
                        + " outstanding tasks remaining ...");
                break;

            case "done":
                try { 
                    int taskIndex = scanner.nextInt();
                    tasks.get(taskIndex - 1).markAsDone();
                } catch (Exception NonIntegerException) {
                    System.out.println("     tasks must be denoted with Integers! :(");
                }

                break;

            case "todo":
                description = scanner.nextLine();

                if (description.isEmpty()) {
                    System.out.println("     the task description cannot be empty!! :o");
                } else {
                    tasks.add(new ToDo(description.trim()));
                    updateTasks(tasks);
                }

                break;

            case "deadline":
                description = scanner.nextLine().trim();
                subcommandIndex = description.indexOf("/by");
                
                try {
                    LocalDateTime By = LocalDateTime.parse(description.substring(subcommandIndex + 3).trim(),
                            inputDateFormat);

                    description = description.substring(0, subcommandIndex - 1).trim();

                    if (description.isEmpty()) {
                        System.out.println("     the task description cannot be empty!! :o");
                    } else {
                        tasks.add(new Deadline(description, By));
                        updateTasks(tasks);
                    }

                } catch (Exception E) {
                    System.out.println("     /by command cannot be empty and must be in " 
                            + "yyyy-MM-dd HHmm format! T.T");
                }

                break;

            case "event":
                description = scanner.nextLine().trim();
                subcommandIndex = description.indexOf("/at");
                
                try {
                    LocalDateTime At = LocalDateTime.parse(description.substring(subcommandIndex + 3).trim(),
                            inputDateFormat);

                    description = description.substring(0, subcommandIndex - 1).trim();

                    if (description.isEmpty()) {
                        System.out.println("     the task description cannot be empty!! :o");
                    } else {
                        tasks.add(new Event(description, At));
                        updateTasks(tasks);
                    }

                } catch (Exception E) {
                    System.out.println("     /at command cannot be empty and must be in "
                            + "yyyy-MM-dd HHmm format! T.T");
                }

                break;

            case "delete":
                try {

                    Task dead = tasks.remove(scanner.nextInt() - 1);
                    System.out.println("     Removed task:");
                    System.out.println("     " + dead.taskInformation());
                    System.out.println("     " + tasks.size() + " tasks remaining ...");

                } catch (Exception NonNumberE) {
                    System.out.println("     the task does not exist or has to be denoted by Integers! ._.");
                }
                break;
                
            default:    // appends to list
                System.out.println("     what do you mean... D:");
            }
            System.out.println("    ______________________________________________________________________\n");
        }

        System.out.println("     Saving tasks under User A0183450J ...");

        try {
            TaskInterpreter.saveTasks(tasks, "A0183450J");
        } catch (IOException DataSaveException) {
            System.out.println("     Tasks save failed spectacularly!");
        }

        System.out.println("     Bye... Hope to see you again soon!");

        scanner.close();
    }

    public static void updateTasks (ArrayList<Task> tasks) {
        System.out.println("     Added task:");
        System.out.println("     " + tasks.get(tasks.size() - 1).taskInformation());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

}