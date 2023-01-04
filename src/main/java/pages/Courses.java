package pages;

import org.openqa.selenium.WebDriver;

public class Courses extends BasePage<Courses> {

    public Courses(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}

