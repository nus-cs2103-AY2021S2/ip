import java.util.Scanner;

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

    public static void main(String[] args) {

        // initialise all necessary variables
        Scanner inputScanner = new Scanner(System.in);
        Boolean terminate = false; // to check if the chatbot should be terminated
        Task[] taskHistory = new Task[100]; // to store history of inputs
        int historyCounter = 0;

        System.out.println("    Good morning comrade, welcome to KGB.\n    What can I do for you?");

        // chatbot continues to wait for user input until the user chooses to terminate the bot
        while (!terminate) {
            String userInput = inputScanner.nextLine();
            if (userInput.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                // print out all tasks
                for (int i = 0; i < historyCounter; i++) {
                    // extract relevant information of each task
                    Task currentTask = taskHistory[i];
                    int taskID = i + 1;
                    String taskDescription = currentTask.getDescription();
                    String taskIcon = currentTask.getStatusIcon();
                    System.out.println("    " + taskID + "." + taskIcon + taskDescription);
                }
            } else if (userInput.equals("bye")) {
                // terminate the chat bot
                System.out.println("    Goodbye comrade. Hope to see you again soon!");
                terminate = true;
            } else if (userInput.startsWith("done")) {
                if (userInput.equals("done")) {
                    System.out.println("    Invalid input. Please try again!");
                    continue;
                }

                // mark the specific task as done
                // extract task index, then mark task as done
                String temp = userInput.substring(5);
                int taskIndex;

                // check if the task number after done is a valid number
                if (isNumber(temp)) {
                    taskIndex = Integer.parseInt(temp);
                } else {
                    System.out.println("    Invalid input. Please try again!");
                    continue;
                }

                // check if the task number entered is valid
                if (taskIndex < 1 || taskIndex >101 || taskHistory[taskIndex-1] == null) {
                    System.out.println("    Your requested task does not exist");
                    continue;
                }
                Task currentTask = taskHistory[taskIndex-1];
                currentTask.markAsDone();

                // print out relevant information
                String taskDescription = currentTask.getDescription();
                String taskIcon = currentTask.getStatusIcon();
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("     " + taskIcon + " " + taskDescription);

            } else {
                // create new task and put inside taskHistory
                Task newTask = new Task(userInput);
                taskHistory[historyCounter] = newTask;
                historyCounter++;
                System.out.println("    added: " + userInput);
            }
        }
    }
}