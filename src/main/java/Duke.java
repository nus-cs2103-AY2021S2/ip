import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String line = "____________________________________________________________";
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(line);
            try {
                list(sc.nextLine());
            } catch (EmptyDescriptionException e) {
                System.out.println(e.toString());
            } catch (UnknownCommandException e) {
                System.out.println(e.toString());
            } catch (WrongFormatException e) {
                System.out.println(e.toString());
            }
            finally {
                System.out.println(line);
            }
        }
    }

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void list(String msg) throws EmptyDescriptionException, UnknownCommandException, WrongFormatException {
        String[] msgs = msg.split(" ");
        switch (msgs[0]) {
            case "bye": {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                break;
            }
            case "list": {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + "." + list.get(i - 1).toString());
                }
                break;
            }
            case "done": {
                int id = Integer.parseInt(msgs[1]) - 1;
                if (id > list.size() - 1 || id <= 0) {
                    System.out.println("Task does not exist");
                    break;
                }
                Task t = list.get(id);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
                break;
            }
            case "todo": {
                if (msg.trim().length() <= 4) {
                    throw new EmptyDescriptionException("todo");
                }
                ToDo td = new ToDo(msg.substring(5, msg.length()));
                list.add(td);
                System.out.println("Got it. I've added this task:");
                System.out.println(td);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            }
            case "deadline": {
                int slash = msg.indexOf("/by");
                if (slash == -1) {
                    throw new WrongFormatException();
                }
                else if (slash <= 10 || msgs.length <= 2) {
                    throw new EmptyDescriptionException("deadline");
                }
                Deadline dl = new Deadline(msg.substring(9, slash - 1), msg.substring(slash + 4, msg.length()));
                list.add(dl);
                System.out.println("Got it. I've added this task:");
                System.out.println(dl);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            }
            case "event": {
                int slash = msg.indexOf("/at");
                if (slash == -1) {
                    throw new WrongFormatException();
                }
                else if (slash <= 7 || msgs.length <= 2) {
                    throw new EmptyDescriptionException("event");
                }
                Event e = new Event(msg.substring(6, slash - 1), msg.substring(slash + 4, msg.length()));
                list.add(e);
                System.out.println("Got it. I've added this task:");
                System.out.println(e);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            }
            default: {
                throw new UnknownCommandException();
            }
        }
    }
}
