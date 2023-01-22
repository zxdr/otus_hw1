package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CoursesPage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FavoriteCoursesComponent extends AbsComponent<FavoriteCoursesComponent> {

    private final List<String> monthNames = Arrays.asList("январь", "февраль", "март", "апрель", "май", "июнь",
            "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь");

    @FindBy(css = ".lessons > a")
    private List<WebElement> lessons;

    public FavoriteCoursesComponent(WebDriver driver) {
        super(driver);
    }

    public CoursesPage clickCourseItemByName(String text) {
        lessons.stream().map(search -> search.findElement(By.cssSelector(".lessons__new-item-title")))
                .filter(webElement -> webElement.getText().contains(text))
                .findAny()
                .ifPresent(this::clickCourseItemByNameWithHighlighting);

        return new CoursesPage(driver);
    }

    private void clickCourseItemByNameWithHighlighting(WebElement webElement) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');",
                webElement);

        webElement.click();
    }

    public CoursesPage clickCourseItemTheEarliest() {
        Stream<String> stringStream = lessons.stream()
                .map(search -> search.findElement(By.cssSelector(".lessons__new-item-start")).getText());

        String s1 = stringStream
                .reduce((text1, text2) -> {
                    Pattern pattern = Pattern.compile("^.*?(\\d+ [а-я]+).*");
                    Matcher matcher = pattern.matcher(text1);
                    Matcher matcher2 = pattern.matcher(text2);
                    LocalDate date1 = parseDate(matcher.group(1));
                    LocalDate date2 = parseDate(matcher2.group(1));
                    return date1.compareTo(date2) >= 0 ? text1 : text2;
                }).orElse(null);

        return new CoursesPage(driver);

    }

    public CoursesPage clickCourseItemTheLatest() {
        Stream<String> stringStream = lessons.stream()
                .map(search -> search.findElement(By.cssSelector(".lessons__new-item-start")).getText());

        String s2 = stringStream
                .reduce((text1, text2) -> {
                    Pattern pattern = Pattern.compile("^.*?(\\d+ [а-я]+).*");
                    Matcher matcher = pattern.matcher(text1);
                    Matcher matcher2 = pattern.matcher(text2);
                    LocalDate date1 = parseDate(matcher.group(1));
                    LocalDate date2 = parseDate(matcher2.group(1));
                    return date1.compareTo(date2) >= 0 ? text2 : text1;
                }).orElse(null);

        return new CoursesPage(driver);

    }

    private LocalDate parseDate(String dayAndMonth) {
        String[] array = dayAndMonth.split(" ");
        return LocalDate.of(LocalDate.now().getYear(), monthNames.indexOf(array[1]), Integer.parseInt(array[0]));
    }

}
