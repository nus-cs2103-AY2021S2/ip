import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> storedTasks;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Duke iceBear = new Duke();
        while (true) {
            String nextCommand = scan.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                iceBear.performCommand(nextCommand);
            }
        }
    }

    public Duke() {
        this.storedTasks = new ArrayList<>();
        System.out.println("Hello! I'm Icebear");
        System.out.println("What can I do for you?");
    }

    public void performCommand(String command) {
        String commandWord = command.split(" ")[0];
        try {
            if (commandWord.equals("list")) {
                this.listTask();
            } else if (commandWord.equals("done")) {
                this.doneTask(command.substring(4));
            } else if (commandWord.equals("todo")) {
                this.addToDo(command.substring(4));
            } else if (commandWord.equals("event")) {
                this.addEvent(command.substring(5));
            } else if (commandWord.equals("deadline")) {
                this.addDeadline(command.substring(8));
            } else if (commandWord.equals("delete")) {
                this.delete(command.substring(6));
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException dukeError) {
            System.out.println(dukeError);
        }
    }

    public void addToDo(String taskName) throws DukeException {
        if (taskName.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo newTask = new ToDo((taskName.substring(1)));
        this.storedTasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
    }

    public void addEvent(String taskName) throws DukeException {
        if (taskName.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        Event newTask = new Event((taskName.substring(1)));
        this.storedTasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
    }

    public void addDeadline(String taskName) throws DukeException{
        if (taskName.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Deadline newTask = new Deadline((taskName.substring(1)));
        this.storedTasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storedTasks.size(); i++) {
            Task currTask = storedTasks.get(i);
            System.out.println(i + 1 + "." + currTask);
        }
    }

    public void doneTask(String index) throws DukeException {
        if (index.length() == 0) {
            throw new DukeException("☹ OOPS!!! The index of the task is missing.");
        }
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(index.substring(1));
        } catch (NumberFormatException error) {
            throw new DukeException("Index of task must be an integer");
        }
        if (this.storedTasks.size() >= doneIndex) {
            Task currTask = this.storedTasks.get(doneIndex - 1);
            currTask.completeTask();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + currTask);
        } else {
            System.out.println("Task not found");
        }
    }

    public void delete(String index) throws DukeException {
        if (index.length() == 0) {
            throw new DukeException("☹ OOPS!!! The index to delete the task is missing.");
        }
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(index.substring(1));
        } catch (NumberFormatException error) {
            throw new DukeException("Index to delete must be an integer");
        }
        if (this.storedTasks.size() >= deleteIndex) {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + this.storedTasks.remove(deleteIndex - 1));
            System.out.println(this);
        } else {
            System.out.println("Task not found");
        }
    }

    @Override
    public String toString() {
        if (this.storedTasks.size() == 1) {
            return "Now you have " + this.storedTasks.size() + " task in the list";
        } else {
            return "Now you have " + this.storedTasks.size() + " tasks in the list";
        }
    }
}
