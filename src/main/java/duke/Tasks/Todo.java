package duke.Tasks;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
        this.cat = 'T';
    }

    @Override
    public String toString() {
        return "[" + this.cat + "] " + super.toString() ;
    }
}