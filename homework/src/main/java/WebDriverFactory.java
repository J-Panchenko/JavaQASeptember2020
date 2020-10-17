import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.Optional;

public class WebDriverFactory {
    private WebDriverFactory() {
    }

    public static WebDriver createWebDriver(String browser) {
        return create(browser, Optional.empty());
    }

    public static WebDriver createWebDriver(String browser, Capabilities options) {
        return create(browser, Optional.of(options));
    }

    private static WebDriver create(String browser, Optional<Capabilities> options) {
        if (browser.equalsIgnoreCase(Browser.CHROME.name())) {
            WebDriverManager.chromedriver().setup();
            return options.map(opts -> new ChromeDriver((ChromeOptions) opts)).orElseGet(ChromeDriver::new);
        } else if (browser.equalsIgnoreCase(Browser.FIREFOX.name())) {
            WebDriverManager.firefoxdriver().setup();
            return options.map(opts -> new FirefoxDriver((FirefoxOptions) opts)).orElseGet(FirefoxDriver::new);
        } else if (browser.equalsIgnoreCase(Browser.OPERA.name())) {
            WebDriverManager.operadriver().setup();
            return options.map(opts -> new OperaDriver((OperaOptions) opts)).orElseGet(OperaDriver::new);
        }
        throw new IllegalArgumentException("Browser name " + browser + " is unsupported");
    }
}
