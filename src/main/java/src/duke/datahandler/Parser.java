package duke.datahandler;

import duke.enums.Command;
import duke.exception.TaskException;

/**
 * The parser object reads and deconstructs the user input into useful parts
 * that can be processed by other handlers.
 */
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
            case SNOOZE:
                this.taskIndex = Integer.parseInt(information[1].split(" ", 2)[0]);
                this.date = information[1].split(" ", 3)[2];

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
        return description.equals("DESCRIPTION.ERROR DATE.ERROR") || description.equals("");
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

    /**
     * throws a task exception for when the parser has a task exception
     * @param exceptionMessage the message to be passed to the task exception
     * @throws TaskException the exception that will be thowen by parser
     */
    public static void exception(String exceptionMessage) throws TaskException {
        throw new TaskException(exceptionMessage);
    }

    /**
     * returns task index the user has inputted
     * @return inputted task index
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * gets the command the user has inputted
     * @return inputted command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * gets the date inputted by the user
     * @return inputted date
     */
    public String getDate() {
        return date;
    }

    /**
     * gets the task description inputted by the user
     * @return inputted task description
     */
    public String getDescription() {
        return description;
    }
}
