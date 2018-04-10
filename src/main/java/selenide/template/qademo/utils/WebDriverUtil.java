package selenide.template.qademo.utils;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtil {
    
    @Step
    public static boolean isElementDisplayed(By locator) {
        try {
            $(locator).isDisplayed();
        } catch (Error e) {
            return false;
        }
        return true;
    }
    
    @Step
    public static String getCurrentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    @Step
    public static String getPageTitle() {
        return WebDriverRunner.getWebDriver().getTitle();
    }
    
    @Step
    public static void switchToOhterTab() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String mainHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!mainHandle.equals(handle)) {
                driver.switchTo().window(handle);
                System.out.println("switched to: " + handle);
                WebDriverRunner.setWebDriver(driver);
                break;
            }
        }
    }
    
    @Step
    public static void closeOtherTabs() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String mainHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(mainHandle);
        WebDriverRunner.setWebDriver(driver);
    }
    
    @Step
    public static String getWindowHandle() {
        return WebDriverRunner.getWebDriver().getWindowHandle();
    }
    
    @Step
    public static void switchToWindow(String handle) {
        WebDriverRunner.getWebDriver().switchTo().window(handle);
    }
    
    @Step
    public static void switchToDefContent() {
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
        System.out.println("swithced to def content");
    }
    
    @Step
    public static boolean acceptAllert(boolean isAccept) {
        Alert alert = null;
        try {
            alert = WebDriverRunner.getWebDriver().switchTo().alert();
            if (isAccept) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        } catch (NoAlertPresentException e) {
        } finally {
            WebDriverRunner.getWebDriver().switchTo().defaultContent();
        }
        
        return alert != null;
    }
    
    @Step
    public static void closeCurrentTab() {
        WebDriverRunner.getWebDriver().close();
    }
    
    @Step
    public static void refreshPage() {
        WebDriverRunner.getWebDriver().navigate().refresh();
        acceptAllert(true);
        waitForDocumentReady();
    }
    
    @Step
    public static void hover(WebElement webElement) {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.moveToElement(webElement).perform();
    }
    
    @Step
    public static Object openPage(String url, Class pageClass) {
        WebDriverRunner.getWebDriver().get(url);
        return page(pageClass);
        
    }
    
    @Step
    public static boolean waitInvisibility(By by, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), timeoutSeconds);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    
    @Step
    public static WebElement waitVisibility(By by, int timeoutSeconds) throws Exception {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), timeoutSeconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    
    @Step
    public static SelenideElement waitFor(SelenideElement elem, Condition condition, int sec) {
        int timeout_ms = sec * 1000;
        SelenideElement elemement = null;
        try {
            elemement = elem.waitUntil(condition, timeout_ms);
        } catch (Error e) {
        }
        waitForDocumentReady();
        return elemement;
    }
    
    @Step
    private static boolean waitForJQueryProcessing() {
        boolean jQcondition = false;
        WebDriver driver = WebDriverRunner.getWebDriver();
        try {
            new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return jQuery.active == 0");
                }
            });
            jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            return jQcondition;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jQcondition;
    }
    
    @Step
    private static boolean waitForDocumentStateReady() {
        boolean jQcondition = false;
        WebDriver driver = WebDriverRunner.getWebDriver();
        try {
            new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return document.readyState == 'complete'");
                }
            });
            jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState == 'complete'");
            return jQcondition;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jQcondition;
    }
    
   
    
    @Step
    public static void waitForDocumentReady() {
       // waitForJQueryProcessing();
        waitForDocumentStateReady();
    }
    
}
