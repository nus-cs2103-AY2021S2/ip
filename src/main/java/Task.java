package main.java;

public class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int doneInt) {
        this.description = description;
        if (doneInt == 1){
            this.isDone = true;
        }else{
            this.isDone = false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public void changeTaskToDone(){
        isDone = true;
    }

    public String toSaveFormat(){
        int isDoneInt = isDone ? 1 : 0;
        return isDoneInt + "|" + description;
    }
}
