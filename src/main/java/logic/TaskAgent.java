package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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

    public static Task addTask(final Task task, User user) throws SQLException {

        AtomicInteger countOfTasks = new AtomicInteger(tasks.size());
        int id = countOfTasks.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);

//     add to db

        Connection connection = ConnectionDataBase.createConnection();

        ConnectionDataBase.addTaskToDB(connection, user, task);

        return task;
    }
    public static User removeTask(final Task task, User user) throws SQLException {
        Map<Integer, Task> tasks = user.getTasks();
        tasks.remove(task.getId());

        Connection connection = ConnectionDataBase.createConnection();

        ConnectionDataBase.deleteTaskFromDb(connection, task);

        return user;
    }

    public static User updateTask(final Task task, User user, String title, String description) throws SQLException {
        Map<Integer, Task> tasks = user.getTasks();
        for (Map.Entry<Integer, Task> taskEntry : tasks.entrySet()) {
            if (taskEntry.getKey() == task.getId()) {
                taskEntry.getValue().setTitle(title);
                taskEntry.getValue().setDescription(description);
            }
        }

        Connection connection = ConnectionDataBase.createConnection();

        ConnectionDataBase.updateTaskFromDb(connection, task);

        return user;
    }
}

