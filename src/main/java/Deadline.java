public class Deadline extends Task {

    String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + this.time + ")";
    }
}
