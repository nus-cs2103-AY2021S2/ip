package duke.task;

import java.util.ArrayList;

/**
 * Class for storing the taskList which stores the tasks
 */
public class TaskList {
    ArrayList<Task> Tasks;

    //Initializes the taskList
    public TaskList() {
        Tasks = new ArrayList<Task>();
    }

    //Adds a task to the task list.
    public void addTask(Task task) {
        Tasks.add(task);
        System.out.println("Got it! Added: \n" + task);
        System.out.println("Now you have " + numOfTasks() + " items in your list");
    }

    //Second add method which does not print any message
    public void silentAdd(Task task) {
        Tasks.add(task);
    }

    //Returns a taskList of the tasks that have matching keyword.
    public void findTask(String keyword) {
        TaskList matches = new TaskList();
        for (Task t : Tasks) {
            String task = t.getTaskName();
            String[] taskName = task.split(" ");
            if (keyword.equals(taskName[0]) || keyword.equals(taskName[1])
                    || keyword.equals(taskName[2])) {
                matches.silentAdd(t);
            } else if (taskName[0].contains(keyword) || taskName[1].contains(keyword)
                    || taskName[2].contains(keyword)) {
                matches.silentAdd(t);
            }
        }
        if (matches.numOfTasks() > 0) {
            System.out.println("We have found the following tasks");
            matches.printTasks();
        } else {
            System.out.println("Sorry! Could not find any matches :(");
        }
    }

    //Delete a task
    public void DeleteTask(int idx) {
        if (idx <= Tasks.size()) {
            System.out.println("Noted. Task removed: \n" + Tasks.get(idx - 1));
            Tasks.remove(idx - 1);
            System.out.println("Now you have " + numOfTasks() + " items in your list");
        } else {
            System.out.println("Sorry! The system does not have that many elements. Try again :(");
        }
    }

    //Marks a task as done
    public void markAsDone(int idx) {
        if (idx <= Tasks.size()) {
            Tasks.get(idx - 1).markDone();
            System.out.println("Task " + idx + " is complete:\n" + Tasks.get(idx - 1));
        } else {
            System.out.println("Sorry! The system does not have that many elements. Try again :(");
        }
    }

    //Retrieves the number of tasks
    public int numOfTasks() {
        return Tasks.size();
    }

    //Retrieves the task at the given index
    public Task getTask(int idx) {
        return Tasks.get(idx);
    }

    //Prints all the tasks
    public void printTasks() {
        System.out.println("Contents: ");
        for (int j = 0; j < Tasks.size(); j++) {
            if (Tasks.get(j) instanceof TodoTask) {
                System.out.println(j + 1 + "." + Tasks.get(j));
            } else if (Tasks.get(j) instanceof DeadlineTask) {
                System.out.println(j + 1 + "." + Tasks.get(j));
            } else if (Tasks.get(j) instanceof EventTask) {
                System.out.println(j + 1 + "." + Tasks.get(j));
            } else {
                System.out.println(j + 1 + "." + Tasks.get(j));
            }
        }
    }
}
