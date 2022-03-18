package ip.src.main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class that deals with loading and updating the tasks in the given file.
 *
 */
public class Storage {
    protected String filePath;
    protected Duke bot;

    Storage (String filePath, Duke bot) {
        this.filePath = filePath;
        this.bot = bot;
    }

    private Task loadToDoTask (String[] taskDataArr) {
        String details = taskDataArr[2];
        Task newTask = new ToDo(details);
        bot.addToBot(newTask);
        return newTask;
    }

    private Task loadEventTask (String[] taskDataArr) {
        String content = taskDataArr[2];
        String at = taskDataArr[3];
        Task newTask = new Event(content, at);
        bot.addToBot(newTask);
        return newTask;
    }

    private Task loadDeadlineTask (String[] taskDataArr) {
        String content = taskDataArr[2];
        String by = taskDataArr[3];
        Task newTask = new Deadline(content, by);
        bot.addToBot(newTask);
        return newTask;
    }

    /**
     * Updates the Duke bot with the tasks by loading the tasks in the file.
     * Task is in the format {type} | {doneStatus} | {details}.
     *
     * @param taskData The task from the file.
     * @throws FileNotFoundException
     */
    public void loadTasks(String taskData) throws FileNotFoundException {
        // create a File for the given file path
        File f = new File(this.filePath);
        // create a Scanner using the File as the source
        Scanner s = new Scanner(f);

        String[] taskDataArr = taskData.split(" \\| ");
        String type = taskDataArr[0];
        String doneStatus = taskDataArr[1];
        Task newTask = new Task("");

        if (newTask.isToDoTask(type)) {
            newTask = loadToDoTask(taskDataArr);
        } else if (newTask.isEventTask(type)) {
            newTask = loadEventTask(taskDataArr);
        } else {
            assert type.equals("D");
            newTask = loadDeadlineTask(taskDataArr);
        }

        if (newTask.isDone(doneStatus)) {
            newTask.markDone();
        }
    }

    /**
     * Updates the Duke bot with the tasks stored in the file by calling the loadTask method.
     *
     * @param filePath The file with the tasks.
     * @param bot The duke bot.
     * @throws FileNotFoundException
     */
    public void createBot(String filePath, Duke bot) throws FileNotFoundException {
        // create a File for the given file path
        File f = new File(filePath);
        // create a Scanner using the File as the source
        Scanner s = new Scanner(f);
        Storage storage = new Storage(filePath, bot);

        while (s.hasNext()) {
            storage.loadTasks(s.nextLine());
        }
    }

    /**
     * Updates the file at the specific relative filepath with the bot's final tasklist.
     *
     * @throws IOException
     */
    public void updateFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task element:this.bot.list.list) {
            fw.write(element.toString() + "\n");
        }
        fw.close();
    }
}
