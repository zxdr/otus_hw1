package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Courses;

import java.util.List;

public class FavoriteCoursesComponent extends AbsComponent<FavoriteCoursesComponent> {

    @FindBy(css = ".lessons > a")
    private List<WebElement> lessons;

    public FavoriteCoursesComponent(WebDriver driver) {
        super(driver);
    }

    public Courses clickCourseItemByName(String text) {
        lessons.stream().map(search -> search.findElement(By.cssSelector(".lessons__new-item-title")))
                .filter(webElement -> webElement.getText().contains(text))
                .findAny()
                .ifPresent(WebElement::click);

        return new Courses(driver);
    }

}
