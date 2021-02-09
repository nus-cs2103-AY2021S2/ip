public class ListCommand extends Command{
    public String action() {
        try {
            return TaskList.printList();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
