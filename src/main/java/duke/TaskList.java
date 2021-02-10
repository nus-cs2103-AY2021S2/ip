package duke;

import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    public ArrayList<Task> listOfTasks;
    public boolean isAlive;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
        this.isAlive = true;
    }

    public TaskList(ArrayList<Task> taskArr) {
        this.listOfTasks = taskArr;
        this.isAlive = true;
    }

    private String echoNoOfTask() {
        int numOfTasks = getNoOfTasks();
        assert numOfTasks >=0 : "Number of tasks cannot be a negative integer";
        String noOfTasksText = "Now you have " + numOfTasks + " tasks in the list\n";
        return noOfTasksText;
    }

    public String addTask(Task task) {
        String addStatus;
        this.listOfTasks.add(task);
        addStatus = "Okay I have added this task: \n" + task + "\n" + echoNoOfTask();
        return addStatus;
    }

    //TODO create a function to check if the task number is valid
    public String deleteTask(int index) throws DukeException {
        String deleteStatus;
        if (index < 1 || index > getNoOfTasks()) {
            throw new DukeException("There is no such task number!");
        }
        Task task = this.listOfTasks.get(index - 1);
        this.listOfTasks.remove(index - 1);
        deleteStatus = "Okay, I've I have removed this task: \n" + task + "\n" + echoNoOfTask();
        return deleteStatus;
    }

    public String deleteMultipleTasks(ArrayList<Integer> tasksToDelete) throws DukeException {
        //sort in descending order to prevent mixing up task number when deleting tasks
        Collections.sort(tasksToDelete, Collections.reverseOrder());
        String deleteStatus;
        for (Integer taskNumber : tasksToDelete) {
            deleteTask(taskNumber);
        }
        deleteStatus = "Successfully deleted tasks";
        return deleteStatus;
    }

    public int getNoOfTasks() {
        return this.listOfTasks.size();
    }

    public String findTasks(String toFind) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task t : this.listOfTasks) {
            if (t.taskDescription.contains(toFind)) {
                newList.add(t);
            }
        }
        String text = "";
        if (newList.size() == 0) {
            System.out.println("Found no related tasks");
        } else {
            text += "Here are the tasks in your list:\n";
            for (int i = 0; i < newList.size(); i++) {
                text += (i+1) +
                        ". " +
                        newList.get(i);
            }
        }
        return text;
    }

    public String displayTasks() {
        String text = "";
        if (this.listOfTasks.size() == 0) {
            text = "There are no tasks";
        } else {
            text += "Here are the tasks in your list:\n";
            for (int i = 0; i < this.listOfTasks.size(); i++) {
                text += (i+1) +
                        ". " +
                        this.listOfTasks.get(i) +
                        "\n";
            }
        }
        return text;
    }

    //TODO consider throwing an exception (eg. DukeException)
    public void complain() throws DukeException {
        throw new DukeException("Sorry I do not understand that!");
    }

    public String markAsDone(int num) throws DukeException{
        //TODO consider adding assertion/exception here(to prevent indexing issues)
        assert num < 0 : "Excepted a positive value for task number, to mark it as done";
        String markStatus;
        if (getNoOfTasks() == 0) {
            markStatus = "There are no tasks in the list to be marked as done!";
        } else {
            try {
                this.listOfTasks.get(num - 1).markAsDone();
                markStatus = "Okay! I've marked this task as done:\n" + this.listOfTasks.get(num - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\t\tOnly Enter natural numbers (i.e. 1,2,3..) not more than " + getNoOfTasks());
            }
        }
        return markStatus;
    }

    @Override
    public String toString() {
        return "TaskList for Duke";
    }
}
