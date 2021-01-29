/**
 * This class processes the command by breaking down into components
 */
public class Parser {
    private String command;
    private String[] commandSeparate;
    private String taskType;
    private String taskName;
    private String date = "";

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
        parseTaskType();
        parseTaskName();
        parseTaskDate();
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

}
