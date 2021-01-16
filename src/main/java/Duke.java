import java.util.*;

public class Duke {
    public static List<String> textList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greeting();
        
        String userCmd = sc.nextLine();
        while (!userCmd.equals("bye")) {
            if (userCmd.equals("list")) {
                printLineBreak();
                for (int i = 0; i < textList.size(); i++) {
                    System.out.println(String.format("\t%d. %s", i+1, textList.get(i)));
                }
                printLineBreak();
            } else {
                textList.add(userCmd);
                responseWrapper(String.format("added: %s", userCmd));
            }
            userCmd = sc.nextLine();
        }
        exit();
        sc.close();
    }

    public static void printLineBreak() {
        System.out.println("\t____________________________________________________________");
    }

    public static void responseWrapper(String msg) {
        printLineBreak();
        System.out.println(String.format("\t%s", msg));
        printLineBreak();
    }

    public static void greeting() {
        printLineBreak();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printLineBreak();
    }

    public static void exit() {
        responseWrapper("Bye. Hope to see you again soon!");
    }
}
