public class DukeInvalidDesException extends DukeException{
    private String keyword;
    private String message;

    /**
     * Constructor for DukeException for invalid descriptions.
     * @param keyword String keyword of input.
     */
    public DukeInvalidDesException(String keyword) {
        super("This is a Duke missing description exception.");
        this.keyword = keyword;
        message = super.getMessage() + " ";
        switch(keyword) {
        case "DONE":
            message += "Your request for DONE is invalid.\n"
                    + "Have you specified a valid task number?";
            break;
        case "DEADLINE":
            message += "Your request for DEADLINE is invalid.\n"
                    + "Have you specified a /by ?";
            break;
        case "EVENT":
            message += "Your request for EVENT is invalid.\n"
                    + "Have you specified an /at ?";
            break;
        case "DELETE":
            message += "Your request for DELETE is invalid.\n"
                + "Have you specified an existing task number?";
            break;
        default:
            message += "Your description is invalid.";
        }
    }

    /**
     * Returns message of the invalid description exception.
     * @return Message of the invalid description exception.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
