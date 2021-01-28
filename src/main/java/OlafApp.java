import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class OlafApp {
    private TaskList taskList;
    private Ui ui;
    private boolean isActive;

    OlafApp(TaskList tasks) {
        this.taskList = tasks;
        this.ui = new Ui();
        this.isActive = true;
    }

    void run() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(PrintText.WELCOME_MESSAGE);

        while(isActive) {
            String command = null;
            try {
                command = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert command != null;

            if(command.equalsIgnoreCase("bye")){
                this.stop();
            } else if(command.equalsIgnoreCase("list")){
                // todo: use try catch here and make list printing a ui method
                if (taskList.hasTasks()) {
                    // print iteratively if there are tasks in the list
                    System.out.println(PrintText.BORDER
                            + "  Here are all the tasks in your list:\n\n"
                            + taskList.toString());
                    System.out.printf("  Only %s tasks left to be done!\n%s",
                            taskList.getTotalNumberOfTasksUndone(), PrintText.BORDER);
                } else {
                    System.out.println(PrintText.EMPTY_TASKLIST_ERROR);
                }
            } else if(command.toLowerCase().startsWith("done")){
                try {
                    int id = Parser.parseIntParameter(command);
                    taskList.markTaskAsDone(id);

                    Storage.saveData(taskList.toString());

                    ui.showDoneSuccess(id, taskList.getTask(id), taskList.getTotalNumberOfTasksUndone());
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showFormatError(PrintText.DONE_FORMAT);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    ui.showInvalidIndexError();
                }
            } else if(command.toLowerCase().startsWith("delete")){
                try {
                    int id = Parser.parseIntParameter(command);
                    Task deleted = taskList.deleteTask(id);

                    Storage.saveData(taskList.toString());

                    ui.showDeleteSuccess(id, deleted, taskList.getTotalNumberOfTasks());
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showFormatError(PrintText.DELETE_FORMAT);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    ui.showInvalidIndexError();
                }
            } else if(command.toLowerCase().startsWith("todo")){
                try {
                    String expression = Parser.parseParameter(command, " ", 1);
                    Todo newTodo = new Todo(expression);
                    taskList.addTask(newTodo);

                    Storage.saveData(taskList.toString());

                    ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                            newTodo, taskList.getTotalNumberOfTasksUndone());
                } catch (IndexOutOfBoundsException e) {
                    ui.showFormatError(PrintText.TODO_FORMAT);
                }
            } else if(command.toLowerCase().startsWith("deadline")){
                try {
                    String expression = Parser.parseParameter(command, " ",1);
                    String description = Parser.parseParameter(expression, "/by", 0);

                    String dateTime = Parser.parseParameter(expression,"/by",1);
                    LocalDateTime deadline = Parser.parseDeadlineParameter(dateTime);

                    Deadline newDeadline = new Deadline(description, deadline);
                    taskList.addTask(newDeadline);

                    Storage.saveData(taskList.toString());

                    ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                            newDeadline, taskList.getTotalNumberOfTasksUndone());
                    System.out.println(PrintText.BORDER);
                } catch (Exception e) {
                    // catches both ParseException and IndexOutOfBounds exception
                    ui.showFormatError(PrintText.DEADLINE_FORMAT);
                }
            } else if(command.toLowerCase().startsWith("event")) {
                try {
                    String expression = Parser.parseParameter(command, " ",1);
                    String description = Parser.parseParameter(expression, "/at", 0);

                    String dateTime = Parser.parseParameter(expression,"/at",1);
                    String start = Parser.parseParameter(dateTime," to ",0);
                    String end = Parser.parseParameter(dateTime," to ",1);

                    LocalDateTime startDateTime = Parser.parseDeadlineParameter(start);
                    LocalDateTime endDateTime = Parser.parseDeadlineParameter(end);

                    Event newEvent = new Event(description, startDateTime, endDateTime);
                    taskList.addTask(newEvent);

                    Storage.saveData(taskList.toString());

                    ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                            newEvent, taskList.getTotalNumberOfTasksUndone());
                } catch (Exception e) {
                    // catches both ParseException and IndexOutOfBounds exception
                    ui.showFormatError(PrintText.EVENT_FORMAT);
                }
            } else if (command.equalsIgnoreCase("help")) {
                ui.showFormatResponse(PrintText.HELP);
            } else {
                ui.showFormatResponse("  Hmm sorry I don't understand :(\n"
                        + "  Type 'help' to find out how you can talk to me!\n");
            }
        }
    }

    void stop() {
        isActive = false;
        ui.formatResponse("  Aww hope to see you soon, goodbye!\n");
    }
}
