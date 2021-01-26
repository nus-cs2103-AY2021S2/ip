package duke.error;

import duke.Duke;
import duke.datetime.DateTimeConverter;
import duke.task.Task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ErrorChecker {
    protected String input;
    protected ArrayList<Task> list;

    public ErrorChecker(String input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

    public boolean check() {
        String taskType = "";

        if (!input.startsWith("done") && !input.startsWith("delete") && !input.startsWith("todo")
                && !input.startsWith("deadline") && !input.startsWith("event")) {
            System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + new IllegalInputException("").toString()
                    + "\n" + Duke.LINE);
            return false;
        }

        try {
            if (input.startsWith("done")) {
                taskType = "done";
                input.substring(6);
                String taskToMark = input.substring(5);
                int taskToMarkInt = Integer.parseInt(taskToMark);
                list.get(taskToMarkInt - 1);
            } else if (input.startsWith("delete")) {
                taskType = "delete";
                input.substring(8);
                String taskToDelete = input.substring(7);
                int taskToDeleteInt = Integer.parseInt(taskToDelete);
                list.get(taskToDeleteInt - 1);
            } else if (input.startsWith("todo")) {
                taskType = "todo";
                input.substring(6);
            } else if (input.startsWith("deadline")) {
                taskType = "deadline";
                input.substring(10);

                String[] inputSplit = input.split("/");
                DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
                dateTimeConverter.convertDate();
            } else if (input.startsWith("event")) {
                taskType = "event";
                input.substring(7);

                String[] inputSplit = input.split("/");
                DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
                dateTimeConverter.convertDate();
                dateTimeConverter.convertTime("from");
                dateTimeConverter.convertTime("to");
            }
        } catch (StringIndexOutOfBoundsException ex) {
            if (input.startsWith("done") || input.startsWith("delete")) {
                System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9
                        + new IllegalDoneDeleteException(ex.getMessage(), taskType).toString() + "\n" + Duke.LINE);
                return false;
            } else {
                System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9
                        + new IllegalTaskException(ex.getMessage(), taskType).toString() + "\n" +  Duke.LINE);
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9
                    + new OutOfBoundsDoneDeleteException(ex.getMessage()).toString() + "\n" +  Duke.LINE);
            return false;
        } catch (DateTimeParseException ex) {
            if (input.startsWith("deadline")) {
                System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9
                        + "Oops, I don't understand that date format!\n" + (char) 9 + (char) 9
                        + "Please re-enter with the format DD-MM-YYYY!\n" + Duke.LINE);
            } else {
                System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9
                        + "Oops, I don't understand that date format!\n"  + (char) 9 + (char) 9
                        + "Please re-enter with the format DD-MM-YYYY /from H AM/PM /to H AM/PM!\n" + Duke.LINE);
            }
            return false;
        }

        return true;
    }
}
