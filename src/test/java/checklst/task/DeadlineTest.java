package checklst.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import checklst.exception.ChecklstException;

public class DeadlineTest {

    @Test
    public void createDeadlineTest() {
        assertThrows(ChecklstException.class, () -> Deadline.makeDeadline(""));
        assertThrows(ChecklstException.class, () -> Deadline.makeDeadline("test "));
        assertThrows(ChecklstException.class, () -> Deadline.makeDeadline("test /by"));
        assertThrows(ChecklstException.class, () -> Deadline.makeDeadline("test /by "));
        assertThrows(ChecklstException.class, () -> Deadline.makeDeadline("test  /by 23rd Feb"));
        assertThrows(ChecklstException.class, () -> Deadline.makeDeadline("test/by2020-11-20"));
    }

}
