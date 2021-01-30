import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;



public class Duke {
    protected static final ArrayList<Task> taskList = new ArrayList<>();
    protected static boolean canExit = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    //user commands
    private final static String listCommand = "list";
    private final static String doneCommand = "done";
    private final static String deleteCommand = "delete";
    private final static String exitCommand = "bye"; //only command that will end chat
    private final static String ToDos = "todo"; //tasks without any date/time attached to it
    private final static String Deadlines = "deadline"; //tasks that need to be done before a specific date/time
    private final static String Events = "event"; //tasks that start at a specific time and ends at a specific time

    public void run() {

    }

    public static void getTasks() {
        int i = 1;

        if (taskList.size() == 0) {
            System.out.println("____________________________________________________________\n"
                    + "You have no task for now, yay!\n"
                    + "____________________________________________________________");
        } else {

            System.out.println("____________________________________________________________\n"
                    + (numberOfTask() == 1 ? "Here is the task in your list: \n" : "Here are the tasks in your list: \n"));

            for (Task t : taskList) {
                System.out.println(i + ". "
                        + t.toString());
                i++;
            }

            System.out.println("____________________________________________________________\n");
        }
    }

    public static Integer numberOfTask() {
        return taskList.size();
    }

    public static void Greet() {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke, 恭喜发财 \u263a.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

    }

    public static void storeTask() throws InvalidCommandException {
        Scanner sc = new Scanner(System.in);
        String description = sc.nextLine();

        if (description.equalsIgnoreCase(listCommand)) {
            getTasks();

        } else if (description.equalsIgnoreCase(exitCommand)) {
            Exit();

        } else if (description.toLowerCase().contains(doneCommand)) {
            try{
                int taskIndex = Integer.parseInt(description.replaceAll("[^0-9]", ""));
                updateTaskStatus(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(
                        "\n___________________________________________________________________\n"
                                + "\u2639 OOPS!!! the task you are referring to does not seem to exist :-( \n"
                                + "___________________________________________________________________\n");
            } catch (NumberFormatException e) {
                System.out.println(
                        "\n___________________________________________________________________\n"
                        + "\u2639 OOPS!!! you need to enter the index of the task :-( \n"
                        + "___________________________________________________________________\n");
            }

        } else if (description.toLowerCase().contains(deleteCommand)) {
            try {
                int taskIndex = Integer.parseInt(description.replaceAll("[^0-9]", ""));
                Delete(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(
                        "\n___________________________________________________________________\n"
                                + "\u2639 OOPS!!! the task you are referring to does not seem to exist :-( \n"
                                + "___________________________________________________________________\n");
            } catch(NumberFormatException e) {
                System.out.println(
                        "\n___________________________________________________________________\n"
                                + "\u2639 OOPS!!! you need to enter the index of the task :-( \n"
                                + "___________________________________________________________________\n");
            }

        } else if (description.toLowerCase().contains(Deadlines)) {
            try {
                addDeadlines(description);
            } catch (InvalidDeadlineException e) {
                System.out.println(e.toString());
            }

        }else if (description.toLowerCase().contains(Events)) {
            try {
                addEvents(description);
            } catch (InvalidEventException e) {
                System.out.println(e.toString());
            }

        } else if (description.toLowerCase().contains(ToDos)) {
            try{
                addTodos(description);
            } catch (InvalidTodoException e) {
                System.out.println(e.toString());
            }

        } else {
            throw new InvalidCommandException();
        }
    }

    public static void updateTaskStatus(Integer index) {
        taskList.get(index - 1).markAsDone();

        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + taskList.get(index - 1).toString()
                + "\n____________________________________________________________\n");
    }

    public static void Remark() {
        if (numberOfTask() <= 1) {
            System.out.println("Now you have " + numberOfTask() + " task in the list."
                    + "\n____________________________________________________________\n");
        } else {
            System.out.println("Now you have " + numberOfTask() + " tasks in the list."
                    + "\n____________________________________________________________\n");
        }
    }

    public static void addTodos(String todo) throws InvalidTodoException {
        if (todo.length() <= 5) {
            throw new InvalidTodoException();
        } else {
            String taskContent = todo.substring(4);
            Todo myTask = new Todo(taskContent);
            taskList.add(myTask);
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task: \n"
                    + myTask.toString());
            Remark();
        }
    }

    public static void addDeadlines(String deadline) throws InvalidDeadlineException {
        if (deadline.length() <= 9) {
            throw new InvalidDeadlineException(deadline);
        } else {
            String[] taskSegments = deadline.split(" /by ");
            if (taskSegments.length < 2) {
                throw new InvalidDeadlineException(deadline);
            } else {
                String taskContent = taskSegments[0].substring(8);
                if (taskContent.equals(" ") || taskContent.equals("")) {
                    throw new InvalidDeadlineException(deadline);
                } else {
                    String taskTime = taskSegments[taskSegments.length - 1];
                    LocalDate t = LocalDate.parse(taskTime);
                    Deadline myTask = new Deadline(taskContent, t);
                    taskList.add(myTask);

                    System.out.println("____________________________________________________________\n"
                            + "Got it. I've added this task: \n"
                            + myTask.toString());
                    Remark();
                }
            }
        }
    }

    public static void addEvents(String event) throws InvalidEventException {
        if (event.length() <= 6) {
            throw new InvalidEventException(event);
        } else {
            String[] taskSegments = event.split(" /at ");
            if (taskSegments.length < 2) {
                throw new InvalidEventException(event);
            } else {
                String taskContent = taskSegments[0].substring(5);
                if (taskContent.equals(" ") || taskContent.equals("")) {
                    throw new InvalidEventException(event);
                } else {
                    String taskTime = taskSegments[taskSegments.length - 1];
                    LocalDateTime t = LocalDateTime.parse(taskTime);
                    Event myTask = new Event(taskContent, t);
                    taskList.add(myTask);

                    System.out.println("____________________________________________________________\n"
                            + "Got it. I've added this task: \n"
                            + myTask.toString());
                    Remark();
                }
            }
        }
    }

    public static void Delete(Integer index) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + taskList.get(index - 1).toString()
                + "\n____________________________________________________________\n");

        taskList.remove(index - 1);

    }


    public static void Exit() {
            String exit = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "Wish you all the best for CS2103T\n"
            + "____________________________________________________________\n";

            canExit = true;
            System.out.println(exit);


        }

    public static void main(String[] args) {

        String path = "duke.txt";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Greet();

        TaskRecord.taskRecorder(path);
        TaskRecord.taskHistory(path);


        while(!canExit) {
            try {
                storeTask();
            } catch (InvalidCommandException e) {
                System.out.println(e.toString());
            }
        }

        TaskRecord.record(taskList, path);
    }
}
