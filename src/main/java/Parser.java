/*
 * Deal with making sense of users' commands.
 */
public class Parser {
    private String command;
    private TaskList taskList;
    private Ui ui;
    private boolean isFinished;

    public Parser(TaskList tasklist, Ui ui) {
        this.command = "";
        this.taskList = tasklist;
        this.ui = ui;
        this.isFinished = false;
    }

    /*
     * Add a new command to be dealt with.
     *
     * @param command New command.
     */
    public void insertCommand(String command) {
        this.command = command;
    }

    /*
     * Check if Parser is done dealing with all commands.
     *
     * @return Done status.
     */
    public boolean isFinished() {
        return this.isFinished;
    }

    /*
     * Deal with the current command.
     * Pick the appropriate response.
     * Pass the response to the Ui to handle.
     * In the case of a bad command, a relevant exception is thrown.
     * The exception is caught and the relevant message is printed.
     */
    public void process() {
        try {
            if (command.equals("list")) {
                ui.replace("Here are the tasks in your list:");
                for (int i = 0; i < taskList.numOfTasks(); i++) {
                    Integer taskNum = i + 1;
                    ui.replace(taskNum.toString() + "." + taskList.getTask(i).toString());
                }
            } else if (command.contains("todo")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new TodoException("Oops! The description for Todo cannot be empty.");
                }
                ui.replace("Got it. I've added this task:");
                String description = "";
                String date = "";
                String time = "";
                boolean isDesc = true;
                boolean isDate = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/at")) {
                        isDesc = false;
                    } else if (isDesc) {
                        if (i + 1 == length) {
                            description += line[i];
                        } else {
                            description += line[i] + " ";
                        }
                    } else if (isDate) {
                        date += line[i];
                        isDate = false;
                    } else {
                        time += line[i];
                    }
                }
                Todo todo = new Todo(description, date, time);
                taskList.addTask(todo);
                ui.replace(todo.toString());
                Integer numOfTasks = taskList.numOfTasks();
                ui.replace("Now you have " + numOfTasks.toString() + " tasks in the list.");
            } else if (command.contains("deadline")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new DeadlineException("Oops! The description for Deadline cannot be empty.");
                }
                ui.replace("Got it. I've added this task:");
                String description = "";
                String date = "";
                String time = "";
                boolean isDesc = true;
                boolean isDate = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/by")) {
                        isDesc = false;
                    } else if (isDesc) {
                        if (i + 1 == length) {
                            description += line[i];
                        } else {
                            description += line[i] + " ";
                        }
                    } else if (isDate) {
                        date += line[i];
                        isDate = false;
                    } else {
                        time += line[i];
                    }
                }
                Deadline deadline = new Deadline(description, date, time);
                taskList.addTask(deadline);
                ui.replace(deadline.toString());
                Integer numOfTask = taskList.numOfTasks();
                ui.replace("Now you have " + numOfTask.toString() + " tasks in the list.");
            } else if (command.contains("event")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new EventException("Oops! The description for Event cannot be empty.");
                }
                ui.replace("Got it. I've added this task:");
                String description = "";
                String date = "";
                String time = "";
                boolean isDesc = true;
                boolean isDate = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/at")) {
                        isDesc = false;
                    } else if (isDesc) {
                        if (i + 1 == length) {
                            description += line[i];
                        } else {
                            description += line[i] + " ";
                        }
                    } else if (isDate) {
                        date += line[i];
                        isDate = false;
                    } else {
                        time += line[i];
                    }
                }
                Event event = new Event(description, date, time);
                taskList.addTask(event);
                ui.replace(event.toString());
                Integer numOfTask = taskList.numOfTasks();
                ui.replace("Now you have " + numOfTask.toString() + " tasks in the list.");
            } else if (command.contains("done")) {
                String[] line = command.split(" ");
                int number = Integer.parseInt(line[1]) - 1;
                Task newTask = taskList.getTask(number).markAsDone();
                taskList.replaceTask(number, newTask);
                ui.replace("Nice! I've marked this task as done:");
                ui.replace(newTask.toString());
            } else if (command.contains("delete")) {
                ui.replace("Noted. I've removed this task:");
                String[] line = command.split(" ");
                int number = Integer.parseInt(line[1]) - 1;
                Task deletedTask = taskList.deleteTask(number);
                ui.replace(deletedTask.toString());
                Integer numOfTask = taskList.numOfTasks();
                ui.replace("Now you have " + numOfTask.toString() + " tasks in the list.");
            } else if (command.equals("bye")) {
                ui.replace("Bye! Hope to see you again soon!");
                this.isFinished = true;
            } else {
                throw new UnknownException("Oops! I'm sorry, but I don't know what that means!");
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
