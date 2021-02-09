package duck.operation;

import duck.task.TaskList;

import java.io.*;

public class Storage {
    private File fileOfData;

    /**
     * initialize Storage object, location file
     * if it does not exist, generate a new one
     *
     * @param filePath the path of file
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.fileOfData = new File(filePath);
        if (!fileOfData.isFile()) {
            fileOfData.createNewFile();
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
