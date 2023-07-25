package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {
    @Test(groups = {"smoke"})
    @Description("Тестирование кнопки SignIn")
    @Link(name = "Sign In Page", type = "href", url = "https://github.com")
    @Severity(SeverityLevel.CRITICAL)
    public void SignInButtonTest() {
        signInPage.openPage()
                .isPageOpened()
                .clickSignInButton();
        Assert.assertTrue(signInPage.isLoginFieldDisplayed());
    }
}
