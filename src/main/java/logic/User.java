package logic;

import java.util.List;
import java.util.Map;

public class User {
    private int id;
    private String name;
    private String login; // ключ в мапе в агенте
    private String password;
    private Map<Integer,Task> tasks;

//    public User(String name, String login, String password, int id) {
//        this.name = name;
//        this.login = login;
//        this.password = password;
//        this.id = id;
//    }

    public User (){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }
}
