import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

/**
 * Represents a task manager that allows users to add, delete and mark tasks as done.
 */
public class Duke {

    public static void level1() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) throws IOException, DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("So here are the rules!: ");
        System.out.println("-- list:  Prints current tasks in the list\n"
                + "-- todo <nameOfToDoTask>:  Adds a todo task to your list\n"
                + "-- deadline <nameOfDeadlineTask> /by <YYYY-MM-DD HH:MM>:  Adds a deadline task to your list\n"
                + "-- event <nameOfEventTask> /at <YYYY-MM-DD HH:MM>:  Adds an event task to your list\n"
                + "-- delete <taskNumber>:  Deletes specified task\n"
                + "-- done <taskNumber>:  Marks specified task as done\n"
                + "-- taskdate <YYYY-MM-DD>:  Prints tasks in your list that match the specified date");

        TaskList taskList = new TaskList();
        String filePath = "data/duke.txt";
        if (FileManager.doesExist(filePath)) {
            taskList = FileManager.textFileToArrayList(filePath);
        }
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] inputArr = input.split(" ");

                if (input.equals("list")) {
                    taskList.printTaskList();
                } else if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                    if (inputArr.length == 1) {
                        String aOrAn = inputArr[0].equals("event")
                                ? "an "
                                : "a ";
                        throw new DukeException("☹ OOPS!!! The description of " + aOrAn + inputArr[0] + " cannot be empty.");
                    }
                    TaskType taskType = TaskType.valueOf(inputArr[0].toUpperCase());
                    String description = input.split(inputArr[0] + " ")[1];
                    taskList.addTask(taskType, description, false);
                } else if (inputArr[0].equals("delete")) {
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The task number has not been specified.");
                    }
                    taskList.deleteTask(Integer.parseInt(inputArr[1]));
                } else if (inputArr[0].equals("done")) {
                    int taskNo = Integer.parseInt(inputArr[1]);
                    taskList.getTask(taskNo).markAsDone();
                } else if (inputArr[0].equals("taskdate")) {
                    LocalDate date = LocalDate.parse(inputArr[1]);
                    taskList.printTasksOn(date);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException | IOException e) {
                System.out.println(e);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
