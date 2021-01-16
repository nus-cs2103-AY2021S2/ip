public class Task {
    public String taskDesc;
    public boolean isCompleted; //Task 1.3

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isCompleted = false;
    }

    public void setCompleted(){
        this.isCompleted = true;
    }

    @Override
    public String toString(){
        return this.taskDesc;
    }
}
