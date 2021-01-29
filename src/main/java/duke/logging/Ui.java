package duke.logging;

import duke.exception.InvalidInputException;
import duke.model.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    public void showWelcome(TaskList tasks) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        tasks.list();
        System.out.println("\n     What can I do for you?");
        System.out.println("     _______________________________________\n");
    }

    public void printLine() {
        System.out.println("     _______________________________________");
    }

    public String[] readCommand(Scanner sc) {
        String[] userInput = new String[2];
        userInput[0] = sc.next();
        userInput[1] = sc.nextLine().strip();
        return userInput;
    }

    public void addCommandInteraction(Task task, ArrayList<Task> tasks) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task);
        System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
    }

    public void deleteCommandInteraction(Task task, ArrayList<Task> tasks) {
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("     " + task);
        System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
    }

    public void doneCommandInteraction(Task task) {
        System.out.println("     Nice! I've marked this task as done:\n     " + task);
    }

    public void listCommandInteraction(TaskList task) {
        task.list();
    }

    public void byeCommandInteraction() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void unknownCommandInteraction() throws InvalidInputException {
        throw new InvalidInputException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void findCommandInteraction(ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() == 0) {
            System.out.println("     ☹ No match found ☹");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
        }
    }
}
