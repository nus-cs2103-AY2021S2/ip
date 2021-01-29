/**
 * This class processes the command by breaking down into components
 */
public class Parser {
    private String command;
    private String[] commandSeparate;
    private String taskType;
    private String taskName;
    private String date = "";
    private String index;

    Parser(String command) {
        this.command = command;
        commandSeparate = command.split(" ");
    }

    public void parseTaskType() {
        taskType = commandSeparate[0];
    }

    public void parseTaskName() {
        taskName = commandSeparate[1] + " " + commandSeparate[2];
    }

    public void parseTaskDate() {
        if (commandSeparate.length <= 3) {
            date = "";
        } else {
            for (int i = 4; i < commandSeparate.length; i++) {
                date += i == commandSeparate.length - 1
                        ? commandSeparate[i] : commandSeparate[i] + " ";
            }
        }
    }

    public void parse() {
        if (commandSeparate.length == 1) {
            parseTaskType();
        } else if (commandSeparate.length == 2) {
            parseTaskType();
            index = commandSeparate[1];
            taskName = commandSeparate[1];
        } else {
            parseTaskType();
            parseTaskName();
            parseTaskDate();
        }
    }

    public int getCommandLength() {
        return commandSeparate.length;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDate() {
        return date;
    }

    public String getIndex(){
        return index;
    }

}
