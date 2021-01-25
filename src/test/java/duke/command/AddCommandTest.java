package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.StorageStub;
import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;

public class AddCommandTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void testExecute() throws DukeException, IOException {
    AddCommand addCmd = new AddCommand(new Todo("borrow book"));
    addCmd.execute(new TaskList(), new Ui(), new StorageStub());
    assertEquals("    Got it. I've added this task:\n      [T][ ] borrow book\n    Now you have 1 tasks in the list.\n", outContent.toString());
  }
}