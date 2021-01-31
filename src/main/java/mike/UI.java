package mike;

public class UI {

    /**
     * Format response by wrapping lines above and below
     * @param response response to be formatted
     */
    public static void printResponse(String response) {
        System.out.print(String.format("____________________________________________________________\n"
                + "%s\n"
                + "____________________________________________________________\n", response));
    }

    /**
     * Format exception with source by wrapping lines above and below
     * @param source source of the exception thrown
     * @param message error to be formatted
     */
    public static void printException(String source, String message) {
        System.out.print(String.format("____________________________________________________________\n"
                + "Error in:%s\n"
                + "Details: %s\n"
                + "____________________________________________________________\n", source, message));
    }

    /**
     * Format exception by wrapping lines above and below
     * @param message error to be formatted
     */
    public static void printException(String message) {
        System.out.print(String.format("____________________________________________________________\n"
                + "Details: %s\n"
                + "____________________________________________________________\n", message));
    }
}
