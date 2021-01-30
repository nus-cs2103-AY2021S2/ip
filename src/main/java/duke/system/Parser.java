package duke.system;
import duke.system.exception.DukeException;
import duke.task.*;

/**
 * Represents a parser that takes in the entered <code>command</code> by the user and filtered by the enum,
 * then return the parsed <code>command</code>, <code>argument</code> and <code>date</code>
 * <code>print</code> the output and add the parsed item to the list if needed
 */
public class Parser {
    private final String command;
    private final String argument;
    private final String date;
    private final String line = "\n____________________________________________________________";

    enum predefinedCommand {
        list,
        bye,
        done,
        todo,
        deadline,
        event,
        error,
        delete
    }

    /**
     * Constructor method that initiates with empty/ null values
     */
    public Parser() {
        this.command = "";
        this.date = null;
        this.argument = "";
    }

    /**
     *
     * @param in - a string that will be parsed and stored as <code>command</code>, <code>argument</code> and <code>date</code> accordingly
     * @throws DukeException.UnknownCommandException if unknown command entered
     * @throws DukeException.NoDescriptionException if required no. of arg is not met
     */
    public Parser(String in) {
        String tempDate = null;
        String tempCommand = "";
        String[] result = in.split("\\s");
        String tempArg = "";
        try {
            if (result[0].equals("done")) {
                tempCommand = result[0];
                if(result.length <= 1) {
                    throw new DukeException.NoDescriptionException(result[0]);
                }else{
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
                }catch(IllegalArgumentException ex){
                    throw new DukeException.UnknownCommandException();
                }
            }
        }catch(DukeException ex){
            tempCommand = "error";
            tempArg = ex.getMessage();
        }
        this.command = tempCommand;
        this.argument = tempArg;
        this.date = tempDate;
    }

    /**
     * Getter method
     * @return the private variable command
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Getter method
     * @return the private variable argument
     */
    public String getDate() {
        return this.argument;
    }

    /**
     *
     * @param inputList - take in the list and do corresponding action according to the command
     * @return a string to be printed to the console by UI
     */
    public String print(TaskList inputList) {
        predefinedCommand switchVal = predefinedCommand.valueOf(this.command);
        switch (switchVal) {
            case bye:
                return "Bye. Hope to see you again soon!";
            case list:
                String initStr = "Here are the tasks in your list:";
                for (int i = 0; i < inputList.getDukeList().size(); i++) {
                    initStr += "\n" + ((i + 1) + "." + inputList.getDukeList().get(i));
                }
                return initStr + line;
            case done:
                inputList.updateItemMutable(Integer.parseInt(this.argument));
                return "Nice! I've marked this task as done: \n" + inputList.getDukeList().get(Integer.parseInt(this.argument) - 1) + line;
            case event:
                Event newEvent = new Event(this.argument, this.date);
                inputList.addCommandMutable(newEvent);
                return printPredefinedMessage(newEvent.toString(), inputList);
            case deadline:
                Deadline newDeadline = new Deadline(this.argument, this.date);
                inputList.addCommandMutable(newDeadline);
                return printPredefinedMessage(newDeadline.toString(), inputList);
            case todo:
                Todo newTodo = new Todo(this.argument);
                inputList.addCommandMutable(newTodo);
                return printPredefinedMessage(newTodo.toString(), inputList);
            case error:
                return this.argument;
            case delete:
                int index = Integer.parseInt(this.argument);
                ListItem tempItem = inputList.getDukeList().get(index - 1);
                inputList.deleteCommandMutable(index);
                return "Noted. I've removed this task: " + tempItem + "\nNow you have " + inputList.getDukeList().size() + " tasks in the list" + line;
        }
        return "";
    }

    /**
     * creates a standardised string that is common for all the tasks type to be printed
     *
     * @param typeOfTask
     * @param inputList - to get the size of the list
     * @return a string to be printed by UI
     */
    public String printPredefinedMessage(String typeOfTask, TaskList inputList) {
        return "Got it. I've added this task: \n" + typeOfTask + "\nNow you have " + inputList.getDukeList().size() + " tasks in the list" + line;
    }
}
