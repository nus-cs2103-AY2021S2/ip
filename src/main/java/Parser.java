public class Parser {
    private final String input;
    private String parsedCommand = "";
    private String taskName = "";
    private String additionals = "";

    Parser(String input) {
        this.input = input;
        if (isListCommand(input)) {
            parsedCommand = "LIST";
        } else if (isDoneCommand(input)) {
            parsedCommand = "DONE";
            additionals = String.valueOf(input.charAt(5));
        } else if (isTask(input)) {
            if (isTodo(input)) {
                parsedCommand = "TODO";
                taskName = input.substring(5);
            } else if (isDeadline(input)) {
                parsedCommand = "DEADLINE";
                int byIndex = input.indexOf(" /by ");
                taskName = input.substring(9, byIndex);
                String ddl = input.substring(byIndex + 5);
                additionals = ddl;
            } else if (isEvent(input)) {
                parsedCommand = "EVENT";
                int atIndex = input.indexOf(" /at ");
                taskName = input.substring(6, atIndex);
                String date = input.substring(atIndex + 5);
                additionals = date;
            }
        }
    }

    public String getParsedCommand() {
        return parsedCommand;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getAdditionals() {
        return additionals;
    }

    private static boolean isListCommand(String sentence) {
        return sentence.equals("list");
    }

    private static boolean isDoneCommand(String sentence) {
        return (sentence.length() > 5 && sentence.substring(0, 5).equals("done ")
                && Character.isDigit(sentence.charAt(5)));
    }

    private boolean isTask(String sentence) {
        return (isTodo(sentence) || isDeadline(sentence) || isEvent(sentence));
    }

    private boolean isTodo(String sentence) {
        return (sentence.length() > 5 && sentence.substring(0, 5).equals("todo "));
    }

    private boolean isDeadline(String sentence) {
        return (sentence.length() > 9 && sentence.substring(0, 9).equals("deadline "));
    }

    private boolean isEvent(String sentence) {
        return (sentence.length() > 6 && sentence.substring(0, 6).equals("event "));
    }
}
