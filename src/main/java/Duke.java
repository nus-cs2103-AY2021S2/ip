import Exceptions.EmptyDescriptionException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidInputException;
import Exceptions.InvalidTaskNumberException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    final static String horzLine = "    _________________________________________________";
    static List<Task> list = new ArrayList<>();
    static boolean exit = false;
    static String filePath = "./data/duke.txt";

    public static void welcome() {
        String logo = " ____        _        \n"
                + "               |  _ \\ _   _| | _____\n"
                + "               | | | | | | | |/ / _ \\\n"
                + "               | |_| | |_| |   <  __/\n"
                + "               |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(horzLine
                + "\n     Hello! I'm" + logo
                + "\n     What can I do for you?\n"
                + horzLine);
    }

    public static void exit() {
        exit = true;
        System.out.println(horzLine
                + "\n     Bye. Hope to see you again soon!\n"
                + horzLine);
    }

    public static void listTask() {
        if (list.size() == 0) {
            System.out.println(horzLine
                    + "\n     There is currently no task in your list.");
        } else {
            System.out.println(horzLine
                    + "\n     Here are the tasks in your list:");
        }

        for (int i = 0; i < list.size(); i++) {
            int number = 1 + i;
            System.out.println("     " + number + ". " + list.get(i));
        }
        System.out.println(horzLine);
    }

    public static void markAsDone(String userInput) throws InvalidTaskNumberException {
        StringTokenizer token = new StringTokenizer(userInput);
        int numWord = token.countTokens();
        boolean checker = false;

        if (numWord == 2) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (taskNumber >= 0 && taskNumber <= list.size() - 1) {
                list.get(taskNumber).markAsDone();
                overWriteFile(filePath, list);
                System.out.println(horzLine
                        + "\n     Nice! I've marked this task as done:\n"
                        + "        " + list.get(taskNumber) + "\n"
                        + horzLine);
                checker = true;
            }
        }

        if (!checker) {
            throw new InvalidTaskNumberException(horzLine
                    + "\n     ☹ OOPS!!! You have entered an invalid task number.\n"
                    + horzLine);
        }
    }

    public static void addNewTask(String userInput) throws EmptyDescriptionException, InvalidDateException  {
        Task newTask;
        StringTokenizer token = new StringTokenizer(userInput);
        int numWord = token.countTokens();
        String taskName;
        String taskDate;

        if (numWord == 1) {
            throw new EmptyDescriptionException(horzLine
                    + "\n     ☹ OOPS!!! The description of a "
                    + userInput.split(" ")[0]
                    + " cannot be empty.\n"
                    + horzLine);
        }

        if (userInput.startsWith("todo")) {
            taskName = userInput.substring(5);
            newTask = new ToDo(taskName);
        } else {
            int index = userInput.indexOf('/');

            if (index == -1) {
                throw new InvalidDateException(horzLine
                        + "\n     ☹ OOPS!!! You have entered an invalid date/time.\n"
                        + horzLine);
            } else {
                if (userInput.startsWith("deadline")) {
                    taskName = userInput.substring(9, index);
                    taskDate = userInput.substring(index + 4);

                    newTask = new Deadline(taskName, taskDate);
                } else {
                    taskName = userInput.substring(6, index);
                    taskDate =  userInput.substring(index + 4);

                    newTask = new Event(taskName, taskDate);
                }
            }
        }

        list.add(newTask);
        overWriteFile(filePath, list);
        System.out.println(horzLine
                + "\n      Got it. I've added this task:\n"
                + "            " + newTask + "\n"
                + "      Now you have " + list.size() + " tasks in the list.\n"
                + horzLine);
    }

    public static void deleteTask(String userInput) throws InvalidTaskNumberException {
        StringTokenizer token = new StringTokenizer(userInput);
        int numWord = token.countTokens();
        boolean checker = false;

        if (numWord == 2) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (taskNumber >= 0 && taskNumber <= list.size() - 1) {
                System.out.println(horzLine
                        + "\n     Noted. I've removed this task:\n"
                        + "        " + list.get(taskNumber)
                        + "\n     Now you have " + (list.size() - 1) + " tasks in the list.\n"
                        + horzLine);
                list.remove(taskNumber);
                overWriteFile(filePath, list);
                checker = true;
            }
        }

        if (!checker) {
            throw new InvalidTaskNumberException(horzLine
                    + "\n     ☹ OOPS!!! You have entered an invalid task number.\n"
                    + horzLine);
        }
    }

    //When Duke startup, read file content & input into a list.
    public static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        Task task;
        String taskDescription;
        String taskDate;

        while (s.hasNextLine()) {
            String taskString = s.nextLine();
            String taskDone = taskString.substring(4, 5);
            int indexOfDivider = taskString.indexOf('|', 8);

            if (taskString.startsWith("T")) {
                taskDescription = taskString.substring(8);
                task = new ToDo(taskDescription);

            } else {
                taskDescription = taskString.substring(8, indexOfDivider - 1);
                taskDate = taskString.substring(indexOfDivider + 2);

                if (taskString.startsWith("D")) {
                    task = new Deadline(taskDescription, taskDate);
                } else {
                    task = new Event(taskDescription, taskDate);
                }
            }

            if (taskDone.equals("1")) {
                task.markAsDone();
            }

            list.add(task);
        }
    }

    public static void overWriteFile(String filePath, List<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task : list) {
                boolean done = task.isDone;
                String doneString = "0";
                String taskDescription = task.description;
                String taskDate;

                if (done) {
                    doneString = "1";
                }

                if (task.taskType.equals("ToDo")) {
                    fw.write("T | " + doneString + " | " + taskDescription + "\n");
                } else {
                    taskDate = task.getTaskDate();

                    if (task.taskType.equals("Deadline")) {
                        fw.write("D | " + doneString + " | " + taskDescription + "| " + taskDate + "\n");
                    } else {
                        fw.write("E | " + doneString + " | " + taskDescription + "| " + taskDate + "\n");
                    }
                }
            }

            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File (filePath);
        welcome();

        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                readFileContents(filePath);
            }
        } catch (IOException ex) {
            System.out.println("     ☹ OOPS!!! Error creating the folder. " +
                    "Please create a data folder before trying again!");
        }

        while (!exit) {
            try {
                String userInput = sc.nextLine();

                if (userInput.equals("bye")) { //exit
                    exit();
                } else if (userInput.equals("list")) { //list task
                    listTask();
                } else if (userInput.startsWith("done")) { //mark as done
                    markAsDone(userInput);
                } else if (userInput.startsWith("todo")
                        || userInput.startsWith("deadline")
                        || userInput.startsWith("event")) { //add new task
                    addNewTask(userInput);
                } else if (userInput.startsWith("delete")) { //delete task
                    deleteTask(userInput);
                } else {
                    throw new InvalidInputException(horzLine
                            + "\n     ☹ OOPS!!! I'm sorry, but I don't know what that means :(\n"
                            + horzLine);
                }
            } catch (EmptyDescriptionException | InvalidInputException | InvalidTaskNumberException
                    | InvalidDateException ex) {
                System.out.println(ex.getMessage());
            }
        }

        sc.close();
    }
}
