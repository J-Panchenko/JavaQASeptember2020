package pages;

import entities.UserContactData;
import entities.UserPersonalData;
import enums.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public class PersonalDataPage extends BasePage {
    private final WebDriver driver;
    private final By firstName = By.cssSelector("[name='fname']");
    private final By lastName = By.cssSelector("[name='lname']");
    private final By latinFirstName = By.cssSelector("[name='fname_latin']");
    private final By latinLastName = By.cssSelector("[name='lname_latin']");
    private final By blogName = By.cssSelector("[name='blog_name']");
    private final By dateOfBirthday = By.cssSelector("[name='date_of_birth']");
    private final By selectUkraine = By.cssSelector(".lk-cv-block__select-scroll_country>button:nth-of-type(5)");
    private final By selectKyiv = By.cssSelector(".lk-cv-block__select-scroll_city>button:nth-of-type(15)");
    private final By selectIntermediateLevel = By.xpath("//input[@name='english_level']/../following-sibling::div//button[5]");
    private final Function<Contact, By> contactValue = (Contact contact) ->
            By.xpath("//input[@name='contact-" + contact.getContactPosition() + "-value']");
    private final Function<Contact, By> contactService = (Contact contact) ->
            By.xpath("//input[@name='contact-" + contact.getContactPosition() +
                    "-service']/../following-sibling::div//button[" +
                    contact.getContactService() + "]");

    public PersonalDataPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void fillOutFieldsWithPersonalData(UserPersonalData userPersonalData) {
        sendKeys(firstName, userPersonalData.getFirstName());
        sendKeys(lastName, userPersonalData.getLastName());
        sendKeys(latinFirstName, userPersonalData.getLatinFirstName());
        sendKeys(latinLastName, userPersonalData.getLatinLastName());
        sendKeys(blogName, userPersonalData.getBlogName());
        sendKeys(dateOfBirthday, userPersonalData.getDateOfBirthday());
    }

    public void selectMainInfo() {
        By countryButton = By.xpath("//input[@name='country']/following-sibling::div");
        By cityButton = By.xpath("//input[@name='city']/following-sibling::div");
        By englishLevelButton = By.xpath("//input[@name='english_level']/following-sibling::div");
        click(countryButton);
        click(selectUkraine);
        click(cityButton);
        click(selectKyiv);
        click(englishLevelButton);
        click(selectIntermediateLevel);
    }

    public void fillOutContact(Contact contact) {
        By chooseContactServiceButton = By.xpath("//input[@name='contact-" +
                contact.getContactPosition() + "-service']/following-sibling::div");
        UserContactData userContactData = UserContactData.defaultContactData();
        click(chooseContactServiceButton);
        click(contactService.apply(contact));
        sendKeys(contactValue.apply(contact), userContactData.getPhoneNumber());
    }

    public void addNewContactButtonClick() {
        By addNewContactButton = By.cssSelector(".js-lk-cv-custom-select-add");
        click(addNewContactButton);
    }

    public void saveAndContinueButtonClick() {
        By saveAndContinueButton = By.cssSelector("button[name='continue']");
        click(saveAndContinueButton);
    }

    public String getFirstName() {
        waitForClickable(firstName);
        return driver.findElement(firstName).getAttribute("value");
    }

    public String getLastName() {
        waitForClickable(lastName);
        return driver.findElement(lastName).getAttribute("value");
    }

    public String getLatinFirstName() {
        waitForClickable(latinFirstName);
        return driver.findElement(latinFirstName).getAttribute("value");
    }

    public String getLatinLastName() {
        waitForClickable(latinLastName);
        return driver.findElement(latinLastName).getAttribute("value");
    }

    public String getBlogName() {
        waitForClickable(blogName);
        return driver.findElement(blogName).getAttribute("value");
    }

    public String getDateOfBirthday() {
        waitForClickable(dateOfBirthday);
        return driver.findElement(dateOfBirthday).getAttribute("value");
    }

    public String getSelectUkraine() {
        return driver.findElement(selectUkraine).getAttribute("title");
    }

    public String getSelectKyiv() {
        return driver.findElement(selectKyiv).getAttribute("title");
    }

    public String getSelectIntermediateLevel() {
        return driver.findElement(selectIntermediateLevel).getAttribute("title");
    }

    public String getContactValue(Contact contact) {
        waitForClickable(contactValue.apply(contact));
        return driver.findElement(contactValue.apply(contact)).getAttribute("value");
    }

    public String getContactService(Contact contact) {
        return driver.findElement(contactService.apply(contact)).getAttribute("title");
    }
}
