public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean status){
        super(description,status);
    }

    @Override
    public String getTaskName() {
        return "[T]" + super.getTaskName();
    }



}
