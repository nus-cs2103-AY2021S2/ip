package project.command;

import java.time.LocalDateTime;
import java.util.Arrays;

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

        return new Event(description, startDateTime, endDateTime);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        try {
            String expression = Parser.parseParameter(userInput, " ", 1);
            String description = Parser.parseParameter(expression, "/at", 0);
            if (description.length() == 0) {
                throw new Exception("Oops! The task description cannot be empty :/");
            }

            String dateTime = Parser.parseParameter(expression, "/at", 1);
            String start = Parser.parseParameter(dateTime, " to ", 0);
            String end = Parser.parseParameter(dateTime, " to ", 1);

            LocalDateTime startDateTime = Parser.parseInputDateTime(start);
            LocalDateTime endDateTime = Parser.parseInputDateTime(end);
            if (!startDateTime.isBefore(endDateTime)) {
                throw new Exception("Oops! End time must be after start of event :)");
            }

            Event newEvent = new Event(description, startDateTime, endDateTime);
            taskList.addTask(newEvent);

            // will save in every storage path provided
            Arrays.stream(storage).forEach(s -> {
                s.saveData(taskList);
                assert s.isSaved();
            });

            return ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                    newEvent, taskList.getTotalNumberOfTasksUndone());
        } catch (Exception e) {
            // catches ParseException, IndexOutOfBounds exception and invalid datetime exception
            return ui.showFormatError(PrintedText.EVENT_FORMAT);
        }
    }
}
