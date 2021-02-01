package kobe;

public class Parser {

    /**
     * Reads and interprets the user input command to subsequently call
     * the addItem method in the Task class corresponding to the command.
     *
     * @param command  the error message
     * @param tasks  the current TaskList object
     * @param storage  the current Storage object
     * @param ui  the user interface to inform the user of the outcome
     */
    public static void readInput(String command, TaskList tasks, Storage storage, Ui ui) {
        String[] commandArr = command.split(" ");
        String text = commandArr[0];

//        switch (text) {
        if (text.equals("bye")) {
//            case "bye":
            Commands.goodbye(storage);
//            case "list":
        } else if (text.equals("list")) {
            Commands.showList(tasks, ui);
//            case "done":
        } else if (text.equals("done")) {
            int taskNumber = Integer.parseInt(commandArr[1]) - 1;
            tasks.completeTask(taskNumber, ui);
//            case "delete":
        } else if (text.equals("delete")) {
            int taskNumber = Integer.parseInt(commandArr[1]) - 1;
            tasks.deleteTask(taskNumber, ui);
//            default:
        } else {
            String taskName = "";
            String type = text;
            String condition = "";

            String[] commandArrFirst2Parts = command.split(" ", 2);
            String firstWord = commandArrFirst2Parts[0];

            if (commandArrFirst2Parts.length == 1) {
                if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                    String errMessage = "Oh no! Kobe doesn't want your " + firstWord + " to be empty!";
                    throw new CustomExceptions.IncompleteDecriptionException(errMessage);
                } else {
                    String errMessage = "Oh no! Kobe doesn't know what you mean!";
                    throw new CustomExceptions.IncorrectDecriptionException(errMessage);
                }
            }

            String[] commandArrSecond2Parts = commandArrFirst2Parts[1].split(" /", 2);

            taskName = commandArrSecond2Parts[0];

            //If the array is in 2 parts, there is a condition, add that
            if (commandArrSecond2Parts.length > 1) {
                condition = commandArrSecond2Parts[1].substring(3);
                //no "/by"
            }

            tasks.addItem(taskName, type, condition);
        }
    }

}