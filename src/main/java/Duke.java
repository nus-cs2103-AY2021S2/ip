import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static boolean isNumber(String str) {
        // check for null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static void taskListWriter(String rootDir, List<Task> taskList) {

        // checking if data folder exists
        File dataFolder = new File(rootDir + "/data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        // delete the original file if exist
        File oldFile = new File(rootDir + "/data/duke.txt");
        if (oldFile.exists()) {
            oldFile.delete();
        }

        // create a new file and update its content with the updated tasklist
        File updatedFile = new File(rootDir + "/data/duke.txt");
        try {
            FileWriter fw = new FileWriter(rootDir + "/data/duke.txt", true);
            for (Task t : taskList) {
                fw.write(t.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write in: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws DukeException{

        String rootDir = System.getProperty("user.dir");

        // initialise all necessary variables
        Scanner inputScanner = new Scanner(System.in);
        Boolean terminate = false; // to check if the chatbot should be terminated
        List<Task> taskList = new ArrayList<>();
        //Task[] taskList = new Task[100]; // to store history of inputs
        int taskCounter = 0;

        System.out.println("    Good morning comrade, welcome to KGB.\n    What can I do for you?");

        // chatbot continues to wait for user input until the user chooses to terminate the bot
        while (!terminate) {

            String userInput = inputScanner.nextLine();

            try {
                if (userInput.equals("list")) {

                    System.out.println("    Here are the tasks in your list:");
                    // print out all tasks
                    int i = 1;
                    for (Task t : taskList) {
                        System.out.println("    " + i + "." + t.toString());
                        i++;
                    }

                } else if (userInput.equals("bye")) {
                    // terminate the chat bot
                    System.out.println("    Goodbye comrade. Hope to see you again soon!");
                    terminate = true;
                } else if (userInput.startsWith("done")) {

                    // check if nothing follows after done, in which case the input should be invalid
                    if (userInput.equals("done")) {
                        throw new DukeException("The task number of a done cannot be empty. Please try again");
                    }

                    // mark the specific task as done
                    // extract task index, then mark task as done
                    String temp = userInput.substring(5);
                    int taskIndex;

                    // check if the task number after done is a valid number
                    if (isNumber(temp)) {
                        taskIndex = Integer.parseInt(temp);
                    } else {
                        throw new DukeException("Please enter a numerical task number");
                    }

                    // check if the task number entered is valid
                    if (taskIndex < 1 || taskIndex > 101 || taskList.get(taskIndex - 1) == null) {
                        throw new DukeException("Your requested task does not exist!");
                    }
                    Task currentTask = taskList.get(taskIndex - 1);
                    currentTask.markAsDone();

                    // print out relevant information
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("     " + currentTask.toString());
                    taskListWriter(rootDir, taskList);

                } else if (userInput.startsWith("todo")) {
                    // check if the todos description is provided
                    if (userInput.equals("todo")) {
                        throw new DukeException("The todo description should not be empty!");
                    }

                    // create new todos and put inside taskList
                    String todoDescription = userInput.substring(5);
                    Todo newTodo = new Todo(todoDescription);
                    taskList.add(newTodo);
                    taskCounter++;

                    // print out relevant information
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("     " + newTodo.toString());
                    System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                    taskListWriter(rootDir, taskList);

                } else if (userInput.startsWith("deadline")) {
                    // check if the deadline description is provided
                    if (userInput.equals("deadline")) {
                        throw new DukeException("The deadline description should not be empty!");
                    }

                    // create new deadline and put inside taskList
                    String dlMsg = userInput.substring(9);
                    String[] temp = dlMsg.split(" /by ");

                    // check if the deadline time is provided
                    if (temp.length == 1) {
                        throw new DukeException("The deadline time should not be empty!");
                    }

                    String dlDescription = temp[0];
                    String by = temp[1];
                    Deadline newDL = new Deadline(dlDescription, by);
                    taskList.add(newDL);
                    taskCounter++;

                    // print out relevant information
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("     " + newDL.toString());
                    System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                    taskListWriter(rootDir, taskList);


                } else if (userInput.startsWith("event")) {

                    // create new event and put inside taskList
                    if (userInput.equals("event")) {
                        throw new DukeException("The event description should not be empty!");
                    }

                    String eMsg = userInput.substring(6);
                    String[] temp = eMsg.split(" /at ");
                    // check if the deadline time is provided
                    if (temp.length == 1) {
                        throw new DukeException("The event time should not be empty!");
                    }

                    String eDescription = temp[0];
                    String at = temp[1];
                    Event newEvent = new Event(eDescription, at);
                    taskList.add(newEvent);
                    taskCounter++;

                    // print out relevant information
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("     " + newEvent.toString());
                    System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                    taskListWriter(rootDir, taskList);

                } else if (userInput.startsWith("delete")) {
                    // check if delete index is provided
                    if (userInput.equals("delete")) {
                        throw new DukeException("The delete number should not be empty!");
                    }

                    // extract task index, then mark task as done
                    String temp = userInput.substring(7);
                    int taskIndex;

                    // check if the task number after done is a valid number
                    if (isNumber(temp)) {
                        taskIndex = Integer.parseInt(temp);
                    } else {
                        throw new DukeException("Please enter a numerical task number");
                    }

                    // check if the task number entered is valid
                    if (taskIndex < 1 || taskIndex > 101 || taskList.get(taskIndex - 1) == null) {
                        throw new DukeException("Your requested task does not exist!");
                    }

                    Task currentTask = taskList.get(taskIndex - 1);
                    taskList.remove(taskIndex - 1);
                    taskCounter--;
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("     " + currentTask.toString());
                    System.out.println("    Now you have " + taskCounter + " tasks in the list.");
                    taskListWriter(rootDir, taskList);
                } else {
                    throw new DukeException("I'm sorry, I don't understand what that means.");
                }
            } catch (DukeException e) {
                System.out.println("    " + e.getMessage());
            }
        }
    }
}