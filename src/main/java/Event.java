public class Event extends Task{
    String time;
    Event(String name, int id, String time){
        super(name,id);
        this.time = time;

    }
    @Override
    public String toString(){
        if(this.isDone){
            return "[E][X] "+ this.id + "." + this.name + "(" + this.time + ")";
        }
        else{
            return "[E][ ] "+ this.id + "." + this.name + "(" + this.time + ")";
        }
    }
}

