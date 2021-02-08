package duke.expenses;

import java.time.LocalDate;
import java.util.Date;

public class Expense {
    private String description;
    private double cashAmount;
    private LocalDate dateOfTransaction;

    public Expense(String description, double cashAmount, LocalDate dateOfTransaction) throws IllegalArgumentException {
        this.description = description;
        this.cashAmount = cashAmount;
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getDescription() {
        return description;
    }

    public double getCashAmount() {
        return cashAmount;
    }

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
