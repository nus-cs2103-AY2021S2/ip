import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final static String LINE = "\t__________________________________\n";
    private final static String GREETING = "\t Hello! I'm Duke\n\t What can I do for you? \n";
    private final static String ENDDUKE = "\t Bye. Hope to see you again soon!\n";
    private final List<Task> list;

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
            handleInput(input);
            System.out.print(LINE);
            input = sc.nextLine();
        }

        System.out.println(LINE + ENDDUKE + LINE);
    }

    private void handleInput(String input) {
        if (input.startsWith("done")) {
            doneTask(input);
        } else if(input.equals("list")) {
            printList();
        } else {
            addTask(input);
        }
    }

    private void doneTask(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        Task task = list.get(index);
        task.done();
        String output = String.format("\t Nice! I've marked this task as done: \n \t\t %s", task);
        System.out.println(output);
    }

    private void addTask(String task) {
        list.add(new Task(task));
        String output = String.format("\t added: %s", task);
        System.out.println(output);
    }

    private void printList() {
        int num = 1;
        for (Task task : list) {
            String output = String.format("\t %d.%s", num, task);
            System.out.println(output);
            num++;
        }
    }
}
