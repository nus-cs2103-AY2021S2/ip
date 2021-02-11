package Duke.Helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeProcessorTest {

    @Test
    public void DateTimeTestOutput(){
        String[] input = new String[]{"2020-02-15 1800", "2021-01-05", "2000-01-01 2400",
                                        "2019-12-19", "2015-09-11 2359", "2021-02-01 000", "1975-04-30 830"};
        String[] expectedOutput = new String[]{"Feb 15 2020 6:00pm", "Jan 5 2021", "Jan 1 2000 12:00am",
                                                "Dec 19 2019", "Sep 11 2015 11:59pm", "Feb 1 2021 12:00am", "Apr 30 1975 8:30am"};
        for (int i = 0; i < input.length; i++) {
            DateTimeProcessor processor = new DateTimeProcessor(input[i]);
            String output = processor.getFullDateTime();
            assertEquals(output, expectedOutput[i]);
        }
    }

    @Test
    public void DateTimeTestError(){
        String[] input = new String[]{"", "20-01-2020", "2020-15-14", "2020-01-38", "2019-01-08 2517", "2014", "2021-01-01 1262",
                                        "1995-04-08 -3615", "2004-05-09 abc", "abc xyz", "2020-04-081500"};
        for (String time : input) {
            DateTimeProcessor processor = new DateTimeProcessor(time);
            String output = processor.getFullDateTime();
            assertEquals(output, "Invalid format for date and time.");
        }
    }
}
