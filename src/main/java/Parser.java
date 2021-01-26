public class Parser {

    public String parseTodoDescription(String str) {
        return str.substring(str.indexOf("  ") + 2);
    }

    public String parseDeadlineEventDescription(String str) {
        return str.substring(str.indexOf("  ") + 2, str.indexOf(" (") + 1);
    }

    public String parseDateTimeDeadline(String str) {
        return str.substring(str.indexOf("by: ") + 4, str.indexOf(")"));
    }

    public String parseDateTimeEvent(String str) {
        return str.substring(str.indexOf("at: ") + 4, str.indexOf(")"));
    }
}
