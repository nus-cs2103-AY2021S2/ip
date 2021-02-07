import java.time.LocalDate;

/**
 * Parser makes sense of the user command and act accordingly
 */
public class Parser {
    private String taskType;
    private String description;
    private LocalDate due;
    private int num;

    /**
     * Constructs an empty Parser
     */
    public Parser() {
        this.taskType = null;
        this.description = null;
        this.due = null;
        this.num = -1;
    }

    /**
     * Constructs a Parser with details on the Task of concern
     * @param taskType type of task
     * @param description description of task
     * @param due due date of task
     * @param num the index of the task of concern
     */
    private Parser(String taskType, String description, LocalDate due, int num) {
        this.taskType = taskType;
        this.description = description;
        this.due = due;
        this.num = num;
    }

    /**
     * Makes sense of the command entered by user
     * @param input Command entered by user
     * @return A Parser containing details of the task of concern
     */
    public Parser parse(String input) {
        try {
            String[] parts = input.split(" ");
            if (parts[0].equals("bye")) {
                return new Parser("bye", null, null, -1);
            } else if (parts[0].equals("list")) {
                return new Parser("list", null, null, -1);
            } else if (parts[0].equals("done")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The "
                            + "description of done cannot be empty.");
                }
                int taskDone = Integer.parseInt(parts[1]);
                return new Parser("done", null, null, taskDone);
            } else if (parts[0].equals("delete")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The "
                            + "description of delete cannot be empty.");
                }
                int taskToDelete = Integer.parseInt(parts[1]);
                return new Parser("delete", null, null, taskToDelete);
            } else if (parts[0].equals("find")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The "
                            + "description of find cannot be empty.");
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
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The "
                            + "description of a todo cannot be empty.");
                }
                String taskDescription = this.getDescription(parts, "\n");
                return new Parser("todo", taskDescription, null, -1);
            } else if (parts[0].equals("deadline")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The "
                            + "description of a deadline cannot be empty.");
                }
                LocalDate due = this.getDueDate(parts, "/by");
                String taskDescription = this.getDescription(parts, "/by");
                return new Parser("deadline", taskDescription, due, -1);
            } else if (parts[0].equals("event")) {
                if (parts.length == 1) {
                    throw new InsufficientArgumentsException("     ☹ OOPS!!! The "
                            + "description of an event cannot be empty.");
                }
                LocalDate due = this.getDueDate(parts, "/at");
                String taskDescription = this.getDescription(parts, "/at");
                return new Parser("event", taskDescription, due, -1);
            } else {
                throw new WrongArgumentException("     ☹ OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-(");
            }
        } catch (InsufficientArgumentsException e) {
            return new Parser("", e.getMessage(), null, -1);
        } catch (WrongArgumentException e) {
            return new Parser("", e.getMessage(), null, -1);
        }
    }

    /**
     * Gets the type for the task of concern
     * @param parts Array containing the command entered by the user
     * @param delimiter String used for identifying due date of the task
     * @return Description of the task
     */
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

    /**
     * Obtains the due date of the task of concern
     * @param parts Array containing the command entered by the user
     * @param delimiter String used for identifying due date of the task
     * @return Due date of the task of concern
     */
    private LocalDate getDueDate(String[] parts, String delimiter) {
        boolean flag = false;
        boolean first = true;
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

    /**
     * Returns a string about the type of task
     * @return Type of task
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns a string containing description of the task of concern
     * @return Description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the due date of the task of concern
     * @return Due date of the task
     */
    public LocalDate getDueDate() {
        return this.due;
    }

    /**
     * Returns the index of the task of concern within the TaskList
     * @return Index of the task of concern within the TaskList
     */
    public int getTaskIdx() {
        return this.num;
    }
}
