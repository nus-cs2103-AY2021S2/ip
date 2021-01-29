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
        commandSeparate = this.command.split(" ");
        process();
    }

    public void process() {
        this.taskType = commandSeparate[0];

        if (commandSeparate.length > 1) {
            this.taskName = commandSeparate.length <= 3 ? commandSeparate[1] :
                    commandSeparate[1] + " " + commandSeparate[2];
            if (commandSeparate.length > 3) {
                for (int i = 3; i < commandSeparate.length; i++) {
                    date += commandSeparate[i] + " ";
                }
            }
        }

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
