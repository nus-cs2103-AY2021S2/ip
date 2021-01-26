import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class Duke {
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    String logo;
    TaskList list;

    public Duke() {
        this.logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        this.list = new TaskList();

    }

    public Duke(TaskList list) {
        this.logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        this.list = list;
    }

    public void greet() {
        pw.printf("Hello from%n%s%n", logo);
        pw.println("Hello! I'm Duke");
        pw.println("What can I do for you?");
        pw.flush();
    }

    public void addTask(String message, String type, String date) {
        Task task = null;
        switch (type) {
        case "todo":
            task = new Todo(message);
            break;
        case "deadline":
            task = new Deadline(message, date);
            break;
        case "event":
            task = new Event(message, date);
            break;
        }

        list.addItem(task);
        pw.println("Got it. I've added this task:");
        pw.printf(" %s%n", task);
        pw.printf("Now you have %d tasks in the list.%n", list.lst.size());
        pw.flush();
    }

    public void removeTask(String id) {
        int n = Integer.parseInt(id) - 1;
        Task task = list.lst.get(n);
        list.removeItem(n);
        pw.println("Noted. I've removed this task:");
        pw.printf(" %s%n", task);
        pw.printf("Now you have %d tasks in the list.%n", list.lst.size());
        pw.flush();
    }

    public void markAsDone(String id) {
        int n = Integer.parseInt(id) - 1;
        list.doneTask(n);
        pw.println("Nice! I've marked this task as done:");
        pw.printf(" %s%n", list.lst.get(n));
        pw.flush();

    }

    public void exit() {
        pw.println("Bye. Hope to see you again soon!");
        pw.flush();
    }

    public void showTasks() {
        list.printList();
    }

    public String getTasks() {
        return list.getTasksAsString();
    }

    public List<Task> getList() { return list.getLst();}

}
