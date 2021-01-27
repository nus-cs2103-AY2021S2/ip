package tlylt.haha;

/**
 * Representation of task number error.
 */
public class HahaTaskNumberNotIntException extends HahaException {

    HahaTaskNumberNotIntException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Is that really a number?\nTry again with a proper task number!";
    }
}
