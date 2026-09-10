package todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private String filename;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public TaskManager(String filename) {
        this.filename = filename;
        this.tasks = new ArrayList<>();
    }

    public void addTask(String title) {
        Task newTask = new Task(title);
        tasks.add(newTask);
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markDone();
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (Task task : tasks) {
                int doneValue = task.isDone() ? 1 : 0;
                writer.println(doneValue + ";" + task.getTitle());
            }
        } catch (Exception e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks() {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 2);
                int doneValue = Integer.parseInt(parts[0]);
                String title = parts[1];

                Task task = new Task(title);
                if (doneValue == 1) {
                    task.markDone();
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
