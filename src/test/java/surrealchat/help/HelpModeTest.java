package surrealchat.help;

import org.junit.jupiter.api.Test;
import surrealchat.command.DeadlineCommand;
import surrealchat.command.DeleteCommand;
import surrealchat.command.DoneCommand;
import surrealchat.command.EditCommand;
import surrealchat.command.EventCommand;
import surrealchat.command.FindCommand;
import surrealchat.command.ListCommand;
import surrealchat.command.ToDoCommand;
import surrealchat.easteregg.OrangEasterEgg;
import surrealchat.easteregg.VegetalEasterEgg;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpModeTest {
    public static final String BYE_HELP = "Quits the program.\n";
    public static final String TODO_HELP = ToDoCommand.displayHelp();
    public static final String DEADLINE_HELP = DeadlineCommand.displayHelp();
    public static final String EVENT_HELP = EventCommand.displayHelp();
    public static final String DONE_HELP = DoneCommand.displayHelp();
    public static final String FIND_HELP = FindCommand.displayHelp();
    public static final String LIST_HELP = ListCommand.displayHelp();
    public static final String EDIT_HELP = EditCommand.displayHelp();
    public static final String DELETE_HELP = DeleteCommand.displayHelp();
    public static final String ORANG_HELP = OrangEasterEgg.displayHelp();
    public static final String VEGETAL_HELP = VegetalEasterEgg.displayHelp();


    @Test
    public void testDisplayHelp() {
        assertEquals(HelpMode.displayHelp("bye"), HelpModeTest.BYE_HELP);
        assertEquals(HelpMode.displayHelp("todo"), HelpModeTest.TODO_HELP);
        assertEquals(HelpMode.displayHelp("deadline"), HelpModeTest.DEADLINE_HELP);
        assertEquals(HelpMode.displayHelp("event"), HelpModeTest.EVENT_HELP);
        assertEquals(HelpMode.displayHelp("done"), HelpModeTest.DONE_HELP);
        assertEquals(HelpMode.displayHelp("find"), HelpModeTest.FIND_HELP);
        assertEquals(HelpMode.displayHelp("list"), HelpModeTest.LIST_HELP);
        assertEquals(HelpMode.displayHelp("edit"), HelpModeTest.EDIT_HELP);
        assertEquals(HelpMode.displayHelp("delete"), HelpModeTest.DELETE_HELP);
        assertEquals(HelpMode.displayHelp("orang"), HelpModeTest.ORANG_HELP);
        assertEquals(HelpMode.displayHelp("vegetal"), HelpModeTest.VEGETAL_HELP);
    }
}
