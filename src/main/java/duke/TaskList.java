package duke;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;
    public boolean isAlive;

    public TaskList() {
        this.list = new ArrayList<>();
        this.isAlive = true;
    }

    public TaskList(ArrayList<Task> taskArr) {
        this.list = taskArr;
        this.isAlive = true;
    }

    private String echoNoOfTask() {
        String noOfTasksText = "Now you have " + getNoOfTasks() + " tasks in the list\n";
        return noOfTasksText;
    }

    public String addTask(Task task) {
        String addStatus;
        this.list.add(task);
        addStatus = "Okay I have added this task: \n" + task + "\n" + echoNoOfTask();
        return addStatus;
    }

    public String deleteTask(int index) throws DukeException {
        String deleteStatus;
        if (index < 1 || index > getNoOfTasks()) {
            throw new DukeException("There is no such task number!");
        }
        Task task = list.get(index - 1);
        this.list.remove(index - 1);
        deleteStatus = "Okay, I've I have removed this task: \n" + task + "\n" + echoNoOfTask();
        return deleteStatus;
    }

    public int getNoOfTasks() {
        return list.size();
    }

    public String findTasks(String toFind) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task t : this.list) {
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
        if (list.size() == 0) {
            text = "There are no tasks";
        } else {
            text += "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                text += (i+1) +
                        ". " +
                        list.get(i) +
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
        String markStatus;
        if (getNoOfTasks() == 0) {
            markStatus = "There are no tasks in the list to be marked as done!";
        } else {
            try {
                list.get(num - 1).markAsDone();
                markStatus = "Okay! I've marked this task as done:\n" + list.get(num - 1);
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
