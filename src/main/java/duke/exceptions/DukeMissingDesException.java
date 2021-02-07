package duke.exceptions;

public class DukeMissingDesException extends DukeException {
    private String keyword;
    private String message;

    /**
     * Constructor for DukeException for missing descriptions.
     * @param keyword String keyword of input.
     */
    public DukeMissingDesException(String keyword) {
        super("This is a Duke missing description exception.");
        this.keyword = keyword;
        message = super.getMessage() + " ";
        switch(keyword) {
        case "DONE":
            message += "Your request for DONE is empty.\n"
                    + "Have you specified a task number?";
            break;
        case "DELETE":
            message += "Your request for DELETE is empty.\n"
                    + "Have you specified a task number?";
            break;
        case "DEADLINE":
            message += "Your request for DEADLINE is empty.\n"
                    + "Have you specified a task description?";
            break;
        case "EVENT":
            message += "Your request for EVENT is empty.\n"
                    + "Have you specified a task description?";
            break;
        case "TODO":
            message += "Your request for TODO is empty.\n"
                    + "Have you specified a task description?";
            break;
        default:
            message += "Your request is missing a description.";
        }
    }

    /**
     * Returns message of the missing description exception.
     * @return Message of the missing description exception.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
