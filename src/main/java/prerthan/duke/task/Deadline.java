package sharadhr.duke.task;

import sharadhr.duke.exception.DukeEmptyDetailException;
import sharadhr.duke.exception.DukeInvalidDateException;
import sharadhr.duke.parse.DateParser;

import java.time.ZonedDateTime;

/**
 * A Deadline, with a date-time group when it is due.
 */
public class Deadline extends Task
{
	private ZonedDateTime by;

	/**
	 * @param detail
	 * @param by
	 * @throws DukeEmptyDetailException
	 */
	public Deadline(String detail, boolean isComplete, ZonedDateTime by)
			throws DukeEmptyDetailException
	{
		super(detail, isComplete);
		this.by = by;
	}

	/**
	 * Creates a deadline with a specified detail, and a date-time to be done by.
	 *
	 * @param detail the deadline detail
	 * @param by     the date-time to be done by
	 * @throws DukeEmptyDetailException if the detail is empty.
	 * @throws DukeInvalidDateException if the given date-time {@link String} cannot be parsed.
	 */
	public Deadline(String detail, String by)
			throws DukeEmptyDetailException, DukeInvalidDateException
	{
		this(detail, false, DateParser.parseDateTimeString(by));
	}

	@Override public char getTaskTypeIcon()
	{
		return 'D';
	}

	@Override public String toString()
	{
		return String.format("[%c]%s (by: %s)", this.getTaskTypeIcon(), super.toString(),
				DateParser.formatZonedDateTime(this.by));
	}

	@Override public String encode()
	{
		return String
				.format("%c,%d,%s,%s", this.getTaskTypeIcon(), this.isComplete ? 1 : 0, this.detail,
						DateParser.formatZonedDateTime(this.by));
	}
}