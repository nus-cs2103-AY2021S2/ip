package Duke;

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
            } else if (userInput.equals("list")) {
                // Print a listing of task
                System.out.println("Here are the tasks in your list:");
                
                // Shows the list of user inputs with its numbering starting from 1
                for (int index = 0; index < taskList.size(); index++) {
                    System.out.println(index + 1 + ". " + taskList.get(index).toString());
                }

            } else if (userInput.startsWith("todo")) {
                // Add a todo task
                ToDo newToDoTask = new ToDo(userInput.substring(5));
                taskList.add(newToDoTask);

                // Print a success message
                System.out.println(newToDoTask.successMessage(taskList.size()));

            } else if (userInput.startsWith("deadline")) {
                // Deadline task values
                String[] userInputValues = userInput.substring(9).split("/by ");
                String description = userInputValues[0];
                String by = userInputValues[1];

                // Add a deadline task
                Deadline newDeadlineTask = new Deadline(description, by);
                taskList.add(newDeadlineTask);
                
                // Print a success message
                System.out.println(newDeadlineTask.successMessage(taskList.size()));

            } else if (userInput.startsWith("event")) {
                // Event task values
                String[] userInputValues = userInput.substring(6).split("/at ");
                String description = userInputValues[0];
                String at = userInputValues[1];

                // Add a deadline task
                Event newEventTask = new Event(description, at);
                taskList.add(newEventTask);
                
                // Print a success message
                System.out.println(newEventTask.successMessage(taskList.size()));
            } else if (userInput.startsWith("done") && userInput.length() > 4) {
                // Mark the task as done based on the number given after the "done" input
                int taskIndex = Integer.parseInt(userInput.substring(5));

                // Print a response to the user
                try {
                    // Mark the task as done
                    taskList.get(taskIndex - 1).markAsDone();

                    // Successfully marked the task as done
                    System.out.println("Nice! I've marked this task as done:\n "
                        + "   " + taskList.get(taskIndex - 1).toString());
                } catch (IndexOutOfBoundsException exception) {
                    // Print an error message if the task number is invalid
                    System.out.println("The task number you have entered in invalid. \n" +
                        "Please try again later.");
                }
            }

        }

        sc.close();
    }
}
