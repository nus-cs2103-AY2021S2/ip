package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class which handles the parsing of user's input and delivers the expected action accordingly
 */
public class Parser {

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public Parser() {

    }

    /**
     * Gets the tasklist
     * @return the tasklist
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a new task into the existing tasklist
     * @param newTask Task to be added
     */
    public static void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:" + "\n" + newTask.toString()
                + "\n" + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Lists out all the tasks currently in the tasklist
     */
    public static void listTask() {
        System.out.println("Here are the task in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    /**
     * Deletes a specific task from the tasklist
     * @param i The numbering of the task to be deleted in chronological order
     */
    public static void deleteTask(int i) {
        Task task = taskList.get(i - 1);
        taskList.remove(i - 1);
        System.out.println("Noted. I've removed this task:" + "\n" + task.toString()
                + "\n" + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Marks a current task on the tasklist as done
     * @param i The numbering of the task to be marked as done in chronological order
     */
    public static void markDone(int i) {
        Task task = taskList.get(i - 1);
        task.done();
        System.out.println("Nice! I've marked this task as done: " + "\n" + task.toString());
    }

    /**
     * Reads user input and parse it accordingly so as to deliver the according action correctly
     * @throws DukeException if user input is empty or invalid
     * @throws Exception
     */
    public static void read() throws DukeException, Exception {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("")) {
                throw new EmptyArgument("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            if (input.startsWith("done")) {
                String[] spiltInput = input.split("\\s+");
                int taskNumber = Integer.parseInt(spiltInput[1]);
                markDone(taskNumber);
            } else if (input.startsWith("delete")) {
                String[] spiltInput = input.split("\\s+");
                int taskNumber = Integer.parseInt(spiltInput[1]);
                deleteTask(taskNumber);
            } else if (input.startsWith("event")) {
                String[] spiltInput = input.split("\\s+");
                String time = "";
                String desc = "";
                int start = 0;
                for (int i = 1; i < spiltInput.length; ++i) {
                    if (spiltInput[i].equals("/at")) {
                        start = i;
                        break;
                    }
                    if (desc.equals("")) {
                        desc += spiltInput[i];
                    } else {
                        desc = desc + " " + spiltInput[i];
                    }
                }
                for (int i = start + 1; i < spiltInput.length; ++i) {
                    if (time.equals("")) {
                        time += spiltInput[i];
                    } else {
                        time = time + " " + spiltInput[i];
                    }
                }
                EventTask task = new EventTask(desc, time);
                addTask(task);
            } else if (input.startsWith("deadline")) {
                String[] spiltInput = input.split("\\s+");
                String time = "";
                String desc = "";
                int start = 0;
                for (int i = 1; i < spiltInput.length; ++i) {
                    if (spiltInput[i].equals("/by")) {
                        start = i;
                        break;
                    }
                    if (desc.equals("")) {
                        desc += spiltInput[i];
                    } else {
                        desc = desc + " " + spiltInput[i];
                    }
                }
                for (int i = start + 1; i < spiltInput.length; ++i) {
                    if (time.equals("")) {
                        time += spiltInput[i];
                    } else {
                        time = time + " " + spiltInput[i];
                    }
                }
                DeadlineTask task = new DeadlineTask(desc, time);
                addTask(task);
            } else if (input.startsWith("todo")) {
                String[] spiltInput = input.split("\\s+");
                String desc = "";
                for (int i = 1; i < spiltInput.length; ++i) {
                    if (desc.equals("")) {
                        desc += spiltInput[i];
                    } else {
                        desc = desc + " " + spiltInput[i];
                    }
                }
                ToDoTask task = new ToDoTask(desc);
                addTask(task);

            } else if (input.equals("bye")) {
                Ui.exit();
                break;
            } else if (input.equals("list")) {
                listTask();
            } else if (input.equals("save")) {
                Storage.saveTaskList();
            } else if (input.equals("load")) {
                Storage.loadTaskList();
            } else {
                throw new InvalidArgument("Your input is invalid, Please try again");
            }
        }
    }

}
