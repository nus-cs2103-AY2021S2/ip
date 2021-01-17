public class Task {
    protected String title;
    protected boolean status;

    public Task(String title){
        this.title = title;
        this.status = false;
    }

    public void setCompleted(){
        this.status = true;
    }

    public String isCompleted(){

        return (this.status ? "\u2718" : " ");
    }

    public String getTaskName(){
        return this.title;
    }

    @Override
    public String toString() {
        return "[" + isCompleted() + "] " + this.getTaskName();
    }
}
