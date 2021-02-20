public class Command {
    protected final int targetIndex;
    protected final IDuke duke;

    protected Command(int targetIndex, IDuke duke) {
        this.targetIndex = targetIndex;
        this.duke = duke;
    }

    public IDuke execute() {
        throw new UnsupportedOperationException("This method is to"
                + " be implemented by child classes.");
    }


        Command setDuke(Duke duke) {
        return new Command(targetIndex, duke);
    }

}


