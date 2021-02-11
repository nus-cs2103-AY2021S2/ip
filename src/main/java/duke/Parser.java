package duke;

import java.util.Arrays;

public class Parser {
    private static Ui ui = new Ui();

    public static Task parseFile(String data) throws DukeException {
        String[] dataSplit = data.split(" \\| ");
        switch (dataSplit[0]) {
            case "T":
                return new ToDos(Boolean.parseBoolean(dataSplit[1]), dataSplit[2]);
            case "D":
                return new Deadlines(Boolean.parseBoolean(dataSplit[1]), dataSplit[2], dataSplit[3]);
            case "E":
                return new Events(Boolean.parseBoolean(dataSplit[1]), dataSplit[2], dataSplit[3]);
            default:
                throw new DukeException("Error while reading file");
        }
    }

    public static TaskList parseInput(String input, TaskList tasks) {
        String[] inputSplit = input.split(" ");
        if (inputSplit.length < 2) {
            ui.showInvalidCommandError();
            return tasks;
        }
        Task task;
        switch (inputSplit[0]) {
            case "done":
                ui.printSetDone(tasks.setDone(Integer.valueOf(inputSplit[1]) - 1));
                break;
            case "delete":
                task = tasks.deleteTask(Integer.valueOf(inputSplit[1]) - 1);
                ui.printDeleteTask(task);
                break;
            case "todo":
                String name = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
                task = new ToDos(false, name);
                tasks.addTask(task);
                ui.printAddTask(task);
                break;
            case "deadline":
                String deadlineName = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
                String[] deadlineSplit = deadlineName.split(" /by ");
                task = new Deadlines(false, deadlineSplit[0], deadlineSplit[1]);
                tasks.addTask(task);
                ui.printAddTask(task);
                break;
            case "event":
                String event = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
                String[] eventSplit = event.split(" /at ");
                task = new Events(false, eventSplit[0], eventSplit[1]);
                tasks.addTask(task);
                ui.printAddTask(task);
                break;
            default:
                ui.showInvalidCommandError();
        }
        return tasks;
    }
}
