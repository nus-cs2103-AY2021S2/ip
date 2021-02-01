package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
	public final LocalDate date;
	public final LocalTime time;

	private Deadline(String description, LocalDate date) {
		super(false, description);
		this.date = date;
		this.time = null;
	}

	private Deadline(String description, LocalDate date, LocalTime time) {
		super(false, description);
		this.date = date;
		this.time = time;
	}

	public static Deadline create(String description, String dateStr) {
		try {
			LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
			return new Deadline(description, date);
		} catch (DateTimeParseException e) {
			System.out.println("\tPlease follow this format \"YYYY-MM-DD [hh:mm[:ss]]\" for datetime.");
			return null;
		}
	}

	public static Deadline create(String description, String dateStr, String timeStr) {
		try {
			LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
			LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_TIME);
			return new Deadline(description, date, time);
		} catch (DateTimeParseException e) {
			System.out.println("\tPlease follow this format \"YYYY-MM-DD [hh:mm[:ss]]\" for datetime.");
			return null;
		}
	}

	public String getDateTime() {
		if (time == null) {
			return date.toString();
		}
		return String.format("%s %s", this.date, this.time);
	}

	@Override
	public String toString() {
		return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.getDateTime());
	}

	@Override
	public String encode() {
		return String.format("D | %s | %s | %s", this.isDone ? "1" : "0", this.description, this.getDateTime());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Deadline)) {
			return false;
		}
		Deadline other = (Deadline) o;
		return this.description.equals(other.description) && this.isDone == other.isDone && this.date.equals(other.date)
				&& (this.time == null || this.time.equals(other.time));
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isDone ? 1 : 0);
		return result;
	}
}
