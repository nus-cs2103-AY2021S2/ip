package chat.task;

import chat.Chat;
import chat.ChatException;

/**
 * Todo is type of <b>task</b>. 
 */
public class Todo extends Task {

    /**
     * Initialises Todo object.
     * <p>Boolean isDone has been set to false by default.</p>
     * 
     * @param name Name or description of the task.
     */
    private Todo (String name) {
        super(name);
    }

    /**
     * Initialises Todo object.
     *
     * @param isDone Boolean that tells if task is completed.
     * @param name Name or description of the task.
     */
    public Todo (boolean isDone, String name) {
        super(isDone, name);
    }

    /**
     * Static method that creates a Todo object.
     * 
     * @param str Inputted command string from user to Chat the Cat.
     * @return Todo Object.
     * @throws ChatException If format of command is wrong.
     */
    public static Todo createTodo (String str) throws ChatException {
        String formatStr = "Please input with this format:\ntodo [name]";
        try {
            checkWrongCommand(str);
            checkNoName(str);
            checkNoSpaceBetweenTodoAndName(str);
            return new Todo(str.replace("todo ", "").strip());
        } catch (ChatException e) {
            throw new ChatException(e.getMessage() + formatStr);
        }
    }

    /**
     * Checks if wrong command is given by user. 
     * 
     * @param str User's input.
     * @throws ChatException ChatException thrown if wrong command is given.
     */
    private static void checkWrongCommand(String str) throws ChatException {
        if (!str.startsWith("todo")) {
            //i.e. deadline
            throw new ChatException("wrong instruction for todo\n");
        }
    }

    /**
     * Checks for empty name.
     * 
     * @param str User's input. 
     * @throws ChatException ChatException thrown if empty name.
     */
    private static void checkNoName(String str) throws ChatException {
        if (str.strip().equals("todo")) {
            //i.e. todo
            //i.e. todo(followed by one or more empty spaces)
            throw new ChatException("todo name missing\n");
        }
    }

    /**
     * Checks for no space between todo and name.
     * 
     * @param str User's input.
     * @throws ChatException ChatException thrown if no space between todo and name.
     */
    private static void checkNoSpaceBetweenTodoAndName(String str) throws ChatException {
        if (!str.startsWith("todo ")) {
            //i.e. todoread book
            throw new ChatException("no spacing after todo\n");
        }
    }
    
    /**
     * Returns a comma separated string of all parameters.
     *
     * @return Comma separated string with all parameters listed out.
     */
    public String allParameterStr() { 
        return String.format("T,%s,%s", this.getIsDone(), this.getName());
    }

    /**
     * Returns a string that shows the details of the task.
     * <p>[ ] will be displayed if isDone = false.</p>
     * <p>[X] will be displayed if isDone = true.</p>
     *
     * @return String showing details of task, i.e. [T][ ] name.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
