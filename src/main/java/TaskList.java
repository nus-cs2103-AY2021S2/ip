import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> storage = new ArrayList<>();
    private static int count = 0;

    static int getCount() {
        return count;
    }

    static void checkFileFolderSpecifications() {
        try {
            File dir = new File("./data");
            dir.mkdir();
            File f = new File("./data/tasks.txt");
            if (!f.createNewFile()) {
                count = Storage.uploadFromHardDrive(storage);
            }

        } catch (IOException e) {
            System.out.println("error in making folder/file");
        }
    }

    static void processTodo(String[] spl) throws InvalidTaskFormatException {
        storage.add(new Todo(spl[1]));
        count++;
        Ui.outputMessageTask(spl[0], storage.get(count - 1));
    }

    static void processDeadline(String[] spl) {
        storage.add(new Deadline(spl[0], LocalDate.parse(spl[1])));
        count++;
        Ui.outputMessageTask(spl[0], storage.get(count - 1));
    }

    static void processEvent(String[] spl) {
        storage.add(new Event(spl[0], LocalDate.parse(spl[1])));
        count++;
        Ui.outputMessageTask(spl[0], storage.get(count - 1));
    }

    static void processDone(String[] spl) {
        int number = Integer.parseInt(spl[1]);
        Task current = storage.get(number - 1);
        current.finished();
        Ui.outputMessageDone(current);
    }

    static void processFind(String[] spl) {
        Ui.outputMessageFind(storage, spl);
    }

    static void processList() {
        Ui.outputMessageList(storage, count);
    }

    static void processDelete(String[] spl) {
        int num = Integer.parseInt(spl[1]);
        Task t = storage.get(num-1);
        storage.remove(num-1);
        count--;
        Ui.outputMessageDelete(t);
    }

    static void processBye() throws IOException {
        Storage.uploadToHardDrive(storage);
        Ui.outputMessageBye();
    }
}
