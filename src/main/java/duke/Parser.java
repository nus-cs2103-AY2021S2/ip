package duke;

public class Parser {
    private String[] information;
    private String date;
    private Command command;
    private String description;
    private int taskIndex;

    /**
     * contructs a Parser object that breaksdown user input to useful parts relevant
     * to execution of commands
     * 
     * @param input user input
     */
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

    /**
     * checks whether there is a lack of decription
     * 
     * @return true if there is no description false otherwise
     */
    public boolean hasDescriptionError() {
        return this.description.equals("DESCRIPTION.ERROR");
    }

    /**
     * checks if there is a lack of date input
     * 
     * @return true if there is no date input false otherwise
     */
    public boolean hasDateError() {
        return this.date.equals("DATE.ERROR");
    }

    /**
     * checks if the bye input has been entered
     * 
     * @return false if command is bye, true otherwise
     */
    public boolean isCommandNotBye() {
        return !this.command.equals(Command.BYE);
    }

    /**
     * returns the index of the task that user is interested in
     * 
     * @return index of task
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * returns the command that the user is interested in executing
     * 
     * @return user command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * returns the date of task
     * 
     * @return date of task
     */
    public String getDate() {
        return date;
    }

    /**
     * returns description of task
     * 
     * @return description of task
     */
    public String getDescription() {
        return description;
    }
}
