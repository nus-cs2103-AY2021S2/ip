public class ToDos extends Task {

    public ToDos(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return  "\t [T]" + super.toString();
    }
}
