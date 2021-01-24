package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                System.out.println(); // Blank line

            } else if (userInput.startsWith("todo")) {
                // Create a new task with the description from user input
                try {
                    // Catch error where the user put empty spaces as description
                    if (userInput.substring(5).isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    ToDo newToDoTask = new ToDo(userInput.substring(5));
                    taskList.add(newToDoTask);
                    updateFile(taskList);

                    // Print a success message
                    System.out.println(newToDoTask.successMessage(taskList.size()));

                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } catch (FileNotFoundException ex) {
                    System.out.println("OOPS!!! The file could not be found.");
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
                    
                    // Specific the date format that our system will accept and save it in the by variable
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime by = LocalDateTime.parse(userInputValues[1], dateFormatter);

                    // Add a deadline task
                    Deadline newDeadlineTask = new Deadline(description, by);
                    taskList.add(newDeadlineTask);
                    updateFile(taskList);
                    
                    // Print a success message
                    System.out.println(newDeadlineTask.successMessage(taskList.size()));
                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                } catch (FileNotFoundException ex) {
                    System.out.println("OOPS!!! The file could not be found.");
                } catch (DateTimeParseException ex) {
                    // Datetime value parsed is not of format "yyyy-MM-dd HHmm"
                    System.out.println("OOPS!! Please input a value date time format of yyyy-MM-dd HHmm");
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
                    
                    // Specific the date format that our system will accept and save it in the by variable
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime at = LocalDateTime.parse(userInputValues[1], dateFormatter);

                    // Add a deadline task
                    Event newEventTask = new Event(description, at);
                    taskList.add(newEventTask);
                    updateFile(taskList);
                    
                    // Print a success message
                    System.out.println(newEventTask.successMessage(taskList.size()));
                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                } catch (FileNotFoundException ex) {
                    System.out.println("OOPS!!! The file could not be found.");
                } catch (DateTimeParseException ex) {
                    // Datetime value parsed is not of format "yyyy-MM-dd HHmm"
                    System.out.println("OOPS!! Please input a value date time format of yyyy-MM-dd HHmm");
                } catch (Exception e) {
                    throw new DukeException("Unknown Exception from event");
                }
                
            } else if (userInput.startsWith("done")) {
                try {
                    // Mark the task as done based on the number given after the "done" input
                    int taskIndex = Integer.parseInt(userInput.substring(5));

                    // Mark the task as done
                    taskList.get(taskIndex - 1).markAsDone();
                    updateFile(taskList);

                    // Print success message that the task was marked as done
                    System.out.println("Nice! I've marked this task as done:\n "
                        + "   " + taskList.get(taskIndex - 1).toString() + "\n");
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    // Task number is empty
                    System.out.println("Please enter a valid task number.");
                } catch (FileNotFoundException ex) {
                    System.out.println("OOPS!!! The file could not be found.");
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
                    updateFile(taskList);
                    System.out.println(taskToBeRemoved.deleteMessage(taskList.size()));
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    // Task number is empty
                    System.out.println("Please enter a valid task number.");
                } catch (FileNotFoundException ex) {
                    System.out.println("OOPS!!! The file could not be found.");
                }  
                
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }
        sc.close();
    }

    private static void updateFile (ArrayList<Task> taskList) throws FileNotFoundException {
        String FILE_PATH = "data/duke.txt"; // Hardcoded file path
        File file = new File(FILE_PATH); // create a File for the given file path
        File folder = new File("data");

        // Ensures that the data folder exists, if not create the folder
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Folder has been created");
            } else {
                System.out.println("There was an error creating the folder");
            };
        }

        // Ensures that the file is created, if not create the file
        if (!file.exists() && !file.isFile()) {
            // Create a new file
            try {
                file.createNewFile();
                System.out.println("File has been created");
            } catch (IOException ex) {
                System.out.println("An IOException has occured while creating the file");
                System.out.println(ex);
            }   
        }

        // Push the file content to the page
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(FILE_PATH);

            // Rewrite the file with the entire list of text
            for (int index = 0; index < taskList.size(); index++) {
                fileWriter.write(taskList.get(index).toString() + "\n");
            }
            
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An IOException has occured while writing to the file");
        }
    }
}
