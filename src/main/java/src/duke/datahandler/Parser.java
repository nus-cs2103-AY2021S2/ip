package duke.datahandler;

import duke.enums.Command;
import duke.exception.TaskException;

public class Parser {
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
            String[] information = input.split(" ", 2);
            assert information.length > 0;
            this.command = Command.valueOf(information[0].toUpperCase());
            switch (this.command) {
                case FIND:
                    this.description = information[1];
                    break;
                case DELETE:
                case DONE:
                    this.taskIndex = Integer.parseInt(information[1]);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    ParseTaskHandler handleTaskInfo = new ParseTaskHandler(information);
                    this.description = handleTaskInfo.getDescription();
                    this.date = handleTaskInfo.getDate();

                    break;
                case BYE:
                case NONE:
                case LIST:
                default:
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
        return description.equals("DESCRIPTION.ERROR DATE.ERROR");
    }

    /**
     * checks if there is a lack of date input
     *
     * @return true if there is no date input false otherwise
     */
    public boolean hasDateError() {
        return !this.command.equals(Command.TODO) && this.date.equals("DATE.ERROR");
    }

    /**
     * checks if the bye input has been entered
     *
     * @return true if command is bye, false otherwise
     */
    public boolean isCommandBye() {
        return this.command.equals(Command.BYE);
    }

    public static void exception(String exceptionMessage) throws TaskException {
        throw new TaskException(exceptionMessage);
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
