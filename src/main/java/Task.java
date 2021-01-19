public class Task {
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

    public void setDone() {
        this.isDone = true;
    }
}
