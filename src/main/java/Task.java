
public class Task {
    String name;
    boolean done;

    Task( String name){
        this.name = name;
        this.done = false;
    }


    public String getName() {
        return name;
    }

    public void setDone(boolean bool){
        this.done = bool;
    }

    public boolean isDone(){
        return this.done;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X]    " + name;
        } else {
            return "[ ]    " + name;
        }
    }

}
