package entities;

public class UserMainInfo {
    private final String country;
    private final String city;
    private final String englishLevel;

    private UserMainInfo(String country, String city, String englishLevel) {
        this.country = country;
        this.city = city;
        this.englishLevel = englishLevel;
    }

    public static UserMainInfo defaultMainInfo() {
        return new UserMainInfo("Украина", "Киев", "Средний (Intermediate)");
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }
}
