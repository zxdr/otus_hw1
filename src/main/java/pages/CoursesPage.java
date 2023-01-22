package pages;

import org.openqa.selenium.WebDriver;

public class CoursesPage extends BasePage<CoursesPage> {

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}

