public class Task {
    private final Boolean isDone;
    protected final String name;

    Task(String name,Boolean isDone){
        this.isDone = isDone;
        this.name = name;
    }

    String getName(){
        return this.name;
    }


    Boolean getIsDone(){
        return this.isDone;
    }

    Task finish(){
        return new Task(this.getName(), true) ;
    }

    @Override
    public String toString(){
        if(this.isDone){
            return "[X] " + this.getName();
        }
        else{
            return "[ ] " + this.getName();
        }
    }
}
