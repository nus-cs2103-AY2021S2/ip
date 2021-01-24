import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
        TaskList taskList = new TaskList("../../../data/tasks.txt");
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
                taskList.writeFile();
            } else if (cmd.contains("delete")){
                int index = Integer.parseInt(cmd.substring(7));
                taskList.removeTask(index );
                taskList.writeFile();
            } else {
                taskList = performChildTask(taskList, cmd);
                taskList.writeFile();
            }
            System.out.println(printLine());
        }
        printBye();
        input.close();
        return taskList;
    }

    public static TaskList performChildTask(TaskList taskList, String cmd) {
        int taskType = checkChildTask(cmd);
        String task = checkInput(cmd, taskType);
        int seg = cmd.indexOf("/");
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
                    } else if (taskType == DEADLINE) {
                        String by = cmd.substring(seg + 4);
                        Deadline newDeadLine = new Deadline(task, by);
                        performAddTask(taskList, newDeadLine);
                    } else {
                        String at = cmd.substring(seg + 4);
                        Event newEvent = new Event(task, at);
                        performAddTask(taskList, newEvent);
                    }
                    printTotalTasks(taskList);
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        }
        return taskList;
    }

    public static int checkChildTask(String cmd) {
        if (cmd.indexOf("todo") == 0 && cmd.contains("todo")) {
            return TODO;
        } else if (cmd.indexOf("deadline") == 0 && cmd.contains("deadline")) {
            return DEADLINE;
        } else if (cmd.indexOf("event") == 0 && cmd.contains("event")) {
            return EVENT;
        } else {
            return -1;
        }
    }

    public static String checkInput(String cmd, int taskID) {
        String task = new String();
        String type = new String();
        try {
            if (taskID == TODO) {
                type = "todo";
                if(cmd.length() > 4) {
                    task = cmd.substring(5);
                }
            } else if (taskID == DEADLINE) {
                type = "deadline";
                int seg = cmd.indexOf("/");
                if(cmd.length() > 8 && seg != -1) {
                    task = cmd.substring(9, seg);
                }
            } else {
                type = "event";
                int seg = cmd.indexOf("/");
                if(cmd.length() > 5 && seg != -1) {
                    task = cmd.substring(6, seg);
                }
            }
            if(task.equals("")){
                throw new DukeException(" ☹ OOPS!!! " +
                    "The description of a " + type + " cannot be empty.");
            }
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
        return task;
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
