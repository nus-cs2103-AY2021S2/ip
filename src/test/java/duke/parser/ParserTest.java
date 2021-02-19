package duke.parser;

import duke.exception.DukeException;
import duke.ui.UI;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @Test
    public void isValidTaskNumberTest() throws DukeException {
        Parser parser = new Parser();

        String dateInString = "19/02/2025";
        LocalDate date =  LocalDate.parse(dateInString, DATE_TIME_FORMATTER);

        assertEquals(parser.check_valid_date("19/02/2025"), date);
    }
}
