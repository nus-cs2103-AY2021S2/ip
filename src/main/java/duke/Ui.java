package duke;

import java.time.LocalDate;

class Ui {

	/**
	 * displays the welcome message
	 */
	static void welcome() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Hello! I'm Duke");
		System.out.println("      What can I do for you?");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	/**
	 * displays the message when an invalid input is given
	 */
	static void invalidInput() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	/**
	 * displays the message when the task has an empty description
	 * @param s name of task
	 */
	static void emptyDescription(String s) {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      ☹ OOPS!!! The description of a " + s + " cannot be empty.");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	/**
	 * displays the message when the task is stored
	 * @param t task that was stored
	 * @param n the number of tasks in the list
	 */
	static void store(Task t, int n) {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Got it. I've added this task: ");
		System.out.println("      " + t);
		System.out.println("      now you have " + n + " tasks in the list.");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	/**
	 * displays the message when the tasks are listed
	 * @param t the given TaskList
	 */
	static void list(TaskList t) {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Here are the tasks in your list:");
		System.out.printf("%s", t.toString());
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	/**
	 * displays the message when the task is marked as done
	 * @param t the Task to be marked as done
	 */
	static void done(Task t) {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Nice! I've marked this task as done:");
		System.out.println("      " + t);
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	/**
	 * displays the message when the task is to be deleted
	 * @param t the Task to be deleted
	 * @param n the size of TaskList after deletion
	 */
	static void delete(Task t, int n) {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Noted. I've removed this task:");
		System.out.println("      " + t);
		System.out.println("      now you have " + n + " tasks in the list.");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	/**
	 * displays the message showing the Tasks on the given day
	 * @param t the given TaskList
	 * @param s the day that is given in yyy-mm-dd format (e.g. 2021-01-31)
	 */
	static void tasksOnDay(TaskList t, String s) {
		LocalDate day = LocalDate.parse(s);
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      Here are the tasks on " + day.toString() + ":");
		System.out.printf("%s", t.tasksOnDay(s));
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}

	static void clear() {
		System.out.println("    ____________________________________________________________");
		System.out.println("");
		System.out.println("      The list has been cleared");
		System.out.println("    ____________________________________________________________");
		System.out.println("");
	}
}
