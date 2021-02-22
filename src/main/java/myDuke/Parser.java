package myDuke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A parser that deals with making sense of the user command and handles parsed input.
 */
public class Parser {

    String input;
    Storage storage;
    TaskList tasks;
    Ui ui;

    Parser(Storage storage, TaskList tasks, Ui ui) {
        this.input = "";
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    public Parser(String input, Storage storage, TaskList tasks, Ui ui) {
        this.input = input;
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses the next input to create a new Parser object.
     * @param input next String input to be parsed.
     * @return Parser object containing the new input.
     */
    public Parser parseNextInput(String input) {
        return new Parser(input, storage, tasks, ui);
    }

    /**
     * Checks for loop terminating condition in main method (if input is "bye")
     * @return boolean result of input.equals("bye").
     */
    public boolean isGoodbye() {
        return this.input.equals("bye");
    }

    /**
     * Splits user input into String arrays with the first two instance space " " in the input.
     * @return String array of parsed input.
     */
    String[] getParsedInput() {
        return this.input.split(" ", 2);
    }

    /**
     * Handles the input according to instructions stated by the input, utilises the ui methods to print
     * respective response messages as output to the users (eg. when list is empty, when there are caught exceptions).
     */
    public void handleInput() {
        switch (getParsedInput()[0]) {
        case "list":
            int counter = 1;
            String[] tempArr = new String[100];
            if (tasks.getTaskList().isEmpty()) { // improved implementation in case list is empty, gives a clear output
                ui.showListEmptyMsg();
            } else {
                for (Task t : tasks.getTaskList()) { // changed String s to Task t
                    tempArr[counter - 1] = counter + ". " + t.toString();
                    counter++;
                }
                ui.printTasksInList(tempArr);
            }
            break;
        case "done":
            try {
                MyDuke.indexChecker(getParsedInput());
                int ref = Integer.parseInt(getParsedInput()[1]);
                tasks.getTaskList().set(ref - 1, tasks.getTaskList().get(ref - 1).setAsDone());
                ui.printDoneTaskAlert(tasks.getTaskList().get(ref - 1).toString());
            } catch (NoIndexException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printErrorMsg("Paikia Bot: the number that you inputted is invalid leh (more than the "
                        + "total number of tasks or less than 1), try again pls ah");
            } catch (NumberFormatException e) {
                ui.printErrorMsg("Paikia Bot: ur input after 'done' is invalid, reminder that it should be"
                        + " a single integerand remember to not leave a space after your input. eg, done 3");
            }
            break;
        case "delete":
            try {
                MyDuke.indexChecker(getParsedInput());
                int ref = Integer.parseInt(getParsedInput()[1]);
                Task toRemove = tasks.getTask(ref);
                tasks.deleteTask(ref - 1);
                ui.printDeletedTaskAlert(toRemove.toString(), tasks.getTaskList().size());
            } catch (NoIndexException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printErrorMsg("Paikia Bot: the number that you inputted is invalid leh (more than the "
                        + " total number of tasks or less than 1), try again pls ah");
            } catch (NumberFormatException e) {
                ui.printErrorMsg("Paikia Bot: ur input after 'done' is invalid, reminder that it should be a "
                        + "single integer and remember to not leave a space after your input. eg, done 3");
            }
            break;
        case "todo":
            try {
                MyDuke.todoChecker(getParsedInput());
                ToDo todo = new ToDo(getParsedInput()[1], false);
                tasks.addTask(todo);
                ui.printAddedTodoAlert(todo.toString(), tasks.getTaskList().size());
            } catch (NoToDoException e) {
                ui.printErrorMsg(e.getMessage());
            }
            break;
        case "event":
            try {
                MyDuke.eventChecker(getParsedInput());
                String[] temp = getParsedInput()[1].split("/", 2);
                Event event = new Event(LocalDate.parse(temp[1].substring(3)), temp[0], false);
                tasks.addTask(event);
                ui.printAddedEventAlert(event.toString(), tasks.getTaskList().size());
            } catch (NoEventException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (NoDateException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.printErrorMsg(new String[]{
                        "Paikia Bot: oi your date input got format error cannot parse sial: "
                                + getParsedInput()[1].split("/", 2)[1],
                        "Paikia Bot: the format should be liddis: yyyy-mm-dd, eg. 2020-03-02"
                });
            }
            break;
        case "deadline":
            try {
                MyDuke.deadlineChecker(getParsedInput());
                String[] temp = getParsedInput()[1].split("/", 2);
                Deadline deadline = new Deadline(LocalDate.parse(temp[1].substring(3)), temp[0], false);
                tasks.addTask(deadline);
                ui.printAddedDeadlineAlert(deadline.toString(), tasks.getTaskList().size());
            } catch (NoDeadlineException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (NoDateException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.printErrorMsg(new String[]{
                        "Paikia Bot: oi your date input got format error cannot parse sial: "
                                + getParsedInput()[1].split("/", 2)[1],
                        "Paikia Bot: the format should be liddis: yyyy-mm-dd, eg. 2020-03-02"
                });
            }
            break;
        case "find":
            try {
                MyDuke.finderChecker(getParsedInput());
                String keyword = this.input.split(" ", 2)[1];
                List<Task> searchResultList = new ArrayList<>();
                for (Task t : tasks.getTaskList()) {
                    if (t.info.contains(keyword)) {
                        searchResultList.add(t);
                    }
                }
                int searchResultCounter = 1;
                String[] tempSearchResultArr = new String[100];
                if (searchResultList.isEmpty()) { // improved implementation in case list is empty, gives a clear output
                    ui.showListEmptyMsg();
                } else {
                    for (Task t : searchResultList) { // changed String s to Task t
                        tempSearchResultArr[searchResultCounter - 1] = searchResultCounter + ". " + t.toString();
                        searchResultCounter++;
                    }
                    ui.printSearchResultList(keyword, tempSearchResultArr);
                }
            } catch (NoKeywordException e) {
                ui.printErrorMsg(e.getMessage());
            }
            break;
        case "bye":
            ui.showByeMsg();
        default:
            ui.showInputError();
        }
    }

    public String handleInputStr() {
        switch (getParsedInput()[0]) {
        case "list":
            int counter = 1;
            String[] tempArr = new String[100];
            if (tasks.getTaskList().isEmpty()) { // improved implementation in case list is empty, gives a clear output
                return ui.showListEmptyMsgStr();
            } else {
                for (Task t : tasks.getTaskList()) { // changed String s to Task t
                    tempArr[counter - 1] = counter + ". " + t.toString();
                    counter++;
                }
                return ui.printTasksInListStr(tempArr);
            }
            case "done":
            try {
                MyDuke.indexChecker(getParsedInput());
                int ref = Integer.parseInt(getParsedInput()[1]);
                tasks.getTaskList().set(ref - 1, tasks.getTaskList().get(ref - 1).setAsDone());
                return ui.printDoneTaskAlertStr(tasks.getTaskList().get(ref - 1).toString());
            } catch (NoIndexException e) {
                return ui.printErrorMsgStr(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                return ui.printErrorMsgStr("Paikia Bot: the number that you inputted is invalid leh (more than the "
                        + "total number of tasks or less than 1), try again pls ah");
            } catch (NumberFormatException e) {
                return ui.printErrorMsgStr("r input after 'done' is invalid, reminder that it should be"
                        + " a single integerand remember to not leave a space after your input. eg, done 3");
            }
        case "delete":
            try {
                MyDuke.indexChecker(getParsedInput());
                int ref = Integer.parseInt(getParsedInput()[1]);
                Task toRemove = tasks.getTask(ref);
                tasks.deleteTask(ref - 1);
                return ui.printDeletedTaskAlertStr(toRemove.toString(), tasks.getTaskList().size());
            } catch (NoIndexException e) {
                return ui.printErrorMsgStr(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                return ui.printErrorMsgStr("the number that you inputted is invalid leh (more than the "
                        + " total number of tasks or less than 1), try again pls ah");
            } catch (NumberFormatException e) {
                return ui.printErrorMsgStr("Paikia Bot: ur input after 'done' is invalid, reminder that it should be a "
                        + "single integer and remember to not leave a space after your input. eg, done 3");
            }
        case "todo":
            try {
                MyDuke.todoChecker(getParsedInput());
                ToDo todo = new ToDo(getParsedInput()[1], false);
                tasks.addTask(todo);
                return ui.printAddedTodoAlertStr(todo.toString(), tasks.getTaskList().size());
            } catch (NoToDoException e) {
                return ui.printErrorMsgStr(e.getMessage());
            }
        case "event":
            try {
                MyDuke.eventChecker(getParsedInput());
                String[] temp = getParsedInput()[1].split("/", 2);
                Event event = new Event(LocalDate.parse(temp[1].substring(3)), temp[0], false);
                tasks.addTask(event);
                return ui.printAddedEventAlertStr(event.toString(), tasks.getTaskList().size());
            } catch (NoEventException e) {
                return ui.printErrorMsgStr(e.getMessage());
            } catch (NoDateException e) {
                return ui.printErrorMsgStr(e.getMessage());
            } catch (DateTimeParseException e) {
                return ui.printErrorMsgStr(new String[]{
                        "Paikia Bot: oi your date input got format error cannot parse sial: "
                                + getParsedInput()[1].split("/", 2)[1],
                        "Paikia Bot: the format should be liddis: yyyy-mm-dd, eg. 2020-03-02"
                });
            }
        case "deadline":
            try {
                MyDuke.deadlineChecker(getParsedInput());
                String[] temp = getParsedInput()[1].split("/", 2);
                Deadline deadline = new Deadline(LocalDate.parse(temp[1].substring(3)), temp[0], false);
                tasks.addTask(deadline);
                return ui.printAddedDeadlineAlertStr(deadline.toString(), tasks.getTaskList().size());
            } catch (NoDeadlineException e) {
                return ui.printErrorMsgStr(e.getMessage());
            } catch (NoDateException e) {
                return ui.printErrorMsgStr(e.getMessage());
            } catch (DateTimeParseException e) {
                return ui.printErrorMsgStr(new String[]{
                        "oi your date input got format error cannot parse sial: "
                                + getParsedInput()[1].split("/", 2)[1],
                        "the format should be liddis: yyyy-mm-dd, eg. 2020-03-02"
                });
            }
        case "find":
            try {
                MyDuke.finderChecker(getParsedInput());
                String keyword = this.input.split(" ", 2)[1];
                List<Task> searchResultList = new ArrayList<>();
                for (Task t : tasks.getTaskList()) {
                    if (t.info.contains(keyword)) {
                        searchResultList.add(t);
                    }
                }
                int searchResultCounter = 1;
                String[] tempSearchResultArr = new String[100];
                if (searchResultList.isEmpty()) { // improved implementation in case list is empty, gives a clear output
                    return ui.showListEmptyMsgStr();
                } else {
                    for (Task t : searchResultList) { // changed String s to Task t
                        tempSearchResultArr[searchResultCounter - 1] = searchResultCounter + ". " + t.toString();
                        searchResultCounter++;
                    }
                    return ui.printSearchResultListStr(keyword, tempSearchResultArr);
                }
            } catch (NoKeywordException e) {
                return ui.printErrorMsgStr(e.getMessage());
            }
        case "bye":
            return ui.showByeMsgStr();
        default:
            return ui.showInputErrorStr();
        }
    }
}