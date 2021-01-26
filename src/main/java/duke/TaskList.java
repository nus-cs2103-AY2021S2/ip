package duke;

import java.util.ArrayList;

class TaskList {
	
	private final ArrayList<Task> list;

	TaskList() {
		this.list = new ArrayList<Task>();
	}

	/**
	 * creates a TaskList from a given Task ArrayList
	 * @param a ArrayList of Task
	 */
	TaskList(ArrayList<Task> a) {
		this.list = a;
	}

	/**
	 * get the entire Task ArrayList
	 * @return Task ArrayList
	 */
	ArrayList<Task> get() {
		return this.list;
	}

	/**
	 * get the task at the given index with 1-based indexing
	 * @param n index of task
	 * @return task at index n (1-based indexing)
	 */
	Task get(int n) {
		return this.list.get(n - 1);
	}

	/**
	 * Appends the task at the end of the list
	 * @param t Task to be appended
	 */
	void store(Task t) {
		this.list.add(t);
	}

	/**
	 * Deletes the given task in the list
	 * @param t the task to be removed from the list
	 */
	void delete(Task t) {
		this.list.remove(t);
	}

	/**
	 * resets the list into an empty list
	 */
	void clear() {
		this.list.clear();
	}

	/**
	 * returns the number of items in the list
	 * @return the number of items in the list
	 */
	int size() {
		return this.list.size();
	}

	/**
	 * Provides a string containing the Tasks in the that return true for onDay
	 * @param day the given day
	 * @return string in the form of a list of the tasks to be done on that day
	 */
	String tasksOnDay(String day) {
		String s = "";
		int count = 1;
		for (int i = 1; i <= this.size(); i += 1) {
			Task t = this.list.get(i - 1);
			if (t.onDay(day)) {
				s += String.format("      %d.  %s\n", count, t);
				count += 1;
			}
		}
		return s;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 1; i <= this.list.size(); i += 1) {
			s += String.format("      %d.  %s\n", i, this.list.get(i - 1));
		}
		return s;
	}
}
