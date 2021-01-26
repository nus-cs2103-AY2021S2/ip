package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
	private final LocalDate time;

	Deadline(String a, String b) {
		super(a);
		this.time = LocalDate.parse(b);
	}

	Deadline(String a, String b, boolean c) {
		super(a, c);
		this.time = LocalDate.parse(b);
	}

	@Override
	String saveName() {
		return String.format("deadline1!1%s1!1%s1!1%b", super.getName(), this.time.toString(), super.getIsDone());
	}

	@Override
	boolean onDay(String s) {
		LocalDate day = LocalDate.parse(s);
		if (day.equals(this.time)) {
			return !super.getIsDone();
		} else {
			return false;
		}
		
	}

	@Override
	public String toString() {
		return String.format("[D]%s (by: %s)", super.toString(), this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
	}
}
