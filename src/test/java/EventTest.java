import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventTesting(){
        assertEquals("[E][✘] osakai (at:2019-10-15)", new Event("osakai", "2019-10-15").getData());
    }
}
