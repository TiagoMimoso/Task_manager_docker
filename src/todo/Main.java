package todo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.loadTasks();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("To-Do List Applications");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task Done");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Enter task title:");
                scanner.nextLine();
                String title = scanner.nextLine();
                taskManager.addTask(title);
                taskManager.saveTasks();
            } else if (choice == 2) {
                taskManager.listTasks();
            } else if (choice == 3) {
                System.out.print("Enter task number to mark as done: ");
                scanner.nextLine();
                int index = scanner.nextInt() - 1;
                taskManager.markTaskDone(index);
                taskManager.saveTasks();
            } else if (choice == 4) {
                System.out.print("Enter task number to remove: ");
                scanner.nextLine();
                int index = scanner.nextInt() - 1;
                taskManager.removeTask(index);
                taskManager.saveTasks();
            } else if (choice == 5) {
                taskManager.saveTasks();
                System.out.println("Exiting...");
                break;
            }
        }
        scanner.close();
    }
}
