package entities;

public class UserContactData {
    private final String phoneNumber;
    private final String serviceViber;
    private final String serviceTelegram;

    private UserContactData(String phoneNumber, String serviceViber, String serviceTelegram) {
        this.phoneNumber = phoneNumber;
        this.serviceViber = serviceViber;
        this.serviceTelegram = serviceTelegram;
    }

    public static UserContactData defaultContactData() {
        return new UserContactData("+711111111111", "Viber", "Тelegram");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getServiceViber() {
        return serviceViber;
    }

    public String getServiceTelegram() {
        return serviceTelegram;
    }
}
