package percy.command;

public class Parser {
    public static String fullCmd;

    public Parser(String fullCmd) {
        this.fullCmd =  fullCmd.trim().strip();
    }

    public Command getCommand() {
        String command = this.fullCmd.split(" ", 2)[0];
        switch (command) {
        case "todo":
            return new TodoCommand(getTodoDescription());
        case "event":
            return new EventCommand(getEventDescription(), getEventDate(), this.getEvent);
        case "deadline":
            return new DeadlineCommand(getDeadlineDescription(), getDeadlineDate());
        case "done":
            return new DoneCommand(this.getTaskNumber());
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(this.getTaskNumber());
        default:
            return new UnknownCommand();
        }
    }

    public static int getTaskNumber() {
        String[] splitCommand = fullCmd.split(" ", 2);
        String TaskNumber = splitCommand[1].trim();
        return Integer.valueOf(TaskNumber);
    }

    public static String getTodoDescription()  {
        String[] splitCommand = fullCmd.split(" ", 2);
        String description = "";
        if (splitCommand.length == 2) {
            description = splitCommand[1].trim();
        }
        return description;
    }

    public static String getEventDescription() {
        String[] splitCommand = fullCmd.split(" ", 2);
        String description = splitCommand[1]
                                .substring(0, splitCommand[1].indexOf("/"))
                                .trim();
        return description;
    }

    public static String getEventDate() {
        String[] splitCommand = fullCmd.split(" ", 2);
        String date = splitCommand[1]
                .substring(splitCommand[1].indexOf("/") + 4, splitCommand[1].length())
                .trim();
        return date;
    }

    public static String getDeadlineDescription() { // same as EventDescription
        String[] splitCommand = fullCmd.split(" ", 2);
        String description = splitCommand[1]
                .substring(0, splitCommand[1].indexOf("/"))
                .trim();
        return description;
    }

    public static String getDeadlineDate() { // same as getEventDate
        String[] splitCommand = fullCmd.split(" ", 2);
        String date = splitCommand[1]
                .substring(splitCommand[1].indexOf("/") + 4, splitCommand[1].length())
                .trim();
        return date;
    }
}
