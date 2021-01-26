public class Ui {
    String logo;

    public Ui(TaskList tasks, String logo) {
        this.logo = logo;
    }

    public void printStart() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}
