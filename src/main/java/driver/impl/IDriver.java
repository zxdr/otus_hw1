package driver.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public interface IDriver {
    String REMOTE_URL = System.getProperty("webdriver.remote.url");
    boolean HEADLESS = Boolean.valueOf(System.getProperty("webdriver.headless"));

    public WebDriver newDriver();

    default URL getRemoteUrl() {
        try {
            return new URL(REMOTE_URL);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    default void downloadLocalWebDriver(DriverManagerType driverType) throws WebDriverManagerException {

        String browserVersion = System.getProperty("browser.version", "");

        if (!browserVersion.isEmpty()) {
            switch (driverType) {
                case CHROME:
                    WebDriverManager.chromedriver().config().setChromeDriverVersion(browserVersion);
                    break;
                default:
                    throw new WebDriverManagerException("Invalid driver manager type: " + driverType.name());
            }
        }

        WebDriverManager.getInstance(driverType).create();
    }
}