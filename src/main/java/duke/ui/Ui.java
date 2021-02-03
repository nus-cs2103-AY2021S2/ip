package duke.ui;

import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeCorruptedStorageException;
import duke.exceptions.DukeCreateDirectoryException;
import duke.exceptions.DukeCreateFileException;
import duke.exceptions.DukeEmptyListException;
import duke.exceptions.DukeNoDescriptionException;
import duke.exceptions.DukeSaveFileException;
import duke.exceptions.DukeUnknownArgumentsException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for ui to using default system.in to read inputs and PrintStream out to
     * display outputs.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void printStartMsg() {
        out.println(Message.getStartMsg());
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void printDivider() {
        out.println(Message.LINE);
    }

    public void printByeMsg() {
        out.println(Message.getByeMsg());
    }

    public void printDoneMsg(Task task) {
        out.println(Message.getDoneMsg(task));
    }

    public void printDeleteMsg(Task task, int size) {
        out.println(Message.getDeleteMsg(task, size));
    }

    public void printAddMsg(Task task, int size) {
        out.println(Message.getAddMsg(task, size));
    }

    public void printTaskList(ArrayList<Task> taskList) {
        out.println(Message.getTaskListMsg(taskList));
    }

    public void printFindMsg(ArrayList<Task> taskList) {
        out.println(Message.getFindMsg(taskList));
    }

    public void printErrorMsg(DukeUnknownArgumentsException e) {
        out.println(Message.getErrorMsg(e));
    }

    public void printErrorMsg(NumberFormatException e) {
        out.println(Message.getErrorMsg(e));
    }

    public void printErrorMsg(IndexOutOfBoundsException e, TaskList taskList) {
        out.println(Message.getErrorMsg(e, taskList));
    }

    public void printErrorMsg(DukeEmptyListException e) {
        out.println(Message.getErrorMsg(e));
    }

    public void printErrorMsg(DukeNoDescriptionException e) {
        out.println(Message.getErrorMsg(e));
    }

    public void printErrorMsg(DateTimeParseException e) {
        out.println(Message.getErrorMsg(e));
    }

    public void printErrorMsg(DukeSaveFileException e) {
        out.println(Message.getErrorMsg(e));
    }

    public void printErrorMsg(DukeCreateFileException e) {
        out.println(Message.getErrorMsg(e));
    }

    public void printErrorMsg(DukeCorruptedStorageException e) {
        out.println(Message.getErrorMsg(e));
    }

    public void printErrorMsg(DukeCreateDirectoryException e) {
        out.println(e);
    }
}
