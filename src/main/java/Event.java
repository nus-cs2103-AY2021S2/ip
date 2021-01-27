public class Event extends Task{
    protected final String time;
    Event(String name, Boolean isDone,String time){
        super(name,isDone);
        this.time = time;

    }
    String getTime(){
        return this.time;
    }
    @Override
    Event finish(){
        return new Event(this.getName(), true,this.getTime()) ;
    }
    @Override
    public String toString(){
        if(this.getIsDone()){
            return "[E][X] " + this.name + "(" + this.time + " )";
        }
        else{
            return "[E][ ] " + this.name + "(" + this.time + " )";
        }
    }
}

