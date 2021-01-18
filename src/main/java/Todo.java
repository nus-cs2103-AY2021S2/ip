public class Todo extends Task{
        Todo(String name, int id){
            super(name, id);
        }
        @Override
        public String toString(){
            if(this.isDone){
                return "[T][X] "+ this.id + "." + this.name;
            }
            else{
                return "[T][ ] "+ this.id + "." + this.name;
            }
    }

}
