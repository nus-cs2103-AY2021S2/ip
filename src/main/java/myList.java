public class myList {
    StringBuilder sb;
    int counter;

    public myList() {
        sb = new StringBuilder();
        counter = 1;
    }

    public void addTask(String task) {
        sb.append("\n" + counter + ". " + task);
        counter++;
        System.out.println("Added to list: " + task + "\n");
    }

    @Override
    public String toString() {
        return "Here's your list of tasks! " + sb.toString();
    }
}