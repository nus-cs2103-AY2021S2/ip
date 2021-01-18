public class Task {
    private static String[] Tasks = new String[100];
    private static int numOfTasks = 0;
    private String taskName;

    public Task(){
        taskName = "";
        Tasks[numOfTasks] = taskName;
        numOfTasks += 1;
    }

    public Task(String taskName){
        this.taskName = taskName;
        Tasks[numOfTasks] = taskName;
        numOfTasks += 1;
    }

    public static int getNumOfTasks(){
        return numOfTasks;
    }

    public static String[] getTasks(){
        return Tasks;
    }

}
