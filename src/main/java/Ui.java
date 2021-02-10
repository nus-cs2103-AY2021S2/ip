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
        String logo =  " ____         _        \n"
                    + "|  _ \\ _   _| |  _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello from\n" + logo;
        greeting += "    _____________________________________\n";
        greeting += "     Hello! I'm Duke\n";
        greeting += "     What can I do for you?\n";
        greeting += "    ______________________________________\n";
        return greeting;
    }

    public TaskList process(TaskList tasks, Parser parser) {
        /*String input = this.next();
        Parser parser = new Parser();
        parser = parser.parse(input);*/
        if (parser.getTaskType().equals("bye")) {
            String msg = this.format("     Bye. Hope to see you again soon!");
            System.out.println(msg);
            this.exit = true;
            return tasks;
        } else if (parser.getTaskType().equals("list")) {
            String msg = "     Here are the tasks in your list:\n";
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
            System.out.println(this.format(msg));
            return tasks;
        } else if (parser.getTaskType().equals("done")) {
            int count = 1;
            ArrayList<Task> taskList = tasks.getList();
            String msg = "     Nice! I've marked this task as done:\n";
            int taskDone = parser.getTaskIdx();
            for (int i = 0; i < taskList.size(); ++i) {
                if (i == taskDone - 1) {
                    taskList.set(i, taskList.get(i).markAsDone());
                    msg += "     " + taskList.get(i);
                }
            }
            System.out.println(this.format(msg));
            return new TaskList(taskList);
        } else if (parser.getTaskType().equals("delete")) {
            int taskToDelete = parser.getTaskIdx();
            String msg = "     Noted. I've removed this task: \n";
            msg += "     " + tasks.get(taskToDelete - 1) + "\n";
            tasks.remove(taskToDelete - 1);
            msg += "     Now you have " + tasks.size() + " tasks in the list.";
            System.out.println(this.format(msg));
            return tasks;
        } else if (parser.getTaskType().equals("find")) {
            String toFind = parser.getDescription();
            ArrayList<Task> taskList = tasks.getList();
            String msg = "     Here are the matching tasks in your list:\n";
            boolean first = true;
            for (int i = 0; i < taskList.size(); ++i) {
                if (taskList.get(i).getDescription().contains(toFind)) {
                    if (first) {
                        msg += "     " + (i + 1) + "." + taskList.get(i).toString();
                        first = false;
                    } else {
                        msg += "\n     " + (i + 1) + "." + taskList.get(i).toString();
                    }
                }
            }
            if (first) {
                msg = "     There are no matching tasks";
            }
            System.out.println(this.format(msg));
            return tasks;
        } else if (parser.getTaskType().equals("todo")) {
            String msg = "     Got it. I've added this task:\n";
            String taskDescription = parser.getDescription();
            Todo todo = new Todo(taskDescription);
            tasks = tasks.add(todo);
            msg += "     " + todo + "\n";
            msg += "     Now you have " + tasks.size() + " tasks in the list.";
            System.out.println(this.format(msg));
        } else if (parser.getTaskType().equals("deadline")) {
            String msg = "     Got it. I've added this task:\n";
            LocalDate dueDate = parser.getDueDate();
            String taskDescription = parser.getDescription();
            Deadline deadline = new Deadline(taskDescription, dueDate);
            tasks = tasks.add(deadline);
            msg += "     " + deadline + "\n";
            msg += "     Now you have " + tasks.size() + " tasks in the list.\n";
            System.out.println(this.format(msg));
            return tasks;
        } else if (parser.getTaskType().equals("event")) {
            String msg = "     Got it. I've added this task:\n";
            LocalDate date = parser.getDueDate();
            String eventDescription = parser.getDescription();
            Event event = new Event(eventDescription, date);
            tasks = tasks.add(event);
            msg += "     " + event + "\n";
            msg += "     Now you have " + tasks.size() + " tasks in the list.";
            System.out.println(this.format(msg));
            return tasks;
        } else {
            System.out.println(this.format(parser.getDescription()));
        }
        return tasks;
    }

    public String getOutput(TaskList tasks, Parser parser) {
        String msg = "";
        if (parser.getTaskType().equals("bye")) {
            msg += this.format("Bye. Hope to see you again soon!");
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
            msg += this.format(parser.getDescription());
        }
        return msg;
    }

    public boolean hasNext() {
        return this.sc.hasNext();
    }

    private String next() {
        return this.sc.nextLine();
    }

    public boolean shouldExit() {
        return this.exit;
    }

    public String format(String response) {
        String res = "    ____________________________________________________________\n";
        res += response + "\n";
        res += "    ____________________________________________________________\n";
        return res;
    }
}
