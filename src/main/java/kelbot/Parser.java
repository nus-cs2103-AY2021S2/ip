package kelbot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private Command command;
    private Integer taskNumber = Integer.MAX_VALUE;
    private String keyword = "";
    private String taskName = "";
    private String tagName = "";
    private LocalDate date = null;
    private boolean isValid = true;
    /**
     * Initializes Parser.
     * @param input The input given by the user.
     * @throws DateTimeParseException if the date is invalid.
     */
    public Parser(String input) throws DateTimeParseException {
        String[] commands = input.split(" ");
        if (input.equals("bye")) {
            command = Command.BYE;
        } else if (input.equals("list")) {
            command = Command.LIST;
        } else if (commands[0].equals("tag")) {
            command = Command.TAG;
            taskNumber = Integer.parseInt(commands[1]) - 1;
            tagName = commands[2];
        } else if (commands[0].equals("done")) {
            command = Command.DONE;
            taskNumber = Integer.parseInt(commands[1]) - 1;
        } else if (commands[0].equals("delete")) {
            command = Command.DELETE;
            taskNumber = Integer.parseInt(commands[1]) - 1;
        } else if (commands[0].equals("find")) {
            command = Command.FIND;
            for (int i = 1; i < commands.length; i++) {
                keyword += " " + commands[i];
            }
        } else if (commands[0].equals("snooze")) {
            command = Command.SNOOZE;
            taskNumber = Integer.parseInt(commands[1]) - 1;
            date = LocalDate.parse(commands[2]);
        } else if (commands[0].equals("todo")) {
            command = Command.TODO;
            for (int i = 1; i < commands.length; i++) {
                taskName += " " + commands[i];
            }
        } else if (commands[0].equals("trivia")) {
            command = Command.TRIVIA;
            for (int i = 1; i < commands.length; i++) {
                taskName += " " + commands[i];
            }
        } else if (commands[0].equals("deadline")) {
            command = Command.DEADLINE;
            String deadlineDate = "";
            for (int i = 1; i < commands.length; i++) {
                if (commands[i].equals("/by")) {
                    for (int j = i + 1; j < commands.length; j++) {
                        deadlineDate += commands[j];
                    }
                    break;
                }
                taskName += " " + commands[i];
            }
            date = LocalDate.parse(deadlineDate);
        } else if (commands[0].equals("event")) {
            command = Command.EVENT;
            String eventDate = "";
            for (int i = 1; i < commands.length; i++) {
                if (commands[i].equals("/at")) {
                    for (int j = i + 1; j < commands.length; j++) {
                        eventDate += commands[j];
                    }
                    break;
                }
                taskName += " " + commands[i];
            }
            date = LocalDate.parse(eventDate);
        } else {
            isValid = false;
        }
    }
    public Command getCommand() {
        return command;
    }
    public String getTaskName() {
        return taskName;
    }
    public String getKeyword() {
        return keyword;
    }
    public String getTagName() {
        return tagName;
    }
    public Integer getTaskNumber() {
        return taskNumber;
    }
    public LocalDate getDate() {
        return date;
    }
    public boolean getIsValid() {
        return isValid;
    }
}
