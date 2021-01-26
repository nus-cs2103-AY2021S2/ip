public abstract class Task {
    private String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String toString(){
        String string = "";
        //Printing isDone
        string += String.format("[%s] ", isDone?"X":" ");
        string += this.content;
        return string;
    }

    abstract public String toFileString();

    public void setDone() {
        this.isDone = true;
    }

    public String getDesc() {
        return this.content;
    }

    public boolean getDone() {
        return this.isDone;
    }
}
