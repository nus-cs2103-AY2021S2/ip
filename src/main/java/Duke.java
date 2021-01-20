import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskArr = new ArrayList<>();
//    private static String greetingCat = " ฅ(=^・ω・^=ฅ)";
//    private static String goodByeCat = "（=^・ω・^=\u2055）";
//    private static String goCat = "(^・ω・^=)~~✧✧";
//    private static String errorCat = "┌(=^>ω<^=)┘";
//    private static String gdJobCat = "ヽ(=^・ω・^=)丿";
    private static String greetingCat = "(=^. .^=)";
    private static String goodByeCat = "(=^. .^=*)";
    private static String goCat = "(=^. .^=)~~";
    private static String errorCat = "(=^> <^=)'''";
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
//        System.out.println("✧･ﾟ:* Goodbye *:･ﾟ✧" + goodByeCat);
        System.out.println("*** Goodbye *** " + goodByeCat);
    }

    public static void printAddedSuccess(Task task) {
        System.out.println(goCat);
        System.out.println("Mew! I've added this task:");
        System.out.println(task);
//        System.out.println(String.format("\n✧･ﾟNow you have %d tasks in the list･ﾟ✧", taskArr.size()));
        System.out.println(String.format("\n** Now you have %d tasks in the list **", taskArr.size()));
    }

    public static void addTodo(String s) {
        String taskName = s.replace("todo ", "");
        Task task = new Todo(taskName);
        taskArr.add(task);
        printAddedSuccess(task);
    }

    public static void addDeadline(String s) {
        String[] strArr = s.replace("deadline ", "").split(" /by ");
        if (strArr.length == 2) {
            Task task = new Deadline(strArr[0], strArr[1]);
            taskArr.add(task);
            printAddedSuccess(task);
        } else {
            //error will be handled in Level-5
        }
    }

    public static void addEvent(String s) {
        String[] strArr = s.replace("event ", "").split(" /at ");
        if (strArr.length == 2) {
            String[] timeArr = strArr[1].split("-");
            if (timeArr.length == 2) {
                Task task = new Event(strArr[0], timeArr[0], timeArr[1]);
                taskArr.add(task);
                printAddedSuccess(task);
            } else {
                //error will be handled in Level-5
            }
        } else {
            //error will be handled in Level-5
        }
    }

    public static void list() {
        int i = 0;
//        System.out.println(" ฅ list ฅ ");
        System.out.println(" * list *");
        while (i < taskArr.size()) {
            System.out.println(Integer.toString(i + 1) + ". " + taskArr.get(i));
            i++;
        }
    }

    public static void done(String str) {
        try {
            int i = Integer.parseInt(str.split(" ")[1]) - 1;
            taskArr.get(i).completed();
            System.out.println(gdJobCat);
            System.out.println("Mew! I've marked this task as done:");
            System.out.println(taskArr.get(i));
//            System.out.println("\nฅ Good job, you deserve a kit-kat ฅ");
            System.out.println("\n* Good job, you deserve a kit-kat *");
        } catch (IndexOutOfBoundsException e1) {
            //list is empty, hence i results in index out of bounds
            //or when i >= taskArr.size()
            System.out.println(errorCat);
            System.out.println("Error: List is empty or index is out of bounds");
        } catch (Exception e){
            //all other errors
            //i.e. "done string"
            System.out.println(errorCat);
            System.out.println("Error: Wrong format to mark task as completed");
            System.out.println("** Please format instruction as: done [index]");
            System.out.println("** where index: index of the task as shown in the list");
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
            if (str.equals("list")) {
                list();
            } else if (str.startsWith("done ")) {
                //assuming that "doneX", without a space is a task
                //and not a request to mark a task as completed
                done(str);
            } else if (str.startsWith("todo ")) {
                addTodo(str);
            } else if (str.startsWith("deadline ")) {
                addDeadline(str);
            } else if (str.startsWith("event ")){
                addEvent(str);
            } else {
                //error will be handled in Level-5
            }
            lines();
            str = sc.nextLine();
        }

        lines();
        goodbye();
        lines();
    }
}
