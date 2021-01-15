public class EmptyToDoException /*extends Exception*/ {
    public EmptyToDoException() {
        System.out.println("------------------------------------------\n" +
                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                "------------------------------------------");
    }
}
