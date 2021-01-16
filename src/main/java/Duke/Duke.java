package Duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
                System.out.println(""); // Blank line

            } else if (userInput.startsWith("todo")) {
                // Create a new task with the description from user input
                try {
                    // Catch error where the user put empty spaces as description
                    if (userInput.substring(5).isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    ToDo newToDoTask = new ToDo(userInput.substring(5));
                    taskList.add(newToDoTask);

                    // Print a success message
                    System.out.println(newToDoTask.successMessage(taskList.size()));

                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } catch (Exception e) {
                    throw new DukeException("Unknown Exception from todo");
                }

            } else if (userInput.startsWith("deadline")) {
                try {
                    // Catch error where the user put empty spaces as description
                    if (userInput.substring(9).isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    // Deadline task values
                    String[] userInputValues = userInput.substring(9).split("/by ");
                    String description = userInputValues[0];
                    String by = userInputValues[1];

                    // Add a deadline task
                    Deadline newDeadlineTask = new Deadline(description, by);
                    taskList.add(newDeadlineTask);
                    
                    // Print a success message
                    System.out.println(newDeadlineTask.successMessage(taskList.size()));
                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                } catch (Exception e) {
                    throw new DukeException("Unknown Exception from deadline");
                }

            } else if (userInput.startsWith("event")) {
                try {
                    // Catch error where the user put empty spaces as description
                    if (userInput.substring(5).isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }
                    
                    // Event task values
                    String[] userInputValues = userInput.substring(6).split("/at ");
                    String description = userInputValues[0];
                    String at = userInputValues[1];

                    // Add a deadline task
                    Event newEventTask = new Event(description, at);
                    taskList.add(newEventTask);
                    
                    // Print a success message
                    System.out.println(newEventTask.successMessage(taskList.size()));
                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                } catch (Exception e) {
                    throw new DukeException("Unknown Exception from event");
                }
                
            } else if (userInput.startsWith("done")) {
                try {
                    // Mark the task as done based on the number given after the "done" input
                    int taskIndex = Integer.parseInt(userInput.substring(5));

                    // Mark the task as done
                    taskList.get(taskIndex - 1).markAsDone();

                    // Print success message that the task was marked as done
                    System.out.println("Nice! I've marked this task as done:\n "
                        + "   " + taskList.get(taskIndex - 1).toString() + "\n");
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    // Task number is empty
                    System.out.println("Please enter a valid task number.");
                } catch (Exception e) {
                    throw new DukeException("Unknown Exception from done");
                }
                
            } else if (userInput.startsWith("delete")) {
                try {
                    // Delete task
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    Task taskToBeRemoved = taskList.get(taskIndex);

                    // Remove the appropriate task away from the list of task
                    taskList.remove(taskIndex);
                    System.out.println(taskToBeRemoved.deleteMessage(taskList.size()));
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    // Task number is empty
                    System.out.println("Please enter a valid task number.");
                }  
                

            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }

        sc.close();
    }
}
