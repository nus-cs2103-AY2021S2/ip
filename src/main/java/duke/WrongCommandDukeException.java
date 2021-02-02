package duke;

public class WrongCommandDukeException extends DukeException {

    /**
     * Returns the string representation of an exception where an invalid command is given.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "I am unable to comprehend what you have just said. I deeply regret my inadequecy.";
    }
}
