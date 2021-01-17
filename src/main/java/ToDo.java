public class ToDo extends Task {

    public ToDo(int id, String description){
        super(id, description);
    }

    @Override
    public String toString(){
    return "[T]" + super.toString();
    }
}
