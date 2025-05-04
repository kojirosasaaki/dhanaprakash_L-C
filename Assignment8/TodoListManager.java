package Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoListManager {
    private final List<TodoItem> todoList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> removeTask();
                case 3 -> markTaskAsDone();
                case 4 -> listTasks();
                case 5 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n1. Add");
        System.out.println("2. Remove");
        System.out.println("3. Done");
        System.out.println("4. List");
        System.out.println("5. Exit");
        System.out.print("> ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a number: ");
            scanner.nextLine();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private void addTask() {
        System.out.print("Enter task: ");
        String task = scanner.nextLine();
        todoList.add(new TodoItem(task));
    }

    private void removeTask() {
        if (todoList.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }

        listTasks();
        System.out.print("Enter task number to remove: ");
        int index = getTaskIndex();
        if (isValidIndex(index)) {
            todoList.remove(index);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void markTaskAsDone() {
        if (todoList.isEmpty()) {
            System.out.println("No tasks to mark as done.");
            return;
        }

        listTasks();
        System.out.print("Enter task number to mark as done: ");
        int index = getTaskIndex();
        if (isValidIndex(index)) {
            todoList.get(index).markDone();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void listTasks() {
        if (todoList.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (int i = 0; i < todoList.size(); i++) {
            System.out.println((i + 1) + ". " + todoList.get(i));
        }
    }

    private int getTaskIndex() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.nextLine();
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        return index;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < todoList.size();
    }
}

