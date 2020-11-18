import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class MarketTest {

    private WebDriver driver;
    private final Logger logger = LogManager.getLogger();
    private static final int WAIT_TIME = 7;

    @BeforeClass
    public void openSite() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches",
                Collections.singletonList("disable-popup-blocking"));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        logger.info("The Chrome browser opened");

        driver.get("https://www.220-volt.ru/");
        logger.info("The 220-volt main page opened");
    }

    @Test
    public void checkProductComparison() throws InterruptedException {
        selectCity();
        openPerforators();
        sortProducts();
        addProductsToComparisonList();
        goToComparisonList();

        Assert.assertTrue(
                existsElement(By.id("399178_compare")) && existsElement(By.id("691618_compare")),
                "No valid products in the comparison list"
        );
        logger.info("Valid products in the comparison list");
    }

    private void goToComparisonList() throws InterruptedException {
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        closePopupAndClick(By.cssSelector(".linkContinueView"));
        logger.info("Going to the product comparison page is completed");

        String newWindow = (new WebDriverWait(driver, WAIT_TIME))
                .until((ExpectedCondition<String>) driver -> {
                    assert driver != null;
                    Set<String> newWindowsSet = driver.getWindowHandles();
                    newWindowsSet.removeAll(oldWindowsSet);
                    return newWindowsSet.size() > 0 ?
                            newWindowsSet.iterator().next() : null;
                });

        driver.switchTo().window(newWindow);
    }

    private void addProductsToComparisonList() throws InterruptedException {
        closePopupAndClick(By.cssSelector("[for='compare-399178']"));
        logger.info("The first item of brand 'Зубр' is chosen");

        closePopupAndClick(By.cssSelector(".toCompare>a"));
        logger.info("Confirmed to continue browsing products");

        closePopupAndClick(By.cssSelector("[for='compare-691618']"));
        logger.info("The first item of brand 'Makita' is chosen");
    }

    private void sortProducts() throws InterruptedException {
        closePopupAndClick(By.cssSelector("[for='producer_16']"));
        closePopupAndClick(By.cssSelector("[for='producer_473']"));

        closePopupAndClick(By.cssSelector("div.rounded5>a"));
        logger.info("Filtering products by brand is completed");

        closePopupAndClick(By.cssSelector(".select2-selection__rendered"));
        closePopupAndClick(By.cssSelector(".select2-results__option:first-of-type"));
        logger.info("Filtering products by price 'min->max' is completed");
    }

    private void openPerforators() throws InterruptedException {
        closePopupAndClick(By.cssSelector("ul.js-menu li:first-child a.menu-catalog-item"));
        closePopupAndClick(By.cssSelector("li.mhtspace-10:nth-of-type(12)"));
        logger.info("Going into the category of 'Perforators' is completed");
    }

    private void selectCity() {
        new WebDriverWait(driver, WAIT_TIME)
                .until(elementToBeClickable(By.cssSelector("[for='city7700000000000']")))
                .click();
        logger.info("The city in the popup is chosen");
    }

    private void closePopupAndClick(By by) throws InterruptedException {
//        'sleep' needs for proper handling popups
        sleep(1000);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        jsExecutor.executeScript("document.getElementsByClassName('flocktory-widget').forEach(e => e.remove());");
        jsExecutor.executeScript("document.getElementsByClassName('flocktory-widget-overlay').forEach(e => e.remove());");
        jsExecutor.executeScript("document.getElementsByClassName('flocktory-widget-overlay').forEach(e => e.remove());");
        jsExecutor.executeScript("document.getElementsByClassName('flocktory-widget-overlay').forEach(e => e.remove());");

        new WebDriverWait(this.driver, WAIT_TIME)
                .until(elementToBeClickable(by))
                .click();
    }

    private boolean existsElement(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            logger.info("The Chrome browser closed");
        }
    }
}
