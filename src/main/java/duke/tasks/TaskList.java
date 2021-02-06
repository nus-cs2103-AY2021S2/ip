package duke.tasks;

import duke.exceptions.TaskNumberNotExistException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getListOfTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTaskByIndex(int index) {
        try {
            if (index > this.getSize() || index <= 0) {
                throw new TaskNumberNotExistException(index);
            }
        } catch (TaskNumberNotExistException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return this.tasks.get(index - 1);
    }

    public Task popTaskByIndex(int index) {
        try {
            if (index > this.getSize() || index <= 0) {
                throw new TaskNumberNotExistException(index);
            }
        } catch (TaskNumberNotExistException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return this.tasks.remove(index - 1);
    }

    public void printTasks() {
        for (int index = 0; index < this.tasks.size(); index++) {
            int taskNumber = index + 1;
            Task task = this.getTaskByIndex(taskNumber);
            System.out.print(taskNumber + "." + task.getStatusString() + "\n");
        }
    }

    public int getSize() {
        return this.tasks.size();
    }
}
