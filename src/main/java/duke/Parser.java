package duke;

import java.io.IOException;

/**
 * Contains the main logic for responses by Duke.
 */
public class Parser {
    /**
     * Executes operations on the task list based on given input and
     * returns the response by the program.
     *
     * @param input Input from user.
     * @param tasks List of tasks.
     * @return Program response as a String.
     * @throws DukeException If input is not recognised.
     */
    public static String process(String input, TaskList tasks) throws DukeException {
        if (input.equals("bye") || input.equals("b")) {
            return null;
        }

        String command = input.split(" ")[0];
        String output;
        switch (command) {
        case "list":
        case "l":
            output = getList(tasks);
            break;
        case "done":
        case "do":
            output = markAsDone(input, tasks);
            break;
        case "delete":
        case "del":
            output = deleteTask(input, tasks);
            break;
        case "find":
        case "f":
            output = findTask(input, tasks);
            break;
        default:
            output = addNewTask(input, tasks);
            break;
        }
        return output;
    }

    private static String getList(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        output.append("  Tasks in list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("    %d.%s\n", i + 1, tasks.get(i)));
        }
        return output.toString();
    }

    private static String markAsDone(String input, TaskList tasks) throws DukeException {
        StringBuilder output = new StringBuilder();
        int inputNumber;
        try {
            inputNumber = Integer.parseInt(input.split(" ")[1]);
        } catch (Exception ex) {
            throw new DukeException("  Please provide an integer.");
        }

        try {
            tasks.markAsDone(inputNumber - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("  That number is invalid.");
        } catch (IOException ex) {
            throw new DukeException("  Unable to save tasks.");
        }

        output.append("  Successfully marked as done:\n");
        output.append(String.format("    %s\n", tasks.get(inputNumber - 1)));
        return output.toString();
    }

    private static String deleteTask(String input, TaskList tasks) throws DukeException {
        StringBuilder output = new StringBuilder();
        int inputNumber;
        try {
            inputNumber = Integer.parseInt(input.split(" ")[1]);
        } catch (Exception ex) {
            throw new DukeException("  Please provide an integer.");
        }

        Task taskToRemove;
        try {
            taskToRemove = tasks.remove(inputNumber - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("  That number is invalid.");
        } catch (IOException ex) {
            throw new DukeException("  Unable to save tasks.");
        }

        output.append("  Successfully removed:\n");
        output.append(String.format("    %s\n", taskToRemove));
        output.append(String.format("  Total tasks in list: %d\n", tasks.size()));
        return output.toString();
    }

    private static String findTask(String input, TaskList tasks) throws DukeException {
        StringBuilder output = new StringBuilder();
        String inputString;
        try {
            inputString = input.split(" ")[1].toLowerCase();
        } catch (Exception ex) {
            throw new DukeException("  Please provide a keyword to search for.");
        }

        output.append("  Matching tasks in list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            for (String word : tasks.get(i).toString().split(" ")) {
                if (inputString.equals(word.toLowerCase())) {
                    output.append(String.format("    %d.%s\n", i + 1, tasks.get(i)));
                }
            }
        }
        return output.toString();
    }

    private static String addNewTask(String input, TaskList tasks) throws DukeException {
        Task task;
        String[] inputs = input.split(" ");
        if (inputs.length <= 1) {
            throw new DukeException("  Please describe the task.");
        }
        String command = inputs[0];
        int index = input.indexOf(' ');
        String description = input.substring(index + 1);
        String[] descriptions;
        String name;

        switch (command) {
        case "todo":
        case "t":
            task = new Todo(description);
            break;
        case "deadline":
        case "d":
            descriptions = description.split("/by");
            if (descriptions.length <= 1) {
                throw new DukeException("  Please provide a description in the format "
                        + "[name] /by [date].");
            }
            name = String.format("%s(by:%s)", descriptions[0], descriptions[1]);
            task = new Deadline(name);
            break;
        case "event":
        case "e":
            descriptions = description.split("/at");
            if (descriptions.length <= 1) {
                throw new DukeException("  Please provide a description in the format "
                        + "[name] /at [date].");
            }
            name = String.format("%s(at:%s)", descriptions[0], descriptions[1]);
            task = new Event(name);
            break;
        default:
            throw new DukeException("  That command is invalid.");
        }

        try {
            tasks.add(task);
        } catch (IOException ex) {
            throw new DukeException("  Unable to save tasks.");
        }

        return "  Task added:\n" + String.format("    %s\n", task)
                + String.format("  Total tasks in list: %d\n", tasks.size());
    }
}
