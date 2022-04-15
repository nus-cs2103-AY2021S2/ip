import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateCommandTest {
    String fullCommand = "date 2021-08-08";
    String typeOfCommand = "date";
    DateCommand command = new DateCommand(fullCommand, typeOfCommand);

    @Test
    void execute_dateCommand_success() throws DukeException {
        List<Task> list = List.of(new ToDo("buy a car"),
                new Deadline("finish iP", LocalDate.of(2021, Month.AUGUST, 8),
                        LocalTime.of(14, 14)),
                new Event("casting", LocalDate.of(2021, Month.AUGUST, 8),
                        LocalTime.of(16, 30), LocalTime.of(17, 0)));
        TaskList tl = new TaskList(list);
        String correctString = "Here are the tasks falling on Aug 8 2021!\n"
                + "1.[D][ ] finish iP(by: Aug 8 2021 02:14PM)\n"
                + "2.[E][ ] casting(at: Aug 8 2021 04:30PM-05:00PM)\n";
        LocalDate date = LocalDate.of(2021, Month.AUGUST, 8);
        assertEquals(correctString, command.execute(tl));
    }
}
