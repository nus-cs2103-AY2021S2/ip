package main.java.command;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Event;
import main.java.entity.Task;

import java.time.LocalDate;

public class AddEventCommand extends AddCommand {
    String eventName;
    String keyword;
    String dateString;
    LocalDate date;
    public AddEventCommand(String eventName, String keyword, String dateString) {
        this.eventName = eventName;
        this.keyword = keyword;
        this.dateString = dateString;
    }

    public AddEventCommand(String eventName, String keyword, LocalDate date) {
        this.eventName = eventName;
        this.keyword = keyword;
        this.date = date;
    }

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
