public class Task {
    String name;
    boolean done;

    Task( String name){
        this.name = name;
        this.done = false;
    }

    public boolean getDone(){
        return this.done;
    }

    public void setDone(boolean bool){
        this.done = bool;
    }

    @Override
    public String toString() {
        if (done){
            return  "[X]    " + name;
        }else{
            return  "[ ]    " + name;
        }
    }
}
