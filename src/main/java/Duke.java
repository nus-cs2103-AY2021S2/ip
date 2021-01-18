import java.util.ArrayList;;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String task = "", deadline = "";
        int firstSpace = 0;
        //Greet User
        printGreetings();
        String command = scanner.nextLine().toLowerCase();
        while (!command.equalsIgnoreCase("bye")) {
            printLine();
            // Split command to check if first word is done
            // and also to extract the option
            firstSpace = command.indexOf(" ");
            firstSpace = firstSpace == -1 ? command.length() : firstSpace;
            switch (command.substring(0,firstSpace)) {
                case "list":
                    //Display all task added
                    listTasks();
                    break;
                case "done":
                    // -1 as ArrayList starts from 0 , user input starts from 1
                    int option = Integer.parseInt(command.substring(firstSpace + 1)) - 1;
                    //Mark task of choice as done
                    completeTask(option);
                    break;
                case "todo":
                    task = retrieveTask(command,firstSpace, command.length());
                    addTask(new Todo(task));
                    break;
                case "deadline":
                    int firstSlash = findSlash(command);
                    task = retrieveTask(command,firstSpace, firstSlash);
                    deadline = retrieveDeadline(command,firstSlash);
                    // +1 to exclude / in the deadline
                    addTask(new Deadline(task, deadline));
                    break;

            }
            printLine();
            command = scanner.nextLine().toLowerCase();
        }  //Exits the program
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static String retrieveDeadline(String command, int start) {
        boolean space = false;
        StringBuilder res = new StringBuilder();
        for(int i = start + 1; i < command.length(); i++) {
            if (!space && command.charAt(i) == ' ') {
                space = true;
                continue;
            }
            if(space) {
                res.append(command.charAt(i));
            }
        }
        return res.toString();
    }

    public static int findSlash(String command) {
        return command.indexOf("/");
    }

    public static String retrieveTask(String command , int start, int end) {
        return command.substring(start,end);
    }

    public static void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it. I`ve added this task: ");
        printTask("", newTask);
        printTaskNum();
    }

    public static void printTaskNum() {
        System.out.printf("Now you have %d task in the list\n", taskList.size());
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
            printTask("", taskList.get(option));
        } else {
            System.out.println(message);
        }
    }

    public static void printTask(String numbering, Task task) {
        System.out.printf("%2s %s\n", numbering , task);
    }

    public static void listTasks() {
        for(int i = 0; i < taskList.size(); i++) {
            printTask (i+1+ "." , taskList.get(i));
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
