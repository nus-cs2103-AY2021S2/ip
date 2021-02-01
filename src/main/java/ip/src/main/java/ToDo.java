public class ToDo extends Task{

    public ToDo(String content){
        super(content);
    }

    @Override
    public String toString() {
        if(!this.done){
            return "T | 0 | " + super.toString();
        }else {
            return "T | 1 | " + super.toString();
        }
    }
}
