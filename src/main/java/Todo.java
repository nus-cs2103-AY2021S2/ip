public class Todo extends Task{

    private final String TYPE_ICON = "[T]";


    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }

}
