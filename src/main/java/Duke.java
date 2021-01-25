import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static String INDENTATION = "    ";
    private static String HORIZON = "------------------------------------------------------";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    private static void printReply(String reply) {
        System.out.println(INDENTATION + HORIZON);
        switch (reply) {
        case "hello":
            System.out.println(INDENTATION + "Hello! I'm Duke Y(^_^)Y");
            System.out.println(INDENTATION + "What can I do for you?\n");
            break;
        case "bye":
            System.out.println(INDENTATION + "Bye. (>_<) Hope to see you again soon! ");
            break;
        case "list":
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(INDENTATION + (i + 1) + "." + taskList.get(i).getTaskInfo());
            }
            break;
        case "error_no_meaning":
            System.out.println(INDENTATION + "OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        case "error_done_empty":
        case "error_delete_empty":
        case "error_date_empty":
            System.out.println(INDENTATION + "OOPS!!! The number cannot be empty :-(");
            break;
        case "error_done_no_meaning":
        case "error_delete_no_meaning":
        case "error_date_no_meaning":
            System.out.println(INDENTATION + "OOPS!!! Please input the number of the Task :-(");
            break;
        case "error_done_non_existed_task":
        case "error_delete_non_existed_task":
        case "error_date_non_existed_task":
            System.out.println(INDENTATION + "OOPS!!! the Task you choosing isn't existed :-(");
            break;
        case "error_todo_empty":
        case "error_deadline_empty":
        case "error_event_empty":
            System.out.println(INDENTATION + "OOPS!!! The description of a task cannot be empty. :-(");
            break;
        case "error_deadline_by":
            System.out.println(INDENTATION + "OOPS!!! The deadline of a deadline task should be meaningful. :-(");
            System.out.println(INDENTATION + "Please enter according to the format eg.description /by YYYY-MM-DD");
            break;
        case "error_event_at":
            System.out.println(INDENTATION + "OOPS!!! The period of a event task should be meaningful. :-(");
            System.out.println(INDENTATION + "Please enter according to the format eg.description /at YYYY-MM-DD");
            break;
        default:
            System.out.println(INDENTATION + "Got it. I've added this task:");
            System.out.println(INDENTATION + taskList.get(taskList.size() - 1).getTaskInfo());
            System.out.println(INDENTATION + "Now you have " + taskList.size() + " tasks in the list.");
        }
        System.out.println(INDENTATION + HORIZON);
    }

    private static void printDoneReply(int done) {
        System.out.println(INDENTATION + HORIZON);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + (done + 1) + "." + taskList.get(done).getTaskInfo());
        System.out.println(INDENTATION + HORIZON);
    }

    private static void printDeleteReply(int done) {
        System.out.println(INDENTATION + HORIZON);
        System.out.println(INDENTATION + "Noted. I've removed this task: ");
        System.out.println(INDENTATION + (done + 1) + "." + taskList.get(done).getTaskInfo());
        System.out.println(INDENTATION + "Now you have " + (taskList.size() - 1) + " tasks in the list.");
        System.out.println(INDENTATION + HORIZON);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String command = "";

        System.out.println("Hello from\n" + logo);
        printReply("hello");
        Scanner scanner = new Scanner(System.in);
        do {
            command = scanner.nextLine().trim();
            String[] commandSplit = command.split(" ");
            switch (commandSplit[0]) {
            case "bye":
            case "list":
            case "hello":
                printReply(command);
                break;
            case "done":
                try {
                    taskList.get(Integer.parseInt(commandSplit[1]) - 1).markAsDone();
                    printDoneReply(Integer.parseInt(commandSplit[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("error_done_empty");
                } catch (NumberFormatException e) {
                    printReply("error_done_no_meaning");
                } catch (IndexOutOfBoundsException e) {
                    printReply("error_done_non_existed_task");
                }
                break;
            case "delete":
                try {
                    taskList.get(Integer.parseInt(commandSplit[1]) - 1);
                    printDeleteReply(Integer.parseInt(commandSplit[1]) - 1);
                    taskList.remove(Integer.parseInt(commandSplit[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("error_delete_empty");
                } catch (NumberFormatException e) {
                    printReply("error_delete_no_meaning");
                } catch (NullPointerException e) {
                    printReply("error_delete_non_existed_task");
                } catch (IndexOutOfBoundsException e) {
                    printReply("error_delete_non_existed_task");
                }
                break;
            case "date":
                try {
                    taskList.get(Integer.parseInt(commandSplit[1]) - 1);
                    System.out.println(INDENTATION + HORIZON);
                    System.out.println(taskList.get(Integer.parseInt(commandSplit[1]) - 1).getPeriodDays());
                    System.out.println(INDENTATION + HORIZON);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("error_date_empty");
                } catch (NumberFormatException e) {
                    printReply("error_date_no_meaning");
                } catch (NullPointerException e) {
                    printReply("error_date_non_existed_task");
                } catch (IndexOutOfBoundsException e) {
                    printReply("error_date_non_existed_task");
                }
                break;


            case "todo":
                try {
                    String test = commandSplit[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("error_todo_empty");
                    break;
                }
                command = command.replaceAll("todo", " ").trim();
                taskList.add(new Todo(command));
                printReply(command);
                break;

            case "deadline":
                try {
                    String test = commandSplit[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("error_deadline_empty");
                    break;
                }
                command = command.replaceAll("deadline", " ").trim();
                commandSplit = command.split("/by");
                try {
                    taskList.add(new Deadline(commandSplit[0].trim(), commandSplit[1].trim()));
                    printReply(command);
                    //System.out.println(taskList.get(taskList.size() - 1));
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("error_deadline_by");
                } catch (DateTimeParseException e) {
                    printReply("error_deadline_by");
                }

                break;
            case "event":
                try {
                    String test = commandSplit[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("error_event_empty");
                    break;
                }
                command = command.replaceAll("event", " ").trim();
                commandSplit = command.split("/at");
                try {
                    taskList.add(new Event(commandSplit[0].trim(), commandSplit[1].trim()));
                    printReply(command);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("error_event_at");
                } catch (DateTimeParseException e) {
                    printReply("error_event_at");
                }

                break;
            default:
                printReply("error_no_meaning");
            }

        } while (!command.equals("bye"));
    }
}
