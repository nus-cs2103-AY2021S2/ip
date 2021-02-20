package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    TaskList taskList;

    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    void parseAll() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            parseLine(line);
            line = sc.nextLine();
        }
        sc.close();
    }
    void parseLine(String input) {
        try {
            if (input.equals("list") || input.contains("done")
                    || input.contains("delete") || input.contains("find")) {
                parseGeneralCommand(input);
            } else if (input.contains("todo") ||
                    input.contains("deadline") ||
                    input.contains("event")) {
                taskList.add(parseAddTaskCommand(input));
            } else {
                throw new DukeInvalidCommandException();
            }
        } catch (DukeInvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (DukeIncompleteCommandException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.printDateFormatError();
        }
    }

    void parseGeneralCommand(String input) throws DukeIncompleteCommandException {
        String[] inputs;
        int index;
        if (input.equals("list")) {
            taskList.displayList();
        } else if (input.contains("done")) {
            inputs = input.split(" ");
            index = Integer.parseInt(inputs[1]) - 1;
            taskList.markDone(index);
        } else if (input.contains("delete")) {
            inputs = input.split(" ");
            index = Integer.parseInt(inputs[1]) - 1;
            taskList.delete(index);
        } else {
            inputs = input.split(" ");
            String keyword = inputs[1];
            taskList.find(keyword);
        }
    }

    public static Task parseAddTaskCommand(String input) throws DukeIncompleteCommandException,
            DateTimeParseException {
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
        if (editedInput.equals("")) {
            throw new DukeIncompleteCommandException();
        }
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


