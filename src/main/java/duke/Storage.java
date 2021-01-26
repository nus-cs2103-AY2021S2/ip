package duke;

public class Storage {
    String taskListStr;
    String filePath;
    public Storage(String filePath){
        this.taskListStr="";
        this.filePath = filePath;
    }

    protected void getLastSave(TaskList taskList){
        this.taskListStr = FileReading.readFile(filePath);
        System.out.println("These are the tasks on your todolist");
        System.out.println(taskListStr);
        addSavedTasks(taskList);
    }

    protected  void addSavedTasks(TaskList taskList){
        String[] lines = taskListStr.split("\n");
        for (int i = 0; i < lines.length ; i++) {
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

    protected static void save(Ui ui, TaskList taskList){
        FileWriting.writeToFile(ui.listTasks(taskList));
    }
}
