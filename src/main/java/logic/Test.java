package logic;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, User> users = UserAgent.getUsers();

        System.out.println(users.size());
        for (Map.Entry<Integer, User> user : users.entrySet()) {
            System.out.println(user.getValue().getName());
        }
    }
}
