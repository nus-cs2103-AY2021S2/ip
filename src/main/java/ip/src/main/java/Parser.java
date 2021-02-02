package ip.src.main.java;

/**
 * Parser is a class that parsers user input to retrieve user commands, information to create Task objects etc.
 *
 */

public class Parser {

    Parser(){
    }

    /**
     * Retrieves the command entered by the user.
     *
     * @param input Input entered by user.
     * @return Returns the first word that represents the command given by the user.
     */
    public String getCommand(String input) {
        String command = input.split(" ")[0];
        return command;
    }

    /**
     * Retrieves the information needed from the user input for a ToDo object.
     *
     * @param input User input.
     * @return String content needed to create a ToDo Object.
     */

    public String toDoTask(String input){
        input = input.split(" ", 2)[1];
        return input;
    }

    /**
     * Retrieves the information needed from the user input for a Event object.
     *
     * @param input User input.
     * @return String content needed to create a Event Object.
     */

    public String eventTaskContent(String input) {
        input = input.split(" ", 2)[1];
        String content = input.split("/at")[0];
        return content;
    }

    /**
     * Retrieves the information needed from the user input for a Event object.
     *
     * @param input User input.
     * @return String date needed to create a Event Object.
     */

    public String eventTaskAt(String input) {
        input = input.split(" ", 2)[1];
        String at = input.split("/at")[1];
        return at;
    }

    /**
     * Retrieves the information needed from the user input for a Deadline object.
     *
     * @param input User input.
     * @return String content needed to create a Deadline Object.
     */

    public String deadlineTaskContent(String input) {
        input = input.split(" ", 2)[1];
        String content = input.split("/by")[0];
        return content;
    }

    /**
     * Retrieves the information needed from the user input for a Deadline object.
     *
     * @param input User input.
     * @return String date needed to create a Deadline Object.
     */

    public String deadlineTaskBy(String input) {
        input = input.split(" ", 2)[1];
        String by = input.split("/by")[1];
        return by;
    }

    /**
     * Gives the ID (position) of the task to be manipulated in the TaskList.
     *
     * @param input User input.
     * @return Integer ID that represents the position (1-th based) of the task in the TaskList.
     */

    public int getId(String input){
        int id = Integer.valueOf(input.split(" ")[1]);
        return id;
    }

    /**
     * Retrieves the keyword from the user input to for the find task method.
     *
     * @param input User input.
     * @return The keyword used to find the matching tasks in the Task list.
     */

    public String getKeyword(String input){
        String keyword = input.split(" ", 2)[1];
        return keyword;
    }



}
