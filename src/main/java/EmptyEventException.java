public class EmptyEventException /*extends Exception*/ {
    public EmptyEventException() {
        System.out.println("------------------------------------------\n" +
                "☹ OOPS!!! The description of an event cannot be empty.\n" +
                "------------------------------------------");
    }
}
