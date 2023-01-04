package driver;

import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
    WebDriver getDriver() throws WebDriverManagerException;
}