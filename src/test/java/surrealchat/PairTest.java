package surrealchat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairTest {
    @Test
    public void testFirstGetter(){
        assertEquals(new Pair<String, Integer>("Test", 5).getFirstItem(), "Test");
    }

    @Test
    public void testSecondGetter(){
        assertEquals(new Pair<String, Integer>("Test", 5).getSecondItem(), 5);
    }
}
