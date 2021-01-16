import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Output initial greeting before asking for input
        String initialGreeting = "Hello I'm Duke \n"
                + "What can I do for you? \n";
        System.out.println(initialGreeting);

        // Standard Input Scanner
        Scanner sc = new Scanner(System.in);

        // User input data
        String userInput;
        ArrayList<Task> taskList = new ArrayList<>();

        // Loop through and echo the input until the user inputs "bye"
        while (true) {
            userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // Exit when the user inputs "bye"
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(userInput.equals("list")) {
                // Shows the list of user inputs with its numbering starting from 1
                for (int index = 0; index < taskList.size(); index++) {
                    System.out.println(index + 1 + ". " + taskList.get(index).getTask());
                }
            } else if (userInput.length() > 4 && userInput.startsWith("done")) {
                // Mark the task as done based on the number given after the "done" input
                int taskIndex = Integer.parseInt(userInput.substring(5));

                // Print a response to the user
                try {
                    // Successfully marked the task as done
                    System.out.println("Nice! I've marked this task as done:\n "
                        + "   " + taskList.get(taskIndex - 1).markAsDone());
                } catch (IndexOutOfBoundsException exception) {
                    // Print an error message if the task number is invalid
                    System.out.println("The task number you have entered in invalid. \n" +
                        "Please try again later.");
                }
            } else {
                // Otherwise, store the user input as task into an arraylist
                Task newTask = new Task(userInput);
                taskList.add(newTask);
                System.out.println("added: " + userInput);
            }

        }
    }
}
