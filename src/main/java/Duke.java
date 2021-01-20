import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskArr = new ArrayList<>();
    private static String greetingCat = "(=^. .^=)";
    private static String goodByeCat = "(=^. .^=*)";
    private static String goCat = "(=^. .^=)~~";
    private static String gdJobCat = "\\(=^> <^=)/";

    public static void lines() {
        System.out.println("__________________________" +
                "__________________________________");
    }

    public static void greet() {
        System.out.println(greetingCat);
        System.out.println("Mew! I'm Chat the Cat");
        System.out.println("What can I do for you?");
    }

    public static void echo(String s) {
        System.out.println(s);
    }

    public static void goodbye() {
        System.out.println("*** Goodbye *** " + goodByeCat);
    }

    public static void printAddedSuccess(Task task) {
        System.out.println(goCat);
        System.out.println("Mew! I've added this task:");
        System.out.println(task);
        System.out.println(String.format("\n** Now you have %d tasks in the list **", taskArr.size()));
    }

    public static void addTodo(String s) {
        try {
            Task task = Todo.createTodo(s);
            taskArr.add(task);
            printAddedSuccess(task);
        } catch (ChatException e) {
            System.out.println(e);
        }
    }

    public static void addDeadline(String s) {
        try {
            Task task = Deadline.createDeadline(s);
            taskArr.add(task);
            printAddedSuccess(task);
        } catch (ChatException e) {
            System.out.println(e);
        }
    }

    public static void addEvent(String s) {
        try {
            Task task = Event.createEvent(s);
            taskArr.add(task);
            printAddedSuccess(task);
        } catch (ChatException e) {
            System.out.println(e);
        }
    }

    public static void list() {
        int i = 0;
        System.out.println(" * list *");
        while (i < taskArr.size()) {
            System.out.println(Integer.toString(i + 1) + ". " + taskArr.get(i));
            i++;
        }
    }

    public static void done(String str) throws ChatException {
        if (str.strip().equals("done")) {
            throw new ChatException("Missing index\n" +
                    "Please input with this format:\n" +
                    "done [index]");
        } else if (!str.contains(" ")) {
            throw new ChatException("Missing space before index\n" +
                    "Please input with this format:\n" +
                    "done [index]");
        }

        try {
            int i = Integer.parseInt(str.split(" ")[1]) - 1;
            if (taskArr.get(i).getDone()) {
                throw new ChatException(String.format("Task already completed\n%s", taskArr.get(i)));
            } else {
                taskArr.get(i).completed();
                System.out.println(gdJobCat);
                System.out.println("Mew! I've marked this task as done:");
                System.out.println(taskArr.get(i));
                System.out.println("\n* Good job, you deserve a kit-kat *");
            }
        } catch (IndexOutOfBoundsException e1) {
            //list is empty, hence i results in index out of bounds
            //or when i >= taskArr.size()
            throw new ChatException("List is empty or index is out of bounds");
        } catch (ChatException e2){
            throw e2;
        } catch (NumberFormatException e3){
            //i.e. "done string"
            throw new ChatException("Index should be an integer\n" +
                    "Please input with this format:\n" +
                    "done [index]");
        }
    }

    public static void main(String[] args) {
        //only add lines in here
        lines();
        greet();
        lines();

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            lines();
            try {
                if (str.equals("list")) {
                    list();
                } else if (str.startsWith("done")) {
                    done(str);
                } else if (str.startsWith("todo")) {
                    addTodo(str);
                } else if (str.startsWith("deadline")) {
                    addDeadline(str);
                } else if (str.startsWith("event")) {
                    addEvent(str);
                } else {
                    ChatException error = new ChatException("Sorry this instruction does not exist!\n" +
                            "Please choose from the following: " +
                            "todo, deadline, event, done, list, bye");
                    System.out.println(error);
                }
            } catch (ChatException e) {
                System.out.println(e);
            }
            lines();
            str = sc.nextLine();
        }
        lines();
        goodbye();
        lines();
    }
}
