import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private String command;
    private TaskList taskList;
    private Ui ui;
    private boolean isEnd;

    public Parser(TaskList tasklist, Ui ui) {
        this.command = "";
        this.taskList = tasklist;
        this.ui = ui;
        this.isEnd = false;
    }

    public void insertCommand(String command) {
        this.command = command;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public void process() {
        try {
            if (command.equals("list")) {
                int size = taskList.numOfTasks();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= size; i++) {
                    System.out.println(i + ". " + taskList.getTask(i - 1).toString());
                }
            } else if (command.contains("todo")) {
                String save = command.replaceAll("todo","");
                ToDo t = new ToDo(save);
                if (save.equals("")) {
                    Thread.setDefaultUncaughtExceptionHandler((u, e) -> System.err.println(e.getMessage()));
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                taskList.addTask(t);
                ui.print("Got it. I've added this task: ");
                ui.print(t.toString());
                Integer numOfTask = taskList.numOfTasks();
                ui.print("Now you have " + numOfTask.toString() + " tasks in the list.");
            } else if (command.contains("deadline")) {
                String[] input = command.split("/by ");
                input[0] = input[0].replaceAll("deadline", "");
                LocalDate date =LocalDate.parse(input[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Deadline d = new Deadline(input[0], date);
                taskList.addTask(d);
                ui.print("Got it. I've added this task: ");
                ui.print(d.toString());
                Integer numOfTask = taskList.numOfTasks();
                ui.print("Now you have " + numOfTask.toString() + " tasks in the list.");
            } else if (command.contains("event")) {
                String[] info = command.split("/at");
                info[0] = info[0].replaceAll("event","");
                Event e = new Event(info[0],info[1]);
                taskList.addTask(e);
                ui.print("Got it. I've added this task: ");
                ui.print(e.toString());
                Integer numOfTask = taskList.numOfTasks();
                ui.print("Now you have " + numOfTask.toString() + " tasks in the list.");
            } else if (command.contains("done")) {
                String[] strArray = command.split(" ");
                int value = Integer.parseInt(strArray[1]);
                Task complete = taskList.getTask(value - 1);
                complete.markAsDone();
                ui.print(" Nice! I've marked this task as done: ");
                ui.print(complete.toString());
            } else if (command.contains("delete")) {
                String[] arr = command.split(" ");
                int value = Integer.parseInt(arr[1]);
                Task remove = taskList.getTask(value - 1);
                taskList.deleteTask(value - 1);
                ui.print("Noted. I've removed this task: ");
                ui.print(remove.toString());
                Integer numOfTask = taskList.numOfTasks();
                ui.print("Now you have " + numOfTask.toString() + " tasks in the list.");
            } else if (command.equals("bye")) {
                ui.print("Bye! Hope to see you again soon!");
                this.isEnd = true;
            } else {
                throw new DukeException("Oops! I'm sorry, but I don't know what that means!");
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
