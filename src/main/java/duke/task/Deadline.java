package duke.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;

public class Deadline extends Task {
    protected final LocalDate deadline;

    /**
     * Creates a Task with a description and a deadline date that it needs
     * to be done by.
     *
     * @param description Description of Deadline
     * @param by When the task needs to be completed
     * @throws EmptyArgumentException Some argument, either 'description' or 'by' is empty.
     * @throws BadDateArgumentException When 'by' is not well formatted
     */
    public Deadline(String description, String by) throws EmptyArgumentException, BadDateArgumentException {
        super(description);
        LocalDate potentialDeadline;
        potentialDeadline = attemptToParseAsKeyword(by);
        if (potentialDeadline != null) {
            deadline = potentialDeadline;
            return;
        }
        potentialDeadline = attemptToParseAsRelativeDay(by);
        if (potentialDeadline != null) {
            deadline = potentialDeadline;
            return;
        }
        potentialDeadline = attemptToParseAsAbsolute(by);
        if (potentialDeadline != null) {
            deadline = potentialDeadline;
            return;
        }
        throw new BadDateArgumentException();
    }

    /**
     * Attempts to parse a string as a numeric date and obtain a LocalDate object
     * Eg: "07 12 2021" -> 7 Dec, 2020
     *
     * @param date The String that might be an absolute numeric date
     * @return Either a date if parsing is successful or null otherwise.
     */
    private static LocalDate attemptToParseAsAbsolute(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Attempts to parse a string as a day of the week (Eg: Wednesday)
     *
     * @param day String that may or may not be a day of the week
     * @return Either a DayOfWeek if successfully parsed or null otherwise.
     */
    private static DayOfWeek attemptToParseAsDayOfWeek(String day) {
        String[] mondayStrings = {"m", "mo", "mon", "monday"};
        String[] tuesdayStrings = {"tu", "tue", "tues", "tuesday"};
        String[] wednesdayStrings = {"w", "we", "wed", "weds", "wednesday"};
        String[] thursdayStrings = {"th", "thu", "thur", "thurs", "thursday"};
        String[] fridayStrings = {"f", "fr", "fri", "friday"};
        String[] saturdayStrings = {"sa", "sat", "saturday"};
        String[] sundayStrings = {"su", "sun", "sunday"};
        String[][] dayStrings = {mondayStrings, tuesdayStrings, wednesdayStrings,
                                 thursdayStrings, fridayStrings, saturdayStrings,
                                 sundayStrings};
        DayOfWeek[] dayOfWeeks = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                                  DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,
                                  DayOfWeek.SUNDAY};
        assert dayStrings.length == dayOfWeeks.length;
        String lowerCaseDay = day.toLowerCase().trim();
        for (int i = 0; i < dayStrings.length; i++) {
            if (Arrays.asList(dayStrings[i]).contains(lowerCaseDay)) {
                return dayOfWeeks[i];
            }
        }
        return null;
    }

    /**
     * Interprets a DayOfWeek as a day relative to today.
     * Eg: Next Monday if today is Saturday is two days from today.
     *
     * @param day DayOfWeek relative to today
     * @return A date relative to the passed in day
     */
    private static LocalDate getNextRelativeDay(DayOfWeek day) {
        LocalDate candidateDate = LocalDate.now().plusDays(1);
        while (!candidateDate.getDayOfWeek().equals(day)) {
            candidateDate = candidateDate.plusDays(1);
        }
        return candidateDate;
    }

    /**
     * Attempts to parse a string as a day relative to today.
     * Eg: Next Monday if today is Saturday is two days from today.
     *
     * @param day String that might be a day
     * @return Either a date if parsing is successful or null otherwise.
     */
    private static LocalDate attemptToParseAsRelativeDay(String day) {
        DayOfWeek dayOfWeek = attemptToParseAsDayOfWeek(day);
        if (dayOfWeek == null) {
            return null;
        }
        return getNextRelativeDay(dayOfWeek);
    }

    /**
     * Attempts to parse a string as a keyword and generate a date
     *
     * @param date String to parse
     * @return Either a date if parsing is successful or null otherwise.
     */
    private static LocalDate attemptToParseAsKeyword(String date) {
        LocalDate now = LocalDate.now();
        switch (date.toLowerCase()) {
        case "yesterday":
            return now.minusDays(1);
        case "now":
            //Fall-through
        case "today":
            return now;
        case "tomorrow":
            //Fall-through
        case "tmr":
            return now.plusDays(1);
        default:
            return null;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return "[D]" + super.toString() + " (Deadline: " + deadline.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String date = deadline.format(formatter);
        return "D," + super.toBaseFileString() + "," + date.length() + "," + date;
    }
}
