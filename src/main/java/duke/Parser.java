package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class Parser {
    public BufferedReader input;
    public UserInterface UI;
    public Storage storage;

    public Parser() throws IOException {
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.UI = new UserInterface();
        this.storage = new Storage("./src/main/DATA/duke.txt");
    }

    /*
     * Process the user input provided by the UI.
     *
     * @throws IOException
     * @throws InvalidCommandException for unrecognised/invalid commands.
     * @throws ParseException for invalid date input.
     */
    public String process(String command) throws IOException {
        assert !command.equals("");
        String[] userInput = command.split(" ", 2);
        String commandHeader = userInput[0].toLowerCase();
        if (commandHeader.equals(Command.TODO.toString())) {
            String description =  userInput[1];
            return processTodo(description);
        } else if (commandHeader.equals(Command.HELLO.toString())) {
            return "Please enter a command.";
        } else if (commandHeader.equals(Command.HELP.toString())) {
            return UI.helpMenu();
        } else if (commandHeader.equals(Command.DEADLINE.toString())) {
            String[] details = userInput[1].split("/", 2);
            return processDeadline(details);
        } else if (commandHeader.equals(Command.EVENT.toString())) {
            String[] details = userInput[1].split("/", 2);
            return processEvent(details);
        } else if (commandHeader.equals(Command.LIST.toString())) {
            return UI.listTasks();
        } else if (commandHeader.equals(Command.FIND.toString())) {
            List<Task> foundTasks = TaskList.find(userInput[1]);
            return UI.listTasks(foundTasks);
        } else if (commandHeader.equals(Command.REMOVE.toString())) {
            String[] details = userInput[1].split(" ", 2);
            return processRemove(details);
        } else if (commandHeader.equals(Command.COMPLETE.toString())) {
            String[] details = userInput[1].split(" ", 2);
            return processComplete(details);
        } else if (commandHeader.equals(Command.BYE.toString())) {
            this.storage.exportTasks();
            System.exit(0);
        } else {
            try {
                throw new InvalidCommandException("Sorry, I don't know what that means!\n" +
                        "Please enter a valid command.\n");
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }
        }
        return "Please enter a valid command.\n";
    }

    /*
     * Processes commands with prefix todo
     *
     * @param description description of the todo task
     * @return string detailing the outcome of the execution the command
     */
    public String processTodo(String description) {
        ToDo newToDo = new ToDo(description);
        if (TaskList.contains(newToDo)) {
            return "This task already exists!";
        }
        TaskList.taskList.add(newToDo);
        return UI.addedTask();
    }

    /*
     * Processes commands with prefix deadline
     *
     * @param description description of the deadline task
     * @return string detailing the outcome of the execution the command
     */
    public String processDeadline(String[] details) {
        String result = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("'by' dd.MM.yyyy HH:mm");
        try {
            Date by = dateFormat.parse(details[1]);
            Deadline newDeadline = new Deadline(details[0], by);
            if (TaskList.contains(newDeadline)) {
                result = "This task already exists!";
            }
            TaskList.taskList.add(newDeadline);
            return UI.addedTask();
        } catch (ParseException e) {
            System.out.println("Please type the date in the format \"by dd.mm.yyyy hh:mm\"!");
        }
        return result;
    }

    /*
     * Processes commands with prefix event
     *
     * @param description description of the event task
     * @return string detailing the outcome of the execution the command
     */
    public String processEvent(String[] details) {
        String result = "";
        SimpleDateFormat startDateFormat = new SimpleDateFormat("'from' dd.MM.yyyy HH:mm ");
        SimpleDateFormat endDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String[] timings = details[1].split("to ", 2);
        try {
            Date start = startDateFormat.parse(timings[0]);
            Date end = endDateFormat.parse(timings[1]);
            Event newEvent = new Event(details[0], start, end);
            if (TaskList.contains(newEvent)) {
                result = "This task already exists!";
            }
            TaskList.taskList.add(newEvent);
            return UI.addedTask();
        } catch (ParseException e){
            System.out.println("Please type the date in the format \"from dd.mm.yyyy hh:mm to dd.mm.yyyy hh:mm\"!");
        }
        return result;
    }

    /*
     * Processes commands with prefix remove
     *
     * @param description description of the remove task
     * @return string detailing the outcome of the execution the command
     */
    public String processRemove(String[] details) {
        if (details[0].toLowerCase().equals(Command.TODO.toString())) {
            TaskList.removeTask(new ToDo(details[1]));
            return "Task removed.\n";
        } else if (details[0].toLowerCase().equals(Command.DEADLINE.toString())) {
            String description = details[1].split("/", 2)[0];
            TaskList.removeTask(new Deadline(description, new Date()));
            return "Task removed.\n";
        } else if (details[0].toLowerCase().equals(Command.EVENT.toString())) {
            String description = details[1].split("/", 2)[0];
            TaskList.removeTask(new Event(description, new Date(), new Date()));
            return "Task removed.\n";
        }
        return "Please enter a valid task.\n";
    }

    /*
     * Processes commands with prefix complete
     *
     * @param description description of the complete task
     * @return string detailing the outcome of the execution the command
     */
    public String processComplete(String[] details) {
        if (details[0].toLowerCase().equals(Command.TODO.toString())) {
            return TaskList.completeTask(new ToDo(details[1]));
        } else if (details[0].toLowerCase().equals(Command.DEADLINE.toString())) {
            String description = details[1].split("/", 2)[0];
            return TaskList.completeTask(new Deadline(description, new Date()));
        } else if (details[0].toLowerCase().equals(Command.EVENT.toString())) {
            String description = details[1].split("/", 2)[0];
            return TaskList.completeTask(new Event(description, new Date(), new Date()));
        }
        return "Please enter a valid task.\n";
    }
}
