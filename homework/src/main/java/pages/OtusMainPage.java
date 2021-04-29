package pages;

import entities.UserAuth;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class OtusMainPage extends BasePage {
    private final WebDriver driver;
    private final By loginAndRegisterButton = By.className("js-open-modal");
    private final By emailField = By.cssSelector(".js-login .js-email-input");
    private final By passwordField = By.cssSelector(".js-login .js-psw-input");
    private final By loginButton = By.cssSelector(".new-input-line_relative .new-button_md");

    public OtusMainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://otus.ru");
        logger.info("The otus main page opened");
    }

    public void authentication(UserAuth userAuth) {
        click(loginAndRegisterButton);
        sendKeys(emailField, userAuth.getEmail());
        sendKeys(passwordField, userAuth.getPassword());
        submit(loginButton);
    }

    public void goToPersonalArea() {
        Actions moveTo = new Actions(driver);
        WebElement headerMenuItem = waitForClickable(By.className("header2-menu__item_dropdown_no-border"));
        moveTo.moveToElement(headerMenuItem).perform();
        WebElement firstDropItem = waitForClickable(By.cssSelector(".header2__right>div>div>div:nth-child(2)>a:nth-child(1)"));
        moveTo.moveToElement(firstDropItem);
        WebElement secondDropItem = waitForClickable(By.cssSelector(".header2__right>div>div>div:nth-child(2)>a:nth-child(2)"));
        moveTo.moveToElement(secondDropItem).click().build().perform();
        logger.info("Going to Personal area completed");
    }
}
