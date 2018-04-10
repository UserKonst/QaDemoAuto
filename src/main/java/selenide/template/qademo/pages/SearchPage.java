package selenide.template.qademo.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    @FindBy(name = "q")
    private SelenideElement fldSearch;

    @FindBy(name = "btnK")
    private SelenideElement btnSearch;

    @FindBy(id = "hplogo")
    private SelenideElement logo;

    public SearchResultPage search(String text) {
        fldSearch.sendKeys(text);
        btnSearch.click();
        return page(SearchResultPage.class);
    }

    @Step
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    @Step
    public boolean isBtnSearchDisplayed() {
        return $(By.xpath("(//input[@type='submit'])[1]")).isDisplayed();
    }

}
