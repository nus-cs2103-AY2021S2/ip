public class Deadline extends Task{
    protected final String deadline;

    public Deadline(String description, String deadline) {
        super(description, "[D]");
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String[] str = deadline.split(" ");
        String date = "";
        for(int i = 1; i < str.length; i++) {
            date = date.concat(str[i]);
            if(i != str.length-1) {
                date = date + " ";
            }
        }
        return super.toString() + " (by: " + date + ")";
    }
}
