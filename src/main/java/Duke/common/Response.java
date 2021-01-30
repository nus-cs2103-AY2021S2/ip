package Duke.common;

/**
 * Stores all generic string responses back to user.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public enum Response {
    GREET("Hello! I'm Duke\nWhat can I do for you?\n"),
    EXIT("Bye. Hope to see you again soon!\n"),
    ADD("Got it. I 've added this task:\n"),
    DELETE("Noted. I've removed this task:\n"),
    DONE("Nice! I 've marked this task as done\n"),
    LIST("Here are the tasks in your list:\n");

    private final String s;

    /** Returns a Response message.
     *
     * @param s generic message.
     * @return Response
     */
    Response(String s) {
        this.s = s;
    }

    /**
     * Returns a String that contain generic response.
     *
     * @return String
     */
    @Override
    public String toString() {
        return s;
    }
}
