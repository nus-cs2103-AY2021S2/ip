class Task {
    private String name;
    private boolean done;
    private String type;
    private String date;

    public Task(String type, String name) {
        this.name = name;
        this.done = false;
        this.type = type;
        this.date = "";
    }
    
    public Task(String type, String name, String date) {
        this.name = name;
        this.done = false;
        this.type = type;
        this.date = date;
    }

    public void setDone() {
        this.done = true;    
    }

    @Override
    public String toString() {
        return done ? "["+ type + "][X] " + name + date
            : "[" + type + "][ ] " + name + date;
    }
}
