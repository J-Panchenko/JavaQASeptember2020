import entities.UserAuth;
import entities.UserContactData;
import entities.UserMainInfo;
import entities.UserPersonalData;
import enums.Contact;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.OtusMainPage;
import pages.PersonalAreaPage;
import pages.PersonalDataPage;

public class PersonalDataTest {
    private WebDriver driver;
    private final Logger logger = LogManager.getLogger();
    private final UserAuth userAuth = UserAuth.defaultUser();

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("The Chrome browser opened");
    }

    @Test
    public void personalDataTest() {
        logger.info("Personal data Test started");
        OtusMainPage mainPage = new OtusMainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        UserPersonalData userPersonalData = UserPersonalData.defaultPersonalData();

        mainPage.openPage();
        mainPage.authentication(userAuth);
        mainPage.goToPersonalArea();
        personalAreaPage.goToPersonalDataPage();
        personalDataPage.fillOutFieldsWithPersonalData(userPersonalData);
        personalDataPage.selectMainInfo();
        logger.info("Creating first contact");
        personalDataPage.fillOutContact(Contact.FIRST);
        logger.info("Creating first contact is finished");
        personalDataPage.addNewContactButtonClick();
        logger.info("Creating second contact");
        personalDataPage.fillOutContact(Contact.SECOND);
        logger.info("Creating second contact is finished");
        personalDataPage.addNewContactButtonClick();
        personalDataPage.saveAndContinueButtonClick();
        logger.info("Personal data Test finished");
    }

    @Test(dependsOnMethods = "personalDataTest")
    public void checkUserProfileTest() {
        logger.info("Check user profile Test started");
        OtusMainPage mainPage = new OtusMainPage(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        mainPage.openPage();
        mainPage.authentication(userAuth);
        mainPage.goToPersonalArea();
        personalAreaPage.goToPersonalDataPage();

        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        UserPersonalData userPersonalData = UserPersonalData.defaultPersonalData();
        UserMainInfo userMainInfo = UserMainInfo.defaultMainInfo();
        UserContactData userContactData = UserContactData.defaultContactData();
        SoftAssert softAssert = new SoftAssert();

        logger.info("Verification of the correctness of the data of the filled fields began");
        softAssert.assertEquals(personalDataPage.getFirstName(), userPersonalData.getFirstName(),
                "Incorrect first name");
        softAssert.assertEquals(personalDataPage.getLastName(), userPersonalData.getLastName(),
                "Incorrect last name");
        softAssert.assertEquals(personalDataPage.getLatinFirstName(), userPersonalData.getLatinFirstName(),
                "Incorrect latin first name");
        softAssert.assertEquals(personalDataPage.getLatinLastName(), userPersonalData.getLatinLastName(),
                "Incorrect latin last name");
        softAssert.assertEquals(personalDataPage.getBlogName(), userPersonalData.getBlogName(),
                "Incorrect blog name");
        softAssert.assertEquals(personalDataPage.getDateOfBirthday(), userPersonalData.getDateOfBirthday(),
                "Incorrect date of birthday");
        softAssert.assertEquals(personalDataPage.getSelectUkraine(), userMainInfo.getCountry(),
                "Incorrect country");
        softAssert.assertEquals(personalDataPage.getSelectKyiv(), userMainInfo.getCity(),
                "Incorrect city");
        softAssert.assertEquals(personalDataPage.getSelectIntermediateLevel(), userMainInfo.getEnglishLevel(),
                "Incorrect English level");
        softAssert.assertEquals(personalDataPage.getContactValue(Contact.FIRST), userContactData.getPhoneNumber(),
                "Incorrect first contact");
        softAssert.assertEquals(personalDataPage.getContactService(Contact.FIRST), userContactData.getServiceViber(),
                "Incorrect first contact service");
        softAssert.assertEquals(personalDataPage.getContactValue(Contact.SECOND), userContactData.getPhoneNumber(),
                "Incorrect second contact");
        softAssert.assertEquals(personalDataPage.getContactService(Contact.SECOND), userContactData.getServiceTelegram(),
                "Incorrect second contact service");
        softAssert.assertAll();
        logger.info("Verification of the correctness of the data of the filled fields is over");
        logger.info("Check user profile Test finished");
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            logger.info("The Chrome browser closed");
        }
    }
}
