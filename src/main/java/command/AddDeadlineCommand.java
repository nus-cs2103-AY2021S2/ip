package main.java.command;

import java.time.LocalDate;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Deadline;
import main.java.entity.Task;

/**
 * Command to add a Deadline Task
 */
public class AddDeadlineCommand extends AddCommand {
    protected String deadlineName;
    protected String keyword;
    protected String dateString;
    protected LocalDate date;

    /**
     * Creates a command to add a Deadline task
     * @param deadlineName name of task
     * @param keyword preposition as time-specifier
     * @param dateString string representation of deadline
     */
    public AddDeadlineCommand(String deadlineName, String keyword, String dateString) {
        this.deadlineName = deadlineName;
        this.keyword = keyword;
        this.dateString = dateString;
    }

    /**
     * Creates a command to add a Deadline task
     * @param deadlineName name of task
     * @param keyword preposition as time-specifier
     * @param date LocalDate representation of deadline
     */
    public AddDeadlineCommand(String deadlineName, String keyword, LocalDate date) {
        this.deadlineName = deadlineName;
        this.keyword = keyword;
        this.date = date;
    }

    /**
     * execute add task command
     * call TaskManager to add the particular task
     * and Ui to display add message
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     */
    @Override
    public void execute(TaskManager tm, Ui ui) {
        Task task;
        if (this.date == null) {
            task = new Deadline(deadlineName, keyword, dateString);
        } else {
            task = new Deadline(deadlineName, keyword, date);
        }
        tm.addTask(task);
        ui.displayAfterAdd(tm.size(), task);
    }
}
