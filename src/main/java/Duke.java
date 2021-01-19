import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String task, deadline, keyword;
        int firstSpace, firstSlash, option;
        //Greet User
        printGreetings();
        String command = scanner.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            printLine();

            firstSpace = command.indexOf(" ");
            keyword = firstSpace == -1 ? command : command.substring(0,firstSpace).toLowerCase();
            firstSlash = command.indexOf("/");;
            try {
                switch (keyword) {
                    case "list":
                        //Display all task added
                        listTasks();
                        break;
                    case "done":
                        // -1 as ArrayList starts from 0 , user input starts from 1
                        option = getChoice(command, firstSpace);
                        //Mark task of choice as done
                        completeTask(option);
                        break;
                    case "delete":
                        option = getChoice(command, firstSpace);
                        deleteTask(option);
                        break;
                    case "todo":
                        task = retrieveTask(command, firstSpace, command.length());
                        addTask(new Todo(task));
                        break;
                    case "deadline":
                        task = retrieveTask(command, firstSpace, firstSlash);
                        deadline = retrieveDeadline(command, firstSlash);
                        addTask(new Deadline(task, deadline));
                        break;
                    case "event":
                        task = retrieveTask(command, firstSpace, firstSlash);
                        deadline = retrieveDeadline(command, firstSlash);
                        addTask(new Event(task, deadline));
                        break;
                    default:
                        System.out.println("OOPS!!! I`m sorry. but i don`t know what that means :-(");
                        break;
                }
            } catch (DukeException e) {
                System.out.printf("OOPS!!! %s %s cannot be empty.\n", e.getMessage(), keyword);
            }
            printLine();
            command = scanner.nextLine();
        }  //Exits the program
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void deleteTask(int option) {
        Task removed_task = taskList.get(option);
        taskList.remove(option);
        System.out.println("Noted. I`ve removed this task:");
        printTask("", removed_task);
        printTaskNum();
    }

    public static int getChoice(String command, int firstSpace) {
        return Integer.parseInt(command.substring(firstSpace + 1)) - 1;
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

    public static String retrieveTask(String command , int start, int end) throws DukeException {
        // RetrieveTask starts from the space after the keyword
        // RetrieveTask ends either end of the string or before / (deadline and event)
        // E.g <keyword> <description> (datetime/time)
        // if start == -1 --> e.g todo , deadline , event
        // if end == -1 --> deadline buy book , event buy book
        if (start == -1) {
            throw new DukeException("The description of a");
        } else if (end == -1) {
            throw new DukeException("The deadline of a");
        }
        return command.substring(start,end);
    }

    public static void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it. I`ve added this task:");
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