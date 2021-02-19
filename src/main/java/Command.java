public class Command {
    String commandDescription;
    boolean isDone;

    // Constructor of Command class
    public Command(String commandDescription) {
        this.commandDescription = commandDescription;
        this.isDone = false;
    }

    public Command markDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.commandDescription;
        } else {
            return "[ ] " + this.commandDescription;
        }
    }

}
