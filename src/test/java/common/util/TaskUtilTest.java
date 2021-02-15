package common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import snom.common.util.TaskUtil;

public class TaskUtilTest {
    private LocalDateTime inputDateTime = LocalDateTime.parse("2021-09-27 15:00", TaskUtil.DATE_TIME_INPUT_FORMAT);
    @Test
    public void dateTimeInputFormat() {
        assertEquals(inputDateTime.getYear(), 2021);
        assertEquals(inputDateTime.getMonthValue(), 9);
        assertEquals(inputDateTime.getDayOfMonth(), 27);
        assertEquals(inputDateTime.getHour(), 15);
        assertEquals(inputDateTime.getMinute(), 0);
    }

    @Test
    public void dateTimeSaveFormat() {
        String saveDateTime = inputDateTime.format(TaskUtil.DATE_TIME_SAVE_FORMAT);
        assertEquals(saveDateTime, "2021-09-27 15:00");
    }
}
