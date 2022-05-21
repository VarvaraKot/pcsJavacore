package ru.netology.javacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Todos {
    private final List<String> tasks;

    public Todos() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        this.tasks.add(task);
    }

    public void removeTask(String task) {
        this.tasks.remove(task);
    }

    public String getAllTasks() {
        Collections.sort(this.tasks);
        StringBuilder stringBuilder = new StringBuilder();
        for (String task : this.tasks) {
            stringBuilder.append(task);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public List<String> getTasks() {
        return this.tasks;
    }

    @Override
    public String toString() {
        return "Todos { " + " tasks = " + tasks + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Todos)) return false;
        Todos todos = (Todos) obj;
        return getAllTasks().equals(todos.getAllTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAllTasks());
    }
}
