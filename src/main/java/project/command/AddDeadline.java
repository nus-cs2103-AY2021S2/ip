package project.command;

import java.time.LocalDateTime;

import project.common.PrintedText;
import project.io.Parser;
import project.io.Ui;
import project.storage.Storage;
import project.task.Deadline;
import project.task.Task;
import project.task.TaskList;

public class AddDeadline extends AddTask {
    public AddDeadline() {
        super();
    }

    public AddDeadline(String userInput) {
        super(userInput);
    }

    @Override
    public Task loadFromStorage(String line) {
        String expression = Parser.parseParameter(line, "] ", 1);
        String description = Parser.parseParameter(expression, "\\(by:", 0);

        String dateTime = Parser.parseParameter(expression, "\\(by:", 1);
        LocalDateTime deadline = Parser.parseOutputDateTime(dateTime);

        return new Deadline(description, deadline);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        try {
            String expression = Parser.parseParameter(userInput, " ", 1);
            String description = Parser.parseParameter(expression, "/by", 0);
            if (description.length() == 0) {
                throw new Exception("*! The task description cannot be empty :/");
            }

            String dateTime = Parser.parseParameter(expression, "/by", 1);
            LocalDateTime deadline = Parser.parseInputDateTime(dateTime);
            if (deadline.isBefore(LocalDateTime.now())) {
                throw new Exception("*! The deadline cannot be in the past...");
            }

            Deadline newDeadline = new Deadline(description, deadline);
            assert newDeadline.getOccurrence().isAfter(LocalDateTime.now());
            taskList.addTask(newDeadline);
            this.saveTasks(taskList, storage);
            return ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                    newDeadline, taskList.getTotalNumberOfTasksUndone());
        } catch (Exception e) {
            // catches ParseException, IndexOutOfBounds exception and invalid dates in the past
            // todo: throw Olaf-specific Exceptions for ParseException and IndexOutOfBounds etc
            // so that error-specific messages can be displayed in the UI
            return ui.showFormatError(PrintedText.DEADLINE_FORMAT);
        }
    }
}
