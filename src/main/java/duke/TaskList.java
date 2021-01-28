package duke;

import java.util.ArrayList;
import java.util.List;

//to handle date and time
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskList {
    public final List<Task> task;

    public TaskList() {
        task = new ArrayList<>();
    }

    public int getSize() {
        return task.size();
    }

    public void add(Task t) {
        task.add(t);
    }

    public Task get(int i) {
        return task.get(i);
    }

    public boolean isDateFormat(String date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date.length() != 10) {
            return false;
        }
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException e) {
            //TODO: handle exception
            return false;
        }
        return true;
    }

    public void add(String[] userInput, Ui ui) throws DukeException {
        switch (userInput[0]) {
            case "todo":
                Todo t = new Todo(userInput[1]);
                task.add(t);
                ui.reportTask(t, this);
                break;

            case "deadline":
                String[] deadlineArr = userInput[1].split(" /by ", 2);
                if (deadlineArr.length != 2) {
                    throw new DukeException("Missing component: due date");
                }
                String time = deadlineArr[1];
                if (isDateFormat(time, "yyyy-mm-dd") || isDateFormat(time, "yyyy-m-dd") || isDateFormat(time, "yyyy-mm-d") || isDateFormat(time, "yyyy-m-d")) {
                    LocalDate date = LocalDate.parse(time);
                    time = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                }
                Deadline d = new Deadline(deadlineArr[0], time);
                task.add(d);
                ui.reportTask(d, this);
                break;

            case "event":
                String eventArr[] = userInput[1].split(" /at ", 2);
                if (eventArr.length != 2) {
                    throw new DukeException("Missing component: event date and time");
                }
                Event e = new Event(eventArr);
                task.add(e);
                ui.reportTask(e, this);
                break;

            default:
                break;
        }
    }

    public void doneTask(String inputIndex, Ui ui) {
        try {
            ui.printLine();
            int index = Integer.parseInt(inputIndex.trim());
            Task t = task.get(index - 1);
            if (t.isDone()) {
                ui.printMessage("This task was marked as done before");
            } else {
                t.markAsDone();
                ui.printMessage("You have done the following task:");
                ui.printMessage(t.toString());
            }
            ui.printLine();
        } catch (IndexOutOfBoundsException e) {
            // TODO: handle exception
            ui.printErrorMessage("Sorry, I cannot find this task, please check your list again");
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, number not recognized");
        }
    }

    public void deleteTask(String inputIndex, Ui ui) {
        try {
            ui.printLine();
            int index = Integer.parseInt(inputIndex.trim());
            Task t = task.get(index - 1);
            task.remove(t);
            ui.printMessage("The following task has been deleted:");
            ui.printMessage(t.toString());
            ui.printMessage("You now have " + task.size() + " task in your list");
        } catch (IndexOutOfBoundsException e) {
            // TODO: handle exception
            ui.printErrorMessage("Sorry, I cannot find this task, please check your list again");
        } catch (NumberFormatException e) {
            ui.printErrorMessage("Sorry, number not recognized");
        }
    }

    public void printTask(Ui ui) {
        ui.printLine();
        ui.printMessage("Here is your current tasks");
        for (int i = 1; i <= task.size(); ++i) {
            ui.printMessage(i + "." + task.get(i - 1).toString());
        }
        ui.printLine();
    }
}
