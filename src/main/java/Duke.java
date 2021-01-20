import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("How can I help you sir/maam?");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 0;
        boolean exit = false;
        while (!exit) {
            try {
                String input = sc.nextLine();
                String[] cmd = input.split(" ");
                if (cmd[0].equals("bye")) {
                    System.out.println("Farewell sir/maam. Hope to see you again soon.");
                    exit = true;
                } else if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                    throw new DukeException("Please describe the nature of your task sir/maam.");
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks you requested to see sir/maam:");
                    for (int i = 0; i < index; i++) {
                        System.out.println(tasks[i].listTask());
                    }
                } else if (input.equals("done")) {
                    throw new DukeException("I apologise but I must ask you to specify the task you have conquered.");
                } else if (cmd[0].equals("done")) {
                    int taskNum = Integer.parseInt(cmd[1]) - 1;
                    if (taskNum >= index || taskNum < 0) {
                        throw new DukeException("I apologise but there is no such index in your list of tasks sir/maam.");
                    } else {
                        String str = tasks[taskNum].checkTask();
                        System.out.println("Well done. I have checked off this task. You are one step closer to victory after conquering:");
                        System.out.println(str);
                    }
                } else if (cmd[0].equals("todo") || cmd[0].equals("deadline") || cmd[0].equals("event")) {
                    if (cmd[0].equals("todo")) {
                        System.out.println("Understood. I have added this task to the list:");
                        Todo task = new Todo(index + 1, input);
                        tasks[index] = task;
                    } else if (cmd[0].equals("deadline")) {
                        Deadline task = new Deadline(index + 1, input);
                        if (task.isValidDeadline()) {
                            System.out.println("Understood. I have added this task to the list:");
                            tasks[index] = task;
                        } else {
                            throw new DukeException("Please specify the deadline for this task using /by.");
                        }
                    } else if (cmd[0].equals("event")) {
                        Event task = new Event(index + 1, input);
                        if (task.isValidDate()) {
                            System.out.println("Understood. I have added this task to the list:");
                            tasks[index] = task;
                        } else {
                            throw new DukeException("Please specify the date of this event using /at.");
                        }
                    }
                    System.out.println("  " + tasks[index]);
                    index++;
                    System.out.println(String.format("Now you have %d tasks in the list.", index));
                } else {
                    throw new DukeException("I am unable to comprehend what you have just said. I deeply regret my insufficiency.");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}

class Task {
    public int index;
    public String command;
    public boolean done;
    static String CHECKED = "[X]";
    static String UNCHECKED = "[ ]";

    public Task(int index, String command) {
        this.index = index;
        this.command = command;
        this.done = false;
    }

    public String checkTask() {
        this.done = true;
        return "  " + this.toString();
    }

    public String toString() {
        String str = String.valueOf(index) + ".";
        if (done) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += " " + command;
        return str;
    }

    public String listTask() {
        String str = String.valueOf(index) + ".";
        str += this.toString();
        return str;
    }
}

class Todo extends Task {
    public Todo(int index, String command) {
        super(index, command);
    }

    public String toString() {
        String[] words = command.split(" ");
        String str = "[T]";
        if (done) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        for (int i = 1; i < words.length; i++) {
            str += " " + words[i];
        }
        return str;
    }
}

class Deadline extends Task {
    public Deadline(int index, String command) {
        super(index, command);
    }

    public int findDeadline() {
        String[] str = command.split(" ");
        boolean found = false;
        int index = 0;
        while (!found && index < str.length) {
            if (str[index].equals("/by")) {
                found = true;
            } else {
                index++;
            }
        }
        return index;
    }

    public boolean isValidDeadline() {
        String[] str = command.split(" ");
        if (this.findDeadline() == str.length) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {
        String[] words = command.split(" ");
        String str = "[D]";
        if (done) {
            str += CHECKED + " ";
        } else {
            str += UNCHECKED + " ";
        }
        int num = findDeadline();
        for (int i = 1; i < num; i++) {
            str += words[i] + " ";
        }
        str += "(by:";
        for (int i = num + 1; i < words.length; i++) {
            str += " " + words[i];
        }
        str += ")";
        return str;
    }
}

class Event extends Task {
    public Event(int index, String command) {
        super(index, command);
    }

    public int findDate() {
        String[] str = command.split(" ");
        boolean found = false;
        int index = 0;
        while (!found && index < str.length) {
            if (str[index].equals("/at")) {
                found = true;
            } else {
                index++;
            }
        }
        return index;
    }

    public boolean isValidDate() {
        String[] str = command.split(" ");
        if (this.findDate() == str.length) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {
        String[] words = command.split(" ");
        String str = "[E]";
        if (done) {
            str += CHECKED + " ";
        } else {
            str += UNCHECKED + " ";
        }
        int num = findDate();
        for (int i = 1; i < num; i++) {
            str += words[i] + " ";
        }
        str += "(at:";
        for (int i = num + 1; i < words.length; i++) {
            str += " " + words[i];
        }
        str += ")";
        return str;
    }
}

class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }
}