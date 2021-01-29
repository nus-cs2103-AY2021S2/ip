package duke;

import duke.task.Task;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingTaskNumberException;

import java.util.List;

public class Parser {
    protected static String task;
    protected String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public String[] parseUserInput() {
        return userInput.split(" ");
    }

    public String getUserAction() {
        return userInput.split(" ")[0];
    }

    public void checkUserInput(List<Task> list)
            throws MissingTaskNumberException, InvalidTaskNumberException, EmptyDescriptionException {
        String[] userInputArr = userInput.split(" ");
        String task = userInputArr[0];

        switch(task) {
        case "todo":
        case "deadline":
        case "event":
            if (userInputArr.length == 1) {
                throw new EmptyDescriptionException(task);
            }
            break;

        case "done":
        case "delete":
            if (userInputArr.length == 1) {
                throw new MissingTaskNumberException();
            }

            int taskNumber = Integer.parseInt(userInputArr[1]) - 1;
            if (taskNumber < 0 || taskNumber > list.size() - 1) {
                throw new InvalidTaskNumberException();
            }
            break;
        }
    }
}
