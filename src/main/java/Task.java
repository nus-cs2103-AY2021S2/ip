public class Task {
    public int number;
    public boolean done;
    public String name;

    public Task(int number, String name) {
        this.number = number;
        this.done = false;
        this.name = name;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void addTask(int count) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task: ");
        System.out.println(this.toString());
        if(count == 1) {
            System.out.println("    Now you have " + count + " task in the list.");
        } else {
            System.out.println("    Now you have " + count + " tasks in the list.");
        }

        System.out.println("    ____________________________________________________________");
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