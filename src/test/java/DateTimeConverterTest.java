import org.junit.Assert;
import org.junit.jupiter.api.Test;

import duke.datetime.DateTimeConverter;

public class DateTimeConverterTest {
    @Test
    void testDateConversion() {
        String input = "deadline return book /by 06-06-2021";
        String[] inputSplit = input.split("/");
        DateTimeConverter dTC = new DateTimeConverter(inputSplit);

        Assert.assertEquals("2021-06-06", dTC.convertDate().toString());
    }

    @Test
    void testTimeConversion() {
        String input = "deadline cs2103t lecture /on 29-01-2021 /from 2 pm /to 4 Pm";
        String[] inputSplit = input.split("/");
        DateTimeConverter dTC = new DateTimeConverter(inputSplit);

        Assert.assertEquals("14:00", dTC.convertTime("from").toString());
        Assert.assertEquals("16:00", dTC.convertTime("to").toString());
    }
}
