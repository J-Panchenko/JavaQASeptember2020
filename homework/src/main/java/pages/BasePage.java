package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private static final int TIME_WAIT = 30;
    private final WebDriver driver;
    protected final Logger logger = LogManager.getLogger();

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement waitForClickable(By by) {
        return new WebDriverWait(driver, TIME_WAIT)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void click(By by) {
        waitForClickable(by).click();
        logger.info("Element with selector \"" + by + "\" is clicked");
    }

    protected void sendKeys(By by, String text) {
        WebElement element = waitForClickable(by);
        element.clear();
        waitForClickable(by).sendKeys(text);
        logger.info("Field with selector \"" + by + "\" is filled out with text \"" + text + "\"");
    }

    protected void submit(By by) {
        waitForClickable(by).submit();
        logger.info("Form with button \"" + by + "\" is submitted");
    }
}
