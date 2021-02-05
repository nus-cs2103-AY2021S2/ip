import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int taskNo) {
        return this.taskList.get(taskNo - 1);
    }

    public void addTask(TaskType taskType, String description) throws DukeException {
        Task newTask = new Task(description);
        if (taskType == TaskType.TODO) {
            newTask = new ToDoTask(description);
        } else if (taskType == TaskType.DEADLINE) {
            String[] descriptionArr = description.split(" /by ");
            if (descriptionArr.length == 1) {
                throw new DukeException("Your description is not given in the correct format!");
            }
            newTask = new DeadlineTask(descriptionArr[0], descriptionArr[1]);
        } else if (taskType == TaskType.EVENT) {
            String[] descriptionArr = description.split(" /at ");
            if (descriptionArr.length == 1) {
                throw new DukeException("Your description is not given in the correct format!");
            }
            newTask = new EventTask(descriptionArr[0], descriptionArr[1]);
        }
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task: \n" +
                "  " + newTask + "\n" +
                "Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void deleteTask(int taskNo) throws DukeException {
         if (taskNo > this.taskList.size()) {
            throw new DukeException("☹ OOPS!!! This task number does not exist.");
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + this.taskList.get(taskNo - 1));
        this.taskList.remove(taskNo - 1);
        String taskOrTasks = (this.taskList.size() <= 1)
                ? " task"
                : " tasks";
        System.out.println("Now you have " + this.taskList.size() + taskOrTasks + " in the list.");
    }

    public void printTaskList() {
        if (this.taskList.size() == 0) {
            System.out.println("There are currently no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= this.taskList.size(); i++) {
                System.out.println(i + "." + this.taskList.get(i - 1));
            }
        }
    }

}
