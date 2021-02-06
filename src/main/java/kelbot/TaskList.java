package kelbot;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    /**
     * Initializes Task List with new taskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    /**
     * Initializes Task List with existing taskList.
     * @param taskList The task list that was loaded from Storage.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    /**
     * Returns Size of taskList.
     * @return Size of task list.
     */
    public int getSize() {
        if (taskList.isEmpty()) {
            return 0;
        } else {
            return taskList.size();
        }
    }
    /**
     * Gets Task List.
     * @return Task List.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    /**
     * Add Task to Task List.
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }
    /**
     * Mark a Task in Task List as Done by Task Number.
     * @param taskNumber The task number to be completed.
     * @return The task that is being marked.
     */
    public Task complete(int taskNumber) {
        Task taskToComplete = taskList.get(taskNumber);
        taskToComplete.complete();
        return taskToComplete;
    }
    /**
     * Delete a Task in Task List by Task Number.
     * @param taskNumber The task number to deleted.
     * @return The task that is being deleted.
     */
    public Task delete(int taskNumber) {
        Task taskToDelete = taskList.remove(taskNumber);
        return taskToDelete;
    }
    /**
     * Searches through all the tasks to see which ones contains the given keyword.
     * @param keyword The keyword to be searched.
     * @return The task list that contains only the relevant tasks.
     */
    public ArrayList<Task> search(String keyword) {
        ArrayList<Task>taskListToPrint = new ArrayList<>();
        for (Task task: taskList) {
            if (task.hasKeyword(keyword)) {
                taskListToPrint.add(task);
            }
        }
        return taskListToPrint;
    }
    /**
     * Reschedules a DeadlineTask or EventTask.
     * @param taskNumber Index of the task in the task list.
     * @param date The new date of the task.
     * @return The task that has been rescheduled.
     * @throws KelbotException If the task if a TodoTask.
     */
    public Task snooze(int taskNumber, LocalDate date) throws KelbotException {
        if (taskList.get(taskNumber) instanceof DeadlineTask) {
            DeadlineTask deadlineTaskToSnooze = (DeadlineTask) taskList.get(taskNumber);
            deadlineTaskToSnooze.setDate(date);
            return deadlineTaskToSnooze;
        } else if (taskList.get(taskNumber) instanceof EventTask) {
            EventTask eventTaskToSnooze = (EventTask) taskList.get(taskNumber);
            eventTaskToSnooze.setDate(date);
            return eventTaskToSnooze;
        } else {
            throw new KelbotException("Todo Task does not have date!");
        }
    }
    /**
     * Tags selected task with given tag name.
     * @param taskNumber Index of the task to be tagged in the task list.
     * @param tagName The tag to be given to task.
     * @return The task that has been tagged.
     */
    public Task tag(int taskNumber, String tagName) {
        Task taskToTag = taskList.get(taskNumber);
        taskToTag.setTag(tagName);
        return taskToTag;
    }
    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i <= taskList.size(); i++) {
            if (i == taskList.size()) {
                result += i + ". " + taskList.get(i - 1);
            } else {
                result += i + ". " + taskList.get(i - 1) + "\n";
            }
        }
        return result;
    }
}
