public class Deadline extends Task{



    public Deadline(String description, String deadline) {
        super(description);
        this.date = deadline;
        this.type = TaskEnum.D;
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + date + ")";
    }
}
