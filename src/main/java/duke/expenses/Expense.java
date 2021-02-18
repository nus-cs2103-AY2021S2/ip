package duke.expenses;

import java.time.LocalDate;

public class Expense {
    private String description;
    private double cashAmount;
    private LocalDate dateOfTransaction;

    /**
     * Constructs an Expense object.
     *
     * @param description String describing what the expense is about
     * @param cashAmount Cash Amount spent
     * @param dateOfTransaction Date the expense is made
     * @throws IllegalArgumentException when the type of input is invalid
     */
    public Expense(String description, double cashAmount, LocalDate dateOfTransaction) throws IllegalArgumentException {
        assert(cashAmount > 0);
        this.description = description;
        this.cashAmount = cashAmount;
        this.dateOfTransaction = dateOfTransaction;
    }

    /**
     * Returns a String of description of an Expense object.
     *
     * @return String of description
     */
    public String getDescription() {
        return description;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    /**
     * Returns a String describing an Expense object, which includes
     * description, cash amount, and the date of expense.
     *
     * @return String describing an Expense
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Way: ").append(description).append(", Amount: ").append(cashAmount)
                .append(", Date: ").append(dateOfTransaction);
        return buffer.toString();
    }

    public String getExpenseDetails() {
        String divider = " | ";
        return description + divider + cashAmount + divider + dateOfTransaction;
    }
}
