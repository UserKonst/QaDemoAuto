package selenide.template.qademo.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "(//div[@class='g'])[1]")
    private SelenideElement divResult;

    @Step
    public String getFirstResultHeader() {
        return divResult.$(By.xpath(".//h3")).text();
    }

    @Step
    public BasePage openPageByNumber(int pageNumber) {
        String pageXpath = String.format("(//h3[@class='r']/a)[%s]", pageNumber);
        $(By.xpath(pageXpath)).click();
        return page(BasePage.class);
    }

}
