public class EmptyDeleteException /*extends Exception*/ {
    public EmptyDeleteException() {
        System.out.println("------------------------------------------\n" +
                "☹ OOPS!!! The description of a delete cannot be empty.\n" +
                "------------------------------------------");
    }
}
