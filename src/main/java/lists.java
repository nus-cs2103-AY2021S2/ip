import java.util.ArrayList;
import java.util.List;

public class lists {
    List<String> dukeList;
    public lists(){
        this.dukeList = new ArrayList<>();
    }

    public lists(inputCommand command){
        this.dukeList = new ArrayList<String>();
        dukeList.add(command.getCommand());
    }

    public lists(List<String> inputList){
        this.dukeList = new ArrayList<>(inputList);
    }

    public lists addCommand(inputCommand command){
        List<String> tempList = new ArrayList<>(this.dukeList);
        tempList.add(command.getCommand());
        return new lists(tempList);
    }

    public void addCommandMutable(inputCommand command){
        this.dukeList.add(command.getCommand());
    }

    public List<String> getDukeList(){
        return this.dukeList;
    }
}
