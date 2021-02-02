package main.java.command;

import java.time.LocalDate;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Event;
import main.java.entity.Task;

/**
 * Command to add a Event Task
 */
public class AddEventCommand extends AddCommand {
    private String eventName;
    private String keyword;
    private String dateString;
    private LocalDate date;

    /**
     * Creates a command to add a Event task
     * @param eventName name of event
     * @param keyword preposition as time-specifier
     * @param dateString string representation of deadline
     */
    public AddEventCommand(String eventName, String keyword, String dateString) {
        this.eventName = eventName;
        this.keyword = keyword;
        this.dateString = dateString;
    }

    /**
     * Creates a command to add a Event task
     * @param eventName name of event
     * @param keyword preposition as time-specifier
     * @param date LocalDate representation of deadline
     */
    public AddEventCommand(String eventName, String keyword, LocalDate date) {
        this.eventName = eventName;
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
            task = new Event(eventName, keyword, dateString);
        } else {
            task = new Event(eventName, keyword, date);
        }
        tm.addTask(task);
        ui.displayAfterAdd(tm.size(), task);
    }
}
