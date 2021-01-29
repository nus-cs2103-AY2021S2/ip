package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

public class ParseCommands {
    private final CommandList commandList;
    private final String data;
    private boolean isExit;

    public ParseCommands(CommandList commandList, String data) {
        this.commandList = commandList;
        this.data = data;
        this.isExit = false;
    }

    public static ParseCommands parseLine(String input, int counter) throws DukeException {
        int first = input.indexOf(" ");
        ParseCommands parseCommands;
        CommandList commandList;
        String line = "";
        String command;

        if (first == -1) {
            command = input.toUpperCase();
        } else {
            command = input.substring(0, first).toUpperCase();
            line = input.substring(first).strip();
        }
        commandList = changeCommand(command);
        checkCommand(input, commandList, first, counter);
        parseCommands = new ParseCommands(commandList, line);
        return parseCommands;
    }

    public void executeCommand(Ui ui, Storage storage) {
        String date;
        switch (this.commandList) {
        case TODO:
            ToDo todo = new ToDo(this.data.strip());
            storage.add(todo);
            ui.showAdd(todo, storage.getArrSize());
            break;
        case DEADLINE:
            int byDate = this.data.lastIndexOf("/by ");
            date = this.data.substring(byDate + 4);
            Deadline deadline = new Deadline(this.data.substring(0, byDate).strip(), date);
            storage.add(deadline);
            ui.showAdd(deadline, storage.getArrSize());
            break;
        case EVENT:
            int atDate = this.data.lastIndexOf("/at ");
            date = this.data.substring(atDate + 4);
            Event event = new Event(this.data.substring(0, atDate).strip(), date);
            storage.add(event);
            ui.showAdd(event, storage.getArrSize());
            break;
        case DONE:
            int task_No = Integer.parseInt(this.data);
            ui.showDone(storage.get(task_No - 1).doTask());
            break;
        case LIST:
            ui.showList(storage);
            break;
        case REMOVE:
            int remove_No = Integer.parseInt(this.data);
            Task task = storage.remove(remove_No - 1);
            ui.showRemove(task, storage.getArrSize());
            break;
        case BYE:
            this.isExit = true;
            ui.showGoodbye();
            break;
        }
    }

    public static CommandList changeCommand (String command) throws DukeException {
        CommandList commandList;
        try {
            commandList = CommandList.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new DukeException(" I don't understand! :(");
        }
        return commandList;
    }

    public static void checkCommand(String input, CommandList commandList, int index, int counter) throws DukeException {
        boolean test = index == -1 || input.substring(index).isBlank();
        switch (commandList) {
        case TODO:
            if (test) {
                throw new DukeException(" ToDo cannot be empty! :(");
            }
            break;
        case DEADLINE:
            if (test) {
                throw new DukeException(" Deadline cannot be empty! :(");
            } else if (!input.contains("/by")) {
                throw new DukeException(" Deadline must have a date! :(");
            } else {
                int by = input.indexOf("/by");
                if (input.substring(index, by).isBlank()) {
                    throw new DukeException(" Deadline must have a task! :(");
                } else if (input.substring(by + 3).isBlank()) {
                    throw new DukeException(" Deadline date cannot be empty! :(");
                }
            }
            break;
        case EVENT:
            if (test) {
                throw new DukeException(" Event cannot be empty! :(");
            } else if (!input.contains("/at")) {
                throw new DukeException(" Event must have a date! :(");
            } else {
                int at = input.indexOf("/at");
                if (input.substring(index, at).isBlank()) {
                    throw new DukeException(" Event must have a task! :(");
                } else if (input.substring(at + 3).isBlank()) {
                    throw new DukeException(" Event date cannot be empty! :(");
                }
            }
            break;
        case DONE:
        case REMOVE:
            if (test) {
                throw new DukeException(" Which task? :(");
            } else {
                int no = Integer.parseInt(input.substring(index + 1));
                if (no > counter || no < 1) {
                    throw new DukeException(" No such task! :(");
                }
            }
            break;
        case LIST:
        case BYE:
        default:
            break;
        }
    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
