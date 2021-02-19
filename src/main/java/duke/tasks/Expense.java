package duke.tasks;

/**
 * Expense is a type of Task which has a description and amount.
 * It also maintains a state of isDone.
 */
public class Expense extends Task {
    protected String amt;

    public Expense(String description, String amt) {
        super(description);
        this.amt = amt;
    }


    public Expense(String description, boolean isDone, String amt) {
        super(description, isDone);
        this.amt = amt;
    }

    /**
     * Returns string representation of Expense when saving locally.
     *
     * @return String of Expense when saving.
     */
    public String saveString() {
        String separatorFormatString = " --- ";
        String eventDetails = description + separatorFormatString + amt;

        String eventDoneRepresentation = "Ex --- 1 --- ";
        String eventDoneString = eventDoneRepresentation + eventDetails;

        String eventNotDoneRepresentation = "Ex --- 0 --- ";
        String eventNotDoneString = eventNotDoneRepresentation + eventDetails;

        return isDone ? eventDoneString : eventNotDoneString;
    }

    /**
     * Returns string representation of Expense.
     *
     * @return String of Expense.
     */
    @Override
    public String toString() {
        return "[Ex]" + super.toString() + " -> amount spent: " + amt;
    }
}
