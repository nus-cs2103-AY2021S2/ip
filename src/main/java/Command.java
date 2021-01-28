public class Command {
    protected final int targetIndex;
    protected final Duke duke;

    protected Command(int targetIndex, Duke duke) {
        this.targetIndex = targetIndex;
        this.duke = duke;
    }
    
    public Duke execute() {
        throw new UnsupportedOperationException("This method is to"
                + " be implemented by child classes.");
    }


    public Command setDuke(Duke duke) {
        return new Command(targetIndex, duke);
    }

}


