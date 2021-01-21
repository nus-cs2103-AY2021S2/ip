import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner scanner = new Scanner(System.in);     
        ArrayList<Task> Tasks = new ArrayList<Task>();

        user_active: while (true) {
            String command = scanner.next();
            String commandcont;
            Integer subcommand;

            System.out.println("    ____________________________________________________________");
            try {
                switch (command) {
                    case "bye":     // exit
                        byebye();
                        break user_active;

                    case "list":    // regurgitate tasks
                        for (int t = 0; t < Tasks.size(); t ++) {
                            System.out.println("     " + (t + 1) +  "." + 
                                    Tasks.get(t).TaskInformation());
                        }
                        break;

                    case "done":
                        try { 
                            Integer taskNumber = scanner.nextInt();
                            Tasks.get(taskNumber - 1).markDone(); 
                        } catch (Exception NonNumberE) {
                            System.out.println("     tasks must be marked with integers!");
                        }
                        break;

                    case "todo":
                        commandcont = scanner.nextLine();
                        if (commandcont.isEmpty()) { throw new Exception ("     missing task description!"); }

                        Tasks.add(new ToDo(commandcont.trim()));
                        updateTasks(Tasks);
                        break;

                    case "deadline":
                        commandcont = scanner.nextLine().trim();
                        subcommand = commandcont.indexOf("/by");
                        if (subcommand < 0) { throw new Exception ("     missing /by subcommand!"); }

                        String By = commandcont.substring(subcommand + 3).trim();
                        if (By.isEmpty()) { throw new Exception ("     missing /by description!"); }

                        commandcont = commandcont.substring(0, subcommand - 1).trim();
                        if (commandcont.isEmpty()) { throw new Exception ("     missing task description!"); }

                        Tasks.add(new Deadline(commandcont, By));
                        updateTasks(Tasks);
                        break;

                    case "event":
                        commandcont = scanner.nextLine().trim();
                        subcommand = commandcont.indexOf("/at");
                        if (subcommand < 0) { throw new Exception ("     missing /at subcommand!"); }

                        String At = commandcont.substring(subcommand + 3).trim();
                        if (At.isEmpty()) { throw new Exception ("     missing /at description!"); }

                        commandcont = commandcont.substring(0, subcommand - 1).trim();
                        if (commandcont.isEmpty()) { throw new Exception ("     missing task description!"); }

                        Tasks.add(new Event(commandcont, At));
                        updateTasks(Tasks);
                        break;

                    case "delete":
                        try { 
                            Task dead = Tasks.remove(scanner.nextInt() - 1);
                            System.out.println("     Got it. I've removed this task:");
                            System.out.println("     " + dead.TaskInformation());
                            System.out.println("     Now you have " + Tasks.size() + " tasks in the list.");

                        } catch (Exception NonNumberE) {
                            System.out.println("     task does not exist or must be marked with integers!");
                        }
                        break;
                        
                    default:    // appends to list
                        System.out.println("     D: what do you mean...");
                }
            } catch (Exception E) {
                System.out.println(E.getMessage());
            }
            System.out.println("    ____________________________________________________________\n");
        }

        scanner.close();
    }

    public static void updateTasks (ArrayList<Task> Tasks) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + Tasks.get(Tasks.size() - 1).TaskInformation());
        System.out.println("     Now you have " + Tasks.size() + " tasks in the list.");
    }

    public static void greet() {
        System.out.println("What can I do for you?\n");
    }

    public static void byebye() {
        System.out.println("ByeBye. Hope to see you again soon!");
    }
}