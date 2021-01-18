public class ToDo extends Task{
    String type;
    ToDo(String job) {
        super(job);
        this.type = "T";
    }
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }
}
