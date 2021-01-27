package task;

import simulator.Ui;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasklist;

    public TaskList() {
        tasklist = new ArrayList<>();
    }

    public String getTally() {
        return "     Currently you have " + tasklist.size() + " tasks in the list.";
    }

    public void addTask(Task task) {
        tasklist.add(task);
        Ui.printBox(Ui.ADD_MSG + task + "\n \n" + getTally());
    }

    public void completeTask(int index) {
        Task task = tasklist.get(index - 1);
        task.complete();
        Ui.printBox(Ui.MARK_MSG + "      " + task);
    }


    public void deleteTask(int index) {
        String task = tasklist.get(index - 1).toString();
        tasklist.remove(index - 1);
        Ui.printBox(Ui.DELETE_MSG + task + "\n \n" + getTally());
    }

    public int size() {
        return tasklist.size();
    }

    public Task get(int index) {
        return tasklist.get(index);
    }

    @Override
    public String toString() {
        return tasklist.toString();
    }
}
