import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import duke.error.ErrorChecker;

public class ErrorCheckerTest {
    @Test
    void testToDo_missingTodoTask_exceptionThrown() {
        ErrorChecker errorChecker = new ErrorChecker("todo ", new ArrayList<>());

        boolean hasNoError = errorChecker.isValid();
        Assert.assertEquals(false, hasNoError);
    }

    @Test
    void testDeadline_missingDeadlineTask_exceptionThrown() {
        ErrorChecker errorChecker = new ErrorChecker("deadline ", new ArrayList<>());

        boolean hasNoError = errorChecker.isValid();
        Assert.assertEquals(false, hasNoError);
    }

    @Test
    void testDeadline_missingEventTask_exceptionThrown() {
        ErrorChecker errorChecker = new ErrorChecker("event ", new ArrayList<>());

        boolean hasNoError = errorChecker.isValid();
        Assert.assertEquals(false, hasNoError);
    }
}
