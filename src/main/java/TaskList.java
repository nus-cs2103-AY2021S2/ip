import java.util.ArrayList;

class TaskList {
	
	private final ArrayList<Task> list;

	TaskList() {
		this.list = new ArrayList<Task>();
	}

	TaskList(ArrayList<Task> a) {
		this.list = a;
	}

	ArrayList<Task> get() {
		return this.list;
	}

	Task get(int n) {
		return this.list.get(n - 1);
	}

	Task store(Task t) {
		this.list.add(t);
		return t;
	}

	void delete(Task t) {
		this.list.remove(t);
	}

	int size() {
		return this.list.size();
	}

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
