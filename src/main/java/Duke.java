import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob :D\n" + "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye! See you soon!");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    System.out.println("This is your list of tasks: \n "+
                            index + "." + list.get(i));
                }
            } else if (userInput.length() >= 4 && userInput.substring(0,4).equals("done")) {
                try {
                    int index = Integer.parseInt(userInput.substring(5));
                    if (index < list.size()) {
                        Task updatedTask = list.get(index - 1);
                        updatedTask.status(true);
                        System.out.println("Good job! This task has been marked as done :)\n" +
                                updatedTask);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid format, please try again using numbers only.");
                }

            } else {
                list.add(new Task(userInput, false));
                System.out.println("added: " + userInput);
            }
        }
    }
}
