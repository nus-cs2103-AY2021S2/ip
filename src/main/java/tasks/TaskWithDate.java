package tasks;

import exceptions.SnomException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class TaskWithDate extends Task{
    private LocalDateTime dateTime;

    public TaskWithDate(String description, LocalDateTime dateTime){
        super(description);
        this.dateTime = dateTime;
    }

    public TaskWithDate(String description, String dateTime) throws SnomException {
        super(description);
        this.dateTime = convertDateTime(dateTime);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns a formatted date time.
     * Eg. Tue 26 Jan 2021 03:33pm
     *
     * @return formatted date time
     */
    public String getDateTimeString(){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("E dd MMM yyyy hh:mma")
                .toFormatter();
        return this.dateTime.format(formatter);
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a LocalDateTime by converting from a given string.
     *
     * @param dateTime          string representing date and time
     * @return                  converted/formatted LocalDateTime
     * @throws SnomException    if the given string is an invalid date time format
     */
    public LocalDateTime convertDateTime(String dateTime) throws SnomException {
        dateTime = dateTime.replaceFirst(" ", "");
        try{
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd[ HH:mm]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();
            LocalDateTime formattedDateTime = LocalDateTime.parse(dateTime, formatter);
            return formattedDateTime;
        }catch(DateTimeParseException e){
            throw new SnomException("Oops! Please enter a valid date time format [YYYY-MM-DD HH:MM]");
        }
    }

    @Override
    public String toSaveString(){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd[ HH:mm]")
                .toFormatter();
        return super.toSaveString() + ", " + this.dateTime.format(formatter);
    }
}
