package duke;

import duke.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.ui.TextUi;

public class Momo {

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Momo with an original task list if file path exists.
     * Creates a Momo with empty task list else.
     *
     * @param filePath the path of the *.txt file including information of existing tasks.
     */
    public Momo(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ParseException | IOException e) {
            tasks = new TaskList();
        }
    }


    /**
     * Get responses from Momo using the input of users
     *
     * @param input input of user
     * @return answer of Momo
     */
    public String getResponse(String input) {
        try {
            int endOfFirstWord = input.indexOf(' ');
            String restInput = input.substring(endOfFirstWord + 1);
        } catch (StringIndexOutOfBoundsException e) {
            try {
                Parser.parseCommand(input);
            } catch (IllegalArgumentException e1) {
                return TextUi.showGeneralError();
            }
            Command command = Parser.parseCommand(input);
            switch (command) {
            case BYE:
                return TextUi.showExitUi();
            case LIST:
                return TextUi.showList(this.tasks);
            default:
                return TextUi.showGeneralError();
            }
        }
        try {
            int endOfFirstWord = input.indexOf(' ');
            assert endOfFirstWord < input.length() : "Doesn't handle the input not containing space";
            String commandInput = input.substring(0, endOfFirstWord);
            String restInput = input.substring(endOfFirstWord + 1);
            System.out.println(restInput);
            Command command = Parser.parseCommand(commandInput);
            switch (command) {
            case DONE:
                int i = Integer.parseInt(restInput);
                System.out.println(i);
                try {
                    return TextUi.showSuccessfulMark(tasks.mark(i));
                } catch (IndexOutOfBoundsException e) {
                    return TextUi.showIndexOutOfBoundsError(tasks);
                }
            case DELETE:
                int j = Integer.parseInt(restInput);
                try {
                    Task taskToBeDeleted = tasks.delete(j);
                    storage.save(tasks);
                    return TextUi.showSuccessfulDelete(tasks, taskToBeDeleted);
                } catch (IndexOutOfBoundsException e) {
                    return TextUi.showIndexOutOfBoundsError(tasks);
                }
            case FIND:
                return TextUi.showMatchingResult(tasks.find(restInput));
            default:
                try {
                    Task taskToBeAdded = Parser.parseDescription(command, restInput);
                    tasks.add(taskToBeAdded);
                    storage.save(tasks);
                    return TextUi.showSuccessfulAdd(tasks, taskToBeAdded);
                } catch (ParseException e) {
                    return TextUi.formatInChatBox(e.getMsgDes());
                } catch (DateTimeParseException e) {
                    return TextUi.showDateParseError();
                }
            }
        } catch (IllegalArgumentException e) {
            return TextUi.showGeneralError();
        }
    }

    public static void main(String[] args) {
        Momo momo = new Momo("data/tasks.txt");
        System.out.println(momo.getResponse("done 2\n"));
    }
}
