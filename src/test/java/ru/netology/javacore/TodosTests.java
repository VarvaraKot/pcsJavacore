package ru.netology.javacore;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodosTests {
    @BeforeEach
    public void init() {
        System.out.println("test started");
    }

    @BeforeAll
    static void started() {
        System.out.println("tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("test completed");
    }

    @AfterAll
    static void finishedAll() {
        System.out.println("tests completed");
    }

    @Test
    public void testAddTask() {
        Todos todos = new Todos();
        todos.addTask("Сходить в магазин");
        boolean actual = todos.getAllTasks().contains("Сходить в магазин");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testRemoveTask() {
        Todos todos = new Todos();
        todos.removeTask("Сходить в магазин");
        boolean actual = todos.getAllTasks().contains("Сходить в магазин");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testGetAllTasks() {
        Todos todos = new Todos();
        String expected = "Заняться учебой" + " " + "Пойти на пробежку" + " " + "Сходить в магазин" + " ";
        todos.addTask("Сходить в магазин");
        todos.addTask("Пойти на пробежку");
        todos.addTask("Заняться учебой");
        Assertions.assertEquals(expected, todos.getAllTasks());
    }

    @Test
    public void testGetTasks() {
        Todos todos = new Todos();
        todos.addTask("Сходить в магазин");
        todos.addTask("Пойти на пробежку");
        todos.addTask("Заняться учебой");
        List<String> expected = new ArrayList<>(Arrays.asList("Сходить в магазин", "Пойти на пробежку", "Заняться учебой"));
        List<String> actual = todos.getTasks();
        Assertions.assertEquals(expected, actual);
    }
}