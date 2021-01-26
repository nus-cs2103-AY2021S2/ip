public abstract class Task {

    private String name;
    private boolean done = false;

    public Task (String name) {
        this.name = name;
    }
    
    public Task (boolean done, String name) { 
        this.done = done; 
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public void completed() {
        this.done = true;
    }
    
    public abstract String allParameterStr();

    @Override
    public String toString() {
        if (this.getDone()) {
            return "[X] " + this.getName();
        } else {
            return "[ ] " + this.getName();
        }
    }

}
