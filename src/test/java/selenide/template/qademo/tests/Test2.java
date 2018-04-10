package selenide.template.qademo.tests;

import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Feature;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import selenide.template.qademo.pages.SearchPage;

@Feature("Feature 2")
public class Test2 {

    private SearchPage searchPage;

    @Test
    public void google_logo_should_be_displayed() {
        searchPage = open("https://google.com.ua", SearchPage.class);
        assertTrue(searchPage.isLogoDisplayed());
    }
}
