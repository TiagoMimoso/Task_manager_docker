package todo;

public class Task {
    private String title;
    private boolean done;

    public Task(String title) {
        this.title = title;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public String toString() {
        String status = done ? "[X]" : "[ ]";
        return status + " " + title;
    }

    public String getTitle() {
        return this.title;
    }
}
