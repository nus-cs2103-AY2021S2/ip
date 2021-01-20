package main.java;

import main.java.Task;

import java.util.*;
import java.util.Scanner;

public class Duke {

    private static List<Task> tasks = new ArrayList<Task>();

    public static void ollySpeak(String message) {
        System.out.println("Olly: " + message);
    }

    private static void inputHandler(String input) throws DukeException {
        if (input.equals("bye")) {
            ollySpeak("Goodbye for now, we will meet again.");
            System.exit(0);
        }

        if (input.equals("list")) {
            printTasks();
        } else if (input.startsWith("todo")) {
            String[] command = input.split("todo ");
            if (command.length == 1) throw new DukeException("The description of a todo cannot be empty.");
            Todo todo = new Todo(command[1]);
            addTask(todo);
        } else if (input.startsWith("deadline")) {
            String[] command = input.split("deadline ");
            if (command.length == 1) throw new DukeException("The description of a deadline cannot be empty.");
            String deadlineArg = command[1];

            String[] byArgs = deadlineArg.split(" /by ");
            if (byArgs.length < 2) throw new DukeException("There must be a date for deadline.");

            Deadline deadline = new Deadline(byArgs[0], byArgs[1]);
            addTask(deadline);
        } else if (input.startsWith("event")) {
            String[] command = input.split("event ");
            if (command.length == 1) throw new DukeException("The description of a event cannot be empty.");
            String eventArg = command[1];

            String[] atArgs = eventArg.split(" /at ");
            if (atArgs.length < 2) throw new DukeException("There must be a date for event.");
            Event event = new Event(atArgs[0], atArgs[1]);
            addTask(event);
        } else if (input.startsWith("done")) {
            String[] command = input.split(" ");
            int index = Integer.parseInt(command[1]);
            if (index > 0 && index <= tasks.size()) {
                Task doneTask = tasks.get(index - 1);
                doneTask.setDone();
                ollySpeak("Swee! This task is done:");
                System.out.println(doneTask);
            } else {
                ollySpeak("The task number does not work, try again?");
            }
        } else if (input.startsWith("delete")) {
            String[] command = input.split(" ");
            int index = Integer.parseInt(command[1]);
            if (index > 0 && index <= tasks.size()) {
                Task doneTask = tasks.get(index - 1);
                tasks.remove(doneTask);
                ollySpeak("Aww man.. I've removed this task:");
                System.out.println(doneTask);
                ollySpeak("Now you have " + getTaskCount() + " tasks left.");
            } else {
                ollySpeak("The task number does not work, try again?");
            }
        } else {
            throw new DukeException("I don't understand your language leh. Speak singlish?");
        }
    }

    private static int getTaskCount() {
        return tasks.size();
    }

    private static void addTask(Task task) {
        tasks.add(task);
        ollySpeak(task.addMessage + (task.addMessage == null ? "" : " ") + "I've added:");
        System.out.println(task);
        ollySpeak("You now have " + getTaskCount() + " tasks at hand.");
    }

    private static void printTasks() {
        if (getTaskCount() == 0) {
            ollySpeak("You currently have no tasks! Use todo, deadline or event.");
        } else {
            ollySpeak("Here you go! Your list of items:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i+1 + ". " + tasks.get(i));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ollySpeak("Hey! Welcome to the chatbot. What can I do for you today?");

        while (sc.hasNext()) {
            String input = sc.nextLine();
            try {
                inputHandler(input);
            } catch (DukeException dukeEx) {
                System.out.println(dukeEx);
            }
        }
    }
}
