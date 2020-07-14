package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectionDatabase {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionDataBase.createConnection();

        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM tasks");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " "
                            + rs.getInt("user_id") + " "
                            + rs.getInt("purpose_id") + " "
                            + rs.getString("title") + " "
                            + rs.getString("content") + " "
                            + rs.getString("done"));
                }
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception from TestConnectionDatabase class: " + e.getMessage());
        }
    }
}
