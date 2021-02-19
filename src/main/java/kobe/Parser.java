package kobe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Reads and interprets the user input command to subsequently call
     * the addItem method in the Task class corresponding to the command.
     *
     * @param command the error message
     * @param tasks   the current TaskList object
     * @param storage the current Storage object
     * @param ui      the user interface to inform the user of the outcome
     */
    public static void readInput(String command, TaskList tasks, Storage storage, Ui ui) {
        String[] commandArr = command.split(" ");
        String text = commandArr[0];

        try {
            if (text.equals("bye")) {
                Commands.goodbye(storage);
            } else if (text.equals("help")) {
                Commands.getHelp();
            } else if (text.equals("list")) {
                Commands.showList(tasks);
            } else if (text.equals("done")) {
                int taskNumber = Integer.parseInt(commandArr[1]) - 1;
                tasks.completeTask(taskNumber);
            } else if (text.equals("delete")) {
                int taskNumber = Integer.parseInt(commandArr[1]) - 1;
                tasks.deleteTask(taskNumber);
            } else if (text.equals("find")) {
                String keyword = commandArr[1];
                tasks.findTask(keyword);
            } else {
                String taskName = "";
                String type = text;
                String condition = "";

                //Isolating the command type, which is the first word
                String[] commandArrFirst2Parts = command.split(" ", 2);
                String firstWord = commandArrFirst2Parts[0];

                //Error Handling for wrong commands
                if (commandArrFirst2Parts.length == 1) {
                    //For incomplete commands
                    if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                        Ui.addIncompleteDescriptionResponse();
                        String errMessage = "Incomplete Description: Command " + firstWord + " does not have any name";
                        throw new CustomExceptions.IncompleteDecriptionException(errMessage);
                    } else {
                        //For gibberish commands
                        assert !firstWord.equals("todo") : "Command word should be gibberish and not be todo";
                        assert !firstWord.equals("deadline") : "Command word should be gibberish and not be deadline";
                        assert !firstWord.equals("event") : "Command word should be gibberish and not be event";
                        Ui.addIncorrectDescriptionResponse();
                        String errMessage = "Incorrect Command Format. See help for more information";
                        throw new CustomExceptions.IncorrectDecriptionException(errMessage);
                    }
                } else if (commandArrFirst2Parts.length == 2) {
                    //Check if the second word is just whitespace
                    //If it is, count it as an error as well
                    String secondWord = commandArrFirst2Parts[1];
                    boolean isWhitespacePresent = secondWord.isBlank() || secondWord.equals("");
                    if (isWhitespacePresent) {
                        Ui.addWhitespaceResponse();
                        String errMessage = "Incorrect Command Format: Extra whitespace detected. See help for more information";
                        throw new CustomExceptions.IncorrectDecriptionException(errMessage);
                    }
                }

                //Isolate the task name
                String[] commandArrSecond2Parts = commandArrFirst2Parts[1].split(" /", 2);
                taskName = commandArrSecond2Parts[0];

                //Isolate the condition if there is any
                if (commandArrSecond2Parts.length > 1) {
                    condition = commandArrSecond2Parts[1].substring(3);
                    //no "/by"
                }

                //Add the task accordingly
                tasks.addItem(taskName, type, condition);
            }
        } catch (CustomExceptions.IncompleteDecriptionException e) {
            e.printStackTrace(); //Unwraps cause within InvocationTargetException
            //Since the reflection layer will wrap any exception in an InvocationTargetException
        } catch (CustomExceptions.IncorrectDecriptionException e) {
            e.printStackTrace();
        }

    }
}