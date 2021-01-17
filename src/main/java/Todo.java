class Todo extends Task {
    protected boolean isDone;
    protected final static String type = "[T]";

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }


    @Override
    public String getType() {
        return type;
    }

}
