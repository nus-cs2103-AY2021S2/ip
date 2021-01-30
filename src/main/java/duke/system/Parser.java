package duke.system;

import duke.system.exception.DukeException;
import duke.task.*;

public class Parser {
    private final String command;
    private final String argument;
    private final String date;
    private static final String LINE = "\n____________________________________________________________";

    enum predefinedCommand {
        LIST,
        BYE,
        DONE,
        TODO,
        DEADLINE,
        EVENT,
        ERROR,
        DELETE
    }

    public Parser() {
        this.command = "";
        this.date = null;
        this.argument = "";
    }

    public Parser(String in) {
        String tempDate = null;
        String tempCommand = "";
        String[] result = in.split("\\s");
        String tempArg = "";
        try {
            if (result[0].equals("done")) {
                tempCommand = result[0];
                if (result.length <= 1) {
                    throw new DukeException.NoDescriptionException(result[0]);
                } else {
                    tempArg = result[1];
                }
            } else if (result[0].equals("todo") || result[0].equals("delete")) {
                String temp = in.substring(in.indexOf(" ") + 1);
                tempArg = temp;
                if (temp.equals("todo") || temp.equals("delete")) {
                    throw new DukeException.NoDescriptionException(result[0]);
                } else {
                    tempCommand = result[0];
                }
            } else if (result[0].equals("deadline") || result[0].equals("event")) {
                String firstParam = in.substring(in.indexOf("/") + 1);
                if (firstParam.equals("deadline") || firstParam.equals("event")) {
                    throw new DukeException.NoDescriptionException(result[0]);
                } else {
                    int dateIndex = Math.max(firstParam.indexOf("by "), firstParam.indexOf("at "));
                    if (dateIndex == -1) {
                        throw new DukeException.NoDescriptionException(result[0]);
                    } else {
                        tempCommand = result[0];
                        tempDate = firstParam.substring(dateIndex + 3);
                        firstParam = in.substring(in.indexOf(" ") + 1);
                        tempArg = firstParam.substring(0, firstParam.indexOf("/") - 1);
                    }
                }
            } else {
//                System.out.println(Arrays.toString(result));
//                System.out.println(predefinedCommand.valueOf(result[0]));
                try {
                    tempCommand = String.valueOf(predefinedCommand.valueOf(result[0]));
                } catch (IllegalArgumentException ex) {
                    throw new DukeException.UnknownCommandException();
                }
            }
        } catch (DukeException ex) {
            tempCommand = "error";
            tempArg = ex.getMessage();
        }
        this.command = tempCommand;
        this.argument = tempArg;
        this.date = tempDate;
    }

    public String getCommand() {
        return this.command;
    }

    public String getArgument() {
        return this.argument;
    }

    public String getDate() {
        return this.argument;
    }

    public String print(TaskList inputList) {
        predefinedCommand switchVal = predefinedCommand.valueOf(this.command);
        switch (switchVal) {
        case BYE:
            return "Bye. Hope to see you again soon!";
        case LIST:
            String initStr = "Here are the tasks in your list:";
            for (int i = 0; i < inputList.getListItems().size(); i++) {
                initStr += "\n" + ((i + 1) + "." + inputList.getListItems().get(i));
            }
            return initStr + LINE;
        case DONE:
            inputList.updateItemMutable(Integer.parseInt(this.argument));
            return "Nice! I've marked this task as done: \n" + inputList.getListItems().get(Integer.parseInt(this.argument) - 1) + LINE;
        case EVENT:
            Event newEvent = new Event(this.argument, this.date);
            inputList.addCommandMutable(newEvent);
            return printPredefinedMessage(newEvent.toString(), inputList);
        case DEADLINE:
            Deadline newDeadline = new Deadline(this.argument, this.date);
            inputList.addCommandMutable(newDeadline);
            return printPredefinedMessage(newDeadline.toString(), inputList);
        case TODO:
            Todo newTodo = new Todo(this.argument);
            inputList.addCommandMutable(newTodo);
            return printPredefinedMessage(newTodo.toString(), inputList);
        case ERROR:
            return this.argument;
        case DELETE:
            int index = Integer.parseInt(this.argument);
            ListItem tempItem = inputList.getListItems().get(index - 1);
            inputList.deleteCommandMutable(index);
            return "Noted. I've removed this task: " + tempItem + "\nNow you have " + inputList.getListItems().size() + " tasks in the list" + LINE;
        }
        // every case must return some form of string, therefore break is not required
        return "";
    }

    public String printPredefinedMessage(String typeOfTask, TaskList inputList) {
        return "Got it. I've added this task: \n" + typeOfTask + "\nNow you have " + inputList.getListItems().size() + " tasks in the list" + LINE;
    }
}
