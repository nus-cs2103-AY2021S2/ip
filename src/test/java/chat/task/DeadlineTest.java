package chat.task;

import chat.ChatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    private static String formatStr = "Please input with this format:\n" +
            "deadline [name] /by [end date/time]\n" +
            "* end date/time should be written as dd/MM/yyyy HH:mm\n" +
            "* i.e. 19/03/2019 23:55";
    
    @Test
    public void createDeadline_wrongInstruction_chatException() {
        String errorMessage = "wrong instruction for deadline\n" + formatStr;
        try {
            Deadline.createDeadline("todo");
            fail(); // the test should not reach this line
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createDeadline_onlyDeadline_chatException() {
        String errorMessage = "deadline name and end date/time missing\n" + formatStr;
        try {
            Deadline.createDeadline("deadline");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createDeadline_deadlineOneSpace_chatException() {
        String errorMessage = "deadline name and end date/time missing\n" + formatStr;
        try {
            Deadline.createDeadline("deadline ");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createDeadline_deadlineManySpaces_chatException() {
        String errorMessage = "deadline name and end date/time missing\n" + formatStr;
        try {
            Deadline.createDeadline("deadline       ");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createDeadline_noSpaceBetweenDeadlineAndDescription_chatException() {
        String errorMessage = "no spacing after deadline\n" + formatStr;
        try {
            Deadline.createDeadline("deadlinereturn book");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
        try {
            Deadline.createDeadline("deadlinereturn book /by 22/01/2020 03:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Deadline.createDeadline("deadline/by");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
    }
    
    @Test
    public void createDeadline_noDescriptionButHaveBy_chatException() {
        String errorMessage = "deadline name missing\n" + formatStr;

        try {
            Deadline.createDeadline("deadline /by");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
        try {
            Deadline.createDeadline("deadline /by 22/01/2020 03:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
    
    @Test
    public void createDeadline_noBy_chatException() {
        String errorMessage = "/by missing\n" + formatStr;

        try {
            Deadline.createDeadline("deadline return book");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
        try {
            Deadline.createDeadline("deadline return book 01/10/2020 10:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
    
    @Test
    public void createDeadline_noSpaceAroundBy_chatException() {
        String errorMessage = "missing spaces before or after /by\n" + formatStr;

        try {
            Deadline.createDeadline("deadline return book/by");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
        try {
            Deadline.createDeadline("deadline return book /by");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
        try {
            Deadline.createDeadline("deadline return book /by01/10/2020 10:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
        try {
            Deadline.createDeadline("deadline return book/by 01/10/2020 10:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
    }
    
    @Test 
    public void createDeadline_moreThanOneBy_chatException() {
        String errorMessage = "not allowed to have more than 1 ' /by '\n" + formatStr;

        try {
            Deadline.createDeadline("deadline return book /by /by 01/10/2020 10:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
    }
    
    @Test
    public void createDeadline_noEndDateTime_chatException() {
        String errorMessage = "missing end date/time\n" + formatStr;
        
        try {
            Deadline.createDeadline("deadline return book /by ");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
    }

    @Test
    public void createDeadline_wrongFormatEndDateTime_chatException() {
        String errorMessage = "End date/time is of wrong format\n" + formatStr;

        try {
            Deadline.createDeadline("deadline return book /by 2020/02");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
        try {
            Deadline.createDeadline("deadline return book /by 20/02/2020");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

    }

    @Test
    public void createDeadline_correctFormat_chatException() {
        try {
            Deadline.createDeadline("deadline return book /by 20/03/2021 20:00");
        } catch (ChatException e) {
            fail();
        }
    }

    @Test
    public void allParameterStr_deadlineObjNotDone_chatException() {
        assertEquals("D,false,return book,20/03/2021 20:00",
                new Deadline(false, "return book", "20/03/2021 20:00").allParameterStr());
    }

    @Test
    public void allParameterStr_deadlineObjDone_chatException() {
        assertEquals("D,true,return book,20/03/2021 20:00",
                new Deadline(true, "return book", "20/03/2021 20:00").allParameterStr());
    }

    @Test
    public void toString_deadlineObjNotDone_chatException() {
        assertEquals("[D][ ] return book (by: 20 Mar 2021, 8:00PM)",
                new Deadline(false, "return book", "20/03/2021 20:00").toString());
    }

    @Test
    public void toString_deadlineObjDone_chatException() {
        assertEquals("[D][X] return book (by: 20 Mar 2021, 8:00PM)",
                new Deadline(true, "return book", "20/03/2021 20:00").toString());
    }

}

