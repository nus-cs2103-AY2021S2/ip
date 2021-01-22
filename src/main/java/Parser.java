import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.time.LocalDate;

public class Parser {
    Ui ui;

    Parser(Ui ui) {
        this.ui = ui;
    }

    public void handleList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:\n" + taskList.toString());
    }

    public void handleDone(String input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            taskList.markAsDone(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.getTaskAtIndex(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "You have " + taskList.getNumberOfTasks()
                            + " tasks in your list. Please check your input.");
        } catch (NumberFormatException e) {
            System.out.println("The input must be a positive integer!");
        }
    }

    public void handleTodo(String input, TaskList taskList) {
        if (!input.contains(" ")) {
            System.out.println("The description of a todo cannot be empty.");
            return;
        }
        String task = input.substring(input.indexOf(" ") + 1);
        Task temp = new ToDo(task);
        taskList.addTask(temp);
        this.ui.printOnListChange(
                "Got it. I have added the following task:",
                temp,
                taskList.getNumberOfTasks());
    }

    public void handleTasksWithTime(String command, String input, TaskList taskList) {
        try {
            String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
            Task temp;
            String timing = input.substring(input.indexOf("/") + 4);
            LocalDate date = LocalDate.parse(timing);
            if (command.startsWith("deadline")) {
                temp = new Deadline(taskName, date);
            } else {
                temp = new Event(taskName, date);
            }
            taskList.addTask(temp);
            this.ui.printOnListChange(
                    "Got it. I have added the following task:",
                    temp,
                    taskList.getNumberOfTasks());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("The timing of the task is not included. Please check your input.");
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Please input a date with correct format (yyyy-mm-dd)");
        }
    }

    public void handleDelete(String input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            this.ui.printOnListChange(
                    "Noted. I have removed the following task:",
                    taskList.deleteTask(index),
                    taskList.getNumberOfTasks()
            );
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "You have " + taskList.getNumberOfTasks() +
                            " tasks in your list. Please check your input.");
        } catch (NumberFormatException e) {
            System.out.println("The input must be a positive integer!");
        }
    }
}
