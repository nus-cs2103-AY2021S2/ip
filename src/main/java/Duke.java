import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> list = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        boolean hasExited = false;
        Scanner scanner = new Scanner(System.in);
        String intro = "Hello I'm\n" + logo +"\nWhat can I do for you?\n";

        printWithBorders(intro);
        while (!hasExited) {
            String input = scanner.nextLine();
            input = input.replace("\n", "");
            if (input.matches("bye")) {
                hasExited = true;
                printWithBorders("Bye. Hope to see you again soon");
            } else if (input.matches("list")) {
                printWithBorders(listToString());
            }
            else {
                addTask(input);
                printWithBorders("added: "+input);
            }

        }
    }

    public static void printWithBorders(String string) {
        System.out.println("-".repeat(25));
        System.out.println(string);
        System.out.println("-".repeat(25));
    }
    private static String listToString() {
        String content="";
        Integer count = 1;
        for(Task t:list) {
            content += count.toString() + ".";
            content += t.getContent();
            content += "\n";
            count++;
        }
        return content;
    }
    private static void addTask(String content) {
        list.add(new Task(content));
    }



}
