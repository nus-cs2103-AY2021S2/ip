public class Task {
    private static int numOfTasks;
    private String taskName;
    private boolean isTaskCompleted;

    public Task(String input) throws EmptyTaskDukeException {
        if (input.replaceAll("\\s+","").equals("")) {
            throw new EmptyTaskDukeException();
        } else {
            this.taskName = input;
            isTaskCompleted = false;
        }
    }

    public String setDone() {
        setTaskCompleted();
        return "Nice! I've marked this task as done: \n"
                + toString();
    }

    public void setTaskCompleted() {
        isTaskCompleted = true;
    }

    public boolean getIsTaskCompleted() {
        return isTaskCompleted;
    }

    public String getTaskName() {
        return taskName;
    }

    private static int getNumOfTasks() {
        return numOfTasks;
    }

    public static void incrementNumOfTask() {
        numOfTasks++;
    }

    public static String getNumOfTasksString() {
        return "Now you have " + getNumOfTasks()
                + (getNumOfTasks() == 1 ? " task" : " tasks")
                + " in the list.";
    }
}
