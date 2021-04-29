package enums;

public enum Contact {
    FIRST(0, 6),
    SECOND(1, 7);

    private final int contactPosition;
    private final int contactService;

    Contact(int contactPosition, int contactService) {
        this.contactPosition = contactPosition;
        this.contactService = contactService;
    }

    public int getContactPosition() {
        return contactPosition;
    }

    public int getContactService() {
        return contactService;
    }
}
