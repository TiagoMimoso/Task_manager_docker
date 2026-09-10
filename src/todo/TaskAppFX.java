package todo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskAppFX extends Application {

    private final TaskManager workManager = new TaskManager("work.txt");
    private final ListView<String> workListView = new ListView<>();

    private final TaskManager personalManager = new TaskManager("personal.txt");
    private final ListView<String> personalListView = new ListView<>();

    private final TaskManager futureGoalsManager = new TaskManager("FutureGoals.txt");
    private final ListView<String> futureGoalsListView = new ListView<>();

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();

        Tab workTab = new Tab("Work", createTaskPane(workManager, workListView));
        Tab personalTab = new Tab("Personal", createTaskPane(personalManager, personalListView));
        Tab futureGoalsTab = new Tab("Future Goals", createTaskPane(futureGoalsManager, futureGoalsListView));
        tabPane.getTabs().addAll(workTab, personalTab, futureGoalsTab);

        Scene scene = new Scene(tabPane, 400, 400);

        stage.setTitle("To-Do List");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createTaskPane(TaskManager manager, ListView<String> listView) {
        manager.loadTasks();
        refreshList(manager, listView);

        TextField taskField = new TextField();
        taskField.setPromptText("Enter new task");

        Button addButton = new Button("Add");
        Button doneButton = new Button("Mark Done");

        addButton.setOnAction(e -> {
            String title = taskField.getText().trim();
            if (!title.isEmpty()) {
                manager.addTask(title);
                manager.saveTasks();
                refreshList(manager, listView);
                taskField.clear();
            }
        });

        doneButton.setOnAction(e -> {
            int index = listView.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                manager.markTaskDone(index);
                manager.saveTasks();
                refreshList(manager, listView);
            }
        });

        HBox buttonBox = new HBox(10, addButton, doneButton);
        return new VBox(10, taskField, listView, buttonBox);
    }

    private void refreshList(TaskManager manager, ListView<String> listView) {
        listView.getItems().clear();
        for (Task task : manager.getTasks()) {
            listView.getItems().add(task.toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
