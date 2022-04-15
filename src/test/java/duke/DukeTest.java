package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    private Duke d;

    @Test
    public void getResponseTest() {
        d = new Duke("data/testData.txt");

        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", d.getResponse("a"));
        assertEquals("Got it. I've added this task:\n[T][ ] a\nNow you have 1 task in the list.",
                d.getResponse("todo a"));
        assertEquals("Got it. I've added this task:\n[D][ ] b (by: 1 Jan)\nNow you have 2 tasks in the list.",
                d.getResponse("deadline b /by 2011-01-01"));
        assertEquals("Got it. I've added this task:\n[E][ ] c (at: 1 Jan)\nNow you have 3 tasks in the list.",
                d.getResponse("event c /at 2011-01-01"));
        assertEquals("Nice! I've marked this task as done:\n[T][X] a", d.getResponse("done 1"));
        assertEquals("Nice! I've marked these tasks as done:\n[T][X] a\n[D][X] b (by: 1 Jan)",
                d.getResponse("done 1 2"));
        assertEquals("Are you sure you want to delete these tasks? y/n", d.getResponse("delete 1 2 3"));
        assertEquals("Deletion cancelled.", d.getResponse("n"));
        assertEquals("Here are the tasks in your list:\n1. [T][X] a\n2. [D][X] b (by: 1 Jan)\n3. [E][ ] c (at: 1 Jan)",
                d.getResponse("list"));
        assertEquals("TaskList have been sorted!", d.getResponse("sort"));
        assertEquals("Here are the tasks in your list:\n1. [E][ ] c (at: 1 Jan)\n2. [T][X] a\n3. [D][X] b (by: 1 Jan)",
                d.getResponse("list"));
        assertEquals("Here are the tasks in your list:\n1. [E][ ] c (at: 1 Jan)\n3. [D][X] b (by: 1 Jan)",
                d.getResponse("search 2011-01-01"));
        assertEquals("Here are the tasks in your list:\n2. [T][X] a", d.getResponse("search a"));
        assertEquals("Do you want to save the current tasklist? y/n", d.getResponse("bye"));
        assertEquals("shutdownConfirm", d.getResponse("n"));
    }
}
