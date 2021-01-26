import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task {
	public final LocalDate date;
	public final LocalTime time;

	private Event(String description, LocalDate date) {
		super(false, description);
		this.date = date;
		this.time = null;
	}

	private Event(String description, LocalDate date, LocalTime time) {
		super(false, description);
		this.date = date;
		this.time = time;
	}

	public static Event create(String description, String dateStr) {
		try {
			LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
			return new Event(description, date);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static Event create(String description, String dateStr, String timeStr) {
		try {
			LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
			LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_TIME);
			return new Event(description, date, time);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public LocalDateTime getDateTime() {
		return LocalDateTime.of(date, time);
	}

	@Override
	public String toString() {
		return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.getDateTime());
	}
}
