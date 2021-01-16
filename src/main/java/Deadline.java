public class Deadline extends Task{
    private String deadline;


    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.type = TaskEnum.D;
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + deadline + ")";
    }
}
