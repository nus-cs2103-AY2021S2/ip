public class ListCommand implements Command{
    public String action() {
        try {
            return TaskList.printList();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
