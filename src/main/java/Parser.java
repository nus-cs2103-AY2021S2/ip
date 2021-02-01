import java.time.LocalDate;

/**
 * Parser makes sense of the user command and act accordingly
 */
public class Parser {
    private String taskType;
    private String description;
    private LocalDate due;
    private int num;

    public Parser() {
        this.taskType = null;
        this.description = null;
        this.due = null;
        this.num = -1;
    }

    private Parser(String taskType, String description, LocalDate due, int num) {
        this.taskType = taskType;
        this.description = description;
        this.due = due;
        this.num = num;
    }

    public Parser parse(String input) {
        try {
            String[] parts = input.split(" ");
            if (parts[0].equals("bye")) {
                return new Parser("bye", null, null, -1);
            } else if (parts[0].equals("list")) {
                return new Parser("list", null, null, -1);
            } else if (parts[0].equals("done")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The " +
                            "description of done cannot be empty.");
                }
                int taskDone = Integer.parseInt(parts[1]);
                return new Parser("done", null, null, taskDone);
            } else if (parts[0].equals("delete")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The " +
                            "description of delete cannot be empty.");
                }
                int taskToDelete = Integer.parseInt(parts[1]);
                return new Parser("delete", null, null, taskToDelete);
            } else if (parts[0].equals("find")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The " +
                            "description of find cannot be empty.");
                }
                String toFind = "";
                for (int i = 1; i < parts.length; ++i) {
                    if (i != parts.length - 1) {
                        toFind += parts[i] + " ";
                    } else {
                        toFind += parts[i];
                    }
                }
                return new Parser("find", toFind, null, -1);
            } else if (parts[0].equals("todo")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The " +
                            "description of a todo cannot be empty.");
                }
                String taskDescription = this.getDescription(parts, "\n");
                return new Parser("todo", taskDescription, null, -1);
            } else if (parts[0].equals("deadline")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The " +
                            "description of a deadline cannot be empty.");
                }
                LocalDate due = this.getDueDate(parts, "/by");
                String taskDescription = this.getDescription(parts, "/by");
                return new Parser("deadline", taskDescription, due, -1);
            } else if (parts[0].equals("event")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The " +
                            "description of an event cannot be empty.");
                }
                LocalDate due = this.getDueDate(parts, "/at");
                String taskDescription = this.getDescription(parts, "/at");
                return new Parser("event", taskDescription, due, -1);
            } else {
                throw new WrongArgumentException("     ☹ OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-(");
            }
        } catch (InsufficientArgumentsException e) {
            return new Parser("", e.getMessage(), null, -1);
        } catch (WrongArgumentException e) {
            return new Parser("", e.getMessage(), null, -1);
        }
    }

    private String getDescription(String[] parts, String delimiter) {
        String description = "";
        boolean first = true;
        for (int i = 1; i < parts.length; ++i) {
            if (!parts[i].equals(delimiter)) {
                if (!first) {
                    description += " " + parts[i];
                } else {
                    description += parts[i];
                }
                first = false;
            } else {
                break;
            }
        }
        return description;
    }

    private LocalDate getDueDate(String[] parts, String delimiter) {
        boolean flag = false, first = true;
        String dueDate = "";
        for (String s : parts) {
            if (flag) {
                if (!first) {
                    dueDate += ' ' + s;
                } else {
                    dueDate += s;
                }
                first = false;
            }
            if (s.equals(delimiter)) {
                flag = true;
            }
        }
        return LocalDate.parse(dueDate);
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDueDate() {
        return this.due;
    }

    public int getTaskIdx() {
        return this.num;
    }
}
