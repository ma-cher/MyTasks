package logic;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//    the only one connection class

public class ConnectionDataBase {
    private static final String host = "jdbc:mysql://localhost:3306/tasks_bd";
    private static final String uName = "root";
    private static final String uPass = "root";

    public static Connection createConnection () throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // download mysql. This forces the driver to register itself, so that Java knows how to handle those database connection strings.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver problem");
        }

        try {
            return DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException sqlException) {
            System.out.println("(method doConnection()) SQLException: " + sqlException.getMessage());
            return null;
        }
    }

//    add user to db to table users

    public static void addUserToDB(Connection connection, User user) {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = String.format("INSERT INTO `users` (`id`, `name`, `login`, `password`) " +
                        "VALUES ('%d', '%s', '%s', '%s');",user.getId(), user.getName(), user.getLogin(), user.getPassword());
                statement.executeUpdate(sql);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("(method addUserToDB()) SQLException: " + sqlException.getMessage());
        }
    }

//    add task to db to table tasks

    public static void addTaskToDB(Connection connection, User user, Task task) {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = String.format("INSERT INTO `tasks` (`id`, `user_id`, `title`, `content`) " +
                        "VALUES ('%d', '%d', '%s', '%s');", task.getId(), user.getId(), task.getTitle(), task.getDescription());
                statement.executeUpdate(sql);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("(method addTaskToDB()) SQLException: " + sqlException.getMessage());
        }
    }

//    get all users from db (use only one time init context)

    public static Map<Integer, User> getUsersFromDb(Connection connection) {
        Map<Integer, User> map = new ConcurrentHashMap<Integer, User>();
        User user;
        int id;
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM users");

                while (rs.next()) {
                    user = new User();
                    id = rs.getInt("id");
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    map.put(id, user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQLException from getUsersFromDb(): " + throwables.getMessage());
        }
        return map;
    }

//    delete task from db

    public static void deleteTaskFromDb(Connection connection, Task task) {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = String.format("DELETE FROM `tasks_bd`.`tasks` WHERE (`id` = '%d'); ", task.getId());
                statement.executeUpdate(sql);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("(method deleteTaskToDB()) SQLException: " + sqlException.getMessage());
        }
    }

    // get all tasks from db (use only one time init context)

    public static Map<Integer, Task> getAllTasksFromDB (Connection connection) {
        Map<Integer, Task> map = new ConcurrentHashMap<Integer, Task>();
        Task task;
        int id;

        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM tasks");

                while (rs.next()) {
                    task = new Task();
                    id = rs.getInt("id");
                    task.setIdUser(rs.getInt("user_id"));
                    task.setId(rs.getInt("id"));
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("content"));
                    map.put(id, task);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQLException from getAllTasksFromDB(): " + throwables.getMessage());
        }

        return map;
    }
    public static Map<Integer, Task> getTasksForUserFromDb(Connection connection, int idUser) {
        Map<Integer, Task> map = new HashMap<Integer, Task>();

        Task task;
        int id;
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM tasks WHERE user_id="+ idUser);

                while (rs.next()) {
                    task = new Task();
                    id = rs.getInt("id");
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("content"));

                    map.put(id, task);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("SQLException from getTasksForUserFromDb(): " + throwables.getMessage());
        }
        return map;
    }

    public static void updateTaskFromDb(Connection connection, Task task) {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = String.format("UPDATE `tasks_bd`.`tasks` SET `title` = '%s', `content` = '%s' WHERE (`id` = '%d');", task.getTitle(), task.getDescription(), task.getId());
                statement.executeUpdate(sql);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("(method updateTaskFromDB()) SQLException: " + sqlException.getMessage());
        }
    }
}
