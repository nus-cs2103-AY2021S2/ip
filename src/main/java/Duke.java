import Exceptions.EmptyDescriptionException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidInputException;
import Exceptions.InvalidTaskNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    final static String horzLine = "    _________________________________________________";
    static List<Task> list = new ArrayList<>();
    static boolean exit = false;

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
        int taskNumber = Integer.parseInt(userInput.substring(5, 6)) - 1;

        if (taskNumber >= 0 && taskNumber <= list.size() - 1) {
            list.get(taskNumber).markAsDone();
        } else {
            throw new InvalidTaskNumberException(horzLine
                    + "\n     ☹ OOPS!!! You have entered an invalid task number.\n"
                    + horzLine);
        }

        System.out.println(horzLine
                + "\n     Nice! I've marked this task as done:\n"
                + "        " + list.get(taskNumber) + "\n"
                + horzLine);
    }

    public static void addNewTask(String userInput) throws EmptyDescriptionException, InvalidDateException  {
        Task newTask;
        StringTokenizer token = new StringTokenizer(userInput);
        int numWord = token.countTokens();

        if (numWord == 1) {
            throw new EmptyDescriptionException(horzLine
                    + "\n     ☹ OOPS!!! The description of a "
                    + userInput.split(" ")[0]
                    + " cannot be empty.\n"
                    + horzLine);
        }

        if (userInput.startsWith("todo")) {
            newTask = new ToDo(userInput.substring(5));
        } else {
            int index = userInput.indexOf('/');

            if (index == -1) {
                throw new InvalidDateException(horzLine
                        + "\n     ☹ OOPS!!! You have entered an invalid date/time.\n"
                        + horzLine);
            } else {
                if (userInput.startsWith("deadline")) {
                    newTask = new Deadline(userInput.substring(9, index),
                            userInput.substring(index + 4));
                } else {
                    newTask = new Event(userInput.substring(6, index),
                            userInput.substring(index + 4));
                }
            }
        }

        list.add(newTask);
        System.out.println(horzLine
                + "\n      Got it. I've added this task:\n"
                + "            " + newTask + "\n"
                + "      Now you have " + list.size() + " tasks in the list.\n"
                + horzLine);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcome();

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
