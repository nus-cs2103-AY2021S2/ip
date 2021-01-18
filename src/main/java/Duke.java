import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {
    /**
     * Starts the program.
     */
    public static void main(String[] args) throws IOException {
        printGreeting();
        //performFirstTask();
        TaskList taskList = performSecondTask();
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
        TaskList taskList = new TaskList();
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
                taskList.getTask(index - 1).setCompleted();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskList.getTask(index - 1));
            } else if (cmd.contains("delete")){
                int index = Integer.parseInt(cmd.substring(7));
                Task temp = taskList.getTask(index - 1);
                taskList.removeTask(index - 1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(temp);
                System.out.println("Now you have "
                    + taskList.getSize()
                    + " tasks in the list.");
            } else {
                taskList = performChildTask(taskList, cmd);
            }
            System.out.println(printLine());
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(printLine());
        input.close();
        return taskList;
    }

    /**
     * Performs the addition of the respective child task into Task List and
     * returns the updated Task List.
     *
     * @param  taskList  previous Task List.
     * @param  cmd       the command type for the respective task.
     * @return           updated Task List.
     */
    public static TaskList performChildTask(TaskList taskList, String cmd) {
        String task = new String();
        try {
            if (cmd.contains("todo")) {
                if(cmd.length() > 4) {
                    task = cmd.substring(5);
                }
                if(task.equals("")){
                    throw new DukeException(" ☹ OOPS!!! " +
                        "The description of a todo cannot be empty.");
                }
                ToDo newToDo = new ToDo(task);
                taskList.addTask(newToDo);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newToDo);
            } else if (cmd.contains("deadline")) {
                int seg = cmd.indexOf("/");
                if(cmd.length() > 8 && seg != -1) {
                    task = cmd.substring(9, seg);
                }
                if(task.equals("")){
                    throw new DukeException(" ☹ OOPS!!! " +
                        "The description of a deadline cannot be empty.");
                }
                String by = cmd.substring(seg + 4);
                Deadline newDeadline = new Deadline(task, by);
                taskList.addTask(newDeadline);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newDeadline);
            } else if (cmd.contains("event")) {
                int seg = cmd.indexOf("/");
                if(cmd.length() > 5 && seg != -1) {
                    task = cmd.substring(6, seg);
                } else if (task.equals("")) {
                    throw new DukeException(" ☹ OOPS!!! " +
                        "The description of a event cannot be empty.");
                }
                String at = cmd.substring(seg + 4);
                Event newEvent = new Event(task, at);
                taskList.addTask(newEvent);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newEvent);
            } else {
                throw new
                    DukeException("☹ OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
            }
            System.out.println("Now you have "
                + taskList.getSize()
                + " tasks in the list.");
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
        return taskList;
    }
}
