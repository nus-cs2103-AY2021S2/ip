package surrealchat.easteregg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EasterEggTest {
    private static final String ORANG_OUTPUT = "Meme Man: ORANG! IT S U...\n"
            + "Orang: No you can't SU\n"
            + "Meme Man: ANGERY!\n";
    private static final String VEGETAL_OUTPUT = "Vegetal: Did someone said... NO VEGETALS?\n"
            + "Meme Man: I taste a vegetal... ANGERY!\n";

    /**
     * Tests the execute() function on OrangEasterEgg and VegetalEasterEgg.
     */
    @Test
    public void testEasterEggOutput() {
        assertEquals(new OrangEasterEgg().execute(), ORANG_OUTPUT);
        assertEquals(new VegetalEasterEgg().execute(), VEGETAL_OUTPUT);
    }
}
