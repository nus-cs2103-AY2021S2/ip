public class EmptyDescription extends DukeException{
    protected String TypeOfTask;
    EmptyDescription(String TypeOfTask) {
        this.TypeOfTask = TypeOfTask;
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a " + this.TypeOfTask + " cannot be empty.";
    }
}
