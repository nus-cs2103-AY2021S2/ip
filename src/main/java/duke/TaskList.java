package duke;

import java.util.ArrayList;

class TaskList {
	
	private final ArrayList<Task> list;
	private final Storage storage;

	TaskList(Storage s) {
		this.list = new ArrayList<Task>();
		this.storage = s;
	}

	/**
	 * creates a TaskList from a given Task ArrayList
	 * @param a ArrayList of Task
	 */
	TaskList(ArrayList<Task> a, Storage s) {
		this.list = a;
		this.storage = s;
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
		this.storage.save(this.list);
	}

	/**
	 * Deletes the given task in the list
	 * @param t the task to be removed from the list
	 */
	void delete(Task t) {
		this.list.remove(t);
		this.storage.save(this.list);
	}

	/**
	 * resets the list into an empty list
	 */
	void clear() {
		this.list.clear();
		this.storage.save(this.list);
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
		String output = "";
		int count = 1;
		for (int i = 1; i <= this.size(); i += 1) {
			Task t = this.list.get(i - 1);
			if (t.onDay(day)) {
				output += String.format("      %d.  %s\n", count, t);
				count += 1;
			}
		}
		return output;
	}

	String find(String s) {
		String output = "";
		int count = 1;
		for (int i = 1; i <= this.size(); i += 1) {
			Task t = this.list.get(i - 1);
			if (t.contains(s)) {
				output += String.format("      %d.  %s\n", count, t);
				count += 1;
			}
		}
		return output;
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
