package logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserAgent {

    //  мапа всех юзеров с ключом по id
    private static Map<Integer, User> users = new ConcurrentHashMap<Integer, User>();

    public static Map<Integer, User> getUsers() {
        return users;
    }

    public static void setUsers(Map<Integer, User> userss) {
        users = userss;
    }

//     добавляет юзера в мапу и бд

    public static boolean add(final User user) {
        boolean result = true;
        List<String> logins = new ArrayList<String>();
        for (Map.Entry<Integer, User> pair : users.entrySet()){
            logins.add(pair.getValue().getLogin());
        }
//        проверяет есть ли в мапе такие логины - если да возвращает false

        if (logins.contains(user.getLogin())) {
            System.out.println("UserAgent add problem");
            result = false;
        } else {
            AtomicInteger countOfUsers = new AtomicInteger(users.size());
            int id = countOfUsers.incrementAndGet();
            user.setId(id);
            users.put(id, user);

//            добавляет в бд
            Connection connection = ConnectionDataBase.createConnection();
            System.out.println("id in add method" + user.getId());

            ConnectionDataBase.addUserToDB(connection, user);
            System.out.println(users.size());
        }
        return result;
    }

    // проверка есть ли в мапе юзеры с таким логином
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

    // получение юзера по логину
    public static User getUserByLogin(final String login) {
        User result = null;
        for (Map.Entry<Integer, User> pair : users.entrySet()){
            if (pair.getValue().getLogin().equalsIgnoreCase(login)) {
                result = pair.getValue();
            }
        }
        return result;
    }

    // проверяет пароль юзера
    public static boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}


//    public static User getUserByID (int id) {
//        User user = new User();
//        for (Map.Entry<Integer, User> pair : users.entrySet()){
//            pair.getValue()
//        }
//

//        for (User u : users) {
//            if (u.getLogin().equalsIgnoreCase(user.getLogin()) && u.getPassword().equals(user.getPassword())) {
//                return false;
//            }
//        }
       // return users.add(user);

//    public User getById(int id) {
//        User result = new User();
//        result.setId(-1);
//
//        for (User user : users) {
//            if (user.getId() == id) {
//                result = user;
//            }
//        }
//
//        return result;
//    }
//
//    public User getUserByLogin(final String login) {
//        User result = new User();
//        result.setId(-1);
//
//        for (User user : users) {
//            if (user.getLogin().equalsIgnoreCase(login)) {
//                result = user;
//            }
//        }
//        return result;
//    }
//
//    public User getUserByLoginPassword(final String login, final String password) {
//        User result = new User();
//        result.setId(-1);
//
//        for (User user : users) {
//            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equals(password)) {
//                result = user;
//            }
//        }
//        return result;
//    }
//
//
//    public boolean userIsExist(final String login, final String password) {
//        boolean result = false;
//        for (User user : users) {
//            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equals(password)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }
