import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<Task> database = new ArrayList<>();

    void tellAdd() {
        System.out.println("Got it. I've added this task:");
    }

    void tellSize() {
        String task = database.size() > 1 ? " tasks" : " task";
        System.out.println("Now you have " + database.size() + task + " in the list");
    }

    void addToDB(Task task) {
        database.add(task);
        tellAdd();
        System.out.println("  " + database.get(database.size() - 1));
        tellSize();
    }

    void deleteFromDB(String inputNum) {
        try {
            int num = Parser.taskNumber(inputNum);
            Task currentTask = database.get(num - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(currentTask);
            database.remove(currentTask);
            tellSize();
        } catch (HahaTaskNumberNotIntException ex) {
            System.out.println(ex);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OOPS! Wrong number!\n Try specify the right task number");
        }
    }

    void listFromDB() {
        if (database.size() == 0) {
            System.out.println("You have nothing going on!");
        } else {
            System.out.println("Here are your list of tasks:");
            for (int i = 0; i < database.size(); i++) {
                String idx = Integer.toString(i + 1) + '.';
                String task = idx + database.get(i);
                System.out.println(task);
            }
        }
    }

    void markDoneToDB(String inputNum) {
        try {
            int givenIndex = Parser.taskNumber(inputNum) - 1;
            if (givenIndex < 0 || givenIndex > database.size()) {
                System.out.println("OOPS! Wrong number!\nTry specify the right task number");
            } else {
                Task currentTask = database.get(givenIndex);
                if (currentTask.getIsDone()) {
                    System.out.println("OOPS! I've marked this task as done ALREADY");
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                    currentTask.setDone(true);
                    System.out.println(currentTask);
                }
            }
        } catch (HahaTaskNumberNotIntException ex) {
            System.out.println(ex);
        }
    }
}
