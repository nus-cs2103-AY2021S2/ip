import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlignerTest {

    @Test
    void align_string_success() {
        String s = "Testing alignment!";
        assertEquals("                           Testing alignment!                           ",
                            Aligner.align(s));
    }
}