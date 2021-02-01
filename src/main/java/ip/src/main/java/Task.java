package ip.src.main.java;

public class Task {
    //int id;
    String content;
    public boolean done = false;
    public Task(String content) {
        //this.id = id;
        this.content = content;

    }

    public void markDone(){
        this.done = true;

    }

    public String toString(){
        return this.content;
    }

}


