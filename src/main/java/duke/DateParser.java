package duke;public class DateParser{/**
     * parses the date and returns the string containing the date if it is of broad format ( yyyy-MM-dd)
     *
     * @param input string to be parsed.
     * @return string containing the date.
     */

    public static java.time.LocalDate parseDate(java.lang.String input) {
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-M-d");
        return java.time.LocalDate.parse(input, dateTimeFormatter);
    }}