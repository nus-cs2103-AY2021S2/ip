public class Task {
    String type;
    String name;
    boolean done;

    Task(String type, String name, boolean done){
        this.type = type;
        this.name = name;
        this.done = done;
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
            return "[" + type + "]" + "[X]" + name ;
        }else{
            return "[" + type + "]" + "[ ]" + name;
        }
    }
}
