public class ToDo extends Task{

    public ToDo(String content){
        super(content);
    }

    @Override
    public String toString() {
        if(!this.done){
            return "[T][ ] " + super.toString();
        }else {
            return "[T][X] " + super.toString();
        }
    }
}
