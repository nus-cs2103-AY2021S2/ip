package duke;

class Deadline extends Task{
    private String deadline;

    /**
     * duke.Deadline constructor.
     *
     * @param content duke.Task description
     * @param deadline The deadline task has to be completed by.
     */
    Deadline(String content,String deadline){
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]"+super.toString()+ String.format(" (by:%s)",this.parseDate(this.deadline.strip()));
    }

    @Override
    public String toFileString() {
        String done;
        if (this.getDone()) {
            done = "1";
        } else {
            done = "0";
        }
        String string = "D|"+ done + "|" + this.getDesc()+ "|" + this.deadline;
        return string;
    }
}
