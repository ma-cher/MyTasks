package logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//  manage map of all tasks

public class TaskAgent {

    private static Map<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();

    public static Map<Integer, Task> getTasks() {
        return tasks;
    }

    public static void setTasks(Map<Integer, Task> taskss) {
        tasks = taskss;
    }


//    add task to map and return the task with increment ID

    public static Task addTask(final Task task, User user) {

        AtomicInteger countOfTasks = new AtomicInteger(tasks.size());
        int id = countOfTasks.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);

//     add to db

        Connection connection = ConnectionDataBase.createConnection();

        ConnectionDataBase.addTaskToDB(connection, user, task);

        return task;
    }
}

