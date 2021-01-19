public class ToDo implements Task {

    private String taskDescription;

    public ToDo(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public String toString() {
        return this.taskDescription;
    }
}
