package duke;

public class Parser {
    private String[] information;
    private String date;
    private Command command;
    private String description;
    private int taskIndex;

    public Parser(String input) {
        try {
            this.information = input.split(" ", 2);
            this.command = Command.valueOf(this.information[0].toUpperCase());

            switch (this.command) {
                case FIND:
                    this.description = this.information[1];
                    break;
                case DELETE:
                case DONE:
                    this.taskIndex = Integer.parseInt(this.information[1]);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    String[] descriptionAndTime = information.length < 2 ? null : information[1].split("/", 2);
                    this.description = descriptionAndTime == null ? "DESCRIPTION.ERROR" : descriptionAndTime[0];
                    this.date = descriptionAndTime == null || descriptionAndTime.length < 2 ? "DATE.ERROR"
                            : descriptionAndTime[1].split(" ", 2)[1];

                    break;
                case BYE:
                case NONE:
                case LIST:
            }
        } catch (IllegalArgumentException e) {
            this.command = Command.NONE;
        }

    }

    public boolean hasDescriptionError() {
        return this.description.equals("DESCRIPTION.ERROR");
    }

    public boolean hasDateError() {
        return this.date.equals("DATE.ERROR");
    }

    public boolean isCommandNotBye() {
        return !this.command.equals(Command.BYE);
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public Command getCommand() {
        return command;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
