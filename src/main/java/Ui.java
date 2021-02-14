package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.parser.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui allows interactions with the user and prompts user for command
 */
public class Ui {
    private final Scanner sc;
    private boolean exit;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.exit = false;
    }

    public String greet() {
        String logo = " ____         _        \n"
                    + "|  _ \\ _   _| |  _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello from\n" + logo;
        greeting += "_____________________________________\n";
        greeting += "Hello! I'm Duke\n";
        greeting += "What can I do for you?\n";
        greeting += "______________________________________\n";
        return greeting;
    }

    public TaskList process(TaskList tasks, Parser parser) {
        if (parser.getTaskType().equals("bye")) {
            this.exit = true;
            return tasks;
        } else if (parser.getTaskType().equals("list")) {
            return tasks;
        } else if (parser.getTaskType().equals("done")) {
            ArrayList<Task> taskList = tasks.getList();
            int taskDone = parser.getTaskIdx();
            for (int i = 0; i < taskList.size(); ++i) {
                if (i == taskDone - 1) {
                    taskList.set(i, taskList.get(i).markAsDone());
                }
            }
            return new TaskList(taskList);
        } else if (parser.getTaskType().equals("delete")) {
            int taskToDelete = parser.getTaskIdx();
            tasks.remove(taskToDelete - 1);
            return tasks;
        } else if (parser.getTaskType().equals("find")) {
            return tasks;
        } else if (parser.getTaskType().equals("todo")) {
            String taskDescription = parser.getDescription();
            Todo todo = new Todo(taskDescription);
            tasks = tasks.add(todo);
        } else if (parser.getTaskType().equals("deadline")) {
            LocalDate dueDate = parser.getDueDate();
            String taskDescription = parser.getDescription();
            Deadline deadline = new Deadline(taskDescription, dueDate);
            tasks = tasks.add(deadline);
            return tasks;
        } else if (parser.getTaskType().equals("event")) {
            LocalDate date = parser.getDueDate();
            String eventDescription = parser.getDescription();
            Event event = new Event(eventDescription, date);
            tasks = tasks.add(event);
            return tasks;
        }
        return tasks;
    }

    public String getOutput(TaskList tasks, Parser parser) {
        String msg = "";
        if (parser.getTaskType().equals("bye")) {
            msg ="Bye. Hope to see you again soon!";
        } else if (parser.getTaskType().equals("list")) {
            msg += "Here are the tasks in your list:\n";
            ArrayList<Task> taskList = tasks.getList();
            int count = 1;
            for (Task t : taskList) {
                if (count == taskList.size()) {
                    msg += "     " + count + "." + t.toString();
                } else {
                    msg += "     " + count + "." + t.toString() + "\n";
                }
                count++;
            }
        } else if (parser.getTaskType().equals("done")) {
            ArrayList<Task> taskList = tasks.getList();
            msg += "Nice! I've marked this task as done:\n";
            int taskDone = parser.getTaskIdx();
            for (int i = 0; i < taskList.size(); ++i) {
                if (i == taskDone - 1) {
                    taskList.set(i, taskList.get(i).markAsDone());
                    msg += taskList.get(i);
                }
            }
        } else if (parser.getTaskType().equals("delete")) {
            int taskToDelete = parser.getTaskIdx();
            msg += "Noted. I've removed this task: \n";
            msg += tasks.get(taskToDelete - 1) + "\n";
            tasks.remove(taskToDelete - 1);
            msg += " Now you have " + (tasks.size() - 1 )+ " tasks in the list.";
        } else if (parser.getTaskType().equals("find")) {
            String toFind = parser.getDescription();
            ArrayList<Task> taskList = tasks.getList();
            msg += "Here are the matching tasks in your list:\n";
            boolean first = true;
            for (int i = 0; i < taskList.size(); ++i) {
                if (taskList.get(i).getDescription().contains(toFind)) {
                    if (first) {
                        msg += (i + 1) + "." + taskList.get(i).toString();
                        first = false;
                    } else {
                        msg += "\n" + (i + 1) + "." + taskList.get(i).toString();
                    }
                }
            }
            if (first) {
                msg = "There are no matching tasks";
            }
        } else if (parser.getTaskType().equals("todo")) {
            msg += "Got it. I've added this task:\n";
            String taskDescription = parser.getDescription();
            Todo todo = new Todo(taskDescription);
            msg += todo + "\n";
            msg += "Now you have " + tasks.size() + " tasks in the list.";
        } else if (parser.getTaskType().equals("deadline")) {
            msg += "Got it. I've added this task:\n";
            LocalDate dueDate = parser.getDueDate();
            String taskDescription = parser.getDescription();
            Deadline deadline = new Deadline(taskDescription, dueDate);
            msg += deadline + "\n";
            msg += "Now you have " + tasks.size() + " tasks in the list.\n";
        } else if (parser.getTaskType().equals("event")) {
            msg += "Got it. I've added this task:\n";
            LocalDate date = parser.getDueDate();
            String eventDescription = parser.getDescription();
            Event event = new Event(eventDescription, date);
            msg += event + "\n";
            msg += "Now you have " + tasks.size() + " tasks in the list.";
        } else {
            msg += parser.getDescription();
        }
        return this.format(msg);
    }

    public String format(String response) {
        String res = "____________________________________________________________\n";
        res += response + "\n";
        res += "____________________________________________________________";
        return res;
    }
}
