package entities;

public class UserAuth {
    private final String email;
    private final String password;

    private UserAuth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static UserAuth defaultUser() {
        return new UserAuth(System.getProperty("email"), System.getProperty("password"));
    }
}
