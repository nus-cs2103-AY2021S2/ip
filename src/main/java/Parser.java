public class Parser {

    public static String parseTaskToString (Task task) {
        String taskIsDone, taskType, taskName, taskByAt;

        taskIsDone = task.getIsDone() ? "1" : "0";
        taskName = task.getDescription();
        taskByAt = "";

        if (task instanceof Deadline) {
            taskType = "deadline ";
            taskByAt = "/by " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            taskType = "event ";
            taskByAt = "/at " + ((Event) task).getAt();
        } else {
            taskType = "todo ";
        }

        return taskIsDone + "," + taskType + taskName + taskByAt;
    }

    public static Task parseStringToTask (String taskString) {
        Task t;
        String taskCommand, taskDesc, taskName, taskByAt;

        taskCommand = new SplitString(taskString, " ").getFirstString(); // e.g. "event"
        taskDesc = new SplitString(taskString, " ").getSecondString(); // e.g. "randomEvent /at ..."
        switch (taskCommand) {
        case "todo":
            t = new Task(taskDesc);
            break;
        case "deadline":
            taskName = new SplitString(taskDesc, "/by ").getFirstString();
            taskByAt = new SplitString(taskDesc, "/by ").getSecondString();
            t = new Deadline(taskName, taskByAt);
            break;
        case "event":
            taskName = new SplitString(taskDesc, "/at ").getFirstString();
            taskByAt = new SplitString(taskDesc, "/at ").getSecondString();
            t = new Event(taskName, taskByAt);
            break;
        case "":
            t = null;
        default:
            throw new IllegalStateException("Unexpected value: " + taskCommand);
        }

        return t;
    }

}
