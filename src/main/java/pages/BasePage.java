package pages;

import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;

public abstract class BasePage<T> {

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver driver;

    private String hostName = System.getProperty("hostName.otus");

    private String getUrlPrefix() {
        UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        }

        return "";
    }

    public T open() {
        String prefix = getUrlPrefix();
        if (!prefix.startsWith("/")) {
            prefix = "/" + prefix;
        }

        driver.get(hostName + prefix);

        return (T) this;
    }

}
