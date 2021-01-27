import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTesting() {
        assertEquals("[D][✘] tea party (by:2019-10-15)", new Deadline("tea party", "2019-10-15").getData());
    }
}
