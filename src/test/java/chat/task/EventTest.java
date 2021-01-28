package chat.task;

import chat.ChatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    private static String formatStr = "Please input with this format:\n" +
            "event [name] /at [end date/time]-[start date/time]\n" +
            "* start and end date/time should be written as dd/MM/yyyy HH:mm\n" +
            "* i.e. 2019/03/19 22:00-2019/03/19 23:00\n" +
            "* i.e. 2019/03/19 22:00 - 2019/03/19 23:00";

    @Test
    public void createEvent_wrongInstruction_chatException() {
        String errorMessage = "wrong instruction for event\n" + formatStr;
        try {
            Event.createEvent("todo");
            fail(); // the test should not reach this line
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createEvent_onlyEvent_chatException() {
        String errorMessage = "event name, start and end date/time missing\n" + formatStr;
        try {
            Event.createEvent("event");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createEvent_eventOneSpace_chatException() {
        String errorMessage = "event name, start and end date/time missing\n" + formatStr;
        try {
            Event.createEvent("event");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createEvent_eventManySpaces_chatException() {
        String errorMessage = "event name, start and end date/time missing\n" + formatStr;
        try {
            Event.createEvent("event       ");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createEvent_noSpaceBetweenEventAndDescription_chatException() {
        String errorMessage = "no spacing after event\n" + formatStr;
        try {
            Event.createEvent("eventfinal exam");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("eventfinal exam /at 22/01/2020 03:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event/at");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

    }

    @Test
    public void createEvent_noDescriptionButHaveAt_chatException() {
        String errorMessage = "event name missing\n" + formatStr;

        try {
            Event.createEvent("event /at");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event /at 22/01/2020 03:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event /at 22/01/2020 03:00 - 22/01/2020 04:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
    }

    @Test
    public void createEvent_noAt_chatException() {
        String errorMessage = "/at missing\n" + formatStr;

        try {
            Event.createEvent("event final exam");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event final exam 01/10/2020 10:00 - 11/10/2020 11:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createEvent_noSpaceAroundAt_chatException() {
        String errorMessage = "missing spaces before or after /at\n" + formatStr;

        try {
            Event.createEvent("event final exam/at");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event final exam /at");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event final exam /at01/10/2020 10:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
        try {
            Event.createEvent("event final exam /at01/10/2020 10:00 - 02/10/2020 10:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event final exam/at 01/10/2020 10:00 - 02/10/2020 10:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

    }

    @Test
    public void createEvent_moreThanOneAt_chatException() {
        String errorMessage = "not allowed to have more than 1 ' /at '\n" + formatStr;

        try {
            Event.createEvent("event final exam /at /at 01/10/2020 10:00 - 01/10/2020 11:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

    }

    @Test
    public void createEvent_noHyphenBetweenStartAndEndDateTime_chatException() {
        String errorMessage = "missing '-'\n" + formatStr;
        
        try {
            Event.createEvent("event final exam /at 20/02/2020 11:00 20/02/2020 12:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event final exam /at 20/02/2020 11:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event final exam /at night");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
    }

    @Test
    public void createEvent_noStartAndEndDateTime_chatException() {
        String errorMessage = "missing start and end date/time\n" + formatStr;

        try {
            Event.createEvent("event final exam /at ");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void createEvent_noStartOrEndDateTime_chatException() {
        String errorMessage = "missing start or end date/time\n" + formatStr;

        try {
            Event.createEvent("event final exam /at 01/10/2021 11:00 -");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Event.createEvent("event final exam /at 01/10/2021 11:00 - ");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

    }
    
    @Test
    public void createEvent_wrongFormatStartOrEndDateTime_chatException() {
        String errorMessage = "Start or end date/time is of wrong format\n" + formatStr;

        try {
            Event.createEvent("event final exam /at 01/10/2021 11:00 - 01/10/2021");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
        
    }

    @Test
    public void createEvent_startAfterEndDateTime_chatException() {
        String errorMessage = "Start date/time is after end date/time\n" + formatStr;

        try {
            Event.createEvent("event final exam /at 01/10/2021 11:00 - 01/10/2021 09:00");
            fail();
        } catch (ChatException e) {
            assertEquals(errorMessage, e.getMessage());
        }

    }

    @Test
    public void createEvent_correctFormat_chatException() {
        try {
            Event.createEvent("event final exam /at 20/03/2021 20:00 - 20/03/2021 21:00");
        } catch (ChatException e) {
            fail();
        }
    }

    @Test
    public void allParameterStr_eventObjNotDone_commaSeparatedString() {
        assertEquals("E,false,final exam,20/03/2021 20:00,20/03/2021 21:00",
                new Event(false, "final exam", "20/03/2021 20:00", "20/03/2021 21:00").allParameterStr());
    }

    @Test
    public void allParameterStr_eventObjDone_commaSeparatedString() {
        assertEquals("E,true,final exam,20/03/2021 20:00,20/03/2021 21:00",
                new Event(true, "final exam", "20/03/2021 20:00", "20/03/2021 21:00").allParameterStr());
    }

    @Test
    public void toString_eventObjNotDoneSameDay_string() {
        assertEquals("[E][ ] final exam (at: 20 Mar 2021, 8:00PM - 9:00PM)",
                new Event(false, "final exam", "20/03/2021 20:00", "20/03/2021 21:00").toString());
    }

    @Test
    public void toString_eventObjNotDoneDiffDay_string() {
        assertEquals("[E][ ] final exam (at: 20 Mar 2021, 8:00PM - 21 Mar 2021, 8:00PM)",
                new Event(false, "final exam", "20/03/2021 20:00", "21/03/2021 20:00").toString());
    }

    @Test
    public void toString_eventObjDoneSameDay_string() {
        assertEquals("[E][X] final exam (at: 20 Mar 2021, 8:00PM - 9:00PM)",
                new Event(true, "final exam", "20/03/2021 20:00", "20/03/2021 21:00").toString());
    }

    @Test
    public void toString_eventObjDoneDiffDay_string() {
        assertEquals("[E][X] final exam (at: 20 Mar 2021, 8:00PM - 21 Mar 2021, 8:00PM)",
                new Event(true, "final exam", "20/03/2021 20:00", "21/03/2021 20:00").toString());
    }

}

