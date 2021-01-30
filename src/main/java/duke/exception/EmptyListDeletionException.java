package duke.exception;

/**
 * Exception where user tries to delete item from an empty list.
 */


public class EmptyListDeletionException {
    /**
     * Method to throw the Exception.
     */
    public EmptyListDeletionException() {
        System.out.println("------------------------------------------\n"
            + "☹ OOPS!!! I'm sorry, but list is empty."
            + "You have to add an item before you can delete it.\n"
            + "------------------------------------------");
    }
}
