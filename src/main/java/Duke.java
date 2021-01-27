import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String line = "____________________________________________________________";
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void main(String[] args) {
        welcome();
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                if (id > list.size() - 1 || id < 0) {
                    System.out.println("Task does not exist");
                    break;
                }
                Task t = list.get(id);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(msgs[1]) - 1;
                if (id > list.size() - 1 || id < 0) {
                    System.out.println("Task does not exist");
                    break;
                }
                Task t = list.remove(id);
                System.out.println("Noted. I've removed this task:");
                System.out.println(t);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
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

    public static void load() throws IOException {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader("./data/duke.txt");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parameters = line.split("\\|");
                Task t;
                switch (parameters[0]) {
                case "T": {
                    t = new ToDo(parameters[2]);
                    break;
                }
                case "D": {
                    t = new Deadline(parameters[2], parameters[3]);
                    break;
                }
                case "E": {
                    t = new Event(parameters[2], parameters[3]);
                    break;
                }
                default: {
                    throw new WrongFormatException();
                }
                }
                if (parameters[1].equals("1")) {
                    t.markAsDone();
                }
                list.add(t);
            }

        } catch (WrongFormatException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public static void save() {

    }
}
