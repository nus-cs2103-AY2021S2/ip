public class ToDo extends Task{
    ToDo(String name){
        super(name);
    }

    ToDo(String name, boolean done){

        super(name, done, "[T]");
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
