package soonwee.duke;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.time.LocalDateTime;

public class Duke {

    public static final int TODO = 1;
    public static final int DEADLINE = 2;
    public static final int EVENT = 3;
    public static Ui display = new Ui();

    /**
     * Starts the program.
     */
    public static void main(String[] args) throws IOException {
        System.out.println(display.printGreeting());
        System.out.println(display.printPrompt());
        selectAction();
        System.out.println(display.printBye());
    }

    public static void selectAction() throws IOException {
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
            System.out.println(display.printLine());
            if (cmd.equals("bye")) {
                break;
            }
            System.out.println(cmd);
            System.out.println(display.printLine());
        }
    }

    /**
     * Performs the second macro task which can add, delete or mark a task as
     * done. Macro will end when user types in 'bye'.
     *
     * @return      updated Task List.
     */
    public static TaskList performSecondTask() throws IOException {
        String cmd = new String();
        Storage storage = new Storage("data/tasks.txt");
        BufferedReader input = new
                BufferedReader(new InputStreamReader(System.in));
        while (true) {
            cmd = input.readLine();
            System.out.println(display.printLine());
            if (cmd.equals("list")) {
                storage.taskList.displayTasks();
            } else if (cmd.equals("bye")) {
                break;
            } else if (cmd.contains("done")) {
                int index = Integer.parseInt(cmd.substring(5));
                storage.taskList.setTaskDone(index);
                storage.writeFile();
            } else if (cmd.contains("delete")) {
                int index = Integer.parseInt(cmd.substring(7));
                storage.taskList.removeTask(index);
                storage.writeFile();
            } else {
                storage.taskList = performChildTask(storage.taskList, cmd);
                storage.writeFile();
            }
            System.out.println(display.printLine());
        }
        input.close();
        return storage.taskList;
    }

    public static TaskList performChildTask(TaskList taskList, String cmd) {
        Parser checker = new Parser();
        int taskType = checker.checkTaskType(cmd);
        String task = checker.checkFrontInput(cmd, taskType);
        if (!task.isEmpty()) {
            try {
                if (taskType == -1) {
                    throw new
                            DukeException("☹ OOPS!!! I'm sorry, " + "but I don't know what that means :-(");
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
                    System.out.println(display.printTotalTasks(taskList));
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
}