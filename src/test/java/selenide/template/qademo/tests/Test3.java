package selenide.template.qademo.tests;

import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Feature;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import selenide.template.qademo.pages.SearchPage;

@Feature("Feature 3")
public class Test3 {

    private SearchPage searchPage;

    @Test
    public void button_search_should_be_displayed() {
        searchPage = open("https://google.com.ua", SearchPage.class);
        assertTrue(searchPage.isBtnSearchDisplayed());
    }
}
