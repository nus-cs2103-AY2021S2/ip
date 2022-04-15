package project.command;

import java.time.LocalDateTime;

import project.common.PrintedText;
import project.io.Parser;
import project.io.Ui;
import project.storage.Storage;
import project.task.Event;
import project.task.Task;
import project.task.TaskList;

public class AddEvent extends AddTask {
    public AddEvent() {
        super();
    }

    public AddEvent(String userInput) {
        super(userInput);
    }

    @Override
    public Task loadFromStorage(String line) {
        String expression = Parser.parseParameter(line, "] ", 1);
        String description = Parser.parseParameter(expression, "\\(at:", 0);

        String dateTime = Parser.parseParameter(expression, "\\(at:", 1);
        String start = Parser.parseParameter(dateTime, " to ", 0);
        String end = Parser.parseParameter(dateTime, " to ", 1);

        LocalDateTime startDateTime = Parser.parseOutputDateTime(start);
        LocalDateTime endDateTime = Parser.parseOutputDateTime(end);

        Event e = new Event(description, startDateTime, endDateTime);
        if (endDateTime.isBefore(LocalDateTime.now())) {
            e.markAsDone();
        }
        return e;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        try {
            String expression = Parser.parseParameter(userInput, " ", 1);
            String description = Parser.parseParameter(expression, "/at", 0);
            if (description.length() == 0) {
                throw new Exception("*! The task description cannot be empty :/");
            }

            String dateTime = Parser.parseParameter(expression, "/at", 1);
            String start = Parser.parseParameter(dateTime, " to ", 0);
            String end = Parser.parseParameter(dateTime, " to ", 1);

            LocalDateTime startDateTime = Parser.parseInputDateTime(start);
            LocalDateTime endDateTime = Parser.parseInputDateTime(end);
            if (!startDateTime.isBefore(endDateTime)) {
                throw new Exception("*! End time must be after start of event :)");
            }

            Event newEvent = new Event(description, startDateTime, endDateTime);
            taskList.addTask(newEvent);
            this.saveTasks(taskList, storage);
            return ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                    newEvent, taskList.getTotalNumberOfTasksUndone());
        } catch (Exception e) {
            // catches ParseException, IndexOutOfBounds exception and invalid datetime exception
            // todo: throw Olaf-specific Exceptions for ParseException and IndexOutOfBounds etc
            // so that error-specific messages can be displayed in the UI
            return ui.showFormatError(PrintedText.EVENT_FORMAT);
        }
    }
}
