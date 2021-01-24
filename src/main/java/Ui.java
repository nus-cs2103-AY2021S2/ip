import exceptions.DukeEmptyListException;
import exceptions.DukeNoDescriptionException;
import exceptions.DukeUnknownArgumentsException;

import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void printStartMsg() {
        out.println(Message.START_MSG);
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void printDivider() {
        out.println(Message.LINE);
    }

    public void printByeMsg() {
        out.println(Message.BYE_MSG);
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
        out.println(Message.getTasksMsg(taskList));
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
}
