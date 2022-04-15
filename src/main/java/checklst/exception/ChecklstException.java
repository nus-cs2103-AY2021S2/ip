package checklst.exception;

/**
 * Custom exception to represent Exceptions in the Checklst program.
 */
public class ChecklstException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4447981462872622989L;

    /**
     * Creates instance for Checklst Exceptions.
     * @param errMessage Error message to be displayed.
     */
    public ChecklstException(String errMessage) {
        super(errMessage);
    }

}
