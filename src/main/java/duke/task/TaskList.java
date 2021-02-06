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
    public String addTask(Task task) {
        Tasks.add(task);
        String out = "Got it! Added: \n" + task + "\n"
                + "Now you have " + numOfTasks() + " items in your list";
        return out;
    }

    //Second add method which does not print any message
    public void silentAdd(Task task) {
        Tasks.add(task);
    }


    //Returns a taskList of the tasks that have matching keyword.
    public String findTask(String keyword) {
        String out = "";
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
            out += "We have found the following tasks\n";
            System.out.println("We have found the following tasks");
            out += matches.printTasks();
        } else {
            out += "Sorry! Could not find any matches :(";
        }
        return out;
    }

    //Delete a task
    public String DeleteTask(int idx) {
        String out = "";
        if (idx <= Tasks.size()) {
            out += "Noted. Task removed: \n" + Tasks.get(idx - 1) + "\n";
            Tasks.remove(idx - 1);
            out += "Now you have " + numOfTasks() + " items in your list";
        } else {
            out += "Sorry! The system does not have that many elements. Try again :(";
        }
        return out;
    }

    //Marks a task as done
    public String markAsDone(int idx) {
        String out;
        if (idx <= Tasks.size()) {
            Tasks.get(idx - 1).markDone();
            out = "Task " + idx + " is complete:\n" + Tasks.get(idx - 1);
        } else {
            out = "Sorry! The system does not have that many elements. Try again :(";
        }
        return out;
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
    public String printTasks() {
        String out = "Contents: \n";
        for (int j = 0; j < Tasks.size(); j++) {
            if (Tasks.get(j) instanceof TodoTask) {
                out += j + 1 + "." + Tasks.get(j) + "\n";
            } else if (Tasks.get(j) instanceof DeadlineTask) {
                out += j + 1 + "." + Tasks.get(j) + "\n";
            } else if (Tasks.get(j) instanceof EventTask) {
                out += j + 1 + "." + Tasks.get(j) + "\n";
            } else {
                out += j + 1 + "." + Tasks.get(j) + "\n";
            }
        }
        return out;
    }
}
