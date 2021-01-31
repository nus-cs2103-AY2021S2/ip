package duke;

/**
 * Represents the stored data of the Duke.
 */
public class Storage {
    String taskListStr;
    String filePath;

    /**
     * Only constructor for Storage class.
     * Sets the filepath for the stored file.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.taskListStr = "";
        this.filePath = filePath;
    }

    /**
     * Obtains the last save of tasks as a string and stores it as an instance variable.
     * Parses the string and inteprets them as tasks that are stored in the current instance
     * of taskList in Duke.
     *
     * @param taskList list of current tasks that the duke has.
     */
    protected void getLastSave(TaskList taskList) {
        this.taskListStr = FileReading.readFile(filePath);
        System.out.println("These are the tasks on your todolist");
        System.out.println(taskListStr);
        addSavedTasks(taskList);
    }

    /**
     * Parses string, in the "list" format, and interprets them into commands that the current
     * instance of Duke should have.
     *
     * @param taskList list of current tasks that the duke has.
     */
    protected void addSavedTasks(TaskList taskList) {
        String[] lines = taskListStr.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].contains(Duke.line)) {
                String[] details = lines[i].split(" ", 2);
                Task currTask;
                if (details[0].contains("T")) {
                    currTask = new Todo("todo" + details[1]);
                } else if (details[0].contains("E")) {
                    String[] nameDate = details[1].split("at:");
                    currTask = new Event("event" + nameDate[0].strip() + "/at" + nameDate[1].strip());
                } else {
                    String[] nameDate = details[1].split("by:");
                    currTask = new Deadline("deadline" + nameDate[0].strip() + "/by" + nameDate[1].strip());
                }
                if (details[0].contains("X")) {
                    currTask.isDone = true;
                }
                taskList.list.add(currTask);
            }
        }
    }

    protected void save(Ui ui, TaskList taskList) {
        FileWriting.write(filePath, ui.listTasks(taskList));
    }
}
