public class Save {
    String taskListStr;
    Duke duke;
    public Save(Duke duke){
        this.taskListStr="";
        this.duke = duke;
    }

    public void getLastSave(){
        this.taskListStr = FileReading.readFile("data/duke.txt");
        System.out.println("These are the tasks on your todolist");
        System.out.println(taskListStr);
        addSavedTasks();
    }

    public void run(){
        duke.start();
    }

    private  void addSavedTasks(){
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
                duke.list.add(currTask);
            }
        }
    }

    public static void save(Duke duke){
        FileWriting.writeToFile(duke.listTasks());
    }
}
