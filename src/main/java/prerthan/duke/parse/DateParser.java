package prerthan.duke.parse;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Optional;
import java.util.stream.Stream;

import prerthan.duke.exception.DukeInvalidDateException;

/**
 * A utility class to parse {@link String}s into {@link ZonedDateTime} objects,
 * useable by the Duke program.
 * 
 */
public final class DateParser {
    private static final String DAY_FORMAT_PATTERN = "[EEEE][E]";
    private static final String DATE_FORMAT_PATTERN = "[dd][d]['st']['nd']['rd']['th'][' of'][/][-][ ][LLLL][MMM][L][, ][/][-][ ][uuuu]";
    private static final String TIME_FORMAT_PATTERN = "[kk:mm][[hh][h][[:][.]mm][ ]a][ v]";
    private static final DateTimeFormatter[] FORMATTERS = Stream
            .of(DAY_FORMAT_PATTERN, DATE_FORMAT_PATTERN, TIME_FORMAT_PATTERN).map(DateTimeFormatter::ofPattern)
            .toArray(DateTimeFormatter[]::new);

    private static final String OUTPUT_FORMAT_PATTERN = "EEEE dd'st' MMMM YYYY, hh:mm a v";

    private static final ZonedDateTime now = ZonedDateTime.now();

    public static ZonedDateTime parseDateTimeString(String dateTimeString) throws DukeInvalidDateException {
        ZonedDateTime returnable = now;
        String[] tokens = dateTimeString.split("\\s+");

        for (String token : tokens) {
            for (DateTimeFormatter formatter : FORMATTERS) {
                returnable = parseTokenWithFormatter(token, formatter);
            }
        }
        return returnable;
    }

    public static String parseZonedDateTime(ZonedDateTime dateTimeGroup) {
        return DateTimeFormatter.ofPattern(OUTPUT_FORMAT_PATTERN).format(dateTimeGroup);
    }

    private static ZonedDateTime parseTokenWithFormatter(String token, DateTimeFormatter formatter)
            throws DukeInvalidDateException {
        Optional<TemporalAccessor> possiblyParsed;

        ZonedDateTime returnable = ZonedDateTime.ofInstant(now.toInstant(), now.getZone());
        try {
            possiblyParsed = Optional.of(formatter.parse(token));
        } catch (DateTimeParseException e) {
            possiblyParsed = Optional.empty();
            throw new DukeInvalidDateException(DateParser.class.getSimpleName(), token);
        }
        if (possiblyParsed.isPresent()) {
            TemporalAccessor parsed = possiblyParsed.get();
            for (TemporalField field : formatter.getResolverFields()) {
                returnable = returnable.with(field, parsed.isSupported(field) ? parsed.get(field) : now.get(field));
            }
        }
        return returnable;
    }
}