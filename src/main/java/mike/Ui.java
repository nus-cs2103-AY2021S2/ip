package mike;

import mike.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Ui {
    private final StringBuilder lengthOfChatBox = new StringBuilder();

    public void setLengthOfChatBox() {
        lengthOfChatBox.append("\n");
        for (int i = 0; i < 50; i++) {
            lengthOfChatBox.append("-");
        }
        lengthOfChatBox.append("\n");
    }

    public void formatInChatBox(String s) {
        System.out.println(lengthOfChatBox + s + lengthOfChatBox);
    }

    public void init() {
        setLengthOfChatBox();
        formatInChatBox("Hello I'm mike.Mike\nWhat can I do for you?\n");
    }

    public void exit() {
        formatInChatBox("Bye. Hope to see you again soon!\n");
    }

    public void showList(TaskList taskList) {
        int numOfText = taskList.size();
        StringBuilder output = new StringBuilder();

        if (taskList.isEmpty()) {
            output.append("No tasks saved\n");
        } else {
            output.append("Here are the tasks in your list: \n");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                output.append(i + 1).append(". ").append(currentTask).append("\n");
            }
        }
        formatInChatBox(output.toString());
    }

    public void showAddSuccess(TaskList taskList, Task task) {
        formatInChatBox("Got it. I've added this task:\n  " + task + "\n Now you have "
                + taskList.size() + " tasks in the list.");
    }

    public void showMarkSuccess(Task task) {
        formatInChatBox("Nice! I've marked this task as done: \n" + task);
    }

    public void showDeleteSuccess(TaskList taskList, Task task) {
        formatInChatBox("Noted. I've removed this task:\n  " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public void showError(ParseException e) {
        formatInChatBox(e.getMessage());
    }

    public void showLoadingError(ParseException e) {
        formatInChatBox(e.getMessage());
    }

    public void showIndexOutOfBoundsError(TaskList taskList) {
        formatInChatBox("OOPS!!! You only have " + taskList.size() + " tasks, please enter an index within the range");
    }

    public void showIOError(IOException e) {
        formatInChatBox(e.getMessage());
    }

    public void showDateTimeParseError(DateTimeParseException e) {
        formatInChatBox(e.getMessage());
    }

    public void showGeneralError() {
        try {
            throw new ParseException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (ParseException e) {
            formatInChatBox(e.getMessage());
        }
    }

    public void showListError() {
        formatInChatBox("OOPS!!! The list is empty.");
    }
}
