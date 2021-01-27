public class InvalidArgumentException extends Exception {


    /**
     * Invalid argument exception for e.g. when an unapplicable command is used on an empty list
     * @param errMsg Error message to display
     */
    public InvalidArgumentException(String errMsg) {
        super(errMsg);
    }

    // not sure how to throw exception from helper method instead of constructor
    /*
    public static InvalidArgumentException invalidNumber(int i, int min, int max) {
        String errMsg = "Invalid argument given: " + i
                + ". Number needs to be between " + min + "and " + max + " (exclusive). ";
        return new InvalidArgumentException(errMsg);
    }
     */

//    public InvalidArgumentException invalidNumber(int i, int min, int max) {
//        super("Invalid argument given: " + i + ". Number needs to be between " + min + "and " + max + " (exclusive). ");
//    }
}
