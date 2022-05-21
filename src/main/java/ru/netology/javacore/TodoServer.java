package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    static class Request {
        String type;
        String task;

        public Request(String type, String task) {
            this.type = type;
            this.task = task;
        }

        @Override
        public String toString() {
            return "type = " + type + ", task = " + task;
        }
    }

    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    System.out.println("Новое подключение принято");
                    System.out.println("Адрес клиента: " + socket.getInetAddress() + ", порт: " + socket.getPort());
                    String json = in.readLine();
                    System.out.println("Сообщение клиента: " + json);
                    Request request = new Gson().fromJson(json, Request.class);
                    switch (request.type) {
                        case "ADD":
                            System.out.println("Задача " + request.task + " добавлена в список задач");
                            todos.addTask(request.task);
                            break;
                        case "REMOVE":
                            System.out.println("Задача " + request.task + " удалена из списка задач");
                            todos.removeTask(request.task);
                            break;
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "TodoServer { " + " port = " + port + ", todos = " + todos + " } ";
    }
}
