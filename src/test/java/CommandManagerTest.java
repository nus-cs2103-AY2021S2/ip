import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import duke.task.CommandManager;
import duke.task.Task;

public class CommandManagerTest {
    @Test
    void testTakeCommandNewTodo() {
        CommandManager cm = new CommandManager();
        String correctMsg = "Added: [T][ ] read book";

        String input = "todo read book";
        String outputMsg = cm.takeCommand(input, new ArrayList<>());
        Assert.assertEquals(correctMsg, outputMsg);
    }

    @Test
    void testTakeCommandNewDeadline() {
        CommandManager cm = new CommandManager();
        String correctMsg = "Added: [D][ ] return book (by: 2021-06-06)";

        String input = "deadline return book /by 06-06-2021";
        String outputMsg = cm.takeCommand(input, new ArrayList<>());
        Assert.assertEquals(correctMsg, outputMsg);
    }

    @Test
    void testTakeCommandNewEvent() {
        CommandManager cm = new CommandManager();
        String correctMsg = "Added: [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)";

        String input = "event project meeting /on 06-08-2021 /from 2 pm /to 4 pm";
        String outputMsg = cm.takeCommand(input, new ArrayList<>());
        Assert.assertEquals(correctMsg, outputMsg);

        input = "event project meeting /on 06-08-2021 /from 2 Pm /to 4 pm";
        outputMsg = cm.takeCommand(input, new ArrayList<>());
        Assert.assertEquals(correctMsg, outputMsg);

        input = "event project meeting /on 06-08-2021 /from 2 pm /to 4 PM";
        outputMsg = cm.takeCommand(input, new ArrayList<>());
        Assert.assertEquals(correctMsg, outputMsg);
    }

    @Test
    void testTakeCommandBye() {
        CommandManager cm = new CommandManager();
        String correctMsg = "Bye! See you soon :)";

        String input = "bye";
        String outputMsg = cm.takeCommand(input, new ArrayList<>());
        Assert.assertEquals(correctMsg, outputMsg);
    }

    @Test
    void testTakeCommandDone() {
        CommandManager cm = new CommandManager();
        ArrayList<Task> tasks = new ArrayList<>();
        String correctMsg = "Good job! You got join sports club done!";
        String correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)\n"
                + "4. [T][X] join sports club";

        cm.takeCommand("todo read book", tasks);
        cm.takeCommand("deadline return book /by 06-06-2021", tasks);
        cm.takeCommand("event project meeting /on 06-08-2021 /from 2 pm /to 4 pm", tasks);
        cm.takeCommand("todo join sports club", tasks);

        String outputMsg = cm.takeCommand("done 4", tasks);
        Assert.assertEquals(correctMsg, outputMsg);

