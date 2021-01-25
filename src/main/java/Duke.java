import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static final int TODO = 1;
    public static final int DEADLINE = 2;
    public static final int EVENT = 3;

    /**
     * Starts the program.
     */
    public static void main(String[] args) throws IOException {
        printGreeting();
        selectAction();
    }

    /**
     * Returns a line of ---- for organization.
     * <p>
     * This method always returns immediately upon launch.
     *
     * @return      a number of - to simulate a line.
     */
    public static String printLine() {
        return "---------------------------";
    }


    /**
     * Prints out a greeting format.
     */
    public static void printGreeting() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(printLine());
        System.out.println(greet);
        System.out.println(printLine());
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(printLine());
    }

    public static void selectAction() throws IOException{
        String prompt = "We have 2 functions in this bot, namely\n";
        prompt = prompt + "1. Echo Bot\n2. Task List Bot\n";
        String instruct = "Please key in 1 or 2 to select the bot.\n";
        System.out.println(prompt);
        System.out.println(instruct);
        BufferedReader input = new
            BufferedReader(new InputStreamReader(System.in));
        String cmd = input.readLine();
        switch (cmd) {
            case "1":
                performFirstTask();
                break;
            case "2":
                TaskList temp = performSecondTask();
                break;
            default:
                break;
        }
        input.close();
    }

    /**
     * Performs the first macro task which will echo whatever the user types
     * in and ends when the user types 'bye'.
     */
    public static void performFirstTask() throws IOException {
        String cmd = new String();
        BufferedReader input = new
            BufferedReader(new InputStreamReader(System.in));
        while (true) {
            cmd = input.readLine();
            System.out.println(printLine());
            if (cmd.equals("bye")) {
                break;
            }
            System.out.println(cmd);
            System.out.println(printLine());
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(printLine());
    }

    /**
     * Performs the second macro task which can add, delete or mark a task as
     * done. Macro will end when user types in 'bye'.
     *
     * @return      updated Task List.
     */
    public static TaskList performSecondTask() throws IOException {
        String cmd = new String();
        TaskList taskList = new TaskList("tasks.txt");
        BufferedReader input = new
            BufferedReader(new InputStreamReader(System.in));
        while (true) {
            cmd = input.readLine();
            System.out.println(printLine());
            if (cmd.equals("list")) {
                taskList.displayTasks();
            } else if (cmd.equals("bye")) {
                break;
            } else if (cmd.contains("done")) {
                int index = Integer.parseInt(cmd.substring(5));
                taskList.setTaskDone(index);
            } else if (cmd.contains("delete")){
                int index = Integer.parseInt(cmd.substring(7));
                taskList.removeTask(index );
            } else{
                taskList = performChildTask(taskList, cmd);
            }
            System.out.println(printLine());
        }
        printBye();
        input.close();
        return taskList;
    }


    public static TaskList performChildTask(TaskList taskList, String cmd) {
        InputChecker checker = new InputChecker();
        int taskType = checker.checkTaskType(cmd);
        String task = checker.checkFrontInput(cmd, taskType);
        if(!task.isEmpty()) {
            try {
                if (taskType == -1) {
                    throw new
                        DukeException("☹ OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-(");
                } else {
                    if (taskType == TODO) {
                        ToDo newToDo = new ToDo(task);
                        performAddTask(taskList, newToDo);
                    } else {
                        LocalDateTime date = checker.dateFormatter(cmd);
                        if (taskType == DEADLINE) {
                            Deadline newDeadLine = new Deadline(task, date);
                            performAddTask(taskList, newDeadLine);
                        } else {
                            Event newEvent = new Event(task, date);
                            performAddTask(taskList, newEvent);
                        }
                    }
                    printTotalTasks(taskList);
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        }
        return taskList;
    }

    public static void performAddTask(TaskList taskList, Task task) {
        taskList.addTask(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
    }

    public static void printTotalTasks(TaskList tasklist) {
        System.out.println("Now you have "
                + tasklist.getSize()
                + " tasks in the list.");
    }
}
