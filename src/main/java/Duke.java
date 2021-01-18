import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Greet User
        printGreetings();
        String command = scanner.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            printLine();
            // Split command to check if first word is done
            // and also to extract the option
            String[] commandArr = command.split(" ");
            switch (commandArr[0]) {
                case "list":
                    //Display all task added
                    listTasks();
                    break;
                case "done":
                    int option = Integer.parseInt(commandArr[commandArr.length - 1]) - 1;
                    //Mark task of choice as done
                    completeTask(option);
                    break;
                default:
                    //Adding command as task into taskList
                    taskList.add(new Task(command));
                    //Echo command
                    System.out.printf("added: %s\n", command);
                    break;
            }
            printLine();
            command = scanner.nextLine().toLowerCase();
        }  //Exits the program
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void completeTask(int option) {
        boolean res = false;
        String message = "Invalid option";
        // Option validation (More than 0 and not more than the number of task)
        if (option >= 0 && option < taskList.size()) {
            res = taskList.get(option).markAsDone();
            message = String.format("Task %d is already been marked as done", option + 1);
        }

        if (res) {
            System.out.println("Nice! I`ve marked this task as done:");
            System.out.println(taskList.get(option));
        } else {
            System.out.println(message);
        }
    }

    public static void printTask(String numbering, Task task) {
        System.out.printf("%s %s\n", numbering , task);
    }

    public static void listTasks() {
        for(int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", (i+1) , taskList.get(i));
        }
    }

    public static void printLine() {
        System.out.println("----------------------------------------------");
    }
    public static void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I`m Duke");
        System.out.println("How can i help you?");
        printLine();
    }
}
