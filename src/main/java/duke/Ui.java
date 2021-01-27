package duke;

import java.time.LocalDate;

class Ui {


	static void welcome() {
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      Hello! I'm Duke");
		System.out.println("      What can I do for you?");
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}

	static void invalidInput() {
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}

	static void emptyDescription(String s) {
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      ☹ OOPS!!! The description of a " + s + " cannot be empty.");
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}

	static void store(Task t, int n) {
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      Got it. I've added this task: ");
		System.out.println("      " + t);
		System.out.println("      now you have " + n + " tasks in the list.");
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}

	static void list(TaskList t) {
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      Here are the tasks in your list:");
		System.out.printf("%s", t.toString());
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}

	static void done(Task t) {
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      Nice! I've marked this task as done:");
		System.out.println("      " + t);
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}

	static void delete(Task t, int n) {
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      Noted. I've removed this task:");
		System.out.println("      " + t);
		System.out.println("      now you have " + n + " tasks in the list.");
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}

	static void tasksOnDay(TaskList t, String s) {
		LocalDate day = LocalDate.parse(s);
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      Here are the tasks on " + day.toString() + ":");
		System.out.printf("%s", t.tasksOnDay(s));
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}

	static void clear() {
		System.out.println("    ____________________________________________________________");
		System.out.println();
		System.out.println("      The list has been cleared");
		System.out.println("    ____________________________________________________________");
		System.out.println();
	}
}
