import java.util.*;
/*
Code was refractored after week 2
Credit of light reuse: James Lee
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println(Duke.line + "\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + Duke.line);
        Duke duke = new Duke();
        duke.start();
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public List<Task> list = new ArrayList<>();
    private boolean isOn = false;
    public static String line = "____________________________________________________________";

    public Duke() {
        this.isOn = true;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String scannedLine = sc.nextLine();
        while (isOn) {
            try {
                this.handleCommand(scannedLine);
            } catch (Exception e) {
                System.out.format("%s\n☹ %s\n%s", Duke.line, e.getMessage(), Duke.line);
            } finally {
                scannedLine = sc.nextLine();
            }
        }
    }

    private void handleCommand(String currLine) throws Exception {
        // basic commands
        currLine = currLine.toLowerCase();
        String[] parsedLine = currLine.split(" ");
        if (currLine.startsWith("list")) {
            print(this.listTasks());
        } else if (currLine.startsWith("bye")) {
            bye();
        } else if (currLine.startsWith("delete")) {
            this.delete(Integer.parseInt(parsedLine[1]));
        } else if (currLine.startsWith("done")) {
            this.doTask(Integer.parseInt(parsedLine[1]));
        } else if (currLine.startsWith("todo")) {
            addTask(new Todo(currLine));
        } else if (currLine.startsWith("deadline")) {
            addTask(new Deadline(currLine));
        } else if (currLine.startsWith("event")) {
            addTask(new Event(currLine));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void addTask(Task t) {
        list.add(t);
        String output = line + "\n" + " Got it. I've added this task: \n"
                + t.printNew() + "\n Now you have " + list.size()
                + " tasks in the list" + "\n" + line;
        print(output);
    }

    private void doTask(int taskNum) {
        Task curr = list.get(taskNum - 1);
        curr.isDone = true;
        System.out.format(Duke.line + "\n Nice! I've marked this task as done: " +
                "\n [%s] [%s] %s" +
                "\n" + Duke.line, curr.type(), curr.status(), curr.toString());
    }

    private void delete(int num) throws ArrayIndexOutOfBoundsException {
        Task curr = list.get(num - 1);
        list.remove(num - 1);
        String deleted = "[" + curr.type() + "]" + "[" + curr.status() + "] " + curr.toString();
        System.out.format("%s\nNoted. I've removed this task: \n %s\nNow you have %d tasks in the list\n%s",
                line, deleted, list.size(), line);
    }

    // I'll need to change this later
    public String listTasks() throws ArrayIndexOutOfBoundsException {
        String output = "";
        output += line +"\n";
        int i = 1;
        for (Task s : this.list) {
            output += String.format("%d.[%s][%s] %s \n", i, s.type(), s.status(), s.toString());
            i++;
        }
        output += line;
        return output;
    }

    private void bye() {
        isOn = false;
        print(line + "\n" + " Bye. Hope to see you again soon!" + "\n" + line);
    }
}
