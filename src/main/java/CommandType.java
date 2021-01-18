
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum CommandType {
    ADD_EVENT("^event\\s*(.+)"),
    ADD_DEADLINE("^deadline\\s*(.*)"),
    ADD_TODO("^todo\\s*(.*)"),
    MARK_AS_DONE("^done\\s*(.+)"),
    REMOVE_TASK("^delete\\s*(.+)");

    protected final Pattern patternToMatch;

    CommandType(String patternToMatch) {
        this.patternToMatch = Pattern.compile(patternToMatch);
    }
    public boolean isMatchingInput(String input) {
        Matcher m = patternToMatch.matcher(input);
        return (m.matches());
    }



}
