package selenide.template.qademo.tests;

import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Feature;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import selenide.template.qademo.pages.BasePage;
import selenide.template.qademo.pages.SearchPage;
import selenide.template.qademo.pages.SearchResultPage;

@Feature("Feature 4")
public class Test4 {

    private SearchPage searchPage;
    private SearchResultPage resultPage;
    private BasePage basePage;

    private String GOOGLE_PAGE_URL = "https://google.com.ua";

    @Test
    public void page_url_should_contains_selenium_camp() {
        searchPage = open(GOOGLE_PAGE_URL, SearchPage.class);
        resultPage = searchPage.search("selenium camp");
        basePage = resultPage.openPageByNumber(1);
        assertTrue(basePage.getPageTitle().contains("Selenium Camp"));
    }
}
