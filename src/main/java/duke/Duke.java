package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private final Ui ui;
    private final TaskList tasklist;
    private final Storage storage;

    private Duke(String txtPathname, String dirPathname) {
        this.ui = new Ui();
        this.storage = new Storage(txtPathname, dirPathname);
        this.tasklist = new TaskList();
    }

    public static void main(String[] args) throws IOException {
        Duke myDuke = new Duke("data/DanhDuke.txt", "data");
        myDuke.ui.echoHi();
        myDuke.storage.writeBack(myDuke.tasklist.listUsed);
        boolean signalToExit = false;
        while (!signalToExit && myDuke.ui.stillHaveCommand()) {
            String commandLine = myDuke.ui.readCommand();
            Command command = Parser.parse(commandLine, myDuke.tasklist.listUsed);
            switch (command.commandTitle) {
                case "list":
                    myDuke.ui.echoPrintList(myDuke.tasklist.listUsed);
                    break;
                case "bye":
                    myDuke.ui.echoBye();
                    signalToExit = true;
                    break;
                case "done":
                    markTaskDone(myDuke, Integer.parseInt(command.commandContent));
                    break;
                case "delete":
                    deleteTask(myDuke, Integer.parseInt(command.commandContent));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addToList(myDuke, command.commandContent);
                    break;
                case "myTaskToday":
                    myDuke.ui.echoTaskToday(myDuke.tasklist.listUsed);
                    break;
                case "myTaskOn":
                    myDuke.ui.echoTaskThisDay(myDuke.tasklist.listUsed,
                            LocalDateTime.parse(command.commandContent, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    break;
                default:
                    try {
                        executeFalseCommand(command.commandContent);
                    } catch (DukeException err) {
                        myDuke.ui.echoErrMsg(err);
                    }
            }
        }
    }

    public static void addToList(Duke duke, String taskDescription) {
        Task task = duke.tasklist.addTask(taskDescription);
        duke.storage.updateFile(duke.tasklist.listUsed);
        duke.ui.echoAddToList(task, duke.tasklist.listUsed.size());
    }

    public static void markTaskDone(Duke duke, int index) {
        Task task = duke.tasklist.doneTask(index);
        duke.storage.updateFile(duke.tasklist.listUsed);
        duke.ui.echoMarkTaskDone(task);
    }

    public static void deleteTask(Duke duke, int index) {
        Task task = duke.tasklist.listUsed.get(index - 1);
        duke.ui.echoDeleteTask(task);
        duke.tasklist.deleteTask(index);
        duke.storage.updateFile(duke.tasklist.listUsed);
    }

    public static void executeFalseCommand(String command) throws DukeException {
        if (command.startsWith("list")) {
            throw new DukeException("     list command should not have body, Sir!");
        } else if (command.startsWith("bye")) {
            throw new DukeException("     bye command should not have body, Sir!");
        } else if (command.startsWith("done")) {
            throw new DukeException("     No body or wrong body format or invalid number for done command, Sir!");
        } else if (command.startsWith("delete")) {
            throw new DukeException("     No body or wrong body format or invalid number for delete command, Sir!");
        } else if (command.startsWith("todo")) {
            throw new DukeException("     No body detected for todo command, Sir!");
        } else if (command.startsWith("deadline")) {
            throw new DukeException("     no body detected or no dlTime detected for deadline command, Sir!");
        } else if (command.startsWith("event")) {
            throw new DukeException("     no body detected or no eTime detected for Event command, Sir!");
        } else if (command.startsWith("myTaskToday")) {
            throw new DukeException("     myTaskToday command should not have body, Sir!");
        } else if (command.startsWith("myTaskOn")) {
            throw new DukeException("     No body or wrong body format for myTaskOn command, Sir!");
        } else {
            throw new DukeException("     Invalid command format");
        }
    }
}


