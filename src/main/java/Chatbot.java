import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Chatbot that provides todo-list function for users.
 */
public class Chatbot {
    static String horizontalLine = "\t--------------------------------\n";
    private final List<String> todo;

    Chatbot() {
        todo = new ArrayList<>();
    }

    /**
     * Print out all the things in the list.
     *
     * @param todo The list of things to do.
     */
    public static void printTodo(List<String> todo) {
        System.out.print(horizontalLine);
        for (int i = 0; i < todo.size(); i++) {
            System.out.println("\t  " + (i + 1) + ". " + todo.get(i));
        }
        System.out.println(horizontalLine);
    }

    /**
     * Execute the chat bot.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {  // exit only when user input "bye"
            if (input.equals("list")) {
                printTodo(todo);
            } else {
                System.out.println(horizontalLine + "\t  added: " + input + "\n" + horizontalLine);
                todo.add(input);
            }
            input = sc.nextLine();
        }
    }

}
