import java.util.ArrayList;
import java.util.List;

public class lists {
    List<listItem> dukeList;
    class listItem{
        private final boolean doneStatus;
        private final String task;
        public listItem(inputCommand command){
            this.task = command.getCommand();
            this.doneStatus = false;
        }

        public listItem(inputCommand command, boolean isDone){
            this.task = command.getCommand();
            this.doneStatus = isDone;
        }

        public listItem markAsDone(){
            return new listItem(new inputCommand(this.task), true);
        }
    }

    public lists(){
        this.dukeList = new ArrayList<>();
    }

    public lists(inputCommand command){
        this.dukeList = new ArrayList<listItem>();
        dukeList.add(new listItem(command));
    }

    public lists(List<listItem> inputList){
        this.dukeList = new ArrayList<listItem>(inputList);
    }

    public lists addCommand(inputCommand command){
        List<listItem> tempList = new ArrayList<>(this.dukeList);
        tempList.add(new listItem(command));
        return new lists(tempList);
    }

    public void addCommandMutable(inputCommand command){
        this.dukeList.add(new listItem(command));
    }

    public List<listItem> getDukeList(){
        return this.dukeList;
    }
}
