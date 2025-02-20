package io.michaeljgkopp.github.springconsoledemo.presentation;

public class MenuItem {
    private final String description;
    private final Runnable runnable;

    public MenuItem(String description, Runnable runnable) {
        this.description = description;
        this.runnable = runnable;
    }

    public void execute() {
        runnable.run();
    }

    public String getDescription() {
        return description;
    }
}