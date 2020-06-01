package logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskAgent {

    //  мапа всех тасок  с ключом  по id

    private static Map<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();

    public static Map<Integer, Task> getTasks() {
        return tasks;
    }

    public static void setTasks(Map<Integer, Task> taskss) {
        tasks = taskss;
    }


    public static Task addTask(final Task task, User user) {

        AtomicInteger countOfTasks = new AtomicInteger(tasks.size());
        int id = countOfTasks.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);

//            добавляет в бд
        Connection connection = ConnectionDataBase.createConnection();
//            System.out.println("id in addTask method idtask " + task.getId());

        ConnectionDataBase.addTaskToDB(connection, user, task);
//            System.out.println("id in addTask method tasks size" + tasks.size());

        return task;
    }
}

