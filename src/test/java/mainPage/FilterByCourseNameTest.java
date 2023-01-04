package mainPage;

import annotations.Driver;
import components.FavoriteCoursesComponent;
import extensions.UiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;


@ExtendWith(UiExtension.class)
public class FilterByCourseNameTest {

    @Driver
    private WebDriver driver;

    @Test
    public void assertCourseNameInFilter() {

        new MainPage(driver)
                .open();

        String pageTitle = new FavoriteCoursesComponent(driver)
                .clickCourseItemByName("Team Lead").getPageTitle();

        Assertions.assertEquals("Курс Team Lead", pageTitle);
    }
}
