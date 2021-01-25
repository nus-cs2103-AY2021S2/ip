package handlers;

import enums.Commands;

import exceptions.InvalidOptionException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import utils.Formatter;

import java.util.ArrayList;

public class TaskHandler {

    public static void addTask(Commands command, String input, ArrayList<Task> taskList) throws InvalidOptionException {
        int numberOfTasks = taskList.size();

        switch (command) {
        case TODO:
            Task todo = new Todo(input);
            taskList.add(todo);
            numberOfTasks += 1;
            Formatter.printBetweenLines("Got it. I've added this task:",
                    Formatter.INDENTATION + todo.toString(),
                    "Now you have " + numberOfTasks + " tasks in the list."
            );
            break;
        case DEADLINE:
            int indexOfBy = input.trim().indexOf("/by");

            if (indexOfBy == 0) {
                throw new InvalidOptionException("DEADLINE");
            }

            String deadlineMessage = input.substring(0, indexOfBy);
            String by = input.substring(indexOfBy + 4);
            Task deadline = new Deadline(deadlineMessage, by);
            taskList.add(deadline);
            numberOfTasks += 1;
            Formatter.printBetweenLines("Got it. I've added this task:",
                    Formatter.INDENTATION + deadline.toString(),
                    "Now you have " + numberOfTasks + " tasks in the list."
            );
            break;
        case EVENT:
            int indexOfAt = input.trim().indexOf("/at");

            if (indexOfAt == 0) {
                throw new InvalidOptionException("EVENT");
            }

            String eventMessage = input.substring(0, indexOfAt);
            String at = input.substring(indexOfAt + 4);
            Task event = new Event(eventMessage, at);
            taskList.add(event);
            numberOfTasks += 1;
            Formatter.printBetweenLines("Got it. I've added this task:",
                    Formatter.INDENTATION + event.toString(),
                    "Now you have " + numberOfTasks + " tasks in the list."
            );
            break;
        }
    }

    public static void listTasks(ArrayList<Task> taskList) {
        Formatter.printHorizontalLine();

        if (taskList.isEmpty()) {
            Formatter.printlnWithIndentation("You have not added any tasks yet.");
        }

        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            Formatter.printlnWithIndentation(index + ". " + taskList.get(i).toString());
        }

        Formatter.printHorizontalLine();
    }

    public static void doneTask(String input, ArrayList<Task> taskList) {
        int index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        Formatter.printBetweenLines("Nice! I've marked this task as done:", task.toString());
    }

    public static void deleteTask(String input, ArrayList<Task> taskList) {
        int index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        taskList.remove(index);
        Integer numberOfTasks = taskList.size();

        Formatter.printBetweenLines("Noted. I've removed this task:",
                Formatter.INDENTATION + task.toString(),
                "Now you have " + numberOfTasks.toString() + " tasks in the list.");
    }
}
