package entities;

public class UserPersonalData {
    private final String firstName;
    private final String lastName;
    private final String latinFirstName;
    private final String latinLastName;
    private final String blogName;
    private final String dateOfBirthday;

    private UserPersonalData(String firstName, String lastName, String latinFirstName,
                             String latinLastName, String blogName, String dateOfBirthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.latinFirstName = latinFirstName;
        this.latinLastName = latinLastName;
        this.blogName = blogName;
        this.dateOfBirthday = dateOfBirthday;
    }

    public static UserPersonalData defaultPersonalData() {
        return new UserPersonalData(
                "Иван",
                "Иванов",
                "Ivan",
                "Ivanov",
                "IvanIvan",
                "15.05.1995"
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLatinFirstName() {
        return latinFirstName;
    }

    public String getLatinLastName() {
        return latinLastName;
    }

    public String getBlogName() {
        return blogName;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }
}
