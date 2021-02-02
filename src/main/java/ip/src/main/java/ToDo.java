package ip.src.main.java;

/**
 * ToDo class is a type of Task that has attribute String content.
 *
 */

public class ToDo extends Task{

    public ToDo(String content){
        super(content);
    }

    /**
     * toString() of ToDo Class.
     *
     * @return toString() representation of a ToDo object with its done status and content.
     */

    @Override
    public String toString() {
        if(!this.done){
            return "T | 0 | " + super.toString();
        }else {
            return "T | 1 | " + super.toString();
        }
    }
}
