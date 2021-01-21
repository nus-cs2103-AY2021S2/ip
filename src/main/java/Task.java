public class Task {
    public int number;
    public boolean done;
    public String name;

    public Task(int number, boolean done, String name) {
        this.number = number;
        this.done = done;
        this.name = name;
    }
    public void markAsDone() {
        this.done = true;
    }
    @Override
    public String toString() {
        String outString;
        if(this.done) {
            outString = "[X] " + this.name;
        } else {
            outString = "[ ] " + this.name;
        }
        return outString;
    }
}
