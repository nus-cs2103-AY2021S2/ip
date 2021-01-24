import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(dateTimeString, inputDateTimeFormatter);
    }

    // Adding, Editing & Displaying Tasks

    public void addTask(String[] userInputArr) throws DukeException {
        switch (userInputArr[0]) {
            case "todo":
                addTodo(userInputArr[1]);
                break;
            case "deadline":
                String[] detailsArr = userInputArr[1].split(" /by ", 2);
                if (detailsArr.length != 2) {
                    throw new DukeException("You can't add a deadline without a deadline!");
                }
                addDeadline(detailsArr);
                break;
            case "event":
                detailsArr = userInputArr[1].split(" /at ", 2);
                if (detailsArr.length != 2) {
                    throw new DukeException("You can't add an event without an event time.");
                }
                addEvent(detailsArr);
                break;
            default:
                break;
        }
    }

    public void addTodo(String details) {
        Todo todo = new Todo(details);
        tasks.addTask(todo);
        ui.printAddTaskReport(todo, tasks);
    }

    public void addDeadline(String[] detailsArr) throws DukeException {
        try {
            LocalDateTime date = parseDateTime(detailsArr[1]);
            Deadline deadline = new Deadline(detailsArr[0], date);
            tasks.addTask(deadline);
            ui.printAddTaskReport(deadline, tasks);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please follow the datetime format of dd/mm/yyyy hhmm.");
        }
    }

    public void addEvent(String[] detailsArr) throws DukeException {
        try {
            LocalDateTime date = parseDateTime(detailsArr[1]);
            Event event = new Event(detailsArr[0], date);
            tasks.addTask(event);
            ui.printAddTaskReport(event, tasks);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please follow the datetime format of dd/mm/yyyy hhmm.");
        }
    }

    public void markTaskAsDone(String index) {
        try {
            int taskIndex = Integer.parseInt(index.trim());
            Task task = tasks.getTask(taskIndex - 1);
            ui.printMarkTaskAsDoneMessage(task);
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("I cannot find the task you are referring to.");
        } catch (NumberFormatException e) {
            ui.printErrorMessage("I can only find a task with its index number.");
        }
    }

    public void deleteTask(String index) {
        try {
            int taskIndex = Integer.parseInt(index.trim());
            Task task = tasks.getTask(taskIndex - 1);
            tasks.deleteTask(taskIndex - 1);
            ui.printDeleteTaskMessage(task, tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("I cannot find the task you are referring to.");
        } catch (NumberFormatException e) {
            ui.printErrorMessage("I can only find a task with its index number.");
        }
    }

    // Handling user inputs

    private static UserInputType getUserInputType(String userInput) throws DukeException {
        try {
            return UserInputType.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }

    public void handleUserInput() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.nextUserInput();
                String[] userInputArr = userInput.split(" ", 2);
                switch (getUserInputType(userInputArr[0])) {
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        if (userInputArr.length != 2) {
                            throw new DukeException("The description of your " + userInputArr[0] + " cannot be empty!");
                        }
                        addTask(userInputArr);
                        break;
                    case DONE:
                        if (userInputArr.length != 2) {
                            throw new DukeException("I'm not sure which task you want to mark as done!");
                        }
                        markTaskAsDone(userInputArr[1]);
                        break;
                    case DELETE:
                        if (userInputArr.length != 2) {
                            throw new DukeException("I'm not sure which task you want to delete!");
                        }
                        deleteTask(userInputArr[1]);
                        break;
                    case LIST:
                        ui.printAllTasks(tasks);
                        break;
                    case BYE:
                        ui.printFarewell();
                        isRunning = false;
                        break;
                    default:
                        throw new DukeException("Sorry, I dont understand what that means :-(");
                }
                storage.saveTasksToFile(tasks);
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public void run() {
        ui.printGreeting();
        try {
            storage.loadTasksFromFile(tasks);
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
        handleUserInput();
        ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
