package main.java.command;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Deadline;
import main.java.entity.Task;
import java.time.LocalDate;

public class AddDeadlineCommand extends AddCommand {
    String deadlineName;
    String keyword;
    String dateString;
    LocalDate date;
    public AddDeadlineCommand(String deadlineName, String keyword, String dateString) {
        this.deadlineName = deadlineName;
        this.keyword = keyword;
        this.dateString = dateString;
    }

    public AddDeadlineCommand(String deadlineName, String keyword, LocalDate date) {
        this.deadlineName = deadlineName;
        this.keyword = keyword;
        this.date = date;
    }

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
