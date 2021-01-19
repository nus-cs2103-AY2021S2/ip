public class Event extends Task{
    protected final String time;
    Event(String name, int id, Boolean isDone,String time){
        super(name,id,isDone);
        this.time = time;

    }
    String getTime(){
        return this.time;
    }
    @Override
    Event finish(){
        return new Event(this.getName(),this.getId(), true,this.getTime()) ;
    }
    @Override
    public String toString(){
        if(this.getIsDone()){
            return "[E][X] "+ this.id + "." + this.name + "(" + this.time + ")";
        }
        else{
            return "[E][ ] "+ this.id + "." + this.name + "(" + this.time + ")";
        }
    }
}

