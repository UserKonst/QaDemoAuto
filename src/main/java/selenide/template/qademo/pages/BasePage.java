package selenide.template.qademo.pages;

import static com.codeborne.selenide.Selenide.$;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import selenide.template.qademo.utils.WebDriverUtil;

public class BasePage {

    public BasePage() {
        WebDriverUtil.waitForDocumentReady();
    }

    @Step
    public String getCurrentUrl() {
        return WebDriverUtil.getCurrentUrl();
    }

    @Step
    public String getPageTitle() {
        return WebDriverUtil.getPageTitle();
    }

    @Step
    public boolean isElementDisplayed(By locator) {
        return $(locator).isDisplayed();
    }
}
