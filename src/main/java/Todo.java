public class Todo extends Task {
    public Todo(String str) {
        super(str);
        if(str.length()==0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
