package driver;

import driver.impl.ChromeWebDriver;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class WebDriverFactory implements IDriverFactory {

    private String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

    // TODO: добавить оперу
    @Override
    public EventFiringWebDriver getDriver() {
        switch (this.browserType) {
            case "chrome": {
                return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
            }
//            case "opera": {
//                return new EventFiringWebDriver(new Opera);
//            }
            default:
                try {
                    throw new WebDriverManagerException(this.browserType);
                } catch (WebDriverManagerException ex) {
                    ex.printStackTrace();
                    return null;
                }
        }
    }
}