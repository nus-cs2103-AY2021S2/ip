//package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Class containing methods for parsing user input and
 * file contents.
 */
public class Parser {
    TaskList taskList;

    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses all user input and processes each line and
     * command.
     */

    void parseAll() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            System.out.println(parseLine(line));
            line = sc.nextLine();
        }
        System.exit(0);
        sc.close();
    }

    /**
     * Parses each line of user input and determines if it
     * is a general command or a command for adding tasks.
     * @param input Each line of user input
     */
    String parseLine(String input) {
        try {
            if (input.equals("list") || input.contains("done")
                    || input.contains("delete") || input.contains("find")) {
                return parseGeneralCommand(input);
            } else if (input.contains("todo") ||
                    input.contains("deadline") ||
                    input.contains("event")) {
                return taskList.add(parseAddTaskCommand(input));
            } else {
                throw new DukeInvalidCommandException();
            }
        } catch (DukeInvalidCommandException e) {
            return (e.getMessage());
        } catch (DukeIncompleteCommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Ui.dateFormatErrorString();
        }
    }

    /**
     * Parses commands like "list", "done" and "delete".
     * @param input User input that are general commands.
     * @throws DukeIncompleteCommandException If "done" or "delete"
     * commands do not state an index.
     */

    String parseGeneralCommand(String input) throws DukeIncompleteCommandException {
        String[] inputs;
        int index;
        if (input.equals("list")) {
            return taskList.listString();
        } else if (input.contains("done")) {
            inputs = input.split(" ");
            index = Integer.parseInt(inputs[1]) - 1;
            return taskList.markDone(index);
        } else if (input.contains("delete")) {
            inputs = input.split(" ");
            index = Integer.parseInt(inputs[1]) - 1;
            return taskList.delete(index);
        } else {
            inputs = input.split(" ");
            String keyword = inputs[1];
            return taskList.find(keyword);
        }
    }

    /**
     * Parses commands for adding tasks like "todo", "event" and "deadline".
     * @param input User input that are task adding commands.
     * @return Task that corresponds to the input.
     * @throws DukeIncompleteCommandException If no task has been specified.
     * @throws DateTimeParseException If date is not in the YYYY-MM-DD format.
     */
    public static Task parseAddTaskCommand(String input) throws DukeIncompleteCommandException,
            DateTimeParseException {
        // Get rid of command string and get date regex
        TimedTask timedTask = new TimedTask();
        String editedInput;
        String regex;
        if (input.contains("todo")) {
            editedInput = input.substring(4).trim();
            regex = "";
        } else if (input.contains("deadline")) {
            editedInput = input.substring(8).trim();
            regex = "/by";
            timedTask = new Deadline();
        } else {
            editedInput = input.substring(5).trim();
            regex = "/at";
            timedTask = new Event();
        }

        // Catch incomplete commands
        if (editedInput.equals("")) {
            throw new DukeIncompleteCommandException();
        }

        // Check if todo, or timed task (event, deadline)
        if (regex.equals("")) {
            Task task = new ToDo(editedInput);
            return task;
        } else {
            String[] editedInputs = editedInput.split(regex);
            timedTask.task = editedInputs[0].trim();
            try {
                timedTask.date = LocalDate.parse(editedInputs[1].trim());
                return timedTask;
            } catch (DateTimeParseException e) {
                throw e;
            }
        }
    }

    /**
     * Parses data stored in file.
     * @param line Each line stored in data.txt.
     * @return Task that corresponds to each line in data.txt.
     */
    static Task parseFile(String line) {
        if (line.contains("TODO")) {
            Task task = new ToDo();
            if (line.charAt(5) == 'X') {
                task.isDone = true;
            } else {
                task.isDone = false;
            }
            task.task = line.substring(7).trim();
            return task;
        } else {
            TimedTask timedTask;
            String regex;
            if (line.contains("DDLN")) {
                timedTask = new Deadline();
                regex = "by: ";
            } else {
                timedTask = new Event();
                regex = "at: ";
            }
            if (line.charAt(5) == 'X') {
                timedTask.isDone = true;
            } else {
                timedTask.isDone = false;
            }
            String[] lines = line.substring(7).trim().split(regex);
            timedTask.task = lines[0].substring(0, lines[0].length() - 2).trim();
            String dateString = lines[1].substring(0, lines[1].length() - 1);
            timedTask.date = LocalDate.parse(dateString.subSequence(0, dateString.length()));
            return timedTask;
        }
    }

}


