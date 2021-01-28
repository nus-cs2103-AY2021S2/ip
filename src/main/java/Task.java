public class Task {
    public boolean done;
    public String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void addTask(int count) throws DukeException{
        if(this.name.equals("todo")) {
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + this.toString());
            if (count == 1) {
                System.out.println("     Now you have " + count + " task in the list.");
            } else {
                System.out.println("     Now you have " + count + " tasks in the list.");
            }
        }
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
