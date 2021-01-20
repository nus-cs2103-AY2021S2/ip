package main.java;

import main.java.Task;

import java.util.*;
import java.util.Scanner;

public class Duke {

    public static void ollySpeak(String message) {
        System.out.println("Olly: " + message);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ollySpeak("Hey! Welcome to the chatbot. What can I do for you today?");
        List<Task> tasks = new ArrayList<Task>();


        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                ollySpeak("Goodbye for now, we will meet again.");
                break;
            }

            if (input.equals("list")) {
                ollySpeak("Here you go! Your list of items:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
            } else if (input.startsWith("todo")) {
                String[] command = input.split("todo ");
                Todo todo = new Todo(command[1]);
                tasks.add(todo);

                ollySpeak("Make sure you do this task! I've added:");
                System.out.println(todo);
                ollySpeak("You now have " + tasks.size() + " tasks at hand.");
            } else if (input.startsWith("deadline")) {
                String[] command = input.split("deadline ");
                String deadlineArg = command[1];
                String[] byArgs = deadlineArg.split(" /by ");

                Deadline deadline = new Deadline(byArgs[0], byArgs[1]);
                tasks.add(deadline);

                ollySpeak("Make sure you meet this deadline! I've added:");
                System.out.println(deadline);
                ollySpeak("You now have " + tasks.size() + " tasks at hand.");
            } else if (input.startsWith("event")) {
                String[] command = input.split("event ");
                String eventArg = command[1];
                String[] atArgs = eventArg.split(" /at ");

                Event event = new Event(atArgs[0], atArgs[1]);
                tasks.add(event);

                ollySpeak("Event coming right up! I've added:");
                System.out.println(event);
                ollySpeak("You now have " + tasks.size() + " tasks at hand.");
            } else if (input.startsWith("done")) {
                String[] command = input.split(" ");
                Integer index = Integer.parseInt(command[1]);
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
                tasks.add(newTask);
                ollySpeak("Task have been added:");
                System.out.println(newTask);
                ollySpeak("You now have " + tasks.size() + " tasks at hand.");
            }
        }
    }
}
