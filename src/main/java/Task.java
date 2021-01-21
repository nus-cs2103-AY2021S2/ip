public class Task {
    //int id;
    String content;
    boolean done = false;
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


