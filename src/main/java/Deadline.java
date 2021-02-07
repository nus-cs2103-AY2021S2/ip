public class Deadline extends Task {

    String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString() + " (by:" + this.time + ")";
    }
}
