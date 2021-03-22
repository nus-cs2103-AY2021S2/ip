package mike;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.MikeCommandExecutionException;
import task.DeadlineTask;
import task.EventTask;
import task.TodoTask;

public class TaskList {
    private List<task.Task> taskList;
    private int numTasks = 0;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Add task to list of tasks then increment number of tasks stored
     *
     * @param task task to be added to list
     */
    public void addTaskToList(task.Task task) {
        taskList.add(task);
        numTasks++;
    }

    /**
     * Get number of tasks stored in task list
     *
     * @return num of tasks in list
     */
    public int getNumTasks() {
        return numTasks;
    }

    /**
     * return nth task starting at index 1
     *
     * @param i the index of the task to be retrieved
     * @return the Task.Task object
     */
    public task.Task getNthTask(int i) {
        assert i <= numTasks && i > 0 : "Task to retrieve not within valid range";
        return this.taskList.get(i - 1);
    }

    /**
     * deleted the nth task starting at index 1
     *
     * @param i the index of the task to be deleted
     */
    public void deleteNthTask(int i) {
        this.numTasks--;
        this.taskList.remove(i - 1);
    }

    /**
     * Convert string representation of a task to a Task object
     * @param strTask String representation of task to be converted
     * @throws MikeCommandExecutionException if string representation of the task is of an invalid format
     */
    public void strToTask(String strTask) throws MikeCommandExecutionException {
        Pattern pattern = Pattern.compile("(\\d)\\. \\[([TDE])\\] \\[([X ])\\] (.+)");
        Matcher matcher = pattern.matcher(strTask);
        Matcher descriptionMatcher;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
        LocalDateTime dateTimeObject;

        matcher.find();

        assert matcher.group(3).length() == 1 : "Task done indicator is not valid";
        boolean isDoneTask = matcher.group(3).equals("X");

        assert matcher.group(4) != null : "Task Description empty";
        switch(matcher.group(2)) {
        case "T":
            this.addTaskToList(new TodoTask(matcher.group(4)));
            break;

        case "E":
            pattern = Pattern.compile("(.+) (?=\\(at: (\\d\\d \\w\\w\\w \\d\\d\\d\\d, \\d\\d:\\d\\d "
                    + "\\w\\w)\\))");
            descriptionMatcher = pattern.matcher(matcher.group(4));
            descriptionMatcher.find();
            dateTimeObject = LocalDateTime.parse(descriptionMatcher.group(2), format);
            this.addTaskToList(new EventTask(descriptionMatcher.group(1), dateTimeObject));
            break;

        case "D":
            pattern = Pattern.compile("(.+) (?=\\(by: (\\d\\d \\w\\w\\w \\d\\d\\d\\d, \\d\\d:\\d\\d "
                    + "\\w\\w)\\))");
            descriptionMatcher = pattern.matcher(matcher.group(4));
            descriptionMatcher.find();
            dateTimeObject = LocalDateTime.parse(descriptionMatcher.group(2), format);
            this.addTaskToList(new DeadlineTask(descriptionMatcher.group(1), dateTimeObject));
            break;

        default:
            throw new MikeCommandExecutionException("strToTask error", "No such task type");
        }
        if (isDoneTask) {
            this.getNthTask(Integer.parseInt(matcher.group(1))).completeTask();
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        for (int i = 1; i <= numTasks; i++) {
            buffer.append(i);
            buffer.append(". ");
            buffer.append(taskList.get(i - 1).toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }

}
