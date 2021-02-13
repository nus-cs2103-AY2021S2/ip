package prerthan.duke.task;

import prerthan.duke.exception.DukeEmptyDetailException;
import prerthan.duke.exception.DukeInvalidDateException;
import prerthan.duke.parse.DateParser;

import java.time.ZonedDateTime;

/**
 * An Event, with a detail, a start time, and an end time.
 */
public class Event extends Task
{
	protected String duration;

	protected ZonedDateTime startTime;
	protected ZonedDateTime endTime;

	public Event(String detail, boolean isComplete, ZonedDateTime startTime, ZonedDateTime endTime)
			throws DukeEmptyDetailException
	{
		super(detail, isComplete);
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Creates an Event with a specified detail, and a {@link String} that specifies
	 * the duration of the Event. The actual starting and ending
	 * {@link ZonedDateTime} instance variables are parsed from this string.
	 *
	 * @param detail     The Event detail
	 * @throws DukeEmptyDetailException if {@code detail} is blank (as specified by
	 *                                  {@link String#isBlank()})
	 * @throws DukeInvalidDateException if the date cannot be parsed into a starting
	 *                                  and ending date
	 */
	public Event(String detail, String startString, String endString)
			throws DukeEmptyDetailException, DukeInvalidDateException
	{
		this(detail, false, DateParser.parseDateTimeString(startString),
				DateParser.parseDateTimeString(endString));
	}

	public char getTaskTypeIcon()
	{
		return 'E';
	}

	@Override public String toString()
	{
		return String
				.format("[%c]%s from: %s\t to: %s", this.getTaskTypeIcon(), super.toString(), DateParser.formatZonedDateTime(startTime), DateParser.formatZonedDateTime(endTime));
	}

	@Override public String encode()
	{
		return String.format("%c,%d,%s,%s,%s", this.getTaskTypeIcon(), this.isComplete ? 1 : 0,
				this.detail, this.startTime.toString(), this.endTime.toString());
	}
}