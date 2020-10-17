import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OtusMainPageTest {

    private WebDriver driver;
    private final Logger logger = LogManager.getLogger();
    private final OtusConfig config = ConfigFactory.create(OtusConfig.class);

    @BeforeClass
    public void openBrowser() {
        String browserName = System.getProperty("browser");
        driver = WebDriverFactory.createWebDriver(browserName);
        logger.info("The Chrome browser opened");
    }

    @Test
    public void checkMainPageTitle() {
        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        driver.get(config.url());
        logger.info("The otus main page opened");
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Incorrect title");
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            logger.info("The Chrome browser closed");
        }
    }
}
