package logic;
import java.sql.*;

public class ConnectionDataBase {
    private static final String host = "jdbc:mysql://localhost:3306/tasks_bd";
    private static final String uName = "root";
    private static final String uPass = "root";

    public static Connection createConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // загружает драйвер из mysql This forces the driver to register itself, so that Java knows how to handle those database connection strings.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver problem");
        }

        try {
            return DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("(method doConnection()) SQLException: " + sqlException.getMessage());
            return null;
        }
    }

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



    public static void addTaskToDB(Connection connection, User user, Task task) {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                String sql = String.format("INSERT INTO `tasks` (`id`, `user_id`, `title`, `content`) " +
                        "VALUES ('%d', '%d', '%s', '%s');", task.getId(), user.getId(), task.getTitle(), task.getDescription());
                statement.executeUpdate(sql);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("(method addTaskToDB()) SQLException: " + sqlException.getMessage());
        }
    }
}
