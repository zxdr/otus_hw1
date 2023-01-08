package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
                .ifPresent(this::clickCourseItemByNameWithHighlighting);

        return new Courses(driver);
    }

    private void clickCourseItemByNameWithHighlighting(WebElement webElement) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');",
                webElement);

        webElement.click();
    }

}
