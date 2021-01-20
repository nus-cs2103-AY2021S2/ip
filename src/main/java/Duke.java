package main.java;

import main.java.Task;

import java.util.*;
import java.util.Scanner;

public class Duke {

    private static List<Task> tasks = new ArrayList<Task>();

    public static void ollySpeak(String message) {
        System.out.println("Olly: " + message);
    }

    private static void inputHandler(String input) {
        if (input.equals("bye")) {
            ollySpeak("Goodbye for now, we will meet again.");
            System.exit(0);
        }

        if (input.equals("list")) {
            printTasks();
        } else if (input.startsWith("todo")) {
            String[] command = input.split("todo ");
            Todo todo = new Todo(command[1]);
            addTask(todo);
        } else if (input.startsWith("deadline")) {
            String[] command = input.split("deadline ");
            String deadlineArg = command[1];
            String[] byArgs = deadlineArg.split(" /by ");
            Deadline deadline = new Deadline(byArgs[0], byArgs[1]);
            addTask(deadline);
        } else if (input.startsWith("event")) {
            String[] command = input.split("event ");
            String eventArg = command[1];
            String[] atArgs = eventArg.split(" /at ");
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
        } else {
            Task newTask = new Task(input);
            addTask(newTask);
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
        ollySpeak("Here you go! Your list of items:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ollySpeak("Hey! Welcome to the chatbot. What can I do for you today?");

        while (sc.hasNext()) {
            String input = sc.nextLine();
            inputHandler(input);
        }
    }
}
