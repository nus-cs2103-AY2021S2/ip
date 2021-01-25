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

    static void check_file_folder_specifications() {
        try {
            File dir = new File("./data");
            dir.mkdir();
            File f = new File("./data/tasks.txt");
            if (!f.createNewFile()) {
                count = Storage.upload_from_hard_drive(storage);
            }

        } catch (IOException e) {
            System.out.println("error in making folder/file");
        }
    }

    static void process_todo(String[] spl) throws InvalidTaskFormatException {
        storage.add(new todo(spl[1]));
        count++;
        Ui.output_message_task(spl[0], storage.get(count - 1));
    }

    static void process_deadline(String[] spl) {
        storage.add(new deadline(spl[0], LocalDate.parse(spl[1])));
        count++;
        Ui.output_message_task(spl[0], storage.get(count - 1));
    }

    static void process_event(String[] spl) {
        storage.add(new event(spl[0], LocalDate.parse(spl[1])));
        count++;
        Ui.output_message_task(spl[0], storage.get(count - 1));
    }

    static void process_done(String[] spl) {
        int number = Integer.parseInt(spl[1]);
        Task current = storage.get(number - 1);
        current.finished();
        Ui.output_message_done(current);
    }

    static void process_list() {
        Ui.output_message_list(storage, count);
    }

    static void process_delete(String[] spl) {
        int num = Integer.parseInt(spl[1]);
        Task t = storage.get(num-1);
        storage.remove(num-1);
        count--;
        Ui.output_message_delete(t);
    }

    static void process_bye() throws IOException {
        Storage.upload_to_hard_drive(storage);
        Ui.output_message_bye();
    }
}
