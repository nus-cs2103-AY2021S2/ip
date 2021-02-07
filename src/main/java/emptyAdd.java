public class emptyAdd extends Command {

    public emptyAdd(String commandDescription) {
        super(commandDescription);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[ ] " + super.toString();
    }

}
