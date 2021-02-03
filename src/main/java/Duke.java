import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import duke.exception.NoCommandException;
import duke.exception.NoMeaningException;
import duke.util.*;
import duke.command.Command;

public class Duke {
    private static String FILE_PATH = "./data/";
    private static String FILE_NAME = "history.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    private Duke(String filePath, String fileName) {
        ui = new Ui();
        storage = new Storage(filePath, fileName);
        taskList = new TaskList(storage.readPreviousFile());
    }

    public static Duke start(){
        return new Duke(FILE_PATH, FILE_NAME);
    }

    /**
     * Process command given by user.
     */
    public String doCommand(String sentence) {
        try {
            Command command = Parser.parseCommand(sentence, taskList);
            return command.execute(storage, taskList, ui, sentence);
        } catch (NoCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Process command list given by user.
     */
    private String doList() {
        String result = "";
        for (int i=0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            result += ui.getList(i, task);
            result += "\n";
        }
        return result;
    }

    /**
     * Process command done given by user.
     * @param index Index of task from TaskList
     */
    private String doDone(int index) {
        try {
            Task currTask = taskList.get(index - 1);
            currTask = currTask.doTask();
            taskList.set(index - 1, currTask);
            return ui.getDoneSuccess(currTask);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return ui.getDoneFail();
        }
    }

    /**
     * Process command delete given by user.
     * @param index Index of task from TaskList
     */
    private String doDelete(int index) {
        try {
            Task currTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            return ui.getDeleteSuccess(currTask, taskList.size());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return ui.getDeleteFail();
        }
    }

    /**
     * Process command find given by user.
     * @param word String inputted by user.
     */
    private String doFind(String word){
        try {
            String toSearch = word.substring(5);
            List<Task> searchedTaskList = taskList.find(toSearch);
            return ui.getFindSuccess(searchedTaskList);
        } catch (StringIndexOutOfBoundsException e) {
            return ui.getFindFail(new NoMeaningException(
                    "☹ OOPS!!! The description of a find cannot be empty."));
        }
    }

    /**
     * Process task type command given by user.
     * @param word the whole sentences entered by the user
     */
    private String doTask(String word) {
        try {
            String[] tmp = word.split(" ");
            String command = tmp[0];
            if (command.equals("todo")) {
                return doToDo(word);
            } else if (command.equals("deadline")) {
                return doDeadline(word);
            } else if (command.equals("event")) {
                return doEvent(word);
            } else {
                throw new NoMeaningException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (NoMeaningException e){
            return ui.getTaskFail(e);
        }
    }

    /**
     * Process task command todo given by user.
     * @param word the whole sentences entered by the user
     */
    private String doToDo(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(5);
            ToDo todo = new ToDo(realWord);
            taskList.add(todo);
            return doTaskFinally(todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException(
                    "☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Process task command deadline given by user.
     * @param word the whole sentences entered by the user
     */
    private String doDeadline(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(9);
            String[] deadlineWords = realWord.split("/by");
            String deadlineWord = deadlineWords[0];
            String deadlineTime = deadlineWords[1];

            String[] deadlineDateHours = deadlineTime.split(" ");
            LocalDate deadlineDate = LocalDate.parse(deadlineDateHours[1]);
            LocalTime deadlineHour = LocalTime.parse(deadlineDateHours[2]);
            Deadline deadline = new Deadline(deadlineWord, deadlineDate, deadlineHour);
            taskList.add(deadline);
            return doTaskFinally(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException(
                    "☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Process task command event given by user.
     * @param word the whole sentences entered by the user
     */
    private String doEvent(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(6);
            String[] eventWords = realWord.split("/at");
            String eventWord = eventWords[0];
            String eventTime = eventWords[1];

            String[] eventDateHours = eventTime.split(" ");
            LocalDate eventDate = LocalDate.parse(eventDateHours[1]);
            LocalTime eventHour = LocalTime.parse(eventDateHours[2]);
            Event event = new Event(eventWord, eventDate, eventHour);
            taskList.add(event);
            return doTaskFinally(event);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException(
                    "☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    /**
     * Process task typed command after all the above functions.
     * @param task Task to print
     */
    private String doTaskFinally(Task task) {
        return ui.getTaskFinally(task, taskList.size());
    }
}
