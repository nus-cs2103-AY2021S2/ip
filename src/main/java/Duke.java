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
                    Integer taskNumber = scanner.nextInt();
                    Tasks.get(taskNumber - 1).markDone();
                    break;

                case "todo":
                    commandcont = scanner.nextLine();
                    Tasks.add(new ToDo(commandcont.substring(1)));
                    updateTasks(Tasks);
                    break;

                case "deadline":
                    commandcont = scanner.nextLine();
                    subcommand = commandcont.indexOf("/by");
                    if (subcommand < 0) {
                        System.out.println("     deadline command requires /by subcommand!");
                    } else {
                        Tasks.add(new Deadline(commandcont.substring(1, subcommand - 1), 
                            commandcont.substring(subcommand + 4, commandcont.length())));
                        updateTasks(Tasks);
                    }
                    break;

                case "event":
                    commandcont = scanner.nextLine();
                    subcommand = commandcont.indexOf("/at");
                    if (subcommand < 0) {
                        System.out.println("     event command requires /by subcommand!");
                    } else {
                        Tasks.add(new Event(commandcont.substring(1, subcommand - 1), 
                            commandcont.substring(subcommand + 4, commandcont.length())));
                        updateTasks(Tasks);
                    }
                    break;
                    
                default:    // appends to list
                    break;
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