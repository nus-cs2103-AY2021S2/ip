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

    //Getters
    public String getDescription() {
        return this.commandDescription;
    }

    public String getDone() {
        if (isDone) {
            return " 1 ";
        } else {
            return " 0 ";
        }
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
