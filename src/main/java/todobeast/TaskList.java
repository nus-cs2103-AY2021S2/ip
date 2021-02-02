package todobeast;

import todobeast.tasks.Deadline;
import todobeast.tasks.Event;
import todobeast.tasks.Task;
import todobeast.tasks.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

public class TaskList {
    public List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public void setTaskAsDone(Task task) {
        task.setDone();
    }

    public boolean isTaskIndexInRange(int taskNumber) {
        return taskNumber >= 0 && taskNumber < taskList.size();
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public String formatTaskListForStorage() {
        StringBuilder outputString = new StringBuilder();
        for (Task task : taskList) {
            outputString.append(task.formatForStorage(Storage.STORAGE_DELIMITER));
            outputString.append("\n");
        }
        return outputString.toString();
    }

    public void printTaskList() {
        int count = 1;
        System.out.print(Ui.line);
        for (Task task : taskList) {
            System.out.println("\t" + count++ + ". " + task);
        }
        System.out.println(Ui.line);
    }
}
