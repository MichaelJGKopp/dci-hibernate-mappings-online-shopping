package io.michaeljgkopp.github.springconsoledemo.presentation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<Integer, MenuItem> menuItems = new LinkedHashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final String title;

    public Menu(String title) {
        this.title = title;
    }

    public void addItem(MenuItem item) {
        menuItems.put(menuItems.size() + 1, item);
    }

    public void addItem(int number, MenuItem item) {
        menuItems.put(number, item);
    }

    public void display() {
        while (true) {
            System.out.println("\n=== " + title + " ===");
            System.out.println("0. Exit");
            menuItems.forEach((key, value) -> System.out.println(key + ". " + value.getDescription()));

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Exiting application...");
                break;
            }

            MenuItem item = menuItems.get(choice);
            if (item != null) {
                item.execute();
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}