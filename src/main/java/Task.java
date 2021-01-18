public class Task {
    Boolean isDone;
    String name;
    int id;

    Task(String name,int id){
        this.isDone = false;
        this.name = name;
        this.id = id;
    }

    void finish(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        if(this.isDone){
            return "[X] "+ this.id + "." + this.name;
        }
        else{
            return "[ ] "+ this.id + "." + this.name;
        }
    }
}
