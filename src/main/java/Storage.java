import java.util.List;

class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    List<Task> load() throws DukeException{
        FileReader fr = new FileReader();

        return fr.readFile(filePath);
    }



    void save(String modifiedResult) throws DukeException{
        DukeFileWriter fw = new DukeFileWriter();

        fw.writeFile(filePath, modifiedResult);
    }
}
