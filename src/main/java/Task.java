class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void setDone() {
        this.done = true;    
    }

    @Override
    public String toString() {
        return done ? "[X] " + name : "[ ] " + name;
    }
}
