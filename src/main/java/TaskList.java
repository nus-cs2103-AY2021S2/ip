public class TaskList {
    private static final int MAX_TASKS = 100;
    public Task[] tasks;
    public int numTask;

    public TaskList(Task[] tasks) {
        this.tasks = tasks;
        int i = 0;
        while (tasks[i] != null) {
            i++;
        }
        this.numTask = i;
    }

    public TaskList() {
        this(new Task[MAX_TASKS]);
    }

    public String listTask() {
        int i = 0;
        String listTaskMessage = "Here are the tasks in your list:\n";
        while (i < numTask) {
            Task t = tasks[i];
            i++;
            listTaskMessage += i + ". " + t.toString() + "\n";
        }
        return listTaskMessage;
    }

    public String addTask(String taskType, String taskDesc) {
        String addTaskMessage;
        if (taskDesc.equals("")) {
            addTaskMessage = "Usage: " + taskType + " [description of task]";
            if (taskType.equals("deadline")) {
                addTaskMessage += " /by [e.g. 2021-02-17]";
            }
            if (taskType.equals("event")) {
                addTaskMessage += " /at [e.g. 2021-02-17]";
            }
            return addTaskMessage;
        }

        String taskName, taskByAt;

        switch (taskType) {
        case "todo":
            tasks[numTask] = new Task(taskDesc);
            break;
        case "deadline":
            taskName = new SplitString(taskDesc, "/by ").getFirstString();
            taskByAt = new SplitString(taskDesc, "/by ").getSecondString();
            tasks[numTask] = new Deadline(taskName, taskByAt);
            break;
        case "event":
            taskName = new SplitString(taskDesc, "/at ").getFirstString();
            taskByAt = new SplitString(taskDesc, "/at ").getSecondString();
            tasks[numTask] = new Event(taskName, taskByAt);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }

        addTaskMessage = "Got it. I've added this task: \n   " + tasks[numTask].toString() + "\n";
        numTask++;
        addTaskMessage += "Now you have " + numTask + " task";
        addTaskMessage += numTask == 1 ? "" : "s";
        addTaskMessage += " in the list.";

        return addTaskMessage;
    }

    public String doTask(String description) {
        if (description.equals("")) {
            return "Usage: done [task number]";
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(description);
        } catch (Exception e) {
            return "Not possible to do task " + description + ".";
        }
        if (taskNum <= 0 || taskNum > numTask) {
            return "Not possible to do task " + description + ".";
        }

        Task t = tasks[taskNum - 1];
        t.markAsDone();

        return "Nice! I've marked this task as done: \n" + "   " + t.toString();
    }

    public String deleteTask(String description) {
        if (description.equals("")) {
            return "Usage: delete [task number]";
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(description);
        } catch (Exception e) {
            return "Not possible to remove task " + description + ".";
        }
        if (taskNum <= 0 || taskNum > numTask) {
            return "Not possible to remove task " + description + ".";
        }

        Task t = tasks[taskNum - 1];
        int i = taskNum;
        while (i < MAX_TASKS) {
            tasks[i - 1] = tasks[i];
            i++;
        }
        numTask--;

        String deleteTaskMessage = "Noted, I've removed this task: \n" + "   " + t.toString() + "\n";
        deleteTaskMessage += "Now you have " + numTask + " task";
        deleteTaskMessage += numTask == 1 ? "" : "s";
        deleteTaskMessage += " in the list.";

        return deleteTaskMessage;
    }
}
