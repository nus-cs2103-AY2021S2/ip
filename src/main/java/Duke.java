import java.util.*;

public class Duke {
    static String CHECKED = "[X] ";
    static String UNCHECKED = "[ ] ";
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 0;
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            String[] cmd = input.split(" ");
            if (cmd[0].equals("bye")) {
                exit = true;
            } else if (cmd[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println(tasks[i].listTask());
                }
            } else if (cmd[0].equals("done")) {
                int taskNum = Integer.parseInt(cmd[1]) - 1;
                String str = tasks[taskNum].checkTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(str);
            } else {
                System.out.println("Got it. I've added this task:");
                if (cmd[0].equals("todo")) {
                    Todo task = new Todo(index + 1, input);
                    tasks[index] = task;
                } else if (cmd[0].equals("deadline")) {
                    Deadline task = new Deadline(index + 1, input);
                    tasks[index] = task;
                } else if (cmd[0].equals("event")) {
                    Event task = new Event(index + 1, input);
                    tasks[index] = task;
                }
                System.out.println("  " + tasks[index]);
                index++;
                System.out.println(String.format("Now you have %d tasks in the list.", index));
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
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
        while (!found) {
            if (str[index].equals("/by")) {
                found = true;
            } else {
                index++;
            }
        }
        return index;
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
        while (!found) {
            if (str[index].equals("/at")) {
                found = true;
            } else {
                index++;
            }
        }
        return index;
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