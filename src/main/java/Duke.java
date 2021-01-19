import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskArr = new ArrayList<>();

    public static void lines() {
        System.out.println("__________________________" +
                "__________________________________");
    }

    public static void greet() {
        String greetingCat = " ฅ(=^・ω・^=ฅ)";
        System.out.println(greetingCat);
        System.out.println("Mew! I'm Chat the Cat");
        System.out.println("What can I do for you?");
    }

    public static void echo(String s) {
        System.out.println(s);
    }

    public static void goodbye() {
        String goodByeCat = "（=^・ω・^=❁）";
        System.out.println("✧･ﾟ:* Goodbye *:･ﾟ✧" + goodByeCat);
    }

    public static void add(String s) {
        taskArr.add(new Task(s));
        String goCat = "(^・ω・^=)~~✧✧";
        System.out.println(goCat);
        System.out.println("Added: " + s);
    }

    public static void list() {
        int i = 0;
        System.out.println(" ฅ list ฅ ");
        while (i < taskArr.size()) {
            System.out.println(Integer.toString(i + 1) + ". " + taskArr.get(i));
            i++;
        }
    }

    public static void done(String str) {
        String errorCat = "┌(=^>ω<^=)┘";
        String gdJobCat = "ヽ(=^・ω・^=)丿";
        try {
            int i = Integer.parseInt(str.split(" ")[1]) - 1;
            taskArr.get(i).completed();
            System.out.println(gdJobCat);
            System.out.println("Mew! I've marked this task as done:");
            System.out.println(taskArr.get(i));
            System.out.println("\nฅ Good job, you deserve a kit-kat ฅ");
        } catch (IndexOutOfBoundsException e1) {
            //list is empty, hence i results in index out of bounds
            //or when i >= taskArr.size()
            System.out.println("┌(=^>ω<^=)┘");
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
            } else {
                add(str);
            }
            lines();
            str = sc.nextLine();
        }

        lines();
        goodbye();
        lines();
    }
}
