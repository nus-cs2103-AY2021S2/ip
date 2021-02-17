package duke;

import java.util.ArrayList;

import duke.expenses.Expense;

public class ExpensesList {
    private ArrayList<Expense> expenses;

    /**
     * Constructs an ExpensesList object using a collection of Expense objects.
     * @param expenses an ArrayList of Expense objects
     */
    public ExpensesList(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * Constructs an empty ExpensesList object.
     */
    public ExpensesList() {
        expenses = new ArrayList<>();
    }

    /**
     * Obtains an ArrayList of Expense objects associated with the ExpensesList.
     * @return ArrayList of Expense objects
     */
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Deletes an expense at the index given from the ExpensesList.
     * @param index Index of Expense to delete
     * @return Expense object that is deleted
     */
    public Expense delete(int index) {
        assert(index >= 0);
        return expenses.remove(index - 1);
    }

    /**
     * Returns an ExpensesList of the Expenses that has the given keyword in their descriptions.
     * @param keyword Keyword
     * @return ArrayList of Expense objects
     */
    public ExpensesList find(String keyword) {
        ArrayList<Expense> arr = new ArrayList<>();
        for (Expense e : expenses) {
            boolean gotKeyword = e.getDescription().contains(keyword);
            if (gotKeyword) {
                arr.add(e);
            }
        }
        return new ExpensesList(arr);
    }

    /**
     * Adds an Expense to the ExpensesList.
     * @param expense Expense
     */
    public void add(Expense expense) {
        assert(expense != null);
        expenses.add(expense);
    }

    /**
     * Returns the sum of the Expenses' amounts in this ExpensesList.
     * @return Double amount
     */
    public double sumUp() {
        double sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getCashAmount();
        }
        return sum;
    }
}
