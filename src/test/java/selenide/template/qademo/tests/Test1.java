package selenide.template.qademo.tests;

import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Feature;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import selenide.template.qademo.pages.SearchPage;

@Feature("Feature 1")
public class Test1 {

    private SearchPage searchPage;

    @Test
    public void page_url_should_contains_google_com() {
        searchPage = open("https://google.com.ua", SearchPage.class);
        assertTrue(searchPage.getCurrentUrl().contains("google.com"));
    }
}
