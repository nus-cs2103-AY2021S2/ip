public class EmptyDeadlineException /*extends Exception*/ {
    public EmptyDeadlineException() {
        System.out.println("------------------------------------------\n" +
                "☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                "------------------------------------------");
    }
}
