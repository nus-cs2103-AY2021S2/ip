import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Chatbot that provides todo-list function for users.
 */
public class Chatbot {
    private final List<Task> todo;

    Chatbot() {
        todo = new ArrayList<>();
    }

    /**
     * Print out all the things in the list.
     *
     * @param todo The list of things to do.
     */
    public static void printTodo(List<Task> todo) {
        System.out.print(Duke.horizontalLine);
        for (int i = 0; i < todo.size(); i++) {
            Task temp = todo.get(i);
            if (temp.getDone()) {
                System.out.println("\t  " + (i + 1) + ".[x] " + todo.get(i).getName());
            } else {
                System.out.println("\t  " + (i + 1) + ".[ ] " + todo.get(i).getName());
            }
        }
        System.out.println(Duke.horizontalLine);
    }

    public void markDone(int order) {
        System.out.print(Duke.horizontalLine);
        System.out.println("\t  Nice! I've marked this task as done: ");
        System.out.println("\t\t[x] " + todo.get(order).getName());
        System.out.println(Duke.horizontalLine);
        todo.set(order, new Task(todo.get(order).getName(), true));
    }

    /**
     * Execute the chat bot.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {  // exit only when user input "bye"
            if (input.toLowerCase().equals("list")) {
                printTodo(todo);
            } else if (input.toLowerCase().contains("done")) {
                String[] temp = input.split(" ");
                int tempOrder = Integer.parseInt(temp[1]);
                if (temp.length == 2 && tempOrder > 0 && tempOrder <= todo.size()) {
                    markDone(tempOrder - 1);
                }
            } else {
                System.out.println(Duke.horizontalLine + "\t  added: " + input + "\n" + Duke.horizontalLine);
                todo.add(new Task(input));
            }
            input = sc.nextLine();
        }
    }

}
