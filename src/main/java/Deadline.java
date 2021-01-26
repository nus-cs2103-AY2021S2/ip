import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
			return null;
		}
	}

	public static Deadline create(String description, String dateStr, String timeStr) {
		try {
			LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
			LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_TIME);
			return new Deadline(description, date, time);
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
		return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.getDateTime());
	}
}
