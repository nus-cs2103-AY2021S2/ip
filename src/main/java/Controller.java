import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final static String LINE = "\t__________________________________\n";
    private final static String GREETING = "\t Hello! I'm Duke\n\t What can I do for you? \n";
    private final static String ENDDUKE = "\t Bye. Hope to see you again soon!\n";
    private final List<String> list;

    public Controller() {
        list = new ArrayList<>();
    }

    public void run() {
        String startMsg = LINE + GREETING + LINE;
        System.out.println(startMsg);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            System.out.print(LINE);
            if (input.equals("list")) {
                printList();
            } else {
                addTask(input);
            }
            System.out.print(LINE);
            input = sc.nextLine();
        }

        System.out.println(LINE + ENDDUKE + LINE);
    }

    private void addTask(String task) {
        list.add(task);
        String output = String.format("\t added: %s", task);
        System.out.println(output);
    }

    private void printList() {
        int num = 1;
        for (String task : list) {
            String output = String.format("\t %d. %s", num, task);
            System.out.println(output);
            num++;
        }
    }
}
