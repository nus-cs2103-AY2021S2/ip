import java.io.IOException;
import java.time.LocalDate;

public class Parser {

    protected Storage storage;
    protected TaskList tasks;
    protected String command;
    protected int index;
    protected int findSlash;
    protected int taskIdentifier;

    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public void handleToDo() throws IOException {
        String descriptionTask = command.substring(index + 1);
        ToDo newToDo = new ToDo(descriptionTask);
        tasks.add(newToDo);
        storage.addTask(newToDo);
        System.out.println("Done! One new task:\n" + newToDo.toString() + "\nNow you have " +
                tasks.getSize() + ((tasks.getSize() == 1) ? " task" : " tasks") +
                " in the list");
    }

    public void handleDeadline() {
        try {
            Validation.checkForSchedule(command, findSlash);
            String descriptionDeadline = command.substring(index + 1, findSlash - 1);
            LocalDate date = DateValidation.handleDate(command.substring(findSlash + 4));
            Deadline newDeadline = new Deadline(descriptionDeadline, date);
            tasks.add(newDeadline);
            storage.addTask(newDeadline);
            System.out.println("Done! One new task:\n" + newDeadline.toString() +
                    "\nNow you have " + tasks.getSize() + ((tasks.getSize() == 1) ? " task" : " tasks") +
                    " in the list");
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    public void handleEvent() {
        try {
            Validation.checkForSchedule(command, findSlash);
            String descriptionEvent = command.substring(index + 1, findSlash - 1);
            String time = command.substring(findSlash + 4);
            Event newEvent = new Event(descriptionEvent, time);
            tasks.add(newEvent);
            storage.addTask(newEvent);
            System.out.println("Done! One new task:\n" + newEvent.toString() +
                    "\nNow you have " + tasks.getSize() + ((tasks.getSize() == 1) ? " task" : " tasks") +
                    " in the list");
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    public void handleDone() {
        try {
            taskIdentifier = Integer.parseInt(command.substring(index + 1));
            Validation.checkValidRange(tasks.getSize(), taskIdentifier);
            Task toMark = tasks.find(taskIdentifier - 1);
            String response = storage.markTask(toMark);
            System.out.println(response);
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    public void handleDelete() {
        try {
            taskIdentifier = Integer.parseInt(command.substring(index + 1));
            Validation.checkValidRange(tasks.getSize(), taskIdentifier);
            Task selected = tasks.find(taskIdentifier - 1);
            tasks.delete(taskIdentifier - 1);
            storage.deleteTask(selected);
            System.out.println("Noted, I've removed this task:\n" + selected.toString() +
                    "\nNow you have " + tasks.getSize() + ((tasks.getSize() == 1) ? " task" : " tasks") +
                    " in the list");
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    public void handleCommand(String command) {
        this.command = command;
        try {
            Validation.checkValidCommand(command);
            index = command.indexOf(' ');
            findSlash = command.indexOf('/');
            if (index > -1) {
                String type = command.substring(0, index);
                switch (type) {
                case "todo":
                    handleToDo();
                    break;
                case "deadline":
                    handleDeadline();
                    break;
                case "event":
                    handleEvent();
                    break;
                case "done":
                    handleDone();
                    break;
                case "delete":
                    handleDelete();
                    break;
                }
            } else {
                switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    tasks.list();
                    break;
                }
            }
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }



}
