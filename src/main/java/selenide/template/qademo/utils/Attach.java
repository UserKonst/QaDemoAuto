package selenide.template.qademo.utils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Attach {

    final static Logger LOG = Logger.getLogger(Attach.class);

//    @Attachment
//    public static String user(User user) {
//        if (user == null) {
//            return "User is null";
//        }
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(user);
//        return json;
//    }

    @Attachment(type = "image/png")
    public static byte[] screenshot() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        if (driver != null) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } else {
            LOG.fatal("Failed to take screenshot: driver is NULL!");
            return null;
        }
    }

    @Attachment(type = "text/html")
    public static String pageUrl() {
        String url = "";
        try {
            WebDriver driver = WebDriverRunner.getAndCheckWebDriver();
            url = driver.getCurrentUrl();
        } catch (Exception e) {
            LOG.error("Error while attaching screenshot", e);
        }
        return String.format("<a href=\"%s\">%s</a>", url, url);
    }

    @Attachment(type = "text/html")
    public static String pageHtml() {
        String pageSource = "";
        try {
            pageSource = WebDriverRunner.getWebDriver().getPageSource();
        } catch (Exception e) {
            LOG.fatal("Failed to get page html!", e);
            return "";
        }
        return pageSource;
    }
    
    @Attachment(type = "text/html", value = "Report Table")
    public static String html(String html) {
        if(html == null || html.isEmpty()){
            return "Html not found!";
        }
        return html;
    }

}