        String outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);
    }

    @Test
    void testTakeCommandDelete() {
        CommandManager cm = new CommandManager();
        ArrayList<Task> tasks = new ArrayList<>();
        String correctMsg = "Deleted: [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)";
        String correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [T][ ] join sports club";

        cm.takeCommand("todo read book", tasks);
        cm.takeCommand("deadline return book /by 06-06-2021", tasks);
        cm.takeCommand("event project meeting /on 06-08-2021 /from 2 pm /to 4 pm", tasks);
        cm.takeCommand("todo join sports club", tasks);

        String outputMsg = cm.takeCommand("delete 3", tasks);
        Assert.assertEquals(correctMsg, outputMsg);

        String outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);
    }

    @Test
    void testTakeCommandFind() {
        CommandManager cm = new CommandManager();
        ArrayList<Task> tasks = new ArrayList<>();
        String correctList = "Here is a list of your tasks that contain book:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "4. [T][ ] borrow new book";

        cm.takeCommand("todo read book", tasks);
        cm.takeCommand("deadline return book /by 06-06-2021", tasks);
        cm.takeCommand("event project meeting /on 06-08-2021 /from 2 pm /to 4 pm", tasks);
        cm.takeCommand("todo borrow new book", tasks);

        String outputList = cm.takeCommand("find book", tasks);
        Assert.assertEquals(correctList, outputList);
    }

    @Test
    void testTakeCommandUndoRedoDone() {
        CommandManager cm = new CommandManager();
        ArrayList<Task> tasks = new ArrayList<>();
        String correctMsg = "Good job! You got join sports club done!";
        String correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)\n"
                + "4. [T][X] join sports club";

        cm.takeCommand("todo read book", tasks);
        cm.takeCommand("deadline return book /by 06-06-2021", tasks);
        cm.takeCommand("event project meeting /on 06-08-2021 /from 2 pm /to 4 pm", tasks);
        cm.takeCommand("todo join sports club", tasks);

        String outputMsg = cm.takeCommand("done 4", tasks);
        Assert.assertEquals(correctMsg, outputMsg);
        String outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);

        correctMsg = "Undid done 4.";
        correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)\n"
                + "4. [T][ ] join sports club";

        outputMsg = cm.takeCommand("undo", tasks);
        Assert.assertEquals(correctMsg, outputMsg);
        outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);

        correctMsg = "Redid done 4.";
        correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)\n"
                + "4. [T][X] join sports club";

        outputMsg = cm.takeCommand("redo", tasks);
        Assert.assertEquals(correctMsg, outputMsg);
        outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);
    }

    @Test
    void testTakeCommandUndoRedoDelete() {
        CommandManager cm = new CommandManager();
        ArrayList<Task> tasks = new ArrayList<>();
        String correctMsg = "Deleted: [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)";
        String correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [T][ ] join sports club";

        cm.takeCommand("todo read book", tasks);
        cm.takeCommand("deadline return book /by 06-06-2021", tasks);
        cm.takeCommand("event project meeting /on 06-08-2021 /from 2 pm /to 4 pm", tasks);
        cm.takeCommand("todo join sports club", tasks);

        String outputMsg = cm.takeCommand("delete 3", tasks);
        Assert.assertEquals(correctMsg, outputMsg);

        String outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);

        correctMsg = "Undid delete 3.";
        correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)\n"
                + "4. [T][ ] join sports club";

        outputMsg = cm.takeCommand("undo", tasks);
        Assert.assertEquals(correctMsg, outputMsg);

        outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);

        correctMsg = "Redid delete 3.";
        correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [T][ ] join sports club";

        outputMsg = cm.takeCommand("redo", tasks);
        Assert.assertEquals(correctMsg, outputMsg);

        outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);
    }

    @Test
    void testTakeCommandUndoRedoNewTask() {
        CommandManager cm = new CommandManager();
        ArrayList<Task> tasks = new ArrayList<>();
        String correctMsg = "Good job! You got join sports club done!";
        String correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)\n"
                + "4. [T][ ] join sports club";

        cm.takeCommand("todo read book", tasks);
        cm.takeCommand("deadline return book /by 06-06-2021", tasks);
        cm.takeCommand("event project meeting /on 06-08-2021 /from 2 pm /to 4 pm", tasks);
        cm.takeCommand("todo join sports club", tasks);

        String outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);

        correctMsg = "Undid todo join sports club.";
        correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)";

        String outputMsg = cm.takeCommand("undo", tasks);
        Assert.assertEquals(correctMsg, outputMsg);
        outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);

        correctMsg = "Redid todo join sports club.";
        correctList = "Here is a list of your tasks:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: 2021-06-06)\n"
                + "3. [E][ ] project meeting (on: 2021-08-06, 14:00 - 16:00)\n"
                + "4. [T][ ] join sports club";

        outputMsg = cm.takeCommand("redo", tasks);
        Assert.assertEquals(correctMsg, outputMsg);
        outputList = cm.takeCommand("list", tasks);
        Assert.assertEquals(correctList, outputList);
    }
}
