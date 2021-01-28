//tasks without any date/time attached to it
public class ToDo extends Task{
    //invoke super constructor
    ToDo(String toDoDetail){
        super(toDoDetail);
    }
    @Override
    public String toString(){
        return "[T]" + " | " + super.toString();
    }
}
