public class EmptyDescription extends DukeException{
    protected String typeOfTask;
    EmptyDescription(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a " + this.typeOfTask + " cannot be empty.\n";
    }
}
