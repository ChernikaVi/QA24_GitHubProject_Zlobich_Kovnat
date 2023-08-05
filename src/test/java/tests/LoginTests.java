package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{
    private String ERROR_USERNAME = "raptorkost@gmal.com";
    private String ERROR_PASSWORD = "12345";

    @Test(groups = {"smoke"})
    @Description("Тестирование кнопки формы Log In")
    @Link(name = "Log In Page", url = "https://app.qase.io/login")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveLogInTest() {
        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL,PASSWORD);
        projectsPage.isPageOpened();
    }

    @Test(groups = {"smoke"}, description = "негативный тест Login формы ", dataProvider = "negativeLoginTestData")
    public void negativeLoginTest(String email, String password, String expectedErrorMessage) {
        loginPage.logIn(email, password);
        Assert.assertEquals(loginPage.getErrorText(), expectedErrorMessage);
    }

    @DataProvider
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {"", PASSWORD, "This field is required"},
                {EMAIL, "", "This field is required"}
        };
    }

    @Test(groups = {"smoke"}, description = "негативный тест Login формы ", dataProvider = "negativeLoginTestData2")
    public void negativeLoginTest2(String email, String password, String expectedErrorMessage) {
        loginPage.logIn(email, password);
        Assert.assertEquals(loginPage.getErrorText2(), expectedErrorMessage);
    }

    @DataProvider
    public Object[][] negativeLoginTestData2() {
        return new Object[][]{
                {ERROR_USERNAME, PASSWORD, "These credentials do not match our records."},
                {EMAIL, ERROR_PASSWORD, "Security notice: The password entered has been found in a public data leak. " +
                        "Please reset your password to ensure the safety of your account"}
        };
    }
}


