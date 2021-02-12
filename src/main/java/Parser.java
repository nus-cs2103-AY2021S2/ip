/**
 * Parser helps to extract the information in txt files stored in strings into
 * a desired format.
 */
public class Parser {

    /**
     * Takes in a line from the txt file and outputs the description of a Todo object.
     *
     * @param str The line read in from the txt file.
     * @return a string that is the description of the Todo object.
     */
    public String parseTodoDescription(String str) {
        return str.substring(str.indexOf("  ") + 2);
    }

    /**
     * Takes in a line from the txt file and outputs the description of
     * a Event or Deadline object.
     *
     * @param str The line read in from the txt file.
     * @return a string that is the description of a Event or Deadline object.
     */
    public String parseDeadlineEventDescription(String str) {
        return str.substring(str.indexOf("  ") + 2, str.indexOf(" (") + 1);
    }

    /**
     * Takes in a line from the txt file and outputs the date of a Deadline object
     * as a string.
     *
     * @param str The line read in from the txt file.
     * @return a string that is the date of a Deadline object.
     */
    public String parseDateTimeDeadline(String str) {
        return str.substring(str.indexOf("by: ") + 4, str.indexOf(")"));
    }

    /**
     * Takes in a line from the txt file and outputs the date of a Event object
     * as a string.
     *
     * @param str The line read in from the txt file.
     * @return a string that is the date of an Event object.
     */
    public String parseDateTimeEvent(String str) {
        return str.substring(str.indexOf("at: ") + 4, str.indexOf(")"));
    }
}
