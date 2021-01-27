package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        try {
            // test creation of task from command line
            assertEquals("[T][\u2718] abcde", new Todo("abcde").toString());
            assertEquals("[T][\u2718] abcd efgh", new Todo("abcd efgh").toString());
            assertEquals("[T][\u2718] abcd 123", new Todo("abcd 123").toString());
            assertEquals("[T][\u2718] abcd e f g", new Todo("abcd e f g").toString());

            // test creation of task from saved data
            assertEquals("[T][\u2718] abcd", new Todo("incomplete", "abcd").toString());
            assertEquals("[T][\u2713] abcd", new Todo("complete", "abcd").toString());
            assertEquals("[T][\u2718] a a a a", new Todo("incomplete", "a a a a").toString());
            assertEquals("[T][\u2713] a a a a", new Todo("complete", "a a a a").toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}