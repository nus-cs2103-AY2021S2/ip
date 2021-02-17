package duke.parser;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageParserTest {

    private static Stream<Arguments> parseTaskFromStorageFormatGetArguments() throws Exception{
        Event e1 =  new Event("party", "my house 2020-10-09");
        Event e2 = new Event("Badminton", "your house 2020-09-08");
        ToDo t1 = new ToDo("Japan trip");
        ToDo t2 = new ToDo("French trip");
        Deadline d1 = new Deadline("homework", "2020-10-09");
        Deadline d2 = new Deadline("nothing", "2020-10-09");
        e1.markAsDone();
        t1.markAsDone();
        d2.markAsDone();
        return Stream.of(
                Arguments.of("E | 1 | party | my house 2020-10-09", e1),
                Arguments.of("E | 0 | Badminton | your house 2020-09-08", e2),
                Arguments.of("T | 1 | Japan trip", t1),
                Arguments.of("T | 0 | French trip", t2),
                Arguments.of("D | 0 | homework | 2020-10-09", d1),
                Arguments.of("D | 1 | nothing | 2020-10-09",d2)
        );
    }
    @ParameterizedTest
    @MethodSource("parseTaskFromStorageFormatGetArguments")
    public void parseTaskFromStorageFormat_success(String input, Task t) throws Exception{
        assertEquals(StorageParser.parseTaskFromStorageFormat(input),t);
    }

    @ParameterizedTest
    @MethodSource("parseTaskFromStorageFormatGetArguments")
    public void convertTaskToStorageFormat_success(String input, Task t) throws Exception{
        assertEquals(input,StorageParser.convertTaskToStorageFormat(t));
    }


}
