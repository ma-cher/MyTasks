package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//      the map with all users (id = key)

public class UserAgent {

    private static Map<Integer, User> users = new ConcurrentHashMap<Integer, User>();

    public static Map<Integer, User> getUsers() {
        return users;
    }

    public static void setUsers(Map<Integer, User> userss) {
        users = userss;
    }

//     add user to map and db (return true if the login is free)

    public static boolean add(final User user) throws SQLException {
        boolean result = true;
        List<String> logins = new ArrayList<String>();
        for (Map.Entry<Integer, User> pair : users.entrySet()) {
            logins.add(pair.getValue().getLogin());
        }
//        check if the map has the same login

        if (logins.contains(user.getLogin())) {
            System.out.println("UserAgent add problem");
            result = false;
        } else {
            AtomicInteger countOfUsers = new AtomicInteger(users.size());
            int id = countOfUsers.incrementAndGet();
            user.setId(id);
            users.put(id, user);

//        add to db
            Connection connection = ConnectionDataBase.createConnection();
            System.out.println("id in add method" + user.getId());

            ConnectionDataBase.addUserToDB(connection, user);
            System.out.println(users.size());
        }
        return result;
    }

//     check is login already exist

    public static boolean loginIsExist(String login) {
        boolean result = false;
        for (Map.Entry<Integer, User> pair : users.entrySet()) {
            if (pair.getValue().getLogin().equalsIgnoreCase(login)) {
                result = true;
                break;
            }
        }
        return result;
    }

//   get user by login

    public static User getUserByLogin(final String login) {
        User result = null;
        for (Map.Entry<Integer, User> pair : users.entrySet()) {
            if (pair.getValue().getLogin().equalsIgnoreCase(login)) {
                result = pair.getValue();
            }
        }
        return result;
    }

//    check password

    public static boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

}

