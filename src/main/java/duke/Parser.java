package duke;

import java.util.Arrays;

public class Parser {

    /**
     * This function returns a Task object after parsing the data from the file
     * @param data line data from the file
     * @return Task object of various types, e.g. Deadlines, ToDos, Events
     * @throws DukeException
     */
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

    /**
     * This function returns the TaskList object after passing the commands from user
     * @param input User input
     * @param tasks TaskList object
     * @return TaskList after adding the new task from the user input
     */
    public static Pair parseInput(String input, TaskList tasks) {
        String[] inputSplit = input.split(" ");
        if (inputSplit.length < 2) {
            return new Pair(tasks, "You have entered invalid command!");
        }
        Task task;
        String message;

        switch (inputSplit[0]) {
        case "find":
            String keyword = inputSplit[1];
            TaskList result = tasks.findTask(keyword);
            message = "Here are the matching tasks in your list: \n";
            for (int i = 0; i < result.getSize(); i++) {
                message += result.getTask(i).toString();
            }
            break;
        case "done":
            message = "Nice, I have set this task as done!" +
                    tasks.setDone(Integer.valueOf(inputSplit[1]) - 1).toString();
            break;
        case "delete":
            task = tasks.deleteTask(Integer.valueOf(inputSplit[1]) - 1);
            message = "Noted. I have deleted this task: \n" + task.toString();
            break;
        case "todo":
            String name = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
            task = new ToDos(false, name);
            tasks.addTask(task);
            message = "Got it. I have added this task: \n" + task.toString();
            break;
        case "deadline":
            String deadlineName = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
            String[] deadlineSplit = deadlineName.split(" /by ");
            task = new Deadlines(false, deadlineSplit[0], deadlineSplit[1]);
            tasks.addTask(task);
            message = "Got it. I have added this task: \n" + task.toString();
            break;
        case "event":
            String event = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
            String[] eventSplit = event.split(" /at ");
            task = new Events(false, eventSplit[0], eventSplit[1]);
            tasks.addTask(task);
            message = "Got it. I have added this task: \n" + task.toString();
            break;
        default:
            message = "You have entered invalid commands!";
        }

        return new Pair(tasks, message);
    }
}
