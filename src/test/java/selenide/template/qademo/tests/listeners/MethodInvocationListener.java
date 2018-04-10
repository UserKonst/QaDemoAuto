package selenide.template.qademo.tests.listeners;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import selenide.template.qademo.utils.Attach;

public class MethodInvocationListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod iim, ITestResult itr) {
    }

    @Override
    public void afterInvocation(IInvokedMethod iim, ITestResult testResult) {
        if (!testResult.isSuccess() && WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshot();
            Attach.pageHtml();
            Attach.pageUrl();
        }
    }

}
