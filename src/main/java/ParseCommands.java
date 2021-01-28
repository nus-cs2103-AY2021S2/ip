import java.util.ArrayList;

public class ParseCommands {
    private final CommandList commandList;
    private final String data;
    private boolean isExit;

    public ParseCommands(CommandList commandList, String data, int first) {
        this.commandList = commandList;
        this.data = data;
        this.isExit = false;
    }

    public static ParseCommands parseLine(Ui ui, String input, int counter) {
        int first = input.indexOf(" ");
        ParseCommands parseCommands = null;
        CommandList commandList;
        String line = "";

        try {
            if (first == -1) {
                commandList = CommandList.valueOf(input.toUpperCase());

            } else {
                commandList = CommandList.valueOf(input.substring(0, first).toUpperCase());
                line = input.substring(first).strip();
            }
            checkCommand(input, commandList, first, counter);
            parseCommands = new ParseCommands(commandList, line, first);
        } catch (DukeException | IllegalArgumentException e) {
            ui.showError(e.toString());
        }
        return parseCommands;
    }

    public void executeCommand(Ui ui, ArrayList<Task> arrL) {
        String date;
        switch (this.commandList) {
        case TODO:
            ToDo todo = new ToDo(this.data.strip());
            arrL.add(todo);
            ui.showAdd(todo, arrL.size());
            break;
        case DEADLINE:
            int byDate = this.data.lastIndexOf("/by ");
            date = this.data.substring(byDate + 4);
            Deadline deadline = new Deadline(this.data.substring(0, byDate).strip(), date);
            arrL.add(deadline);
            ui.showAdd(deadline, arrL.size());
            break;
        case EVENT:
            int atDate = this.data.lastIndexOf("/at ");
            date = this.data.substring(atDate + 4);
            Event event = new Event(this.data.substring(0, atDate).strip(), date);
            arrL.add(event);
            ui.showAdd(event, arrL.size());
            break;
        case DONE:
            int task_No = Integer.parseInt(this.data);
            arrL.get(task_No - 1).doTask();
            ui.showDone(arrL.get(task_No - 1));
            break;
        case LIST:
            ui.showList(arrL);
            break;
        case REMOVE:
            int remove_No = Integer.parseInt(this.data);
            Task task = arrL.remove(remove_No - 1);
            ui.showRemove(task, arrL.size());
            break;
        case BYE:
            this.isExit = true;
            ui.showGoodbye();
            break;
        }
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
        case LIST:
        case BYE:
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
        default:
            throw new DukeException(" I don't understand! :(");
        }
    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
