public class Task {
    private final Boolean isDone;
    protected final String name;
    protected final int id;

    Task(String name,int id,Boolean isDone){
        this.isDone = isDone;
        this.name = name;
        this.id = id;
    }

    String getName(){
        return this.name;
    }

    int getId(){
        return this.id;
    }

    Boolean getIsDone(){
        return this.isDone;
    }

    Task finish(){
        return new Task(this.getName(),this.getId(), true) ;
    }

    @Override
    public String toString(){
        if(this.isDone){
            return "[X] "+ this.getId() + "." + this.getName();
        }
        else{
            return "[ ] "+ this.getId() + "." + this.getName();
        }
    }
}
