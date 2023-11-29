package Login;

import java.util.ArrayList;
import tools.Util;

public class UserList {

    public static ArrayList<User> userList = addUser();

    public static ArrayList<User> addUser() {
        ArrayList<User> userlist = new ArrayList<>();
        userlist.add(new User("U001", "123456", "admin"));
        userlist.add(new User("U002", "123456", "user"));
        userlist.add(new User("U003", "123456", "user"));
        userlist.add(new User("U004", "123456", "user"));
        return userlist;
    }

    public static String getUserLogin() {
        do {
            String inputID = Util.loginUser("ID");
            String inputPass = Util.loginUser("Password");
            for (User user : userList) {
                if (inputID.equals(user.getID()) && inputPass.equals(user.getPassword())) {
                    System.out.println("Log in successfully with role " + user.getRole() + "!");
                    System.out.println("");
                    return user.getRole();
                }
            }
            System.out.println("User ID or Password is wrong. Please check again!");
        } while (true);
    }
}
