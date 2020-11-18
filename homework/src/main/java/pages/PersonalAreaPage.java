package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAreaPage extends BasePage {

    public PersonalAreaPage(WebDriver driver) {
        super(driver);
    }

    public void goToPersonalDataPage() {
        By aboutMeButton = By.cssSelector(".body>:nth-child(6)>div>div>div>a:nth-child(3)");
        click(aboutMeButton);
    }
}
