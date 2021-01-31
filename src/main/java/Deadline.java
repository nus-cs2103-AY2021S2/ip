public class Deadline extends Task {
    
    protected final String dueDate;

    /**
     * Factory method to create a new Deadline.
     * @param input - Input for the Deadline in the form "{ name } /by { date }".
     * @return - New Deadline object.
     */
    public static Deadline makeDeadline(String input) throws ChecklstException {
        String[] splitInput = input.split(" /by ");
        if (splitInput.length == 1) {
            throw new ChecklstException("Inproper Deadline format used! Please use { name } /by { deadline }");
        }

        return new Deadline(splitInput[0], splitInput[1]);
    }

    protected Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    protected Deadline(String name, boolean completed, String dueDate) {
        super(name, completed);
        this.dueDate = dueDate;
    }

    @Override
    public Task complete() {
        return new Deadline(this.name, true, this.dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate;
    }

}
