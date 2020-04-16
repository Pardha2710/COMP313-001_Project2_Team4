package nearlynew.com;

public class User {

    public String name;
    public String phone;
    public String password;
    public String role;
    public String email;
    public String active;
    public String gender;

    public User() {
    }

    public User(String name, String email, String phone, String password,
                String role, String active, String gender) {
        this.name = name;
        this.email = email;
        this.active = active;
        this.gender = gender;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }
}
