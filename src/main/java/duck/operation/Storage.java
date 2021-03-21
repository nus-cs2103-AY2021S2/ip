package duck.operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import duck.task.TaskList;




public class Storage {
    private File fileOfData;

    /**
     * initialize Storage object, location file
     * if it does not exist, generate a new one
     *
     * @param filePath the path of file
     */
    public Storage(String filePath) {
        this.fileOfData = new File(filePath);
        File directory = this.fileOfData.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            fileOfData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get the data in the file, and return a list of string
     *
     * @return a list of data
     * @throws IOException
     */
    public String[] load() throws IOException {
        FileInputStream inStream = new FileInputStream(fileOfData);
        InputStreamReader reader = new InputStreamReader(inStream);
        BufferedReader br = new BufferedReader(reader);
        String[] data = new String[1000];
        int lines = 0;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            data[lines] = line;
            lines++;
        }
        assert (lines < 1000);
        br.close();
        return data;
    }

    /**
     * update the data of the file when task list changes.
     *
     * @param tasks
     * @throws IOException
     */
    public void updateFile(TaskList tasks) throws IOException {
        FileOutputStream outStream = new FileOutputStream(fileOfData);
        OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
        writer.write("");
        writer.flush();
        for (int i = 0; i < tasks.getSizeOfTasks(); i++) {
            writer.write(tasks.getTask(i).getTaskInfoOfFile() + "\n");
        }
        writer.flush();
    }

}
