package duke;

import java.util.ArrayList;

import duke.expenses.Expense;

public class ExpensesList {
    private ArrayList<Expense> expenses;

    public ExpensesList() {
        expenses = new ArrayList<>();
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public ExpensesList(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public Expense delete(int index) {
        assert(index >= 0);
        return expenses.remove(index - 1);
    }

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

    public void add(Expense expense) {
        assert(expense != null);
        expenses.add(expense);
    }

    public double sumUp() {
        double sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getCashAmount();
        }
        return sum;
    }
}
