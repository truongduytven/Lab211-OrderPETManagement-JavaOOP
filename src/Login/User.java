package Login;

public class User {

    private String ID;
    private String Password;
    private String role;

    public User() {
    }

    public User(String ID, String Password, String role) {
        this.ID = ID;
        this.Password = Password;
        this.role = role;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
