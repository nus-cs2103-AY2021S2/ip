package level4;

public class Todo extends Task{ //similar to prev
    //simpler
    public Todo(String detailFromTask){
        super(detailFromTask); //subclass things
    }

    @Override
     public char forIconGetWhatIsTaskType() {
        return 'T'; //follow the specification
    }
    
    @Override
    public String toString() { //string to string method
        return String.format("[%c]%s",
                this.forIconGetWhatIsTaskType(), super.toString());
    }
}