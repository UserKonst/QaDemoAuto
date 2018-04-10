package selenide.template.qademo.tests.google;

import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Feature;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import selenide.template.qademo.pages.SearchPage;
import selenide.template.qademo.pages.SearchResultPage;

@Feature("Some other feature 1")
public class Test1 {

    private SearchPage searchPage;
    private SearchResultPage resultPage;
    private final String TEXT = "Automated testing";

    @Test
    public void first_search_result_should_contains_automated_testing() {
        searchPage = open("https://google.com.ua", SearchPage.class);
        resultPage = searchPage.search(TEXT);
        String resultText = resultPage.getFirstResultHeader();
        assertEquals(resultText, TEXT);
    }
}
